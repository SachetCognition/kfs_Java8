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
package org.kuali.kfs.vnd.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.vnd.VendorPropertyConstants;
import org.kuali.kfs.vnd.businessobject.CommodityCode;
import org.kuali.kfs.vnd.dataaccess.CommodityCodeDao;

/**
 * JPA/Hibernate implementation of CommodityCodeDao.
 */
public class CommodityCodeDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CommodityCodeDao {
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

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(CommodityCodeDaoJpa.class);

    /**
     * @see org.kuali.kfs.vnd.dataaccess.CommodityCodeDao#wildCardCommodityCodeExists(java.lang.String)
     */
    public boolean wildCardCommodityCodeExists(String wildCardCommodityCode) {
        String commodityCodeString = StringUtils.replace(wildCardCommodityCode, KFSConstants.WILDCARD_CHARACTER, KFSConstants.PERCENTAGE_SIGN);
        Criteria criteria = new Criteria();
        criteria.addLike(VendorPropertyConstants.PURCHASING_COMMODITY_CODE, commodityCodeString);        
        int count =  entityManager.createQuery(QueryFactory.newQuery(CommodityCode.class, criteria).getResultList().size());
        boolean exists = ((count > 0) ? true : false);
        return exists;
    }
}
