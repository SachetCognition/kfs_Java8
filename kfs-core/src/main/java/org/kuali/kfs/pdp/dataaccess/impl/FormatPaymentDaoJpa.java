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

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.pdp.businessobject.PaymentProcess;
import org.kuali.kfs.pdp.dataaccess.FormatPaymentDao;

import org.kuali.rice.krad.service.BusinessObjectService;

public class FormatPaymentDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements FormatPaymentDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(FormatPaymentDaoJpa.class);

    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kfs.pdp.dataaccess.FormatPaymentDao#markPaymentsForFormat(org.kuali.kfs.pdp.businessobject.PaymentProcess,
     *      java.util.List, java.util.Date, java.lang.String)
     */
    public Iterator markPaymentsForFormat(List customerIds, Timestamp paydateTs, String paymentTypes) {
        LOG.debug("markPaymentsForFormat() started");

        Criteria criteria = new Criteria();

        if (customerIds.size() > 0) {
            criteria.addIn(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_BATCH + "." + PdpPropertyConstants.BatchConstants.CUSTOMER_ID, customerIds);
        }

        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PAYMENT_STATUS_CODE, PdpConstants.PaymentStatusCodes.OPEN);

        if (PdpConstants.PaymentTypes.DISBURSEMENTS_WITH_SPECIAL_HANDLING.equals(paymentTypes)) {
            // special handling only
            criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_SPECIAL_HANDLING, Boolean.TRUE);
        }
        else if (PdpConstants.PaymentTypes.DISBURSEMENTS_NO_SPECIAL_HANDLING.equals(paymentTypes)) {
            // no special handling only
            criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_SPECIAL_HANDLING, Boolean.FALSE);
        }
        else if (PdpConstants.PaymentTypes.DISBURSEMENTS_WITH_ATTACHMENTS.equals(paymentTypes)) {
            // attachments only
            criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_ATTACHMENT, Boolean.TRUE);
        }
        else if (PdpConstants.PaymentTypes.DISBURSEMENTS_NO_ATTACHMENTS.equals(paymentTypes)) {
            // no attachments only
            criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_ATTACHMENT, Boolean.FALSE);
        }

        if (PdpConstants.PaymentTypes.PROCESS_IMMEDIATE.equals(paymentTypes)) {
            criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PROCESS_IMMEDIATE, Boolean.TRUE);
        }
        else {
            // (Payment date <= usePaydate OR immediate = TRUE)
            Criteria criteria1 = new Criteria();
            criteria1.addEqualTo(PdpPropertyConstants.PaymentGroup.PROCESS_IMMEDIATE, Boolean.TRUE);

            Criteria criteria2 = new Criteria();
            criteria2.addLessOrEqualThan(PdpPropertyConstants.PaymentGroup.PAYMENT_DATE, paydateTs);
            criteria1.addOrCriteria(criteria2);

            criteria.addAndCriteria(criteria1);
        }

        Iterator groupIterator = entityManager.createQuery(new QueryByCriteria(PaymentGroup.class, criteria).getResultList().iterator());
        return groupIterator;
    }

    /**
     * @see org.kuali.kfs.pdp.dataaccess.FormatPaymentDao#unmarkPaymentsForFormat(org.kuali.kfs.pdp.businessobject.PaymentProcess)
     */
    public Iterator unmarkPaymentsForFormat(PaymentProcess proc) {
        LOG.debug("unmarkPaymentsForFormat() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PROCESS_ID, proc.getId());
        criteria.addEqualTo(PdpPropertyConstants.PaymentGroup.PAYMENT_GROUP_PAYMENT_STATUS_CODE, PdpConstants.PaymentStatusCodes.FORMAT);

        Iterator groupIterator = entityManager.createQuery(new QueryByCriteria(PaymentGroup.class, criteria).getResultList().iterator());
        return groupIterator;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
