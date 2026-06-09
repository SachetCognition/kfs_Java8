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
package org.kuali.kfs.sys.batch.dataaccess.impl;

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

import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.batch.dataaccess.FinancialSystemDocumentHeaderPopulationDao;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeader;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeaderMissingFromWorkflow;

/**
 * Base implementation of the FinancialSystemDocumentHeaderPopulationDao DAO
 */
public class FinancialSystemDocumentHeaderPopulationDaoJpa extends org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria implements FinancialSystemDocumentHeaderPopulationDao {
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
     *
     * @see org.kuali.kfs.sys.batch.dataaccess.FinancialSystemDocumentHeaderPopulationDao#countTotalFinancialSystemDocumentHeadersToProcess()
     */
    @Override
    public int countTotalFinancialSystemDocumentHeadersToProcess() {
        Criteria c = buildFinancialSystemDocumentHeaderCriteria();
        QueryByCriteria query = QueryFactory.newQuery(FinancialSystemDocumentHeader.class, c);
        final int numOfFSDocHeaders = entityManager.createQuery(query).getResultList().size();
        return numOfFSDocHeaders;
    }

    /**
     * Reads in a set of FinancialSystemDocumentHeader records, limited by the indices, ordered by document number
     * @see org.kuali.kfs.sys.batch.dataaccess.FinancialSystemDocumentHeaderPopulationDao#getFinancialSystemDocumentHeadersForBatch(int, int)
     */
    @Override
    public Collection<FinancialSystemDocumentHeader> getFinancialSystemDocumentHeadersForBatch(int batchStartIndex, int batchEndIndex) {
        Criteria c = buildFinancialSystemDocumentHeaderCriteria();
        QueryByCriteria query = QueryFactory.newQuery(FinancialSystemDocumentHeader.class, c);
        query.setStartAtIndex(batchStartIndex);
        query.setEndAtIndex(batchEndIndex);
        query.addOrderByDescending(KFSPropertyConstants.DOCUMENT_NUMBER); // roughly try to process newer documents first

        Collection<FinancialSystemDocumentHeader> documentHeaders = entityManager.createQuery(query).getResultList();
        return documentHeaders;
    }

    /**
     * @return the criteria which will be used to variously find all remaining-to-be-processed FinancialSystemDocumentHeader records
     */
    protected Criteria buildFinancialSystemDocumentHeaderCriteria() {
        Criteria c = new Criteria();
        c.addIsNull(KFSPropertyConstants.INITIATOR_PRINCIPAL_ID);
        c.addNotIn(KFSPropertyConstants.DOCUMENT_NUMBER, buildFinancialSystemDocumentHeadersWithNoWorkflowHeadersSubQuery());
        return c;
    }

    /**
     * @return a query which returns all data objects of class FinancialSystemDocumentHeaderMissingFromWorkflow
     */
    protected Query buildFinancialSystemDocumentHeadersWithNoWorkflowHeadersSubQuery() {
        ReportQueryByCriteria query = QueryFactory.newReportQuery(FinancialSystemDocumentHeaderMissingFromWorkflow.class, new Criteria());
        query.setAttributes(new String[] { KFSPropertyConstants.DOCUMENT_NUMBER });
        return query;
    }
}
