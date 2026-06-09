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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.bc.BCConstants;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAccountOrganizationHierarchy;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAccountReports;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionFundingLock;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionHeader;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionOrganizationReports;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPullup;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionAppointmentFunding;
import org.kuali.kfs.module.bc.businessobject.PendingBudgetConstructionGeneralLedger;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionDao;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.api.util.type.KualiInteger;

public class BudgetConstructionDaoJpa implements BudgetConstructionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BudgetConstructionHeader getByCandidateKey(String chartOfAccountsCode, String accountNumber, String subAccountNumber, Integer fiscalYear) {
        TypedQuery<BudgetConstructionHeader> query = entityManager.createQuery(
            "SELECT h FROM BudgetConstructionHeader h WHERE h.chartOfAccountsCode = :chart " +
            "AND h.accountNumber = :account AND h.subAccountNumber = :subAccount " +
            "AND h.universityFiscalYear = :fiscalYear", BudgetConstructionHeader.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("account", accountNumber);
        query.setParameter("subAccount", subAccountNumber);
        query.setParameter("fiscalYear", fiscalYear);
        List<BudgetConstructionHeader> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public void deleteBudgetConstructionFundingLock(BudgetConstructionFundingLock budgetConstructionFundingLock) {
        BudgetConstructionFundingLock managed = entityManager.contains(budgetConstructionFundingLock)
            ? budgetConstructionFundingLock
            : entityManager.merge(budgetConstructionFundingLock);
        entityManager.remove(managed);
    }

    @Override
    public Collection<BudgetConstructionFundingLock> getFlocksForAccount(String chartOfAccountsCode, String accountNumber, String subAccountNumber, Integer fiscalYear) {
        TypedQuery<BudgetConstructionFundingLock> query = entityManager.createQuery(
            "SELECT f FROM BudgetConstructionFundingLock f WHERE f.chartOfAccountsCode = :chart " +
            "AND f.accountNumber = :account AND f.subAccountNumber = :subAccount " +
            "AND f.universityFiscalYear = :fiscalYear", BudgetConstructionFundingLock.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("account", accountNumber);
        query.setParameter("subAccount", subAccountNumber);
        query.setParameter("fiscalYear", fiscalYear);
        List<BudgetConstructionFundingLock> fundingLocks = query.getResultList();
        for (BudgetConstructionFundingLock lock : fundingLocks) {
            lock.setPositionNumber(getPositionAssociatedWithFundingLock(lock));
        }
        return fundingLocks;
    }

    @Override
    public String getPositionAssociatedWithFundingLock(BudgetConstructionFundingLock budgetConstructionFundingLock) {
        TypedQuery<String> query = entityManager.createQuery(
            "SELECT p.positionNumber FROM BudgetConstructionPosition p " +
            "WHERE p.positionLockUserIdentifier = :lockUser " +
            "AND EXISTS (SELECT 1 FROM PendingBudgetConstructionAppointmentFunding f " +
            "WHERE f.chartOfAccountsCode = :chart AND f.accountNumber = :account " +
            "AND f.subAccountNumber = :subAccount AND f.universityFiscalYear = :fiscalYear " +
            "AND f.positionNumber = p.positionNumber)", String.class);
        query.setParameter("lockUser", budgetConstructionFundingLock.getAppointmentFundingLockUserId());
        query.setParameter("chart", budgetConstructionFundingLock.getChartOfAccountsCode());
        query.setParameter("account", budgetConstructionFundingLock.getAccountNumber());
        query.setParameter("subAccount", budgetConstructionFundingLock.getSubAccountNumber());
        query.setParameter("fiscalYear", budgetConstructionFundingLock.getUniversityFiscalYear());
        List<String> results = query.getResultList();
        return results.isEmpty() ? BCConstants.POSITION_NUMBER_NOT_FOUND : results.get(0);
    }

    @Override
    public void deleteBudgetConstructionPullupByUserId(String principalName) {
        entityManager.createQuery(
            "DELETE FROM BudgetConstructionPullup p WHERE p.principalId = :principalId")
            .setParameter("principalId", principalName)
            .executeUpdate();
    }

    @Override
    public List<BudgetConstructionPullup> getBudgetConstructionPullupFlagSetByUserId(String principalName) {
        TypedQuery<BudgetConstructionPullup> query = entityManager.createQuery(
            "SELECT p FROM BudgetConstructionPullup p WHERE p.principalId = :principalId " +
            "AND p.pullFlag > :minFlag", BudgetConstructionPullup.class);
        query.setParameter("principalId", principalName);
        query.setParameter("minFlag", BCConstants.OrgSelControlOption.NO.getKey());
        List<BudgetConstructionPullup> results = query.getResultList();
        return results.isEmpty() ? Collections.<BudgetConstructionPullup>emptyList() : results;
    }

    @Override
    public List<BudgetConstructionPullup> getBudgetConstructionPullupChildOrgs(String principalId, String chartOfAccountsCode, String organizationCode) {
        TypedQuery<BudgetConstructionPullup> query = entityManager.createQuery(
            "SELECT p FROM BudgetConstructionPullup p WHERE p.reportsToChartOfAccountsCode = :chart " +
            "AND p.reportsToOrganizationCode = :org AND p.principalId = :principalId " +
            "AND NOT (p.chartOfAccountsCode = p.reportsToChartOfAccountsCode AND p.organizationCode = p.reportsToOrganizationCode)",
            BudgetConstructionPullup.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        query.setParameter("principalId", principalId);
        List<BudgetConstructionPullup> results = query.getResultList();
        return results.isEmpty() ? Collections.<BudgetConstructionPullup>emptyList() : results;
    }

    @Override
    public KualiInteger getPendingBudgetConstructionAppointmentFundingRequestSum(PendingBudgetConstructionGeneralLedger salaryDetailLine) {
        javax.persistence.Query query = entityManager.createQuery(
            "SELECT SUM(f.appointmentRequestedAmount) FROM PendingBudgetConstructionAppointmentFunding f " +
            "WHERE f.universityFiscalYear = :fiscalYear AND f.chartOfAccountsCode = :chart " +
            "AND f.accountNumber = :account AND f.subAccountNumber = :subAccount " +
            "AND f.financialObjectCode = :objCode AND f.financialSubObjectCode = :subObjCode");
        query.setParameter("fiscalYear", salaryDetailLine.getUniversityFiscalYear());
        query.setParameter("chart", salaryDetailLine.getChartOfAccountsCode());
        query.setParameter("account", salaryDetailLine.getAccountNumber());
        query.setParameter("subAccount", salaryDetailLine.getSubAccountNumber());
        query.setParameter("objCode", salaryDetailLine.getFinancialObjectCode());
        query.setParameter("subObjCode", salaryDetailLine.getFinancialSubObjectCode());
        Object result = query.getSingleResult();
        if (result == null) {
            return KualiInteger.ZERO;
        }
        return new KualiInteger((BigDecimal) result);
    }

    @Override
    public List getDocumentPBGLFringeLines(String documentNumber, List fringeObjects) {
        entityManager.clear();
        TypedQuery<PendingBudgetConstructionGeneralLedger> query = entityManager.createQuery(
            "SELECT p FROM PendingBudgetConstructionGeneralLedger p WHERE p.documentNumber = :docNum " +
            "AND p.financialObjectCode IN :fringeObjects ORDER BY p.financialObjectCode",
            PendingBudgetConstructionGeneralLedger.class);
        query.setParameter("docNum", documentNumber);
        query.setParameter("fringeObjects", fringeObjects);
        return query.getResultList();
    }

    @Override
    public List<BudgetConstructionAccountOrganizationHierarchy> getAccountOrgHierForAccount(String chartOfAccountsCode, String accountNumber, Integer universityFiscalYear) {
        TypedQuery<BudgetConstructionAccountOrganizationHierarchy> query = entityManager.createQuery(
            "SELECT h FROM BudgetConstructionAccountOrganizationHierarchy h " +
            "WHERE h.universityFiscalYear = :fiscalYear AND h.chartOfAccountsCode = :chart " +
            "AND h.accountNumber = :account ORDER BY h.organizationLevelCode",
            BudgetConstructionAccountOrganizationHierarchy.class);
        query.setParameter("fiscalYear", universityFiscalYear);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("account", accountNumber);
        return query.getResultList();
    }

    @Override
    public List getPBGLSalarySettingRows(String documentNumber, List salarySettingObjects) {
        TypedQuery<PendingBudgetConstructionGeneralLedger> query = entityManager.createQuery(
            "SELECT p FROM PendingBudgetConstructionGeneralLedger p WHERE p.documentNumber = :docNum " +
            "AND p.financialObjectCode IN :objects ORDER BY p.financialObjectCode",
            PendingBudgetConstructionGeneralLedger.class);
        query.setParameter("docNum", documentNumber);
        query.setParameter("objects", salarySettingObjects);
        return query.getResultList();
    }

    @Override
    public List<PendingBudgetConstructionAppointmentFunding> getAllFundingForPosition(Integer universityFiscalYear, String positionNumber) {
        TypedQuery<PendingBudgetConstructionAppointmentFunding> query = entityManager.createQuery(
            "SELECT f FROM PendingBudgetConstructionAppointmentFunding f " +
            "WHERE f.universityFiscalYear = :fiscalYear AND f.positionNumber = :posNumber",
            PendingBudgetConstructionAppointmentFunding.class);
        query.setParameter("fiscalYear", universityFiscalYear);
        query.setParameter("posNumber", positionNumber);
        return query.getResultList();
    }

    @Override
    public BudgetConstructionAccountReports getAccountReports(String chartOfAccountsCode, String accountNumber) {
        TypedQuery<BudgetConstructionAccountReports> query = entityManager.createQuery(
            "SELECT r FROM BudgetConstructionAccountReports r WHERE r.chartOfAccountsCode = :chart " +
            "AND r.accountNumber = :account", BudgetConstructionAccountReports.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("account", accountNumber);
        List<BudgetConstructionAccountReports> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public BudgetConstructionOrganizationReports getOrganizationReports(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<BudgetConstructionOrganizationReports> query = entityManager.createQuery(
            "SELECT r FROM BudgetConstructionOrganizationReports r WHERE r.chartOfAccountsCode = :chart " +
            "AND r.organizationCode = :org", BudgetConstructionOrganizationReports.class);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        List<BudgetConstructionOrganizationReports> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public boolean insertAccountIntoAccountOrganizationHierarchy(String rootChart, String rootOrganization, Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, Integer currentLevelCode, String organizationChartOfAccountsCode, String organizationCode) {
        boolean overFlow = false;
        BudgetConstructionAccountOrganizationHierarchy hierarchy = new BudgetConstructionAccountOrganizationHierarchy();
        hierarchy.setUniversityFiscalYear(universityFiscalYear);
        hierarchy.setChartOfAccountsCode(chartOfAccountsCode);
        hierarchy.setAccountNumber(accountNumber);
        hierarchy.setOrganizationLevelCode(currentLevelCode);
        hierarchy.setOrganizationChartOfAccountsCode(organizationChartOfAccountsCode);
        hierarchy.setOrganizationCode(organizationCode);
        entityManager.merge(hierarchy);

        if (!(rootChart.equalsIgnoreCase(organizationChartOfAccountsCode) && rootOrganization.equalsIgnoreCase(organizationCode))) {
            if (currentLevelCode < BCConstants.MAXIMUM_ORGANIZATION_TREE_DEPTH) {
                BudgetConstructionOrganizationReports orgReports = this.getOrganizationReports(organizationChartOfAccountsCode, organizationCode);
                if (orgReports != null) {
                    currentLevelCode++;
                    overFlow = this.insertAccountIntoAccountOrganizationHierarchy(rootChart, rootOrganization, universityFiscalYear, chartOfAccountsCode, accountNumber, currentLevelCode, orgReports.getReportsToChartOfAccountsCode(), orgReports.getReportsToOrganizationCode());
                }
            } else {
                overFlow = true;
            }
        }
        return overFlow;
    }

    @Override
    public void deleteExistingAccountOrganizationHierarchy(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber) {
        entityManager.createQuery(
            "DELETE FROM BudgetConstructionAccountOrganizationHierarchy h " +
            "WHERE h.universityFiscalYear = :fiscalYear AND h.chartOfAccountsCode = :chart " +
            "AND h.accountNumber = :account")
            .setParameter("fiscalYear", universityFiscalYear)
            .setParameter("chart", chartOfAccountsCode)
            .setParameter("account", accountNumber)
            .executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
