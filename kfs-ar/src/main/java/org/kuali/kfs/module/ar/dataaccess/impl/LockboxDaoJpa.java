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
package org.kuali.kfs.module.ar.dataaccess.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.kuali.kfs.module.ar.businessobject.Lockbox;
import org.kuali.kfs.module.ar.dataaccess.LockboxDao;

public class LockboxDaoJpa implements LockboxDao {

    private static final Logger LOG = Logger.getLogger(LockboxDaoJpa.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Lockbox> getAllLockboxes() {
        LOG.debug("getAllLockboxes() started");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lockbox> cq = cb.createQuery(Lockbox.class);
        Root<Lockbox> root = cq.from(Lockbox.class);
        cq.orderBy(cb.asc(root.get("processedInvoiceDate")), cb.asc(root.get("batchSequenceNumber")));

        TypedQuery<Lockbox> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Long getMaxLockboxSequenceNumber() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Lockbox> root = cq.from(Lockbox.class);
        cq.select(cb.max(root.<Long>get("invoiceSequenceNumber")));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        Long result = query.getSingleResult();
        return result != null ? result : 0L;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
