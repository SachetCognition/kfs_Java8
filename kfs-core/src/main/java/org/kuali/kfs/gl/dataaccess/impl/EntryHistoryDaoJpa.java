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

import java.math.BigDecimal;
import java.util.Iterator;

import org.kuali.kfs.gl.businessobject.EntryHistory;
import org.kuali.kfs.gl.dataaccess.LedgerEntryHistoryBalancingDao;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.util.TransactionalServiceUtils;

import org.kuali.rice.krad.util.ObjectUtils;

/**
 * A JPA/Hibernate implementation of LedgerEntryHistoryBalancingDao
 */
public class EntryHistoryDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements LedgerEntryHistoryBalancingDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(EntryHistoryDaoJpa.class);
    
    /**
     * @see org.kuali.kfs.gl.dataaccess.LedgerEntryHistoryBalancingDao#findSumRowCountGreaterOrEqualThan(java.lang.Integer)
     */
    public Integer findSumRowCountGreaterOrEqualThan(Integer year) {
        Criteria criteria = new Criteria();
        criteria.addGreaterOrEqualThan(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, year);
        
        ReportQueryByCriteria reportQuery = QueryFactory.newReportQuery(EntryHistory.class, criteria);
        reportQuery.setAttributes(new String[] { "sum(" + KFSPropertyConstants.ROW_COUNT  + ")"});
        
        Iterator<Object[]> iterator = entityManager.createQuery(reportQuery).getResultList().iterator();
        Object[] returnResult = TransactionalServiceUtils.retrieveFirstAndExhaustIterator(iterator);
        
        return ObjectUtils.isNull(returnResult[0]) ? 0 : ((BigDecimal) returnResult[0]).intValue();
    }
}
