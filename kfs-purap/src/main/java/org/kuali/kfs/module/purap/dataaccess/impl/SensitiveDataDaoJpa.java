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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.businessobject.PurchaseOrderSensitiveData;
import org.kuali.kfs.module.purap.businessobject.SensitiveData;
import org.kuali.kfs.module.purap.businessobject.SensitiveDataAssignment;
import org.kuali.kfs.module.purap.dataaccess.SensitiveDataDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SensitiveDataDaoJpa implements SensitiveDataDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SensitiveDataDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<SensitiveData> getSensitiveDatasAssignedByPoId(Integer poId) {
        LOG.debug("getSensitiveDatasAssignedByPoId(Integer) started");
        TypedQuery<PurchaseOrderSensitiveData> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderSensitiveData p WHERE p.purapDocumentIdentifier = :poId",
            PurchaseOrderSensitiveData.class);
        query.setParameter("poId", poId);
        List<PurchaseOrderSensitiveData> posdList = query.getResultList();
        List<SensitiveData> sdList = new ArrayList<SensitiveData>();
        for (PurchaseOrderSensitiveData posd : posdList) {
            sdList.add(posd.getSensitiveData());
        }
        return sdList;
    }

    @Override
    public List<SensitiveData> getSensitiveDatasAssignedByReqId(Integer reqId) {
        LOG.debug("getSensitiveDatasAssignedByReqId(Integer) started");
        TypedQuery<PurchaseOrderSensitiveData> query = entityManager.createQuery(
            "SELECT p FROM PurchaseOrderSensitiveData p WHERE p.requisitionIdentifier = :reqId",
            PurchaseOrderSensitiveData.class);
        query.setParameter("reqId", reqId);
        List<PurchaseOrderSensitiveData> posdList = query.getResultList();
        List<SensitiveData> sdList = new ArrayList<SensitiveData>();
        for (PurchaseOrderSensitiveData posd : posdList) {
            sdList.add(posd.getSensitiveData());
        }
        return sdList;
    }

    @Override
    public void deletePurchaseOrderSensitiveDatas(Integer poId) {
        LOG.debug("deletePurchaseOrderSensitiveDatas(Integer) started");
        entityManager.createQuery(
            "DELETE FROM PurchaseOrderSensitiveData p WHERE p.purapDocumentIdentifier = :poId")
            .setParameter("poId", poId)
            .executeUpdate();
    }

    @Override
    public SensitiveDataAssignment getLastSensitiveDataAssignment(Integer poId) {
        LOG.debug("getLastSensitiveDataAssignment(Integer) started");
        TypedQuery<SensitiveDataAssignment> query = entityManager.createQuery(
            "SELECT s FROM SensitiveDataAssignment s WHERE s.purapDocumentIdentifier = :poId ORDER BY s.sensitiveDataAssignmentIdentifier DESC",
            SensitiveDataAssignment.class);
        query.setParameter("poId", poId);
        query.setMaxResults(1);
        List<SensitiveDataAssignment> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
