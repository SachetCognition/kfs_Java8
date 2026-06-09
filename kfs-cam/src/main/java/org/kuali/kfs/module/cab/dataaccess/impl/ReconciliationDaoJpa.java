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
package org.kuali.kfs.module.cab.dataaccess.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.gl.businessobject.Entry;
import org.kuali.kfs.module.cab.CabPropertyConstants;
import org.kuali.kfs.module.cab.businessobject.GeneralLedgerEntry;
import org.kuali.kfs.module.cab.dataaccess.ReconciliationDao;

public class ReconciliationDaoJpa implements ReconciliationDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReconciliationDaoJpa.class);

    private EntityManager entityManager;

    @Override
    public boolean isDuplicateEntry(Entry glEntry) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GeneralLedgerEntry> cq = cb.createQuery(GeneralLedgerEntry.class);
        Root<GeneralLedgerEntry> root = cq.from(GeneralLedgerEntry.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.UNIVERSITY_FISCAL_YEAR), glEntry.getUniversityFiscalYear()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.CHART_OF_ACCOUNTS_CODE), glEntry.getChartOfAccountsCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.ACCOUNT_NUMBER), glEntry.getAccountNumber()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.SUB_ACCOUNT_NUMBER), glEntry.getSubAccountNumber()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_OBJECT_CODE), glEntry.getFinancialObjectCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_SUB_OBJECT_CODE), glEntry.getFinancialSubObjectCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_BALANCE_TYPE_CODE), glEntry.getFinancialBalanceTypeCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_OBJECT_TYPE_CODE), glEntry.getFinancialObjectTypeCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.UNIVERSITY_FISCAL_PERIOD_CODE), glEntry.getUniversityFiscalPeriodCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_DOCUMENT_TYPE_CODE), glEntry.getFinancialDocumentTypeCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_SYSTEM_ORIGINATION_CODE), glEntry.getFinancialSystemOriginationCode()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.DOCUMENT_NUMBER), glEntry.getDocumentNumber()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.TRANSACTION_LEDGER_ENTRY_SEQUENCE_NUMBER), glEntry.getTransactionLedgerEntrySequenceNumber()));
        predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.PROJECT_CD), glEntry.getProjectCode()));

        if (StringUtils.isEmpty(glEntry.getOrganizationReferenceId())) {
            Predicate isNull = cb.isNull(root.get(CabPropertyConstants.GeneralLedgerEntry.ORGNIZATION_REFERENCE_ID));
            Predicate isEqual = cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.ORGNIZATION_REFERENCE_ID), glEntry.getOrganizationReferenceId());
            predicates.add(cb.or(isEqual, isNull));
        } else {
            predicates.add(cb.equal(root.get(CabPropertyConstants.GeneralLedgerEntry.ORGNIZATION_REFERENCE_ID), glEntry.getOrganizationReferenceId()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        Collection<GeneralLedgerEntry> matchingEntries = entityManager.createQuery(cq).getResultList();
        return matchingEntries != null && !matchingEntries.isEmpty();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
