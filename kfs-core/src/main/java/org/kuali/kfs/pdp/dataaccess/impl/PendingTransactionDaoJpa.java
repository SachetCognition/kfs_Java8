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
/*
 * Created on Sep 2, 2004
 *
 */
package org.kuali.kfs.pdp.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.Iterator;

import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.GlPendingTransaction;
import org.kuali.kfs.pdp.dataaccess.PendingTransactionDao;

/**
 * @see org.kuali.kfs.pdp.dataaccess.PendingTransactionDao
 */
public class PendingTransactionDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements PendingTransactionDao {
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

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(PendingTransactionDaoJpa.class);

    public PendingTransactionDaoJpa() {
        super();
    }

    /**
     * @see org.kuali.kfs.pdp.dataaccess.PendingTransactionDao#getUnextractedTransactions()
     */
    public Iterator<GlPendingTransaction> getUnextractedTransactions() {
        LOG.debug("save() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PROCESS_IND, false);
        
        Criteria criteria2 = new Criteria();
        criteria2.addIsNull(PdpPropertyConstants.PROCESS_IND);
        
        criteria.addOrCriteria(criteria2);
        return entityManager.createQuery(new QueryByCriteria(GlPendingTransaction.class, criteria).getResultList().iterator());
    }
    
    /**
     * @see org.kuali.kfs.pdp.dataaccess.PendingTransactionDao#clearExtractedTransactions()
     */
    public void clearExtractedTransactions() {
        LOG.debug("clearExtractedTransactions() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PROCESS_IND, true);

        QueryByCriteria qbc = QueryFactory.newQuery(GlPendingTransaction.class, criteria);
        entityManager.createQuery(qbc).executeUpdate();
        entityManager.clear();
    }

}
