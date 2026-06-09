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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.kfs.gl.businessobject.BalanceHistory;
import org.kuali.kfs.gl.dataaccess.LedgerBalanceHistoryBalancingDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * A JPA/Hibernate implementation of EntryHistoryDao
 */
public class BalanceHistoryDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements LedgerBalanceHistoryBalancingDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(BalanceHistoryDaoJpa.class);
    
    /**
     * @see org.kuali.kfs.gl.dataaccess.LedgerBalanceHistoryBalancingDao#findDistinctFiscalYears()
     */
    public List<Integer> findDistinctFiscalYears() {
        Criteria crit = new Criteria();
        ReportQueryByCriteria q = QueryFactory.newReportQuery(BalanceHistory.class, crit);
        q.setAttributes(new String[] { KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR });
        q.setDistinct(true);

        Iterator<Object[]> years = entityManager.createQuery(q).getResultList().iterator();
        List<Integer> yearList = new ArrayList<Integer>();
        
        while (years != null && years.hasNext()) {
            Object[] year = years.next();
            yearList.add(new Integer(year[0].toString()));
        }
        
        return yearList;
    }
}
