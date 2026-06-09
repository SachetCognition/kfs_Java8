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
package org.kuali.kfs.module.cab.dataaccess.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.cab.CabConstants;
import org.kuali.kfs.module.cab.CabPropertyConstants;
import org.kuali.kfs.module.cab.businessobject.GeneralLedgerEntry;
import org.kuali.kfs.module.cab.businessobject.PurchasingAccountsPayableDocument;
import org.kuali.kfs.module.cab.dataaccess.PurchasingAccountsPayableReportDao;
import org.kuali.kfs.sys.KFSConstants;

public class PurchasingAccountsPayableReportDaoJpa implements PurchasingAccountsPayableReportDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchasingAccountsPayableReportDaoJpa.class);

    private EntityManager entityManager;

    @Override
    public Collection findPurchasingAccountsPayableDocuments(Map fieldValues) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PurchasingAccountsPayableDocument> cq = cb.createQuery(PurchasingAccountsPayableDocument.class);
        Root<PurchasingAccountsPayableDocument> root = cq.from(PurchasingAccountsPayableDocument.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Object entryObj : fieldValues.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                predicates.add(cb.equal(root.get(key), value));
            }
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Iterator findGeneralLedgers(Map fieldValues) {
        LOG.debug("findGeneralLedgers started...");
        Collection docTypeCodes = getDocumentType(fieldValues);
        Collection activityStatusCodes = getActivityStatusCode(fieldValues);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GeneralLedgerEntry> cq = cb.createQuery(GeneralLedgerEntry.class);
        Root<GeneralLedgerEntry> root = cq.from(GeneralLedgerEntry.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Object entryObj : fieldValues.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                predicates.add(cb.equal(root.get(key), value));
            }
        }

        if (!docTypeCodes.isEmpty()) {
            predicates.add(root.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_DOCUMENT_TYPE_CODE).in(docTypeCodes));
        }
        if (!activityStatusCodes.isEmpty()) {
            predicates.add(root.get(CabPropertyConstants.GeneralLedgerEntry.ACTIVITY_STATUS_CODE).in(activityStatusCodes));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList().iterator();
    }

    protected Collection getActivityStatusCode(Map fieldValues) {
        Collection activityStatusCodes = new ArrayList<String>();
        if (fieldValues.containsKey(CabPropertyConstants.GeneralLedgerEntry.ACTIVITY_STATUS_CODE)) {
            String fieldValue = (String) fieldValues.get(CabPropertyConstants.GeneralLedgerEntry.ACTIVITY_STATUS_CODE);
            if (KFSConstants.NON_ACTIVE_INDICATOR.equalsIgnoreCase(fieldValue)) {
                activityStatusCodes.add(CabConstants.ActivityStatusCode.NEW);
                activityStatusCodes.add(CabConstants.ActivityStatusCode.MODIFIED);
            } else if (KFSConstants.ACTIVE_INDICATOR.equalsIgnoreCase(fieldValue)) {
                activityStatusCodes.add(CabConstants.ActivityStatusCode.PROCESSED_IN_CAMS);
                activityStatusCodes.add(CabConstants.ActivityStatusCode.ENROUTE);
            }
            fieldValues.remove(CabPropertyConstants.GeneralLedgerEntry.ACTIVITY_STATUS_CODE);
        }
        return activityStatusCodes;
    }

    protected Collection getDocumentType(Map fieldValues) {
        Collection docTypeCodes = new ArrayList<String>();
        if (fieldValues.containsKey(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_DOCUMENT_TYPE_CODE)) {
            String fieldValue = (String) fieldValues.get(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_DOCUMENT_TYPE_CODE);
            if (StringUtils.isNotBlank(fieldValue)) {
                docTypeCodes.add(fieldValue);
            }
            fieldValues.remove(CabPropertyConstants.GeneralLedgerEntry.FINANCIAL_DOCUMENT_TYPE_CODE);
        }
        return docTypeCodes;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
