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
package org.kuali.kfs.module.ld.dataaccess.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.gl.businessobject.Balance;
import org.kuali.kfs.gl.businessobject.Encumbrance;
import org.kuali.kfs.module.ld.businessobject.LaborLedgerPendingEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerPendingEntryDao;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class LaborLedgerPendingEntryDaoJpa implements LaborLedgerPendingEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterator<LaborLedgerPendingEntry> findPendingLedgerEntriesForLedgerBalance(Map fieldValues, boolean isApproved, String currentFYPeriod, int currentFY, List<String> encumbranceBalanceTypes) {
        return new ArrayList<LaborLedgerPendingEntry>().iterator();
    }

    @Override
    public Collection<LaborLedgerPendingEntry> hasPendingLaborLedgerEntry(Map fieldValues, Object businessObject) {
        return new ArrayList<>();
    }

    @Override
    public KualiDecimal getTransactionSummary(Collection universityFiscalYears, String chartOfAccountsCode, String accountNumber, Collection objectCodes, Collection balanceTypeCodes, boolean isDebit) {
        // TODO: Implement full transaction summary query once kfs-core parent class is JPA-annotated.
        // This requires dynamic query construction based on debit/credit code filtering.
        return KualiDecimal.ZERO;
    }

    @Override
    public KualiDecimal getTransactionSummary(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, Collection objectTypeCodes, Collection balanceTypeCodes, String acctSufficientFundsFinObjCd, boolean isDebit, boolean isYearEnd) {
        // TODO: Implement full transaction summary query once kfs-core parent class is JPA-annotated.
        return KualiDecimal.ZERO;
    }

    @Override
    public KualiDecimal getTransactionSummary(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, Collection objectTypeCodes, Collection balanceTypeCodes, String acctSufficientFundsFinObjCd, boolean isYearEnd) {
        // TODO: Implement full transaction summary query once kfs-core parent class is JPA-annotated.
        return KualiDecimal.ZERO;
    }

    @Override
    public Collection findPendingEntries(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFiscalYear, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>();
    }

    @Override
    public void delete(String documentHeaderId) {
        entityManager.createNativeQuery("DELETE FROM LD_PND_LDGR_ENTR_T WHERE FDOC_NBR = :docId")
            .setParameter("docId", documentHeaderId)
            .executeUpdate();
    }

    @Override
    public void deleteByFinancialDocumentApprovedCode(String financialDocumentApprovedCode) {
        entityManager.createNativeQuery("DELETE FROM LD_PND_LDGR_ENTR_T WHERE FDOC_APRVL_CD = :code")
            .setParameter("code", financialDocumentApprovedCode)
            .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator findApprovedPendingLedgerEntries() {
        Query query = entityManager.createNativeQuery(
            "SELECT * FROM LD_PND_LDGR_ENTR_T WHERE FDOC_APRVL_CD = :approvedCode",
            LaborLedgerPendingEntry.class);
        query.setParameter("approvedCode", "A");
        return query.getResultList().iterator();
    }

    @Override
    public int countPendingLedgerEntries(Account account) {
        Query query = entityManager.createNativeQuery(
            "SELECT COUNT(*) FROM LD_PND_LDGR_ENTR_T WHERE FIN_COA_CD = :chart AND ACCOUNT_NBR = :account");
        query.setParameter("chart", account.getChartOfAccountsCode());
        query.setParameter("account", account.getAccountNumber());
        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public Iterator findPendingLedgerEntries(Encumbrance encumbrance, boolean isApproved) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntries(Balance balance, boolean isApproved, boolean isConsolidated) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntriesForEntry(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFY, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntriesForBalance(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFY, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntriesForCashBalance(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFY, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntriesForEncumbrance(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFiscalYear, SystemOptions currentYearOptions, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntrySummaryForAccountBalance(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFiscalYear, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator findPendingLedgerEntriesForAccountBalance(Map fieldValues, boolean isApproved, String currentFiscalPeriodCode, int currentFiscalYear, List<String> encumbranceBalanceTypes) {
        return new ArrayList<>().iterator();
    }
}
