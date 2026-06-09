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
 * Created on Aug 19, 2004
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

import java.util.List;

import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.PaymentProcess;
import org.kuali.kfs.pdp.dataaccess.ProcessDao;

/**
 * 
 */
public class ProcessDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements ProcessDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ProcessDaoJpa.class);

    public ProcessDaoJpa() {
        super();
    }
    
    public List<PaymentProcess> getAllExtractsToRun() {
        Criteria c = new Criteria();
        c.addEqualTo(PdpPropertyConstants.PaymentProcess.EXTRACTED_IND, false);
        c.addEqualTo(PdpPropertyConstants.PaymentProcess.FORMATTED_IND, true);
        return (List<PaymentProcess>) entityManager.createQuery(new QueryByCriteria(PaymentProcess.class, c).getResultList());
    }
    
    public PaymentProcess get(Integer procId) {
        LOG.debug("get() started");

        Criteria c = new Criteria();
        c.addEqualTo("id", procId);

        PaymentProcess p = (PaymentProcess) getPersistenceBrokerTemplate().getObjectByQuery(new QueryByCriteria(PaymentProcess.class, c));
        if (p != null) {
            
            return p;
        }
        else {
            return null;
        }
    }

}

