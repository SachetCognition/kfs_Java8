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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.document.BulkReceivingDocument;
import org.kuali.kfs.module.purap.document.dataaccess.BulkReceivingDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BulkReceivingDaoJpa implements BulkReceivingDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BulkReceivingDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> getDocumentNumbersByPurchaseOrderId(Integer id) {
        return getDocNumbers("SELECT d FROM BulkReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId ORDER BY d.documentNumber DESC", "poId", id);
    }

    @Override
    public List<String> duplicateBillOfLadingNumber(Integer poId, String billOfLadingNumber) {
        TypedQuery<BulkReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM BulkReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentBillOfLadingNumber = :bol ORDER BY d.documentNumber DESC",
            BulkReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("bol", billOfLadingNumber);
        List<String> result = new ArrayList<String>();
        for (BulkReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> duplicatePackingSlipNumber(Integer poId, String packingSlipNumber) {
        TypedQuery<BulkReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM BulkReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentPackingSlipNumber = :psl ORDER BY d.documentNumber DESC",
            BulkReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("psl", packingSlipNumber);
        List<String> result = new ArrayList<String>();
        for (BulkReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<String> duplicateVendorDate(Integer poId, Date vendorDate) {
        TypedQuery<BulkReceivingDocument> query = entityManager.createQuery(
            "SELECT d FROM BulkReceivingDocument d WHERE d.purchaseOrderIdentifier = :poId AND d.shipmentReceivedDate = :dt ORDER BY d.documentNumber DESC",
            BulkReceivingDocument.class);
        query.setParameter("poId", poId);
        query.setParameter("dt", vendorDate);
        List<String> result = new ArrayList<String>();
        for (BulkReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    private List<String> getDocNumbers(String jpql, String paramName, Object paramValue) {
        TypedQuery<BulkReceivingDocument> query = entityManager.createQuery(jpql, BulkReceivingDocument.class);
        query.setParameter(paramName, paramValue);
        List<String> result = new ArrayList<String>();
        for (BulkReceivingDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }
}
