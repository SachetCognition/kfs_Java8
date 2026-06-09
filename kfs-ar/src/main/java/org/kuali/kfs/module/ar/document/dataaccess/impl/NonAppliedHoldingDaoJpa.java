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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ar.businessobject.NonAppliedHolding;
import org.kuali.kfs.module.ar.document.dataaccess.NonAppliedHoldingDao;

public class NonAppliedHoldingDaoJpa implements NonAppliedHoldingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<NonAppliedHolding> getNonAppliedHoldingsByListOfDocumentNumbers(List<String> docNumbers) {
        if (docNumbers == null || docNumbers.isEmpty()) {
            return Collections.emptyList();
        }
        TypedQuery<NonAppliedHolding> query = entityManager.createQuery(
                "SELECT n FROM NonAppliedHolding n WHERE n.referenceFinancialDocumentNumber IN :docNumbers",
                NonAppliedHolding.class);
        query.setParameter("docNumbers", docNumbers);
        return query.getResultList();
    }

    @Override
    public Collection<NonAppliedHolding> getNonAppliedHoldingsForCustomer(String customerNumber) {
        TypedQuery<NonAppliedHolding> query = entityManager.createQuery(
                "SELECT n FROM NonAppliedHolding n WHERE n.customerNumber = :customerNumber",
                NonAppliedHolding.class);
        query.setParameter("customerNumber", customerNumber);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
