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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.tem.businessobject.PerDiem;
import org.kuali.kfs.module.tem.dataaccess.PerDiemDao;

public class PerDiemDaoJpa implements PerDiemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<PerDiem> findAllPerDiemsOrderedBySeasonAndDest() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PerDiem> cq = cb.createQuery(PerDiem.class);
        Root<PerDiem> root = cq.from(PerDiem.class);
        cq.orderBy(cb.asc(root.get("primaryDestinationId")), cb.asc(root.get("seasonBeginMonthAndDay")));

        TypedQuery<PerDiem> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Collection<PerDiem> findSimilarPerDiems(PerDiem perdiem) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PerDiem> cq = cb.createQuery(PerDiem.class);
        Root<PerDiem> root = cq.from(PerDiem.class);

        cq.where(
            cb.lessThanOrEqualTo(root.<java.sql.Date>get("effectiveFromDate"), perdiem.getEffectiveFromDate()),
            cb.lessThanOrEqualTo(root.<java.sql.Date>get("effectiveToDate"), perdiem.getEffectiveToDate()),
            cb.equal(root.get("primaryDestinationId"), perdiem.getPrimaryDestinationId())
        );

        TypedQuery<PerDiem> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
