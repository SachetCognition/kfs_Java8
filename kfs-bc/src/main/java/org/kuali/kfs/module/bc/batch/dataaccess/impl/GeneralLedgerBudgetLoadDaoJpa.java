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
package org.kuali.kfs.module.bc.batch.dataaccess.impl;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kfs.module.bc.batch.dataaccess.GeneralLedgerBudgetLoadDao;

public class GeneralLedgerBudgetLoadDaoJpa extends BudgetConstructionBatchHelperDaoJpa implements GeneralLedgerBudgetLoadDao {

    @Override
    public void loadGeneralLedgerFromBudget(Integer fiscalYear, Date currentSqlDate, String financialSystemOriginationCode) {
        Query query = entityManager.createNativeQuery(
            "INSERT INTO GL_BALANCE_T (UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, " +
            "FIN_OBJECT_CD, FIN_SUB_OBJ_CD, FIN_BALANCE_TYP_CD, FIN_OBJ_TYP_CD, " +
            "ACLN_ANNL_BAL_AMT, FIN_BEG_BAL_LN_AMT, CONTR_GR_BB_AC_AMT, " +
            "MO1_ACCT_LN_AMT, MO2_ACCT_LN_AMT, MO3_ACCT_LN_AMT, MO4_ACCT_LN_AMT, " +
            "MO5_ACCT_LN_AMT, MO6_ACCT_LN_AMT, MO7_ACCT_LN_AMT, MO8_ACCT_LN_AMT, " +
            "MO9_ACCT_LN_AMT, MO10_ACCT_LN_AMT, MO11_ACCT_LN_AMT, MO12_ACCT_LN_AMT, " +
            "MO13_ACCT_LN_AMT, TIMESTAMP) " +
            "SELECT g.UNIV_FISCAL_YR, g.FIN_COA_CD, g.ACCOUNT_NBR, g.SUB_ACCT_NBR, " +
            "g.FIN_OBJECT_CD, g.FIN_SUB_OBJ_CD, g.FIN_BALANCE_TYP_CD, g.FIN_OBJ_TYP_CD, " +
            "g.ACLN_ANNL_BAL_AMT, g.FIN_BEG_BAL_LN_AMT, 0, " +
            "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, :currentDate " +
            "FROM LD_PND_BCNSTR_GL_T g " +
            "WHERE g.UNIV_FISCAL_YR = :fiscalYear");
        query.setParameter("fiscalYear", fiscalYear);
        query.setParameter("currentDate", currentSqlDate);
        query.executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
