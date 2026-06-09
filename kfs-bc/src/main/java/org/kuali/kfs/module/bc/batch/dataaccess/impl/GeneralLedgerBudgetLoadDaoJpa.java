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

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.kuali.kfs.module.bc.BCConstants;
import org.kuali.kfs.module.bc.batch.dataaccess.GeneralLedgerBudgetLoadDao;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionMonthly;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionGeneralLedger;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntry;
import org.kuali.rice.core.api.util.type.KualiInteger;

public class GeneralLedgerBudgetLoadDaoJpa extends BudgetConstructionBatchHelperDaoJpa implements GeneralLedgerBudgetLoadDao {

    private static Logger LOG = Logger.getLogger(GeneralLedgerBudgetLoadDaoJpa.class);

    @Override
    public void loadGeneralLedgerFromBudget(Integer fiscalYear, Date currentSqlDate, String financialSystemOriginationCode) {
        HashMap<String, Integer> entrySequenceNumbers = buildEntrySequenceNumbers(fiscalYear);
        HashSet<String> accountsNotToBeLoaded = getAccountsNotToBeLoaded();

        openAllAccountingPeriods(fiscalYear);

        loadPendingBudgetConstructionGeneralLedger(fiscalYear, currentSqlDate, financialSystemOriginationCode, entrySequenceNumbers, accountsNotToBeLoaded);

        loadBudgetConstructionMonthlyBudget(fiscalYear, currentSqlDate, financialSystemOriginationCode, entrySequenceNumbers, accountsNotToBeLoaded);
    }

