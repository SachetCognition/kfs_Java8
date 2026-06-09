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
package org.kuali.kfs.module.tem.dataaccess.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.tem.dataaccess.TravelAuthorizationDao;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.sys.KFSConstants.DocumentStatusCodes;

public class TravelAuthorizationDaoJpa implements TravelAuthorizationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TravelAuthorizationDocument> findTravelAuthorizationByTraveler(Integer temProfileId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TravelAuthorizationDocument> cq = cb.createQuery(TravelAuthorizationDocument.class);
        Root<TravelAuthorizationDocument> root = cq.from(TravelAuthorizationDocument.class);

        cq.where(
            cb.equal(root.get("temProfileId"), temProfileId),
            cb.not(root.get("documentHeader").get("financialDocumentStatusCode").in(Arrays.asList(DocumentStatusCodes.INITIATED)))
        );

        TypedQuery<TravelAuthorizationDocument> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
