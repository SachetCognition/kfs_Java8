/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.bc.document.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.bc.BCConstants;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.document.dataaccess.PayrateExportDao;

public class PayrateExportDaoJpa implements PayrateExportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isValidPositionUnionCode(String positionUnionCode) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(p) FROM BudgetConstructionPosition p WHERE p.positionUnionCode = :code",
            Long.class);
        query.setParameter("code", positionUnionCode);
        return query.getSingleResult() > 0;
    }

    @Override
    public Integer buildPayRateHoldingRows(Integer budgetYear, String positionUnionCode, String principalId) {
        entityManager.createNativeQuery(
            "DELETE FROM LD_BCN_PAYRT_HLDG_T WHERE PERSON_UNVL_ID = ?1")
            .setParameter(1, principalId)
            .executeUpdate();

        Query insertQuery = entityManager.createNativeQuery(
            "INSERT INTO LD_BCN_PAYRT_HLDG_T (PERSON_UNVL_ID, EMPLID, POSITION_NBR, " +
            "PERSON_NM, SETID_SALARY, SAL_ADMIN_PLAN, GRADE, UNION_CD, APPT_RQST_PAY_RT, VER_NBR) " +
            "SELECT DISTINCT ?1, f.EMPLID, f.POSITION_NBR, " +
            "i.PERSON_NM, p.SETID_SALARY, p.SAL_ADMIN_PLAN, p.GRADE, p.POS_UNION_CD, 0, 1 " +
            "FROM LD_PNDBC_APPTFND_T f " +
            "JOIN LD_BCN_POS_T p ON f.POSITION_NBR = p.POSITION_NBR AND f.UNIV_FISCAL_YR = p.UNIV_FISCAL_YR " +
            "JOIN LD_BCN_INTINCBNT_T i ON f.EMPLID = i.EMPLID " +
            "WHERE f.UNIV_FISCAL_YR = ?2 " +
            "AND f.EMPLID <> ?3 " +
            "AND f.APPT_FND_DLT_CD = 'Y' " +
            "AND p.POS_UNION_CD = ?4 " +
            "AND p.CONFIDENTIAL_POSN = 'N'");
        insertQuery.setParameter(1, principalId);
        insertQuery.setParameter(2, budgetYear);
        insertQuery.setParameter(3, BCConstants.VACANT_EMPLID);
        insertQuery.setParameter(4, positionUnionCode);
        return insertQuery.executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
