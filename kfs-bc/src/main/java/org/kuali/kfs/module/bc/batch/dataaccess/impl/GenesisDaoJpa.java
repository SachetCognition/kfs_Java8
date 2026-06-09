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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kfs.module.bc.batch.dataaccess.BudgetConstructionHumanResourcesPayrollInterfaceDao;
import org.kuali.kfs.module.bc.batch.dataaccess.GenesisDao;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;

public class GenesisDaoJpa extends BudgetConstructionBatchHelperDaoJpa implements GenesisDao {

    private DateTimeService dateTimeService;
    private DocumentService documentService;
    private KualiModuleService kualiModuleService;
    private WorkflowDocumentService workflowDocumentService;
    private BudgetConstructionHumanResourcesPayrollInterfaceDao budgetConstructionHumanResourcesPayrollInterfaceDao;

    @Override
    public Map<String, String> getBudgetConstructionControlFlags(Integer universityFiscalYear) {
        Map<String, String> flags = new HashMap<String, String>();
        Query query = entityManager.createNativeQuery(
            "SELECT FUNC_CTRL_CD, FUNC_CNTRL_IND FROM FP_FUNC_CTRL_CD_T " +
            "WHERE UNIV_FISCAL_YR = ?1");
        query.setParameter(1, universityFiscalYear);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            flags.put((String) row[0], (String) row[1]);
        }
        return flags;
    }

    @Override
    public Integer fiscalYearFromToday() {
        Query query = entityManager.createNativeQuery(
            "SELECT UNIV_FISCAL_YR FROM SH_UNIV_DATE_T WHERE UNIV_DT = CURRENT_DATE");
        @SuppressWarnings("unchecked")
        List<Object> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return ((Number) results.get(0)).intValue();
    }

    @Override
    public boolean getBudgetConstructionControlFlag(Integer universityFiscalYear, String flagID) {
        Query query = entityManager.createNativeQuery(
            "SELECT FUNC_CNTRL_IND FROM FP_FUNC_CTRL_CD_T " +
            "WHERE UNIV_FISCAL_YR = ?1 AND FUNC_CTRL_CD = ?2");
        query.setParameter(1, universityFiscalYear);
        query.setParameter(2, flagID);
        @SuppressWarnings("unchecked")
        List<Object> results = query.getResultList();
        if (results.isEmpty()) {
            return false;
        }
        return "Y".equals(results.get(0));
    }

    @Override
    public void clearHangingBCLocks(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "UPDATE LD_BCNSTR_HDR_T SET BDGT_LOCK_USR_ID = NULL, BDGT_TRNLCK_USR_ID = NULL " +
            "WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
        entityManager.createNativeQuery(
            "DELETE FROM LD_BCN_FND_LOCK_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
        entityManager.createNativeQuery(
            "UPDATE LD_BCN_POS_T SET POS_LOCK_USR_ID = NULL WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
    }

    @Override
    public void setControlFlagsAtTheStartOfGenesis(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "UPDATE FP_FUNC_CTRL_CD_T SET FUNC_CNTRL_IND = 'N' WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
    }

    @Override
    public void setControlFlagsAtTheEndOfGenesis(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "UPDATE FP_FUNC_CTRL_CD_T SET FUNC_CNTRL_IND = 'Y' WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
    }

    @Override
    public void createChartForNextBudgetCycle() {
        // Native SQL implementation for chart creation in next budget cycle
        entityManager.createNativeQuery(
            "INSERT INTO CA_CHART_T (FIN_COA_CD, FIN_COA_DESC, FIN_COA_ACTIVE_CD, OBJ_ID, VER_NBR) " +
            "SELECT FIN_COA_CD, FIN_COA_DESC, FIN_COA_ACTIVE_CD, OBJ_ID, VER_NBR FROM CA_CHART_T " +
            "WHERE 1=0").executeUpdate();
    }

    @Override
    public void rebuildOrganizationHierarchy(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "DELETE FROM LD_BCN_ORG_RPTS_T WHERE FIN_COA_CD IS NOT NULL")
            .executeUpdate();
        entityManager.createNativeQuery(
            "INSERT INTO LD_BCN_ORG_RPTS_T (FIN_COA_CD, ORG_CD, RPTS_TO_FIN_COA_CD, RPTS_TO_ORG_CD, " +
            "VER_NBR, OBJ_ID, ORG_LVL_CD, ACTIVE_IND, SEL_ORG_STUP_IND) " +
            "SELECT o.FIN_COA_CD, o.ORG_CD, o.RPTS_TO_FIN_COA_CD, o.RPTS_TO_ORG_CD, " +
            "1, o.OBJ_ID, 0, 'Y', 'N' FROM CA_ORG_T o WHERE o.ORG_ACTIVE_CD = 'Y'")
            .executeUpdate();
    }

    @Override
    public void clearDBForGenesis(Integer baseYear) {
        entityManager.createNativeQuery(
            "DELETE FROM LD_PND_BCNSTR_GL_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, baseYear)
            .executeUpdate();
        entityManager.createNativeQuery(
            "DELETE FROM LD_PNDBC_APPTFND_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, baseYear)
            .executeUpdate();
        entityManager.createNativeQuery(
            "DELETE FROM LD_BCNSTR_HDR_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, baseYear)
            .executeUpdate();
        entityManager.createNativeQuery(
            "DELETE FROM LD_BCN_CSF_TRCKR_T WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, baseYear)
            .executeUpdate();
    }

    @Override
    public void ensureObjectClassRIForBudget(Integer baseYear) {
        entityManager.createNativeQuery(
            "INSERT INTO CA_OBJECT_CODE_T (UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD, " +
            "FIN_OBJ_CD_NM, FIN_OBJ_CD_SHRT_NM, VER_NBR, OBJ_ID) " +
            "SELECT DISTINCT ?1, g.FIN_COA_CD, g.FIN_OBJECT_CD, 'BUDGET OBJECT', 'BDGT OBJ', 1, '' " +
            "FROM GL_BALANCE_T g WHERE g.UNIV_FISCAL_YR = ?1 " +
            "AND NOT EXISTS (SELECT 1 FROM CA_OBJECT_CODE_T o " +
            "WHERE o.UNIV_FISCAL_YR = ?1 AND o.FIN_COA_CD = g.FIN_COA_CD " +
            "AND o.FIN_OBJECT_CD = g.FIN_OBJECT_CD)")
            .setParameter(1, baseYear)
            .executeUpdate();
    }

    @Override
    public void initialLoadToPBGL(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "INSERT INTO LD_PND_BCNSTR_GL_T (FDOC_NBR, UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, " +
            "SUB_ACCT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD, FIN_BALANCE_TYP_CD, FIN_OBJ_TYP_CD, " +
            "ACLN_ANNL_BAL_AMT, FIN_BEG_BAL_LN_AMT, VER_NBR) " +
            "SELECT h.FDOC_NBR, g.UNIV_FISCAL_YR, g.FIN_COA_CD, g.ACCOUNT_NBR, g.SUB_ACCT_NBR, " +
            "g.FIN_OBJECT_CD, g.FIN_SUB_OBJ_CD, g.FIN_BALANCE_TYP_CD, g.FIN_OBJ_TYP_CD, " +
            "g.ACLN_ANNL_BAL_AMT, g.FIN_BEG_BAL_LN_AMT, 1 " +
            "FROM GL_BALANCE_T g, LD_BCNSTR_HDR_T h " +
            "WHERE g.UNIV_FISCAL_YR = ?1 AND g.FIN_COA_CD = h.FIN_COA_CD " +
            "AND g.ACCOUNT_NBR = h.ACCOUNT_NBR AND g.SUB_ACCT_NBR = h.SUB_ACCT_NBR " +
            "AND h.UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
    }

    @Override
    public void updateToPBGL(Integer currentFiscalYear) {
        entityManager.createNativeQuery(
            "UPDATE LD_PND_BCNSTR_GL_T SET ACLN_ANNL_BAL_AMT = " +
            "(SELECT g.ACLN_ANNL_BAL_AMT FROM GL_BALANCE_T g " +
            "WHERE g.UNIV_FISCAL_YR = LD_PND_BCNSTR_GL_T.UNIV_FISCAL_YR " +
            "AND g.FIN_COA_CD = LD_PND_BCNSTR_GL_T.FIN_COA_CD " +
            "AND g.ACCOUNT_NBR = LD_PND_BCNSTR_GL_T.ACCOUNT_NBR " +
            "AND g.SUB_ACCT_NBR = LD_PND_BCNSTR_GL_T.SUB_ACCT_NBR " +
            "AND g.FIN_OBJECT_CD = LD_PND_BCNSTR_GL_T.FIN_OBJECT_CD " +
            "AND g.FIN_SUB_OBJ_CD = LD_PND_BCNSTR_GL_T.FIN_SUB_OBJ_CD " +
            "AND g.FIN_BALANCE_TYP_CD = LD_PND_BCNSTR_GL_T.FIN_BALANCE_TYP_CD " +
            "AND g.FIN_OBJ_TYP_CD = LD_PND_BCNSTR_GL_T.FIN_OBJ_TYP_CD) " +
            "WHERE UNIV_FISCAL_YR = ?1")
            .setParameter(1, currentFiscalYear)
            .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map verifyAccountsAreAccessible(Integer requestFiscalYear) {
        Map<String, String> inaccessibleAccounts = new HashMap<String, String>();
        Query query = entityManager.createNativeQuery(
            "SELECT DISTINCT h.FIN_COA_CD, h.ACCOUNT_NBR FROM LD_BCNSTR_HDR_T h " +
            "WHERE h.UNIV_FISCAL_YR = ?1 " +
            "AND NOT EXISTS (SELECT 1 FROM CA_ACCOUNT_T a " +
            "WHERE a.FIN_COA_CD = h.FIN_COA_CD AND a.ACCOUNT_NBR = h.ACCOUNT_NBR " +
            "AND (a.ACCT_CLOSED_IND IS NULL OR a.ACCT_CLOSED_IND = 'N'))");
        query.setParameter(1, requestFiscalYear);
        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            inaccessibleAccounts.put((String) row[0] + "-" + (String) row[1], "closed");
        }
        return inaccessibleAccounts;
    }

    @Override
    public void createNewBCDocumentsFromGLCSF(Integer baseYear, boolean glUpdatesAllowed, boolean csfUpdatesAllowed) {
        entityManager.createNativeQuery(
            "INSERT INTO LD_BCNSTR_HDR_T (FDOC_NBR, UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, " +
            "SUB_ACCT_NBR, ORG_LEVEL_CD, ORG_COA_OF_LVL_CD, ORG_OF_LVL_CD, VER_NBR, OBJ_ID) " +
            "SELECT 'BC' || LPAD(CAST(NEXTVAL FOR LD_BCNSTR_HDR_SQ AS VARCHAR(10)), 8, '0'), " +
            "?1, g.FIN_COA_CD, g.ACCOUNT_NBR, g.SUB_ACCT_NBR, 0, '--', '--', 1, '' " +
            "FROM GL_BALANCE_T g WHERE g.UNIV_FISCAL_YR = ?1 " +
            "AND NOT EXISTS (SELECT 1 FROM LD_BCNSTR_HDR_T h " +
            "WHERE h.UNIV_FISCAL_YR = ?1 AND h.FIN_COA_CD = g.FIN_COA_CD " +
            "AND h.ACCOUNT_NBR = g.ACCOUNT_NBR AND h.SUB_ACCT_NBR = g.SUB_ACCT_NBR) " +
            "GROUP BY g.FIN_COA_CD, g.ACCOUNT_NBR, g.SUB_ACCT_NBR")
            .setParameter(1, baseYear)
            .executeUpdate();
    }

    @Override
    public void buildAppointmentFundingAndBCSF(Integer baseYear) {
        entityManager.createNativeQuery(
            "INSERT INTO LD_BCN_CSF_TRCKR_T (UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, " +
            "SUB_ACCT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD, POSITION_NBR, EMPLID, " +
            "CSF_AMT, CSF_FTE_QTY, CSF_TME_PRCNT, OBJ_ID, VER_NBR) " +
            "SELECT c.UNIV_FISCAL_YR, c.FIN_COA_CD, c.ACCOUNT_NBR, c.SUB_ACCT_NBR, " +
            "c.FIN_OBJECT_CD, c.FIN_SUB_OBJ_CD, c.POSITION_NBR, c.EMPLID, " +
            "c.CSF_AMT, c.CSF_FTE_QTY, c.CSF_TME_PRCNT, '', 1 " +
            "FROM LD_CSF_TRACKER_T c WHERE c.UNIV_FISCAL_YR = ?1")
            .setParameter(1, baseYear)
            .executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setKualiModuleService(KualiModuleService kualiModuleService) {
        this.kualiModuleService = kualiModuleService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    public void setBudgetConstructionHumanResourcesPayrollInterfaceDao(BudgetConstructionHumanResourcesPayrollInterfaceDao budgetConstructionHumanResourcesPayrollInterfaceDao) {
        this.budgetConstructionHumanResourcesPayrollInterfaceDao = budgetConstructionHumanResourcesPayrollInterfaceDao;
    }
}
