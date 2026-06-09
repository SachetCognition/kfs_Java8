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
package org.kuali.kfs.module.purap.document.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.PurapPropertyConstants;
import org.kuali.kfs.module.purap.businessobject.AutoClosePurchaseOrderView;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.dataaccess.PurchaseOrderDao;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PurchaseOrderDaoJpa implements PurchaseOrderDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchaseOrderDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Integer getPurchaseOrderIdForCurrentPurchaseOrderByRelatedDocId(Integer accountsPayablePurchasingDocumentLinkIdentifier) {
        TypedQuery<PurchaseOrderDocument> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p WHERE p.accountsPayablePurchasingDocumentLinkIdentifier = :linkId AND p.purchaseOrderCurrentIndicator = true",
            PurchaseOrderDocument.class);
        query.setParameter("linkId", accountsPayablePurchasingDocumentLinkIdentifier);
        List<PurchaseOrderDocument> results = query.getResultList();
        for (PurchaseOrderDocument po : results) {
            return po.getPurapDocumentIdentifier();
        }
        return null;
    }

    @Override
    public PurchaseOrderDocument getCurrentPurchaseOrder(Integer id) {
        TypedQuery<PurchaseOrderDocument> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p WHERE p.purapDocumentIdentifier = :id AND p.purchaseOrderCurrentIndicator = true",
            PurchaseOrderDocument.class);
        query.setParameter("id", id);
        List<PurchaseOrderDocument> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public String getDocumentNumberForPurchaseOrderId(Integer id) {
        TypedQuery<PurchaseOrderDocument> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p WHERE p.purapDocumentIdentifier = :id ORDER BY p.documentNumber ASC",
            PurchaseOrderDocument.class);
        query.setParameter("id", id);
        List<PurchaseOrderDocument> results = query.getResultList();
        if (results.isEmpty()) return null;
        if (results.size() > 1) {
            throw new RuntimeException("Expected single document number for given criteria but multiple were returned");
        }
        return results.get(0).getDocumentNumber();
    }

    @Override
    public String getDocumentNumberForCurrentPurchaseOrder(Integer id) {
        TypedQuery<PurchaseOrderDocument> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p WHERE p.purapDocumentIdentifier = :id AND p.purchaseOrderCurrentIndicator = true ORDER BY p.documentNumber ASC",
            PurchaseOrderDocument.class);
        query.setParameter("id", id);
        List<PurchaseOrderDocument> results = query.getResultList();
        if (results.isEmpty()) return null;
        if (results.size() > 1) {
            throw new RuntimeException("Expected single document number for given criteria but multiple were returned");
        }
        return results.get(0).getDocumentNumber();
    }

    @Override
    public String getOldestPurchaseOrderDocumentNumber(Integer id) {
        TypedQuery<PurchaseOrderDocument> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p WHERE p.purapDocumentIdentifier = :id ORDER BY p.documentHeader.workflowCreateDate ASC",
            PurchaseOrderDocument.class);
        query.setParameter("id", id);
        query.setMaxResults(1);
        List<PurchaseOrderDocument> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0).getDocumentNumber();
    }

    @Override
    public boolean itemExistsOnPurchaseOrder(Integer poItemLineNumber, String docNumber) {
        TypedQuery<PurchaseOrderItem> query = entityManager.createQuery(
            "SELECT i FROM PurchaseOrderItem i WHERE i.documentNumber = :docNum AND i.itemLineNumber = :lineNum",
            PurchaseOrderItem.class);
        query.setParameter("docNum", docNumber);
        query.setParameter("lineNum", poItemLineNumber);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<AutoClosePurchaseOrderView> getAllOpenPurchaseOrders(List<String> excludedVendorChoiceCodes) {
        LOG.debug("getAllOpenPurchaseOrders() started");
        StringBuilder jpql = new StringBuilder(
            "SELECT a FROM AutoClosePurchaseOrderView a WHERE a.recurringPaymentTypeCode IS NULL AND a.totalEncumbrance = :zero AND a.purchaseOrderCurrentIndicator = true");
        for (int i = 0; i < excludedVendorChoiceCodes.size(); i++) {
            jpql.append(" AND a.vendorChoiceCode != :excl").append(i);
        }
        TypedQuery<AutoClosePurchaseOrderView> query = entityManager.createQuery(jpql.toString(), AutoClosePurchaseOrderView.class);
        query.setParameter("zero", new KualiDecimal(0));
        for (int i = 0; i < excludedVendorChoiceCodes.size(); i++) {
            query.setParameter("excl" + i, excludedVendorChoiceCodes.get(i));
        }
        return query.getResultList();
    }

    @Override
    public List<AutoClosePurchaseOrderView> getAutoCloseRecurringPurchaseOrders(List<String> excludedVendorChoiceCodes) {
        LOG.debug("getAutoCloseRecurringPurchaseOrders() started");
        StringBuilder jpql = new StringBuilder(
            "SELECT a FROM AutoClosePurchaseOrderView a WHERE a.recurringPaymentTypeCode IS NOT NULL AND a.totalEncumbrance = :zero AND a.purchaseOrderCurrentIndicator = true");
        for (int i = 0; i < excludedVendorChoiceCodes.size(); i++) {
            jpql.append(" AND a.vendorChoiceCode != :excl").append(i);
        }
        TypedQuery<AutoClosePurchaseOrderView> query = entityManager.createQuery(jpql.toString(), AutoClosePurchaseOrderView.class);
        query.setParameter("zero", new KualiDecimal(0));
        for (int i = 0; i < excludedVendorChoiceCodes.size(); i++) {
            query.setParameter("excl" + i, excludedVendorChoiceCodes.get(i));
        }
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrderDocument> getPendingPurchaseOrdersForFaxing() {
        LOG.debug("Getting pending purchase orders for faxing");
        return entityManager.createQuery(
            "SELECT p FROM PurchaseOrderDocument p", PurchaseOrderDocument.class)
            .getResultList();
    }
}
