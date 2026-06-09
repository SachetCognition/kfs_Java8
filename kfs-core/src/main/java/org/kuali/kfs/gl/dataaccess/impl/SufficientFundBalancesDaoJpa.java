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
package org.kuali.kfs.gl.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.Collection;

import org.kuali.kfs.gl.businessobject.SufficientFundBalances;
import org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * A JPA/Hibernate implementation of the SufficientFundBalancesDao
 */
public class SufficientFundBalancesDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements SufficientFundBalancesDao {
    @PersistenceContext
    private EntityManager entityManager;

    private org.kuali.rice.core.framework.persistence.platform.DatabasePlatform dbPlatform;

    @Override
    public org.kuali.rice.core.framework.persistence.platform.DatabasePlatform getDbPlatform() {
        return dbPlatform;
    }

    public void setDbPlatform(org.kuali.rice.core.framework.persistence.platform.DatabasePlatform dbPlatform) {
        this.dbPlatform = dbPlatform;
    }

    public void setJcdAlias(String jcdAlias) {
        // no-op: JPA does not use OJB jcdAlias
    }


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SufficientFundBalancesDaoJpa.class);

    /**
     * Builds an OJB query based on the parameter values and returns all the sufficient fund balances that match that record
     * 
     * @param universityFiscalYear the university fiscal year of sufficient fund balances to find
     * @param chartOfAccountsCode the chart of accounts code of sufficient fund balances to find
     * @param financialObjectCode the object code of sufficient fund balances to find
     * @return a Collection of sufficient fund balances, qualified by the parameter values
     * @see org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao#getByObjectCode(java.lang.Integer, java.lang.String, java.lang.String)
     */
    public Collection getByObjectCode(Integer universityFiscalYear, String chartOfAccountsCode, String financialObjectCode) {
        LOG.debug("getByObjectCode() started");

        Criteria crit = new Criteria();
        crit.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, universityFiscalYear);
        crit.addEqualTo(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, chartOfAccountsCode);
        crit.addEqualTo(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, financialObjectCode);

        QueryByCriteria qbc = QueryFactory.newQuery(SufficientFundBalances.class, crit);
        return entityManager.createQuery(qbc).getResultList();
    }

    /**
     * Deletes sufficient fund balances associated with a given year, chart, and account number
     * 
     * @param universityFiscalYear the university fiscal year of sufficient fund balances to delete
     * @param chartOfAccountsCode the chart code of sufficient fund balances to delete
     * @param accountNumber the account number of sufficient fund balances to delete
     * @return the number of records deleted
     * @see org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao#deleteByAccountNumber(java.lang.Integer, java.lang.String, java.lang.String)
     */
    public int deleteByAccountNumber(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber) {
        LOG.debug("deleteByAccountNumber() started");
        
        Criteria crit = new Criteria();
        crit.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, universityFiscalYear);
        crit.addEqualTo(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, chartOfAccountsCode);
        crit.addEqualTo(KFSPropertyConstants.ACCOUNT_NUMBER, accountNumber);

        QueryByCriteria qbc = QueryFactory.newQuery(SufficientFundBalances.class, crit);
        int count = entityManager.createQuery(qbc).getResultList().size();
        entityManager.createQuery(qbc).executeUpdate();

        // This has to be done because deleteByQuery deletes the rows from the table,
        // but it doesn't delete them from the cache. If the cache isn't cleared,
        // later on, you could get an Optimistic Lock Exception because OJB thinks rows
        // exist when they really don't.
        entityManager.clear();
        
        return count;
    }

    /**
     * This method should only be used in unit tests. It loads all the gl_sf_balances_t rows in memory into a collection. This won't
     * sace for production.
     * 
     * @return a Collection of all sufficient funds records in the database
     */
    public Collection testingGetAllEntries() {
        LOG.debug("testingGetAllEntries() started");

        Criteria criteria = new Criteria();
        QueryByCriteria qbc = QueryFactory.newQuery(SufficientFundBalances.class, criteria);
        qbc.addOrderBy(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, true);
        qbc.addOrderBy(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, true);
        qbc.addOrderBy(KFSPropertyConstants.ACCOUNT_NUMBER, true);
        qbc.addOrderBy(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, true);

        return entityManager.createQuery(qbc).getResultList();
    }
}
