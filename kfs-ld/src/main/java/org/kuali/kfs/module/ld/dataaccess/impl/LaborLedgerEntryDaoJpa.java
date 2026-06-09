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
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.gl.dataaccess.LedgerEntryBalancingDao;
import org.kuali.kfs.module.ld.businessobject.LedgerEntry;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerEntryDao;

public class LaborLedgerEntryDaoJpa implements LaborLedgerEntryDao, LedgerEntryBalancingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getMaxSquenceNumber(LedgerEntry ledgerEntry) {
        return 0;
    }

    @Override
    public Iterator<LedgerEntry> find(Map<String, String> fieldValues) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LedgerEntry> query = cb.createQuery(LedgerEntry.class);
        Root<LedgerEntry> root = query.from(LedgerEntry.class);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
            }
        }
        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList().iterator();
    }

    @Override
    public List<String> findEmployeesWithPayType(Map<Integer, Set<String>> payPeriods, List<String> balanceTypes, Map<String, Set<String>> earnCodePayGroupMap) {
        return new ArrayList<>();
    }

    @Override
    public Collection<LedgerEntry> getLedgerEntriesForEmployeeWithPayType(String emplid, Map<Integer, Set<String>> payPeriods, List<String> balanceTypes, Map<String, Set<String>> earnCodePayGroupMap) {
        return new ArrayList<>();
    }

    @Override
    public boolean isEmployeeWithPayType(String emplid, Map<Integer, Set<String>> payPeriods, List<String> balanceTypes, Map<String, Set<String>> earnCodePayGroupMap) {
        return false;
    }

    @Override
    public void deleteLedgerEntriesPriorToYear(Integer fiscalYear, String chartOfAccountsCode) {
        entityManager.createQuery("DELETE FROM LedgerEntry e WHERE e.universityFiscalYear < :year AND e.chartOfAccountsCode = :chart")
            .setParameter("year", fiscalYear)
            .setParameter("chart", chartOfAccountsCode)
            .executeUpdate();
    }

    // LedgerEntryBalancingDao methods

    @Override
    public Object[] findEntryByGroup(Integer universityFiscalYear, String chartOfAccountsCode, String financialObjectCode, String financialBalanceTypeCode, String universityFiscalPeriodCode, String transactionDebitCreditCode) {
        return null;
    }

    @Override
    public Integer findCountGreaterOrEqualThan(Integer year) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(e) FROM LedgerEntry e WHERE e.universityFiscalYear >= :year", Long.class);
        query.setParameter("year", year);
        return query.getSingleResult().intValue();
    }
}
