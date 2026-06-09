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
package org.kuali.kfs.sys.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.kuali.kfs.sys.businessobject.OriginationCode;
import org.kuali.kfs.sys.dataaccess.OriginationCodeDao;

public class OriginationCodeDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements OriginationCodeDao {
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

    private static Logger LOG = Logger.getLogger(OriginationCodeDaoJpa.class);

    public OriginationCodeDaoJpa() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.rice.krad.dao.OriginationCodeDao#delete(org.kuali.rice.krad.bo.OriginationCode)
     */
    public void delete(OriginationCode code) {
        entityManager.remove(entityManager.contains(code) ? code : entityManager.merge(code));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.rice.krad.dao.OriginationCodeDao#findByCode(java.lang.String)
     */
    public OriginationCode findByCode(String originationCode) {
        // TODO Auto-generated method stub
        Criteria criteria = new Criteria();
        criteria.addEqualTo("FS_ORIGIN_CD", originationCode);

        QueryByCriteria query = QueryFactory.newQuery(OriginationCode.class, criteria);
        return (OriginationCode) entityManager.createQuery(query).getSingleResult();
    }

}
