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
package org.kuali.kfs.gl.document.dataaccess.impl;

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
import java.util.Collection;

import org.kuali.kfs.gl.document.GeneralLedgerCorrectionProcessDocument;
import org.kuali.kfs.gl.document.dataaccess.CorrectionDocumentDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;

/**
 * The JPA/Hibernate implementation of CorrectionDocumentDao
 */
public class CorrectionDocumentDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements CorrectionDocumentDao {
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

    /**
     * Queries the database to get a Collection of GLCP documents finalized on the given date
     * 
     * @param date the finalization date of GLCP documents to find
     * @return a Collection of GLCP documents
     */
    public Collection<GeneralLedgerCorrectionProcessDocument> getCorrectionDocumentsFinalizedOn(Date documentFinalDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(KFSConstants.DOCUMENT_HEADER_PROPERTY_NAME + "." + KFSPropertyConstants.DOCUMENT_FINAL_DATE, documentFinalDate);
        return entityManager.createQuery(QueryFactory.newQuery(GeneralLedgerCorrectionProcessDocument.class, criteria).getResultList());
    }
}
