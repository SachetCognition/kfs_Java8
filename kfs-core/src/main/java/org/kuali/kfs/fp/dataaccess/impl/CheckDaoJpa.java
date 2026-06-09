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
package org.kuali.kfs.fp.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.kuali.kfs.fp.businessobject.Check;
import org.kuali.kfs.fp.businessobject.CheckBase;
import org.kuali.kfs.fp.dataaccess.CheckDao;

import org.springframework.dao.DataAccessException;

/**
 * This class is the JPA/Hibernate implementation of the AccountingLineDao interface.
 */

public class CheckDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CheckDao {
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

    private static final Logger LOG = Logger.getLogger(CheckDaoJpa.class);

    /**
     * @param line
     * @throws DataAccessException
     */
    public void deleteCheck(Check check) throws DataAccessException {
        entityManager.remove(entityManager.contains(check) ? check : entityManager.merge(check));
    }

    /**
     * Retrieves accounting lines associate with a given document header ID using OJB.
     * 
     * @param classname
     * @param id
     * @return
     */
    public Collection<CheckBase> findByDocumentHeaderId(String documentHeaderId) throws DataAccessException {
        Criteria criteria = new Criteria();
        criteria.addEqualTo("FDOC_NBR", documentHeaderId);

        QueryByCriteria query = QueryFactory.newQuery(CheckBase.class, criteria);

        Collection<CheckBase> lines = entityManager.createQuery(query).getResultList();

        return lines;
    }
}
