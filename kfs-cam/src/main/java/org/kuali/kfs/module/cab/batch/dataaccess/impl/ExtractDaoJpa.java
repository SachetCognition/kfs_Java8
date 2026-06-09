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
package org.kuali.kfs.module.cab.batch.dataaccess.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.gl.businessobject.Entry;
import org.kuali.kfs.module.cab.CabPropertyConstants;
import org.kuali.kfs.module.cab.batch.dataaccess.ExtractDao;
import org.kuali.kfs.module.cab.businessobject.BatchParameters;
import org.kuali.kfs.module.purap.businessobject.CreditMemoAccountRevision;
import org.kuali.kfs.module.purap.businessobject.PaymentRequestAccountRevision;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderAccount;

public class ExtractDaoJpa implements ExtractDao {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ExtractDaoJpa.class);

    private EntityManager entityManager;

    @Override
    public Collection<Entry> findMatchingGLEntries(BatchParameters batchParameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entry> cq = cb.createQuery(Entry.class);
        Root<Entry> root = cq.from(Entry.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.greaterThan(root.<Timestamp>get("transactionDateTimeStamp"), batchParameters.getLastRunTime()));

        if (!batchParameters.getExcludedChartCodes().isEmpty()) {
            predicates.add(cb.not(root.get("chartOfAccountsCode").in(batchParameters.getExcludedChartCodes())));
        }
        if (!batchParameters.getExcludedSubFundCodes().isEmpty()) {
            predicates.add(cb.not(root.get("account").get("subFundGroupCode").in(batchParameters.getExcludedSubFundCodes())));
        }
        if (!batchParameters.getIncludedFinancialBalanceTypeCodes().isEmpty()) {
            predicates.add(root.get("financialBalanceTypeCode").in(batchParameters.getIncludedFinancialBalanceTypeCodes()));
        }
        if (!batchParameters.getIncludedFinancialObjectSubTypeCodes().isEmpty()) {
            predicates.add(root.get("financialObject").get("financialObjectSubTypeCode").in(batchParameters.getIncludedFinancialObjectSubTypeCodes()));
        }
        if (!batchParameters.getExcludedFiscalPeriods().isEmpty()) {
            predicates.add(cb.not(root.get("universityFiscalPeriodCode").in(batchParameters.getExcludedFiscalPeriods())));
        }
        if (!batchParameters.getExcludedDocTypeCodes().isEmpty()) {
            predicates.add(cb.not(root.get("financialDocumentTypeCode").in(batchParameters.getExcludedDocTypeCodes())));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("documentNumber")), cb.asc(root.get("transactionDateTimeStamp")));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Collection<PurchaseOrderAccount> findPreTaggablePOAccounts(BatchParameters batchParameters, List<String> docNumbersAwaitingPurchaseOrderStatus) {
        // Simplified JPA implementation
        Collection<PurchaseOrderAccount> allAccounts = findPreTaggablePOAccountsInternal(batchParameters);
        Collection<PurchaseOrderAccount> result = new ArrayList<PurchaseOrderAccount>();
        for (PurchaseOrderAccount account : allAccounts) {
            if (docNumbersAwaitingPurchaseOrderStatus.contains(account.getDocumentNumber())) {
                result.add(account);
            }
        }
        return result;
    }

    @Override
    @Deprecated
    public Collection<PurchaseOrderAccount> findPreTaggablePOAccounts(BatchParameters batchParameters) {
        return new ArrayList<PurchaseOrderAccount>();
    }

    private Collection<PurchaseOrderAccount> findPreTaggablePOAccountsInternal(BatchParameters batchParameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PurchaseOrderAccount> cq = cb.createQuery(PurchaseOrderAccount.class);
        Root<PurchaseOrderAccount> root = cq.from(PurchaseOrderAccount.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        Timestamp lastRunTimestamp = new Timestamp(((java.util.Date) batchParameters.getLastRunDate()).getTime());
        predicates.add(cb.greaterThan(root.<Timestamp>get("purchaseOrder").<Timestamp>get("purchaseOrderInitialOpenTimestamp"), lastRunTimestamp));
        predicates.add(cb.greaterThanOrEqualTo(root.get("item").<java.math.BigDecimal>get("itemUnitPrice"), batchParameters.getCapitalizationLimitAmount()));

        if (!batchParameters.getExcludedChartCodes().isEmpty()) {
            predicates.add(cb.not(root.get("chartOfAccountsCode").in(batchParameters.getExcludedChartCodes())));
        }
        if (!batchParameters.getExcludedSubFundCodes().isEmpty()) {
            predicates.add(cb.not(root.get("account").get("subFundGroupCode").in(batchParameters.getExcludedSubFundCodes())));
        }
        if (!batchParameters.getIncludedFinancialObjectSubTypeCodes().isEmpty()) {
            predicates.add(root.get("financialObject").get("financialObjectSubTypeCode").in(batchParameters.getIncludedFinancialObjectSubTypeCodes()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Collection<CreditMemoAccountRevision> findCreditMemoAccountRevisions(BatchParameters batchParameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CreditMemoAccountRevision> cq = cb.createQuery(CreditMemoAccountRevision.class);
        Root<CreditMemoAccountRevision> root = cq.from(CreditMemoAccountRevision.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.greaterThan(root.<Timestamp>get("accountRevisionTimestamp"), batchParameters.getLastRunTime()));

        if (!batchParameters.getExcludedChartCodes().isEmpty()) {
            predicates.add(cb.not(root.get("chartOfAccountsCode").in(batchParameters.getExcludedChartCodes())));
        }
        if (!batchParameters.getExcludedSubFundCodes().isEmpty()) {
            predicates.add(cb.not(root.get("account").get("subFundGroupCode").in(batchParameters.getExcludedSubFundCodes())));
        }
        if (!batchParameters.getIncludedFinancialObjectSubTypeCodes().isEmpty()) {
            predicates.add(root.get("financialObject").get("financialObjectSubTypeCode").in(batchParameters.getIncludedFinancialObjectSubTypeCodes()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Collection<PaymentRequestAccountRevision> findPaymentRequestAccountRevisions(BatchParameters batchParameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentRequestAccountRevision> cq = cb.createQuery(PaymentRequestAccountRevision.class);
        Root<PaymentRequestAccountRevision> root = cq.from(PaymentRequestAccountRevision.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.greaterThan(root.<Timestamp>get("accountRevisionTimestamp"), batchParameters.getLastRunTime()));

        if (!batchParameters.getExcludedChartCodes().isEmpty()) {
            predicates.add(cb.not(root.get("chartOfAccountsCode").in(batchParameters.getExcludedChartCodes())));
        }
        if (!batchParameters.getExcludedSubFundCodes().isEmpty()) {
            predicates.add(cb.not(root.get("account").get("subFundGroupCode").in(batchParameters.getExcludedSubFundCodes())));
        }
        if (!batchParameters.getIncludedFinancialObjectSubTypeCodes().isEmpty()) {
            predicates.add(root.get("financialObject").get("financialObjectSubTypeCode").in(batchParameters.getIncludedFinancialObjectSubTypeCodes()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
