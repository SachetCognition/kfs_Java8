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
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.document.RequisitionDocument;
import org.kuali.kfs.module.purap.document.dataaccess.RequisitionDao;
import org.kuali.kfs.sys.KFSConstants;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RequisitionDaoJpa implements RequisitionDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RequisitionDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String getDocumentNumberForRequisitionId(Integer id) {
        TypedQuery<RequisitionDocument> query = entityManager.createQuery(
            "SELECT r FROM RequisitionDocument r WHERE r.purapDocumentIdentifier = :id ORDER BY r.documentNumber ASC",
            RequisitionDocument.class);
        query.setParameter("id", id);
        List<RequisitionDocument> results = query.getResultList();
        if (results.isEmpty()) return null;
        if (results.size() > 1) {
            throw new RuntimeException("Expected single document number for given criteria but multiple were returned");
        }
        return results.get(0).getDocumentNumber();
    }

    @Override
    public List<String> getDocumentNumbersAwaitingContractManagerAssignment() {
        TypedQuery<RequisitionDocument> query = entityManager.createQuery(
            "SELECT r FROM RequisitionDocument r WHERE r.documentHeader.workflowDocumentStatusCode IN :statuses AND r.documentHeader.applicationDocumentStatus = :appStatus ORDER BY r.documentNumber DESC",
            RequisitionDocument.class);
        query.setParameter("statuses", Arrays.asList(KFSConstants.DocumentStatusCodes.FINAL, KFSConstants.DocumentStatusCodes.PROCESSED));
        query.setParameter("appStatus", PurapConstants.RequisitionStatuses.APPDOC_AWAIT_CONTRACT_MANAGER_ASSGN);
        List<String> result = new ArrayList<String>();
        for (RequisitionDocument doc : query.getResultList()) {
            result.add(doc.getDocumentNumber());
        }
        return result;
    }

    @Override
    public List<RequisitionDocument> getDocumentsAwaitingContractManagerAssignment() {
        TypedQuery<RequisitionDocument> query = entityManager.createQuery(
            "SELECT r FROM RequisitionDocument r WHERE r.documentHeader.workflowDocumentStatusCode IN :statuses AND r.documentHeader.applicationDocumentStatus = :appStatus",
            RequisitionDocument.class);
        query.setParameter("statuses", Arrays.asList(KFSConstants.DocumentStatusCodes.FINAL, KFSConstants.DocumentStatusCodes.PROCESSED));
        query.setParameter("appStatus", PurapConstants.RequisitionStatuses.APPDOC_AWAIT_CONTRACT_MANAGER_ASSGN);
        return query.getResultList();
    }
}
