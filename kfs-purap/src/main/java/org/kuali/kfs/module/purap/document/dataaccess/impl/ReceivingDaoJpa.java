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

import org.kuali.kfs.module.purap.document.CorrectionReceivingDocument;
import org.kuali.kfs.module.purap.document.LineItemReceivingDocument;
import org.kuali.kfs.module.purap.document.dataaccess.ReceivingDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ReceivingDaoJpa implements ReceivingDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReceivingDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> getDocumentNumbersByPurchaseOrderId(Integer id) {
        TypedQuery<LineItemReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM LineItemReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId ORDER BY d.documentNumber DESC",
            LineItemReceivingDocument.class);
        query.setParameter("poId", id);
        List<String> result = new ArrayList<String>();
        for (LineItemReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> getCorrectionReceivingDocumentNumbersByPurchaseOrderId(Integer id) {
        TypedQuery<CorrectionReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM CorrectionReceivingDocument d WHERE d.lineItemReceivingDocument.purchaseOrderIdentifier = :poId ORDER BY d.documentNumber DESC",
            CorrectionReceivingDocument.class);
        query.setParameter("poId", id);
        List<String> result = new ArrayList<String>();
        for (CorrectionReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> getCorrectionReceivingDocumentNumbersByReceivingLineNumber(String receivingDocumentNumber) {
        TypedQuery<CorrectionReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM CorrectionReceivingDocument d WHERE d.lineItemReceivingDocumentNumber = :docNum ORDER BY d.documentNumber DESC",
            CorrectionReceivingDocument.class);
        query.setParameter("docNum", receivingDocumentNumber);
        List<String> result = new ArrayList<String>();
        for (CorrectionReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> duplicateBillOfLadingNumber(Integer poId, String billOfLadingNumber) {
        TypedQuery<LineItemReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM LineItemReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentBillOfLadingNumber = :bol ORDER BY d.documentNumber DESC",
            LineItemReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("bol", billOfLadingNumber);
        List<String> result = new ArrayList<String>();
        for (LineItemReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> duplicatePackingSlipNumber(Integer poId, String packingSlipNumber) {
        TypedQuery<LineItemReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM LineItemReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentPackingSlipNumber = :psl ORDER BY d.documentNumber DESC",
            LineItemReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("psl", packingSlipNumber);
        List<String> result = new ArrayList<String>();
        for (LineItemReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> duplicateVendorDate(Integer poId, java.sql.Date vendorDate) {
        TypedQuery<LineItemReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM LineItemReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentReceivedDate = :dt ORDER BY d.documentNumber DESC",
            LineItemReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("dt", vendorDate);
        List<String> result = new ArrayList<String>();
        for (LineItemReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }
}
