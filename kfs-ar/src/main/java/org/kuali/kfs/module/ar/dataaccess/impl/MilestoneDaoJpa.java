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
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.ar.ArPropertyConstants;
import org.kuali.kfs.module.ar.businessobject.Milestone;
import org.kuali.kfs.module.ar.dataaccess.MilestoneDao;
import org.kuali.kfs.sys.KFSPropertyConstants;

public class MilestoneDaoJpa implements MilestoneDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Milestone> getMilestonesForNotification(Date expectedCompletionLimitDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Milestone> cq = cb.createQuery(Milestone.class);
        Root<Milestone> root = cq.from(Milestone.class);

        Predicate activePredicate = cb.equal(root.get(KFSPropertyConstants.ACTIVE), true);
        Predicate billedPredicate = cb.equal(root.get(ArPropertyConstants.BILLED), false);
        Predicate actualDateNull = cb.isNull(root.get(ArPropertyConstants.MilestoneFields.MILESTONE_ACTUAL_COMPLETION_DATE));
        Predicate expectedDatePredicate = cb.lessThanOrEqualTo(root.<Date>get(ArPropertyConstants.MilestoneFields.MILESTONE_EXPECTED_COMPLETION_DATE), expectedCompletionLimitDate);

        cq.where(cb.and(activePredicate, billedPredicate, actualDateNull, expectedDatePredicate));
        cq.orderBy(cb.asc(root.get(KFSPropertyConstants.PROPOSAL_NUMBER)), cb.asc(root.get(ArPropertyConstants.MilestoneFields.MILESTONE_NUMBER)));

        TypedQuery<Milestone> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
