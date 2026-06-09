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
package org.kuali.kfs.vnd.batch.dataaccess;

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

import org.kuali.kfs.vnd.businessobject.DebarredVendorMatch;
import org.kuali.kfs.vnd.businessobject.VendorDetail;

public class DebarredVendorMatchDaoJpa implements org.kuali.rice.core.framework.persistence.dao.PlatformAwareDao, DebarredVendorMatchDao {
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


    /**
     * @see org.kuali.kfs.vnd.batch.dataaccess.DebarredVendorMatchDao.getPreviousVendorExcludeConfirmation(org.kuali.kfs.vnd.businessobject.DebarredVendorMatch)
     */
    @Override
    public DebarredVendorMatch getPreviousVendorExcludeConfirmation(DebarredVendorMatch match) {
        Criteria criteria = new Criteria();

        criteria.addEqualTo("vendorHeaderGeneratedIdentifier", match.getVendorHeaderGeneratedIdentifier());
        criteria.addEqualTo("vendorDetailAssignedIdentifier", match.getVendorDetailAssignedIdentifier());

        if (match.getName() != null) {
            criteria.addEqualTo("upper(name)", match.getName().toUpperCase());
        } else {
            criteria.addIsNull("name");
        }
        if (match.getAddress1() != null) {
            criteria.addEqualTo("upper(address1)", match.getAddress1().toUpperCase());
        } else {
            criteria.addIsNull("address1");
        }
        if (match.getAddress2() != null) {
            criteria.addEqualTo("upper(address2)", match.getAddress2().toUpperCase());
        } else {
            criteria.addIsNull("address2");
        }
        if (match.getCity() != null) {
            criteria.addEqualTo("upper(city)", match.getCity().toUpperCase());
        } else {
            criteria.addIsNull("city");
        }
        if (match.getState() != null) {
            criteria.addEqualTo("upper(state)", match.getState().toUpperCase());
        } else {
            criteria.addIsNull("state");
        }
        if (match.getZip() != null) {
            criteria.addEqualTo("zip", match.getZip());
        } else {
            criteria.addIsNull("zip");
        }
        QueryByCriteria query = QueryFactory.newQuery(DebarredVendorMatch.class, criteria);
        List<DebarredVendorMatch> matches = (List<DebarredVendorMatch>)entityManager.createQuery(query).getResultList();

        DebarredVendorMatch oldMatch = null;
        if (matches.size() > 0) {
            oldMatch = matches.get(0);
        }
        return oldMatch;
    }

    /**
     * @see org.kuali.kfs.vnd.batch.dataaccess.DebarredVendorMatchDao.getDebarredVendorsUnmatched()
     */
    @Override
    public List<VendorDetail> getDebarredVendorsUnmatched() {

        Criteria subcr = new Criteria();
        subcr.addEqualToField("vendorHeaderGeneratedIdentifier", Criteria.PARENT_QUERY_PREFIX + "vendorHeaderGeneratedIdentifier");
        subcr.addEqualToField("vendorDetailAssignedIdentifier", Criteria.PARENT_QUERY_PREFIX + "vendorDetailAssignedIdentifier");
        Criteria orcr = new Criteria();
        orcr.addEqualTo("confirmStatusCode", "C");
        Criteria orcr2 = new Criteria();
        orcr2.addEqualTo("confirmStatusCode", "U");
        orcr.addOrCriteria(orcr2);
        subcr.addAndCriteria(orcr);
        QueryByCriteria subqr = QueryFactory.newQuery(DebarredVendorMatch.class, subcr);

        Criteria criteria = new Criteria();
        criteria.addEqualTo("vendorHeader.vendorDebarredIndicator", "Y");
        criteria.addNotExists(subqr);
        QueryByCriteria query = QueryFactory.newQuery(VendorDetail.class, criteria);
        List<VendorDetail> vendors = (List<VendorDetail>) entityManager.createQuery(query).getResultList();

        return vendors;
      }

    @Override
    public DebarredVendorMatch getDebarredVendor(int debarredVendorId) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo("debarredVendorId", debarredVendorId);
        QueryByCriteria query = QueryFactory.newQuery(DebarredVendorMatch.class, criteria);
        return (DebarredVendorMatch)entityManager.createQuery(query).getSingleResult();
    }

}
