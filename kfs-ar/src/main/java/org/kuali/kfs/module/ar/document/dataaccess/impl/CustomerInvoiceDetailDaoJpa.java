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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.document.dataaccess.CustomerInvoiceDetailDao;

public class CustomerInvoiceDetailDaoJpa implements CustomerInvoiceDetailDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection getCustomerInvoiceDetailsByAccountNumberByInvoiceDocumentNumbers(String accountNumber, List documentNumbers) {
        if (documentNumbers == null || documentNumbers.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        TypedQuery<CustomerInvoiceDetail> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDetail d WHERE d.accountNumber = :accountNumber " +
                "AND d.documentNumber IN :documentNumbers",
                CustomerInvoiceDetail.class);
        query.setParameter("accountNumber", accountNumber);
        query.setParameter("documentNumbers", documentNumbers);
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
