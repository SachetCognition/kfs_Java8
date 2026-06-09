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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.businessobject.ElectronicInvoiceItemMapping;
import org.kuali.kfs.module.purap.businessobject.ItemType;
import org.kuali.kfs.module.purap.dataaccess.ElectronicInvoiceItemMappingDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ElectronicInvoiceItemMappingDaoJpa implements ElectronicInvoiceItemMappingDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ElectronicInvoiceItemMappingDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List getAll() {
        LOG.debug("getAll() started");
        return entityManager.createQuery(
            "SELECT e FROM ElectronicInvoiceItemMapping e ORDER BY e.invoiceMapIdentifier", ElectronicInvoiceItemMapping.class)
            .getResultList();
    }

    @Override
    public ElectronicInvoiceItemMapping getByUniqueKeys(Integer headerId, Integer detailId, String invoiceTypeCode) {
        LOG.debug("getByUniqueKeys() started");
        TypedQuery<ElectronicInvoiceItemMapping> query = entityManager.createQuery(
            "SELECT e FROM ElectronicInvoiceItemMapping e WHERE e.vendorHeaderGeneratedIdentifier = :headerId AND e.vendorDetailAssignedIdentifier = :detailId AND e.invoiceItemTypeCode = :typeCode",
            ElectronicInvoiceItemMapping.class);
        query.setParameter("headerId", headerId);
        query.setParameter("detailId", detailId);
        query.setParameter("typeCode", invoiceTypeCode);
        List<ElectronicInvoiceItemMapping> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List getAllItemTypes() {
        LOG.debug("getAllItemTypes() started");
        return entityManager.createQuery(
            "SELECT i FROM ItemType i WHERE i.active = true ORDER BY i.itemTypeCode", ItemType.class)
            .getResultList();
    }

    @Override
    public ItemType getItemTypeByCode(String code) {
        LOG.debug("getItemTypeByCode() started");
        TypedQuery<ItemType> query = entityManager.createQuery(
            "SELECT i FROM ItemType i WHERE i.itemTypeCode = :code", ItemType.class);
        query.setParameter("code", code);
        List<ItemType> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public ElectronicInvoiceItemMapping getById(String id) {
        LOG.debug("getById() started");
        return entityManager.find(ElectronicInvoiceItemMapping.class, id);
    }

    @Override
    public void delete(ElectronicInvoiceItemMapping row) {
        LOG.debug("delete() started");
        if (entityManager.contains(row)) {
            entityManager.remove(row);
        } else {
            ElectronicInvoiceItemMapping merged = entityManager.merge(row);
            entityManager.remove(merged);
        }
    }
}
