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

import org.kuali.kfs.gl.businessobject.CorrectionChange;
import org.kuali.kfs.gl.dataaccess.CorrectionChangeDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * The JPA/Hibernate implementation of the CorrectionChangeDao
 */
public class CorrectionChangeDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CorrectionChangeDao {
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


    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(CorrectionChangeDaoJpa.class);

    /**
     * Delete a CorrectionChange from the database
     * 
     * @param spec the CorrectionChange to delete
     * @see org.kuali.kfs.gl.dataaccess.CorrectionChangeDao#delete(org.kuali.kfs.gl.businessobject.CorrectionChange)
     */
    public void delete(CorrectionChange spec) {
        LOG.debug("delete() started");

        entityManager.remove(entityManager.contains(spec) ? spec : entityManager.merge(spec));
    }

    /**
     * Query the database to find qualifying CorrectionChange records
     * 
     * @param documentHeaderId the document number of a GLCP document
     * @param correctionGroupLineNumber the line number of the group within the GLCP document to find correction chagnes for
     * @return a List of correction changes
     * @see org.kuali.kfs.gl.dataaccess.CorrectionChangeDao#findByDocumentHeaderIdAndCorrectionGroupNumber(java.lang.String,
     *      java.lang.Integer)
     */
    public List findByDocumentHeaderIdAndCorrectionGroupNumber(String documentNumber, Integer correctionGroupLineNumber) {
        LOG.debug("findByDocumentHeaderIdAndCorrectionGroupNumber() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo(KFSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
        criteria.addEqualTo("correctionChangeGroupLineNumber", correctionGroupLineNumber);

        QueryByCriteria query = QueryFactory.newQuery(CorrectionChange.class, criteria);

        return (List) entityManager.createQuery(query).getResultList();
    }

}
