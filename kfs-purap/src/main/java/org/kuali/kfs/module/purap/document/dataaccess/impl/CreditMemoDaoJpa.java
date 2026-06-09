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
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.document.VendorCreditMemoDocument;
import org.kuali.kfs.module.purap.document.dataaccess.CreditMemoDao;
import org.kuali.kfs.module.purap.util.VendorGroupingHelper;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreditMemoDaoJpa implements CreditMemoDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CreditMemoDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<VendorCreditMemoDocument> getCreditMemosToExtract(String chartCode) {
        LOG.debug("getCreditMemosToExtract() started");
        return entityManager.createQuery(
            "SELECT c FROM VendorCreditMemoDocument c WHERE c.processingCampusCode = :chart AND c.extractedTimestamp IS NULL AND c.holdIndicator = false",
            VendorCreditMemoDocument.class)
            .setParameter("chart", chartCode)
            .getResultList();
    }

    @Override
    public Collection<VendorCreditMemoDocument> getCreditMemosToExtractByVendor(String chartCode, VendorGroupingHelper vendor) {
        LOG.debug("getCreditMemosToExtractByVendor() started");
        return entityManager.createQuery(
            "SELECT c FROM VendorCreditMemoDocument c WHERE c.processingCampusCode = :chart AND c.extractedTimestamp IS NULL AND c.holdIndicator = false" +
            " AND c.vendorHeaderGeneratedIdentifier = :vh AND c.vendorDetailAssignedIdentifier = :vd" +
            " AND c.vendorCountryCode = :country AND c.vendorPostalCode LIKE :postal",
            VendorCreditMemoDocument.class)
            .setParameter("chart", chartCode)
            .setParameter("vh", vendor.getVendorHeaderGeneratedIdentifier())
            .setParameter("vd", vendor.getVendorDetailAssignedIdentifier())
            .setParameter("country", vendor.getVendorCountry())
            .setParameter("postal", vendor.getVendorPostalCode() + "%")
            .getResultList();
    }

    @Override
    public boolean duplicateExists(Integer vendorNumberHeaderId, Integer vendorNumberDetailId, String creditMemoNumber) {
        LOG.debug("duplicateExists() started");
        Long count = entityManager.createQuery(
            "SELECT COUNT(c) FROM VendorCreditMemoDocument c WHERE c.vendorHeaderGeneratedIdentifier = :vh AND c.vendorDetailAssignedIdentifier = :vd AND c.creditMemoNumber = :num",
            Long.class)
            .setParameter("vh", vendorNumberHeaderId)
            .setParameter("vd", vendorNumberDetailId)
            .setParameter("num", creditMemoNumber)
            .getSingleResult();
        return count > 0;
    }

    @Override
    public String getDocumentNumberByCreditMemoId(Integer id) {
        TypedQuery<VendorCreditMemoDocument> query = entityManager.createQuery(
            "SELECT c FROM VendorCreditMemoDocument c WHERE c.purapDocumentIdentifier = :id ORDER BY c.documentNumber DESC",
            VendorCreditMemoDocument.class);
        query.setParameter("id", id);
        List<VendorCreditMemoDocument> results = query.getResultList();
        if (results.isEmpty()) return null;
        if (results.size() > 1) {
            throw new RuntimeException("Expected single document number for given criteria but multiple were returned");
        }
        return results.get(0).getDocumentNumber();
    }

    @Override
    public boolean duplicateExists(Integer vendorNumberHeaderId, Integer vendorNumberDetailId, Date date, KualiDecimal amount) {
        LOG.debug("duplicateExists() started");
        Long count = entityManager.createQuery(
            "SELECT COUNT(c) FROM VendorCreditMemoDocument c WHERE c.vendorHeaderGeneratedIdentifier = :vh AND c.vendorDetailAssignedIdentifier = :vd AND c.creditMemoDate = :dt AND c.creditMemoAmount = :amt",
            Long.class)
            .setParameter("vh", vendorNumberHeaderId)
            .setParameter("vd", vendorNumberDetailId)
            .setParameter("dt", date)
            .setParameter("amt", amount)
            .getSingleResult();
        return count > 0;
    }

    @Override
    public List<String> getActiveCreditMemoDocumentNumbersForPurchaseOrder(Integer purchaseOrderId) {
        LOG.debug("getActiveCreditMemoDocumentNumbersForPurchaseOrder() started");
        TypedQuery<VendorCreditMemoDocument> query = entityManager.createQuery(
            "SELECT c FROM VendorCreditMemoDocument c WHERE c.purchaseOrderIdentifier = :poId ORDER BY c.documentNumber DESC",
            VendorCreditMemoDocument.class);
        query.setParameter("poId", purchaseOrderId);
        List<String> result = new ArrayList<String>();
        for (VendorCreditMemoDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }
}
