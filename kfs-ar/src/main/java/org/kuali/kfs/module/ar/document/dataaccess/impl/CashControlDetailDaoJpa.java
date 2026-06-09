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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ar.businessobject.CashControlDetail;
import org.kuali.kfs.module.ar.document.dataaccess.CashControlDetailDao;

public class CashControlDetailDaoJpa implements CashControlDetailDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CashControlDetail getCashControlDetailByRefDocNumber(String referenceDocumentNumber) {
        TypedQuery<CashControlDetail> query = entityManager.createQuery(
                "SELECT c FROM CashControlDetail c WHERE c.referenceFinancialDocumentNumber = :refDocNumber",
                CashControlDetail.class);
        query.setParameter("refDocNumber", referenceDocumentNumber);
        List<CashControlDetail> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
