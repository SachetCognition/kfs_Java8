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
package org.kuali.kfs.module.ar.document.dataaccess.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.ar.document.CashControlDocument;
import org.kuali.kfs.module.ar.document.dataaccess.CashControlDocumentDao;

import java.util.ArrayList;

public class CashControlDocumentDaoJpa implements CashControlDocumentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CashControlDocument getCashControlDocument(Map fieldValues) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CashControlDocument> cq = cb.createQuery(CashControlDocument.class);
        Root<CashControlDocument> root = cq.from(CashControlDocument.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Object key : fieldValues.keySet()) {
            Object value = fieldValues.get(key);
            if (value != null) {
                predicates.add(cb.equal(root.get((String) key), value));
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<CashControlDocument> query = entityManager.createQuery(cq);
        query.setMaxResults(1);
        List<CashControlDocument> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
