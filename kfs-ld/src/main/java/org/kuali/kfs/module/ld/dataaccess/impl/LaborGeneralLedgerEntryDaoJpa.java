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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ld.businessobject.LaborGeneralLedgerEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborGeneralLedgerEntryDao;

public class LaborGeneralLedgerEntryDaoJpa implements LaborGeneralLedgerEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getMaxSequenceNumber(LaborGeneralLedgerEntry laborGeneralLedgerEntry) {
        TypedQuery<Integer> query = entityManager.createQuery(
            "SELECT MAX(e.transactionLedgerEntrySequenceNumber) FROM LaborGeneralLedgerEntry e " +
            "WHERE e.universityFiscalYear = :fiscalYear " +
            "AND e.chartOfAccountsCode = :chart " +
            "AND e.accountNumber = :account " +
            "AND e.subAccountNumber = :subAccount " +
            "AND e.financialObjectCode = :objectCode " +
            "AND e.financialSubObjectCode = :subObjectCode " +
            "AND e.financialBalanceTypeCode = :balanceType " +
            "AND e.financialObjectTypeCode = :objectType " +
            "AND e.universityFiscalPeriodCode = :periodCode " +
            "AND e.financialDocumentTypeCode = :docType " +
            "AND e.financialSystemOriginationCode = :originCode " +
            "AND e.documentNumber = :docNumber", Integer.class);
        query.setParameter("fiscalYear", laborGeneralLedgerEntry.getUniversityFiscalYear());
        query.setParameter("chart", laborGeneralLedgerEntry.getChartOfAccountsCode());
        query.setParameter("account", laborGeneralLedgerEntry.getAccountNumber());
        query.setParameter("subAccount", laborGeneralLedgerEntry.getSubAccountNumber());
        query.setParameter("objectCode", laborGeneralLedgerEntry.getFinancialObjectCode());
        query.setParameter("subObjectCode", laborGeneralLedgerEntry.getFinancialSubObjectCode());
        query.setParameter("balanceType", laborGeneralLedgerEntry.getFinancialBalanceTypeCode());
        query.setParameter("objectType", laborGeneralLedgerEntry.getFinancialObjectTypeCode());
        query.setParameter("periodCode", laborGeneralLedgerEntry.getUniversityFiscalPeriodCode());
        query.setParameter("docType", laborGeneralLedgerEntry.getFinancialDocumentTypeCode());
        query.setParameter("originCode", laborGeneralLedgerEntry.getFinancialSystemOriginationCode());
        query.setParameter("docNumber", laborGeneralLedgerEntry.getDocumentNumber());
        Integer result = query.getSingleResult();
        return result != null ? result : Integer.valueOf(0);
    }
}
