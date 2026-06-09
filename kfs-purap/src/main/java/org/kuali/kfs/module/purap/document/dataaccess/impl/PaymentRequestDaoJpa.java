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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.PurapPropertyConstants;
import org.kuali.kfs.module.purap.document.PaymentRequestDocument;
import org.kuali.kfs.module.purap.document.dataaccess.PaymentRequestDao;
import org.kuali.kfs.module.purap.util.VendorGroupingHelper;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PaymentRequestDaoJpa implements PaymentRequestDao {

    private static Logger LOG = Logger.getLogger(PaymentRequestDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PaymentRequestDocument> getPaymentRequestsToExtract(boolean onlySpecialPayments, String chartCode, Date onOrBeforePaymentRequestPayDate) {
        LOG.debug("getPaymentRequestsToExtract() started");
        StringBuilder jpql = new StringBuilder("SELECT p FROM PaymentRequestDocument p WHERE p.extractedTimestamp IS NULL AND p.holdIndicator = false");
        if (chartCode != null) {
            jpql.append(" AND p.processingCampusCode = :chartCode");
        }
        if (onlySpecialPayments) {
            jpql.append(" AND (((p.specialHandlingInstructionLine1Text IS NOT NULL OR p.specialHandlingInstructionLine2Text IS NOT NULL OR p.specialHandlingInstructionLine3Text IS NOT NULL OR p.paymentAttachmentIndicator = true) AND p.paymentRequestPayDate <= :payDate) OR p.immediatePaymentIndicator = true)");
        } else {
            jpql.append(" AND (p.paymentRequestPayDate <= :payDate OR p.immediatePaymentIndicator = true)");
        }
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(jpql.toString(), PaymentRequestDocument.class);
        if (chartCode != null) {
            query.setParameter("chartCode", chartCode);
        }
        query.setParameter("payDate", onOrBeforePaymentRequestPayDate);
        return query.getResultList();
    }

    @Override
    public List<PaymentRequestDocument> getImmediatePaymentRequestsToExtract(String chartCode) {
        LOG.debug("getImmediatePaymentRequestsToExtract() started");
        StringBuilder jpql = new StringBuilder("SELECT p FROM PaymentRequestDocument p WHERE p.extractedTimestamp IS NULL AND p.immediatePaymentIndicator = true");
        if (chartCode != null) {
            jpql.append(" AND p.processingCampusCode = :chartCode");
        }
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(jpql.toString(), PaymentRequestDocument.class);
        if (chartCode != null) {
            query.setParameter("chartCode", chartCode);
        }
        return query.getResultList();
    }

    @Override
    @Deprecated
    public List<PaymentRequestDocument> getPaymentRequestsToExtract(String campusCode, Integer paymentRequestIdentifier, Integer purchaseOrderIdentifier, Integer vendorHeaderGeneratedIdentifier, Integer vendorDetailAssignedIdentifier, Date currentSqlDateMidnight) {
        LOG.debug("getPaymentRequestsToExtract() started");
        StringBuilder jpql = new StringBuilder("SELECT p FROM PaymentRequestDocument p WHERE p.processingCampusCode = :campus AND p.extractedTimestamp IS NULL AND p.holdIndicator = false");
        jpql.append(" AND (p.paymentRequestPayDate <= :payDate OR p.immediatePaymentIndicator = true)");
        jpql.append(" AND p.vendorHeaderGeneratedIdentifier = :vendorHeader AND p.vendorDetailAssignedIdentifier = :vendorDetail");
        if (paymentRequestIdentifier != null) {
            jpql.append(" AND p.purapDocumentIdentifier = :preqId");
        }
        if (purchaseOrderIdentifier != null) {
            jpql.append(" AND p.purchaseOrderIdentifier = :poId");
        }
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(jpql.toString(), PaymentRequestDocument.class);
        query.setParameter("campus", campusCode);
        query.setParameter("payDate", currentSqlDateMidnight);
        query.setParameter("vendorHeader", vendorHeaderGeneratedIdentifier);
        query.setParameter("vendorDetail", vendorDetailAssignedIdentifier);
        if (paymentRequestIdentifier != null) {
            query.setParameter("preqId", paymentRequestIdentifier);
        }
        if (purchaseOrderIdentifier != null) {
            query.setParameter("poId", purchaseOrderIdentifier);
        }
        return query.getResultList();
    }

    @Override
    public Collection<PaymentRequestDocument> getPaymentRequestsToExtractForVendor(String campusCode, VendorGroupingHelper vendor, Date onOrBeforePaymentRequestPayDate) {
        LOG.debug("getPaymentRequestsToExtract() started");
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.processingCampusCode = :campus AND p.extractedTimestamp IS NULL AND p.holdIndicator = false" +
            " AND (p.paymentRequestPayDate <= :payDate OR p.immediatePaymentIndicator = true)" +
            " AND p.vendorHeaderGeneratedIdentifier = :vendorHeader AND p.vendorDetailAssignedIdentifier = :vendorDetail" +
            " AND p.vendorCountryCode = :country AND p.vendorPostalCode LIKE :postal",
            PaymentRequestDocument.class);
        query.setParameter("campus", campusCode);
        query.setParameter("payDate", onOrBeforePaymentRequestPayDate);
        query.setParameter("vendorHeader", vendor.getVendorHeaderGeneratedIdentifier());
        query.setParameter("vendorDetail", vendor.getVendorDetailAssignedIdentifier());
        query.setParameter("country", vendor.getVendorCountry());
        query.setParameter("postal", vendor.getVendorPostalCode() + "%");
        return query.getResultList();
    }

    @Override
    public List<String> getEligibleForAutoApproval(Date todayAtMidnight) {
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.paymentRequestPayDate <= :payDate AND p.holdIndicator = false AND p.paymentRequestedCancelIndicator = false" +
            " AND p.documentHeader.applicationDocumentStatus IN :statuses",
            PaymentRequestDocument.class);
        query.setParameter("payDate", todayAtMidnight);
        query.setParameter("statuses", Arrays.asList(PurapConstants.PaymentRequestStatuses.PREQ_STATUSES_FOR_AUTO_APPROVE));
        List<String> result = new ArrayList<String>();
        for (PaymentRequestDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public String getDocumentNumberByPaymentRequestId(Integer id) {
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.purapDocumentIdentifier = :id ORDER BY p.documentNumber DESC",
            PaymentRequestDocument.class);
        query.setParameter("id", id);
        List<PaymentRequestDocument> results = query.getResultList();
        if (results.isEmpty()) return null;
        if (results.size() > 1) {
            throw new RuntimeException("Expected single document number for given criteria but multiple were returned");
        }
        return results.get(0).getDocumentNumber();
    }

    @Override
    public List<String> getDocumentNumbersByPurchaseOrderId(Integer poPurApId) {
        TypedQuery<PaymentRequestDocument> query = entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.purchaseOrderIdentifier = :poId ORDER BY p.documentNumber DESC",
            PaymentRequestDocument.class);
        query.setParameter("poId", poPurApId);
        List<String> result = new ArrayList<String>();
        for (PaymentRequestDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<PaymentRequestDocument> getActivePaymentRequestsByVendorNumberInvoiceNumber(Integer vendorHeaderGeneratedId, Integer vendorDetailAssignedId, String invoiceNumber) {
        LOG.debug("getActivePaymentRequestsByVendorNumberInvoiceNumber() started");
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.vendorHeaderGeneratedIdentifier = :vh AND p.vendorDetailAssignedIdentifier = :vd AND p.invoiceNumber = :inv",
            PaymentRequestDocument.class)
            .setParameter("vh", vendorHeaderGeneratedId)
            .setParameter("vd", vendorDetailAssignedId)
            .setParameter("inv", invoiceNumber)
            .getResultList();
    }

    @Override
    public List<PaymentRequestDocument> getActivePaymentRequestsByVendorNumber(Integer vendorHeaderGeneratedId, Integer vendorDetailAssignedId) {
        LOG.debug("getActivePaymentRequestsByVendorNumber started");
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.vendorHeaderGeneratedIdentifier = :vh AND p.vendorDetailAssignedIdentifier = :vd",
            PaymentRequestDocument.class)
            .setParameter("vh", vendorHeaderGeneratedId)
            .setParameter("vd", vendorDetailAssignedId)
            .getResultList();
    }

    @Override
    public List<PaymentRequestDocument> getActivePaymentRequestsByPOIdInvoiceAmountInvoiceDate(Integer poId, KualiDecimal vendorInvoiceAmount, Date invoiceDate) {
        LOG.debug("getActivePaymentRequestsByPOIdInvoiceAmountInvoiceDate() started");
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.purchaseOrderIdentifier = :poId AND p.vendorInvoiceAmount = :amount AND p.invoiceDate = :date",
            PaymentRequestDocument.class)
            .setParameter("poId", poId)
            .setParameter("amount", vendorInvoiceAmount)
            .setParameter("date", invoiceDate)
            .getResultList();
    }

    @Override
    public List<PaymentRequestDocument> getActivePaymentRequestDocumentNumbersForPurchaseOrder(Integer purchaseOrderId) {
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.purchaseOrderIdentifier = :poId",
            PaymentRequestDocument.class)
            .setParameter("poId", purchaseOrderId)
            .getResultList();
    }

    @Override
    public List<PaymentRequestDocument> getPaymentRequestInReceivingStatus() {
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.holdIndicator = false AND p.paymentRequestedCancelIndicator = false",
            PaymentRequestDocument.class)
            .getResultList();
    }
}
