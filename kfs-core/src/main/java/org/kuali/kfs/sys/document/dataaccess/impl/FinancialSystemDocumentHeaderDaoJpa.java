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
package org.kuali.kfs.sys.document.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import org.kuali.kfs.sys.document.dataaccess.FinancialSystemDocumentHeaderDao;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.dao.impl.DocumentHeaderDaoOjb;

/**
 * This class is the financial system document header dao ojb implementation
 */
public class FinancialSystemDocumentHeaderDaoJpa extends DocumentHeaderDaoOjb implements FinancialSystemDocumentHeaderDao {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.dao.DocumentHeaderDao#getCorrectingDocumentHeader(java.lang.Long)
     */
    public DocumentHeader getCorrectingDocumentHeader(String documentId) {
        Criteria correctedByCriteria = new Criteria();
        correctedByCriteria.addEqualTo("financialDocumentInErrorNumber", documentId);

        return (DocumentHeader) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(getDocumentHeaderBaseClass(), correctedByCriteria));
    }

}
