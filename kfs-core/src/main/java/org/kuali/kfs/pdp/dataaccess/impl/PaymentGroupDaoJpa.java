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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;

public class PaymentGroupDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements PaymentGroupDao {
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

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(PaymentGroupDaoJpa.class);

    
    public PaymentGroupDaoJpa() {
        super();
    }

    /**
     * @see org.kuali.kfs.pdp.dataaccess.PaymentGroupDao#getDisbursementNumbersByDisbursementType(java.lang.Integer, java.lang.String)
     */
    public List<Integer> getDisbursementNumbersByDisbursementType(Integer pid,String disbursementType) {
        LOG.debug("getDisbursementNumbersByDisbursementType() started");

        List<Integer> results = new ArrayList<Integer>();

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PROCESS_ID, pid);
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_TYPE_CODE, disbursementType);

        String[] fields = new String[] { PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_NBR };

        ReportQueryByCriteria rq = QueryFactory.newReportQuery(PaymentGroup.class, fields, criteria, true);
        rq.addOrderBy(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_NBR, true);

        Iterator i = entityManager.createQuery(rq).getResultList().iterator();
        while ( i.hasNext() ) {
            Object[] data = (Object[])i.next();
            BigDecimal d = (BigDecimal)data[0];
            results.add( new Integer(d.intValue()) );
        }
        return results;
    }
    
    /**
     * @see org.kuali.kfs.pdp.dataaccess.PaymentGroupDao#getDisbursementNumbersByDisbursementType(java.lang.Integer, java.lang.String, java.lang.String)
     */
    public List<Integer> getDisbursementNumbersByDisbursementTypeAndBankCode(Integer pid, String disbursementType, String bankCode) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getDisbursementNumbersByDisbursementType() started");
        }

        List<Integer> results = new ArrayList<Integer>();

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PROCESS_ID, pid);
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_TYPE_CODE, disbursementType);
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_BANK_CODE, bankCode);

        String[] fields = new String[] { PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_NBR };

        ReportQueryByCriteria rq = QueryFactory.newReportQuery(PaymentGroup.class, fields, criteria, true);
        rq.addOrderBy(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_NBR, true);

        Iterator i = entityManager.createQuery(rq).getResultList().iterator();
        while ( i.hasNext() ) {
            Object[] data = (Object[])i.next();
            BigDecimal d = (BigDecimal)data[0];
            results.add( new Integer(d.intValue()) );
        }
        return results;
    }

    /**
     * Given a process id and a disbursement type, finds a distinct list of bank codes used by payment groups within that payment process
     * @param pid payment process to query payment groups of
     * @param disbursementType the type of disbursements to query
     * @return a sorted List of bank codes
     */
    public List<String> getDistinctBankCodesForProcessAndType(Integer pid, String disbursementType) {
        List<String> results = new ArrayList<String>();
        
        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PROCESS_ID, pid);
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_DISBURSEMENT_TYPE_CODE, disbursementType);

        String[] fields = new String[] { PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_BANK_CODE };
        
        ReportQueryByCriteria rq = QueryFactory.newReportQuery(PaymentGroup.class, fields, criteria, true);
        rq.addOrderBy(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_BANK_CODE, true);
        
        Iterator iter = entityManager.createQuery(rq).getResultList().iterator();
        while (iter.hasNext()) {
            final Object[] row = (Object[])iter.next();
            final String bankCode = (String)row[0];
        
            results.add(bankCode);
        }
        
        return results;
    }

    /**
     * @see org.kuali.kfs.pdp.dataaccess.PaymentGroupDao#getAchPaymentsNeedingAdviceNotification()
     */
    public List<PaymentGroup> getAchPaymentsNeedingAdviceNotification() {
        LOG.debug("getAchPaymentsNeedingAdviceNotification() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PAYMENT_STATUS_CODE, PdpConstants.PaymentStatusCodes.EXTRACTED);
        criteria.addEqualTo(PdpPropertyConstants.DISBURSEMENT_TYPE_CODE, PdpConstants.DisbursementTypeCodes.ACH);
        criteria.addIsNull(PdpPropertyConstants.ADVICE_EMAIL_SENT_DATE);

        return (List<PaymentGroup>) entityManager.createQuery(new QueryByCriteria(PaymentGroup.class, criteria).getResultList());
    }
 
}

