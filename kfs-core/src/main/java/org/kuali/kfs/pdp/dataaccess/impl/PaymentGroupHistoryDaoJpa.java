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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.PaymentGroupHistory;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupHistoryDao;

public class PaymentGroupHistoryDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements PaymentGroupHistoryDao {
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

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(PaymentGroupHistoryDaoJpa.class);

    public PaymentGroupHistoryDaoJpa() {
        super();
    }

    /**
     * @see org.kuali.kfs.pdp.dataaccess.PaymentGroupHistoryDao#getCanceledChecks()
     */
    public Iterator getCanceledChecks() {
        LOG.debug("getCanceledChecks() started");

        Collection codes = new ArrayList();
        codes.add(PdpConstants.PaymentChangeCodes.CANCEL_DISBURSEMENT);
        codes.add(PdpConstants.PaymentChangeCodes.CANCEL_REISSUE_DISBURSEMENT);
        codes.add(PdpConstants.PaymentChangeCodes.REISSUE_DISBURSEMENT);

        Criteria crit = new Criteria();
        crit.addIn(PdpPropertyConstants.PAYMENT_CHANGE_CODE, codes);
        crit.addIsNull(PdpPropertyConstants.PaymentGroupHistory.PMT_CANCEL_EXTRACT_DATE);

        Criteria o1 = new Criteria();
        o1.addNotEqualTo(PdpPropertyConstants.DISBURSEMENT_TYPE_CODE, PdpConstants.DisbursementTypeCodes.ACH);
        Criteria o1a = new Criteria();
        o1a.addIsNull(PdpPropertyConstants.DISBURSEMENT_TYPE_CODE);
        o1.addOrCriteria(o1a);

        Criteria o2 = new Criteria();
        o2.addEqualTo(PdpPropertyConstants.DISBURSEMENT_TYPE_CODE, PdpConstants.DisbursementTypeCodes.CHECK);
        Criteria o2a = new Criteria();
        o2a.addEqualTo(PdpPropertyConstants.PAYMENT_GROUP + "." + PdpPropertyConstants.DISBURSEMENT_TYPE_CODE, PdpConstants.DisbursementTypeCodes.CHECK);
        o2.addOrCriteria(o2a);

        crit.addAndCriteria(o1);
        crit.addAndCriteria(o2);

        return entityManager.createQuery(new QueryByCriteria(PaymentGroupHistory.class, crit).getResultList().iterator());
    }
}
