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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.businessobject.ExpenseTypeObjectCode;
import org.kuali.kfs.module.tem.dataaccess.ExpenseTypeObjectCodeDao;

public class ExpenseTypeObjectCodeDaoJpa implements ExpenseTypeObjectCodeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExpenseTypeObjectCode> findMatchingExpenseTypeObjectCodes(String expenseCodeType, Set<String> documentTypes, String tripType, String travelerType) {
        if (StringUtils.isBlank(expenseCodeType)) {
            throw new IllegalArgumentException("The expenseCodeType parameter may not be null or empty");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseTypeObjectCode> cq = cb.createQuery(ExpenseTypeObjectCode.class);
        Root<ExpenseTypeObjectCode> root = cq.from(ExpenseTypeObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(root.get("expenseTypeCode"), expenseCodeType));

        if (documentTypes != null && !documentTypes.isEmpty()) {
            predicates.add(root.get("documentTypeName").in(documentTypes));
        }

        Set<String> tripTypes = new HashSet<String>();
        tripTypes.add(TemConstants.ALL_EXPENSE_TYPE_OBJECT_CODE_TRIP_TYPE);
        if (!StringUtils.isBlank(tripType)) {
            tripTypes.add(tripType);
        }
        predicates.add(root.get("tripTypeCode").in(tripTypes));

        Set<String> travelerTypes = new HashSet<String>();
        travelerTypes.add(TemConstants.ALL_EXPENSE_TYPE_OBJECT_CODE_TRAVELER_TYPE);
        if (!StringUtils.isBlank(travelerType)) {
            travelerTypes.add(travelerType);
        }
        predicates.add(root.get("travelerTypeCode").in(travelerTypes));
        predicates.add(cb.equal(root.get("active"), Boolean.TRUE));

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ExpenseTypeObjectCode> query = entityManager.createQuery(cq);
        return new ArrayList<ExpenseTypeObjectCode>(query.getResultList());
    }

    @Override
    public List<ExpenseTypeObjectCode> findMatchingExpenseTypesObjectCodes(Set<String> documentTypes, String tripType, String travelerType) {
        if (documentTypes == null || documentTypes.isEmpty()) {
            throw new IllegalArgumentException("The documentTypes parameter may not be null or empty");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpenseTypeObjectCode> cq = cb.createQuery(ExpenseTypeObjectCode.class);
        Root<ExpenseTypeObjectCode> root = cq.from(ExpenseTypeObjectCode.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(root.get("documentTypeName").in(documentTypes));
        predicates.add(cb.equal(root.get("active"), Boolean.TRUE));

        if (!StringUtils.isBlank(tripType)) {
            Set<String> tripTypes = new HashSet<String>();
            tripTypes.add(tripType);
            tripTypes.add(TemConstants.ALL_EXPENSE_TYPE_OBJECT_CODE_TRIP_TYPE);
            predicates.add(root.get("tripTypeCode").in(tripTypes));
        }

        if (!StringUtils.isBlank(travelerType)) {
            Set<String> travelerTypes = new HashSet<String>();
            travelerTypes.add(travelerType);
            travelerTypes.add(TemConstants.ALL_EXPENSE_TYPE_OBJECT_CODE_TRAVELER_TYPE);
            predicates.add(root.get("travelerTypeCode").in(travelerTypes));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("expenseType").get("name")));

        TypedQuery<ExpenseTypeObjectCode> query = entityManager.createQuery(cq);
        return new ArrayList<ExpenseTypeObjectCode>(query.getResultList());
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
