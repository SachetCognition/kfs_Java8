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
        Query deleteQuery = entityManager.createQuery(
            "DELETE FROM BudgetConstructionPayRateHolding h WHERE h.principalId = :principalId");
        deleteQuery.setParameter("principalId", principalId);
        deleteQuery.executeUpdate();

        Query insertQuery = entityManager.createNativeQuery(
            "INSERT INTO LD_BCN_PAYRT_HLDG_T (PRNCPL_ID, EMPLID, POSITION_NBR, " +
            "SETID_SALARY, SAL_ADMIN_PLAN, GRADE, APPT_RQST_PAY_RT, APPT_RQCSF_FTE_QTY, " +
            "APPT_RQST_FTE_QTY, SETID_DEPT_ID) " +
            "SELECT DISTINCT :principalId, f.EMPLID, f.POSITION_NBR, " +
            "p.SETID_SALARY, p.SAL_ADMIN_PLAN, p.GRADE, f.APPT_RQST_PAY_RT, " +
            "f.APPT_RQCSF_FTE_QTY, f.APPT_RQST_FTE_QTY, p.SETID_DEPT_ID " +
            "FROM LD_PNDBC_APPTFND_T f, LD_BCN_POS_T p " +
            "WHERE f.UNIV_FISCAL_YR = :budgetYear AND f.POSITION_NBR = p.POSITION_NBR " +
            "AND f.UNIV_FISCAL_YR = p.UNIV_FISCAL_YR AND p.POS_UNION_CD = :unionCode " +
            "AND f.APPT_FND_DLT_CD = 'N'");
        insertQuery.setParameter("principalId", principalId);
        insertQuery.setParameter("budgetYear", budgetYear);
        insertQuery.setParameter("unionCode", positionUnionCode);
        return insertQuery.executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
