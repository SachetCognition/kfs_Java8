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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.kuali.kfs.module.tem.businessobject.HistoricalTravelExpense;
import org.kuali.kfs.module.tem.dataaccess.HistoricalTravelExpenseDao;

public class HistoricalTravelExpenseDaoJpa implements HistoricalTravelExpenseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HistoricalTravelExpense> getImportedExpesnesToBeNotified() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HistoricalTravelExpense> cq = cb.createQuery(HistoricalTravelExpense.class);
        Root<HistoricalTravelExpense> root = cq.from(HistoricalTravelExpense.class);

        Predicate nullDate = cb.isNull(root.get("expenseNotificationDate"));
        Predicate notAssigned = cb.equal(root.get("assigned"), "N");
        cq.where(nullDate, notAssigned);

        TypedQuery<HistoricalTravelExpense> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<HistoricalTravelExpense> getImportedExpesnesToBeNotified(Integer travelerProfileId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HistoricalTravelExpense> cq = cb.createQuery(HistoricalTravelExpense.class);
        Root<HistoricalTravelExpense> root = cq.from(HistoricalTravelExpense.class);

        Predicate profilePred = cb.equal(root.get("profileId"), travelerProfileId);
        Predicate nullDate = cb.isNull(root.get("expenseNotificationDate"));
        Predicate notAssigned = cb.equal(root.get("assigned"), "N");
        cq.where(profilePred, nullDate, notAssigned);

        TypedQuery<HistoricalTravelExpense> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
