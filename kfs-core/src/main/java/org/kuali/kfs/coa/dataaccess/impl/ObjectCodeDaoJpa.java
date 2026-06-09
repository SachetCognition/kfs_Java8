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
package org.kuali.kfs.coa.dataaccess.impl;

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
import java.util.List;

import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.dataaccess.ObjectCodeDao;

/**
 * This class is the JPA/Hibernate implementation of the ObjectCodeDao interface.
 */
public class ObjectCodeDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements ObjectCodeDao {
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

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ObjectCodeDaoJpa.class);

    /**
     * @see org.kuali.kfs.coa.dataaccess.ObjectCodeDao#getYearList(java.lang.String, java.lang.String)
     */
    @Override
    public List getYearList(String chartOfAccountsCode, String financialObjectCode) {

        List returnList = new ArrayList();
        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("financialObjectCode", financialObjectCode);
        Collection years = entityManager.createQuery(QueryFactory.newQuery(ObjectCode.class, criteria).getResultList());
        for (Iterator iter = years.iterator(); iter.hasNext();) {
            ObjectCode o = (ObjectCode) iter.next();
            if (o != null) {
                returnList.add(o.getUniversityFiscalYear());
            }
        }
        return returnList;
    }
}
