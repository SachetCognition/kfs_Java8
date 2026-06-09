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

import org.kuali.kfs.gl.dataaccess.LedgerBalanceBalancingDao;
import org.kuali.kfs.module.ld.businessobject.EmployeeFunding;
import org.kuali.kfs.module.ld.businessobject.LaborBalanceSummary;
import org.kuali.kfs.module.ld.businessobject.LedgerBalance;
import org.kuali.kfs.module.ld.businessobject.LedgerBalanceForYearEndBalanceForward;
import org.kuali.kfs.module.ld.dataaccess.LaborLedgerBalanceDao;

public class LaborLedgerBalanceDaoJpa implements LaborLedgerBalanceDao, LedgerBalanceBalancingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Deprecated
    public Iterator findBalance(Map fieldValues, boolean isConsolidated, List<String> encumbranceBalanceTypes) {
        return findBalance(fieldValues, isConsolidated, encumbranceBalanceTypes, false);
    }

    @Override
    public Iterator<LedgerBalance> findBalance(Map fieldValues, boolean isConsolidated, List<String> encumbranceBalanceTypes, boolean noZeroAmounts) {
        return new ArrayList<LedgerBalance>().iterator();
    }

    @Override
    @Deprecated
    public Iterator getConsolidatedBalanceRecordCount(Map fieldValues, List<String> encumbranceBalanceTypes) {
        return getConsolidatedBalanceRecordCount(fieldValues, encumbranceBalanceTypes, false);
    }

    @Override
    public Iterator getConsolidatedBalanceRecordCount(Map fieldValues, List<String> encumbranceBalanceTypes, boolean noZeroAmounts) {
        return new ArrayList<>().iterator();
    }

    @Override
    public Iterator<LedgerBalance> findBalancesForFiscalYear(Integer fiscalYear) {
        return new ArrayList<LedgerBalance>().iterator();
    }

    @Override
    public Iterator<LedgerBalance> findBalancesForFiscalYear(Integer fiscalYear, Map<String, String> fieldValues, List<String> encumbranceBalanceTypes) {
        return new ArrayList<LedgerBalance>().iterator();
    }

    @Override
    public Iterator<LedgerBalanceForYearEndBalanceForward> findBalancesForFiscalYear(Integer fiscalYear, Map<String, String> fieldValues, List<String> subFundGroupCodes, List<String> fundGroupCodes) {
        return new ArrayList<LedgerBalanceForYearEndBalanceForward>().iterator();
    }

    @Override
    public List<LedgerBalance> findCurrentFunds(Map fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public List<LedgerBalance> findEncumbranceFunds(Map fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public List<EmployeeFunding> findCurrentEmployeeFunds(Map fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public List<EmployeeFunding> findEncumbranceEmployeeFunds(Map fieldValues) {
        return new ArrayList<>();
    }

    @Override
    public List<LaborBalanceSummary> findBalanceSummary(Integer fiscalYear, Collection<String> balanceTypes) {
        return new ArrayList<>();
    }

    @Override
    public List<List<String>> findAccountsInFundGroups(Integer fiscalYear, Map<String, String> fieldValues, List<String> subFundGroupCodes, List<String> fundGroupCodes) {
        return new ArrayList<>();
    }

    @Override
    public Collection<LedgerBalance> findLedgerBalances(Map<String, List<String>> fieldValues, Map<String, List<String>> excludedFieldValues, Set<Integer> fiscalYears, List<String> balanceTypeList, List<String> positionObjectGroupCodes) {
        return new ArrayList<>();
    }

    @Override
    public void deleteLedgerBalancesPriorToYear(Integer fiscalYear, String chartOfAccountsCode) {
        entityManager.createQuery("DELETE FROM LedgerBalance b WHERE b.universityFiscalYear < :year AND b.chartOfAccountsCode = :chart")
            .setParameter("year", fiscalYear)
            .setParameter("chart", chartOfAccountsCode)
            .executeUpdate();
    }

    @Override
    public Integer findCountGreaterOrEqualThan(Integer year) {
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(b) FROM LedgerBalance b WHERE b.universityFiscalYear >= :year", Long.class);
        query.setParameter("year", year);
        return query.getSingleResult().intValue();
    }
}
