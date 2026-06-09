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
package org.kuali.kfs.fp.document.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import org.kuali.kfs.fp.businessobject.TravelMileageRate;
import org.kuali.kfs.fp.document.dataaccess.TravelMileageRateDao;

/**
 * This class is the JPA/Hibernate implementation of the TravelMileageRate interface.
 */
public class TravelMileageRateDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements TravelMileageRateDao {
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


    private static Logger LOG = Logger.getLogger(TravelMileageRateDaoJpa.class);

    /**
     * @see org.kuali.kfs.fp.document.dataaccess.TravelMileageRateDao#retrieveMostEffectiveMileageRates(java.sql.Timestamp)
     */
    @Override
    public Collection<TravelMileageRate> retrieveMostEffectiveMileageRates(Date effectiveDate) {
        Criteria criteria = new Criteria();
        criteria.addLessOrEqualThan("disbursementVoucherMileageEffectiveDate", effectiveDate);

        QueryByCriteria queryByCriteria = new QueryByCriteria(TravelMileageRate.class, criteria);
        queryByCriteria.addOrderByDescending("disbursementVoucherMileageEffectiveDate");
        queryByCriteria.addOrderByDescending("mileageLimitAmount");

        Collection mostEffectiveRates = new ArrayList();
        Collection rates = entityManager.createQuery(queryByCriteria).getResultList();
        Date mostEffectiveDate = ((TravelMileageRate) rates.iterator().next()).getDisbursementVoucherMileageEffectiveDate();
        for (Iterator iter = rates.iterator(); iter.hasNext();) {
            TravelMileageRate rate = (TravelMileageRate) iter.next();
            if (rate.getDisbursementVoucherMileageEffectiveDate().compareTo(mostEffectiveDate) == 0) {
                mostEffectiveRates.add(rate);
            }
        }

        return mostEffectiveRates;
    }

}
