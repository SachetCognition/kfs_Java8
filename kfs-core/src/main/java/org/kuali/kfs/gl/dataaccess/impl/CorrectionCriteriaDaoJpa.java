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
package org.kuali.kfs.gl.dataaccess.impl;

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

import org.kuali.kfs.gl.businessobject.CorrectionCriteria;
import org.kuali.kfs.gl.dataaccess.CorrectionCriteriaDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * A JPA/Hibernate implementation of CorrectionCriteriaDao
 */
public class CorrectionCriteriaDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CorrectionCriteriaDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(CorrectionCriteriaDaoJpa.class);

    /**
     * Deletes a correction criterion
     * 
     * @param criterion the criterion to delete
     * @see org.kuali.kfs.gl.dataaccess.CorrectionCriteriaDao#delete(org.kuali.kfs.gl.businessobject.CorrectionCriteria)
     */
    public void delete(CorrectionCriteria criterion) {
        LOG.debug("delete() started");

        entityManager.remove(entityManager.contains(criterion) ? criterion : entityManager.merge(criterion));
    }

    /**
     * Queries the database for a list of all the correction criteria associated with the given GLCP document and correction group
     * 
     * @param documentNumber the GLCP document number of correction criteria to find
     * @param correctionGroupLineNumber the correction group of correction criteria to find
     * @return a List of collection criteria
     * @see org.kuali.kfs.gl.dataaccess.CorrectionCriteriaDao#findByDocumentNumberAndCorrectionGroupNumber(java.lang.String,
     *      java.lang.Integer)
     */
    public List findByDocumentNumberAndCorrectionGroupNumber(String documentNumber, Integer correctionGroupLineNumber) {
        LOG.debug("findByDocumentNumberAndCorrectionGroupNumber() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(KFSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
        criteria.addEqualTo("correctionChangeGroupLineNumber", correctionGroupLineNumber);

        Class clazz = CorrectionCriteria.class;
        QueryByCriteria query = QueryFactory.newQuery(clazz, criteria);

        List returnList = (List) entityManager.createQuery(query).getResultList();
        return returnList;
    }

}
