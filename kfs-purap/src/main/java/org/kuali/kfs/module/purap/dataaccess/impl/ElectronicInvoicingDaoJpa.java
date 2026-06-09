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
package org.kuali.kfs.module.purap.dataaccess.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceItemMapping;
import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceLoadSummary;
import org.kuali.kfs.module.purap.dataaccess.ElectronicInvoicingDao;
import org.kuali.kfs.module.purap.document.PaymentRequestDocument;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ElectronicInvoicingDaoJpa implements ElectronicInvoicingDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ElectronicInvoicingDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ElectronicInvoiceLoadSummary getElectronicInvoiceLoadSummary(Integer loadId, String vendorDunsNumber) {
        LOG.debug("getElectronicInvoiceLoadSummary() started");
        TypedQuery<ElectronicInvoiceLoadSummary> query = entityManager.createQuery(
            "SELECT e FROM ElectronicInvoiceLoadSummary e WHERE e.id = :loadId AND e.vendorDunsNumber = :duns",
            ElectronicInvoiceLoadSummary.class);
        query.setParameter("loadId", loadId);
        query.setParameter("duns", vendorDunsNumber);
        List<ElectronicInvoiceLoadSummary> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List getPendingElectronicInvoices() {
        LOG.debug("getPendingElectronicInvoices() started");
        return entityManager.createQuery(
            "SELECT p FROM PaymentRequestDocument p WHERE p.applicationDocumentStatus = :status AND p.isElectronicInvoice = true",
            PaymentRequestDocument.class)
            .setParameter("status", PurapConstants.PaymentRequestStatuses.APPDOC_PENDING_E_INVOICE)
            .getResultList();
    }

    @Override
    public Map getDefaultItemMappingMap() {
        LOG.debug("getDefaultItemMappingMap() started");
        List<ElectronicInvoiceItemMapping> mappings = entityManager.createQuery(
            "SELECT e FROM ElectronicInvoiceItemMapping e WHERE e.vendorHeaderGeneratedIdentifier IS NULL AND e.vendorDetailAssignedIdentifier IS NULL AND e.active = true",
            ElectronicInvoiceItemMapping.class)
            .getResultList();
        return buildMappingMap(mappings);
    }

    @Override
    public Map getItemMappingMap(Integer vendorHeaderId, Integer vendorDetailId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getItemMappingMap() started for vendor id " + vendorHeaderId + "-" + vendorDetailId);
        }
        List<ElectronicInvoiceItemMapping> mappings = entityManager.createQuery(
            "SELECT e FROM ElectronicInvoiceItemMapping e WHERE e.vendorHeaderGeneratedIdentifier = :headerId AND e.vendorDetailAssignedIdentifier = :detailId AND e.active = true",
            ElectronicInvoiceItemMapping.class)
            .setParameter("headerId", vendorHeaderId)
            .setParameter("detailId", vendorDetailId)
            .getResultList();
        return buildMappingMap(mappings);
    }

    protected Map buildMappingMap(List<ElectronicInvoiceItemMapping> mappings) {
        Map hm = new HashMap();
        for (ElectronicInvoiceItemMapping mapping : mappings) {
            hm.put(mapping.getInvoiceItemTypeCode(), mapping);
        }
        return hm;
    }
}