    protected HashMap<String, Integer> buildEntrySequenceNumbers(Integer fiscalYear) {
        @SuppressWarnings("unchecked")
        List<Object> docNumbers = entityManager.createNativeQuery(
            "SELECT FDOC_NBR FROM LD_BCNSTR_HDR_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, fiscalYear)
            .getResultList();
        HashMap<String, Integer> seqMap = new HashMap<String, Integer>(docNumbers.size() + 1);
        for (Object docNum : docNumbers) {
            seqMap.put((String) docNum, Integer.valueOf(0));
        }
        return seqMap;
    }

    protected void openAllAccountingPeriods(Integer fiscalYear) {
        int updated = entityManager.createNativeQuery(
            "UPDATE SH_ACCT_PERIOD_T SET ACCT_PRD_ACTV_STAT_CD = 'Y' " +
            "WHERE UNIV_FISCAL_YR = ?1 AND ACCT_PRD_ACTV_STAT_CD <> 'Y'")
            .setParameter(1, fiscalYear)
            .executeUpdate();
        LOG.warn(String.format("\n\naccounting periods for %d changed to open status: %d", fiscalYear, updated));
    }

    protected HashSet<String> getAccountsNotToBeLoaded() {
        HashSet<String> bannedSubFunds = getSubFundsNotToBeLoaded();
        HashSet<String> bannedAccounts = new HashSet<String>();
        @SuppressWarnings("unchecked")
        List<Object[]> accounts = entityManager.createNativeQuery(
            "SELECT ACCOUNT_NBR, FIN_COA_CD, SUB_FUND_GRP_CD FROM CA_ACCOUNT_T")
            .getResultList();
        for (Object[] row : accounts) {
            String subFundGroupCode = (String) row[2];
            if (bannedSubFunds.contains(subFundGroupCode)) {
                bannedAccounts.add((String) row[0] + (String) row[1]);
            }
        }
        return bannedAccounts;
    }

    protected HashSet<String> getSubFundsNotToBeLoaded() {
        HashSet<String> bannedSubFunds = new HashSet<String>();
        if (BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.size() != 0) {
            StringBuilder inClause = new StringBuilder();
            for (int i = 0; i < BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.size(); i++) {
                if (i > 0) inClause.append(",");
                inClause.append("?").append(i + 1);
            }
            Query query = entityManager.createNativeQuery(
                "SELECT SUB_FUND_GRP_CD FROM CA_SUB_FUND_GRP_T WHERE FUND_GRP_CD IN (" + inClause + ")");
            for (int i = 0; i < BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.size(); i++) {
                query.setParameter(i + 1, BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.get(i));
            }
            @SuppressWarnings("unchecked")
            List<Object> results = query.getResultList();
            for (Object subFund : results) {
                bannedSubFunds.add((String) subFund);
            }
        }
        for (String subFund : BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS) {
            bannedSubFunds.add(subFund);
        }
        return bannedSubFunds;
    }

    @SuppressWarnings("unchecked")
    protected void loadPendingBudgetConstructionGeneralLedger(Integer fiscalYear, Date transactionDate,
            String financialSystemOriginationCode, HashMap<String, Integer> entrySequenceNumbers,
            HashSet<String> accountsNotToBeLoaded) {
        List<Object[]> rows = entityManager.createNativeQuery(
            "SELECT FDOC_NBR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, " +
            "FIN_SUB_OBJ_CD, FIN_OBJ_TYP_CD, ACLN_ANNL_BAL_AMT " +
            "FROM LD_PND_BCNSTR_GL_T WHERE UNIV_FISCAL_YR = ?1 AND ACLN_ANNL_BAL_AMT <> 0")
            .setParameter(1, fiscalYear)
            .getResultList();

        for (Object[] row : rows) {
            String accountNumber = (String) row[2];
            String chartCode = (String) row[1];
            if (accountsNotToBeLoaded.contains(accountNumber + chartCode)) {
                continue;
            }
            String documentNumber = (String) row[0];
            String subAccountNumber = (String) row[3];
            String objectCode = (String) row[4];
            String subObjectCode = (String) row[5];
            String objectTypeCode = (String) row[6];
            Number amount = (Number) row[7];

            Integer seqNum = getNextSequenceNumber(entrySequenceNumbers, documentNumber);
            entityManager.createNativeQuery(
                "INSERT INTO GL_PENDING_ENTRY_T (FS_ORIGIN_CD, FDOC_NBR, TRN_ENTR_SEQ_NBR, " +
                "UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, " +
                "FIN_SUB_OBJ_CD, FIN_BALANCE_TYP_CD, FIN_OBJ_TYP_CD, UNIV_FISCAL_PRD_CD, " +
                "FDOC_TYP_CD, TRN_LDGR_ENTR_DESC, TRN_LDGR_ENTR_AMT, TRANSACTION_DT, " +
                "FDOC_APPROVED_CD, TRN_ENTR_OFST_CD) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)")
                .setParameter(1, financialSystemOriginationCode)
                .setParameter(2, documentNumber)
                .setParameter(3, seqNum)
                .setParameter(4, fiscalYear)
                .setParameter(5, chartCode)
                .setParameter(6, accountNumber)
                .setParameter(7, subAccountNumber)
                .setParameter(8, objectCode)
                .setParameter(9, subObjectCode)
                .setParameter(10, KFSConstants.BALANCE_TYPE_BASE_BUDGET)
                .setParameter(11, objectTypeCode)
                .setParameter(12, KFSConstants.PERIOD_CODE_BEGINNING_BALANCE)
                .setParameter(13, BCConstants.BUDGET_CONSTRUCTION_BEGINNING_BALANCE_DOCUMENT_TYPE)
                .setParameter(14, BCConstants.BC_TRN_LDGR_ENTR_DESC)
                .setParameter(15, amount)
                .setParameter(16, transactionDate)
                .setParameter(17, KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.APPROVED)
                .setParameter(18, false)
                .executeUpdate();

            Integer seqNum2 = getNextSequenceNumber(entrySequenceNumbers, documentNumber);
            entityManager.createNativeQuery(
                "INSERT INTO GL_PENDING_ENTRY_T (FS_ORIGIN_CD, FDOC_NBR, TRN_ENTR_SEQ_NBR, " +
                "UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, " +
                "FIN_SUB_OBJ_CD, FIN_BALANCE_TYP_CD, FIN_OBJ_TYP_CD, UNIV_FISCAL_PRD_CD, " +
                "FDOC_TYP_CD, TRN_LDGR_ENTR_DESC, TRN_LDGR_ENTR_AMT, TRANSACTION_DT, " +
                "FDOC_APPROVED_CD, TRN_ENTR_OFST_CD) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)")
                .setParameter(1, financialSystemOriginationCode)
                .setParameter(2, documentNumber)
                .setParameter(3, seqNum2)
                .setParameter(4, fiscalYear)
                .setParameter(5, chartCode)
                .setParameter(6, accountNumber)
                .setParameter(7, subAccountNumber)
                .setParameter(8, objectCode)
                .setParameter(9, subObjectCode)
                .setParameter(10, KFSConstants.BALANCE_TYPE_CURRENT_BUDGET)
                .setParameter(11, objectTypeCode)
                .setParameter(12, KFSConstants.PERIOD_CODE_BEGINNING_BALANCE)
                .setParameter(13, BCConstants.BUDGET_CONSTRUCTION_BEGINNING_BALANCE_DOCUMENT_TYPE)
                .setParameter(14, BCConstants.BC_TRN_LDGR_ENTR_DESC)
                .setParameter(15, amount)
                .setParameter(16, transactionDate)
                .setParameter(17, KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.APPROVED)
                .setParameter(18, false)
                .executeUpdate();
        }
    }

    @SuppressWarnings("unchecked")
    protected void loadBudgetConstructionMonthlyBudget(Integer fiscalYear, Date transactionDate,
            String financialSystemOriginationCode, HashMap<String, Integer> entrySequenceNumbers,
            HashSet<String> accountsNotToBeLoaded) {
        List<Object[]> rows = entityManager.createNativeQuery(
            "SELECT FDOC_NBR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, " +
            "FIN_SUB_OBJ_CD, FIN_OBJ_TYP_CD, " +
            "FDOC_LN_MO1_AMT, FDOC_LN_MO2_AMT, FDOC_LN_MO3_AMT, FDOC_LN_MO4_AMT, " +
            "FDOC_LN_MO5_AMT, FDOC_LN_MO6_AMT, FDOC_LN_MO7_AMT, FDOC_LN_MO8_AMT, " +
            "FDOC_LN_MO9_AMT, FDOC_LN_MO10_AMT, FDOC_LN_MO11_AMT, FDOC_LN_MO12_AMT " +
            "FROM LD_BCNSTR_MONTH_T WHERE UNIV_FISCAL_YR = ?1 AND (" +
            "FDOC_LN_MO1_AMT <> 0 OR FDOC_LN_MO2_AMT <> 0 OR FDOC_LN_MO3_AMT <> 0 OR " +
            "FDOC_LN_MO4_AMT <> 0 OR FDOC_LN_MO5_AMT <> 0 OR FDOC_LN_MO6_AMT <> 0 OR " +
            "FDOC_LN_MO7_AMT <> 0 OR FDOC_LN_MO8_AMT <> 0 OR FDOC_LN_MO9_AMT <> 0 OR " +
            "FDOC_LN_MO10_AMT <> 0 OR FDOC_LN_MO11_AMT <> 0 OR FDOC_LN_MO12_AMT <> 0)")
            .setParameter(1, fiscalYear)
            .getResultList();

        String[] periodCodes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        for (Object[] row : rows) {
            String accountNumber = (String) row[2];
            String chartCode = (String) row[1];
            if (accountsNotToBeLoaded.contains(accountNumber + chartCode)) {
                continue;
            }
            String documentNumber = (String) row[0];
            String subAccountNumber = (String) row[3];
            String objectCode = (String) row[4];
            String subObjectCode = (String) row[5];
            String objectTypeCode = (String) row[6];

            for (int i = 0; i < 12; i++) {
                Number monthlyAmount = (Number) row[7 + i];
                if (monthlyAmount != null && monthlyAmount.longValue() != 0) {
                    Integer seqNum = getNextSequenceNumber(entrySequenceNumbers, documentNumber);
                    entityManager.createNativeQuery(
                        "INSERT INTO GL_PENDING_ENTRY_T (FS_ORIGIN_CD, FDOC_NBR, TRN_ENTR_SEQ_NBR, " +
                        "UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, " +
                        "FIN_SUB_OBJ_CD, FIN_BALANCE_TYP_CD, FIN_OBJ_TYP_CD, UNIV_FISCAL_PRD_CD, " +
                        "FDOC_TYP_CD, TRN_LDGR_ENTR_DESC, TRN_LDGR_ENTR_AMT, TRANSACTION_DT, " +
                        "FDOC_APPROVED_CD, TRN_ENTR_OFST_CD) " +
                        "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)")
                        .setParameter(1, financialSystemOriginationCode)
                        .setParameter(2, documentNumber)
                        .setParameter(3, seqNum)
                        .setParameter(4, fiscalYear)
                        .setParameter(5, chartCode)
                        .setParameter(6, accountNumber)
                        .setParameter(7, subAccountNumber)
                        .setParameter(8, objectCode)
                        .setParameter(9, subObjectCode)
                        .setParameter(10, KFSConstants.BALANCE_TYPE_MONTHLY_BUDGET)
                        .setParameter(11, objectTypeCode)
                        .setParameter(12, periodCodes[i])
                        .setParameter(13, BCConstants.BUDGET_CONSTRUCTION_BEGINNING_BALANCE_DOCUMENT_TYPE)
                        .setParameter(14, BCConstants.BC_TRN_LDGR_ENTR_DESC)
                        .setParameter(15, monthlyAmount)
                        .setParameter(16, transactionDate)
                        .setParameter(17, KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.APPROVED)
                        .setParameter(18, false)
                        .executeUpdate();
                }
            }
        }
    }

    protected Integer getNextSequenceNumber(HashMap<String, Integer> entrySequenceNumbers, String documentNumber) {
        Integer current = entrySequenceNumbers.get(documentNumber);
        if (current == null) {
            current = Integer.valueOf(0);
        }
        entrySequenceNumbers.put(documentNumber, current + 1);
        return current;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
