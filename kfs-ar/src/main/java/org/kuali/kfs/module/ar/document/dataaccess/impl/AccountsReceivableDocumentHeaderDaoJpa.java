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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ar.businessobject.AccountsReceivableDocumentHeader;
import org.kuali.kfs.module.ar.document.dataaccess.AccountsReceivableDocumentHeaderDao;
import org.kuali.kfs.sys.KFSConstants;

public class AccountsReceivableDocumentHeaderDaoJpa implements AccountsReceivableDocumentHeaderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection getARDocumentHeadersByCustomerNumber(String customerNumber) {
        TypedQuery<AccountsReceivableDocumentHeader> query = entityManager.createQuery(
                "SELECT h FROM AccountsReceivableDocumentHeader h WHERE h.customerNumber = :customerNumber",
                AccountsReceivableDocumentHeader.class);
        query.setParameter("customerNumber", customerNumber);
        return query.getResultList();
    }

    @Override
    public Collection getARDocumentHeadersByCustomerNumberByProcessingOrgCodeAndChartCode(String customerNumber, String processingChartOfAccountCode, String processingOrganizationCode) {
        TypedQuery<AccountsReceivableDocumentHeader> query = entityManager.createQuery(
                "SELECT h FROM AccountsReceivableDocumentHeader h WHERE h.customerNumber = :customerNumber " +
                "AND h.processingChartOfAccountCode = :chartCode AND h.processingOrganizationCode = :orgCode",
                AccountsReceivableDocumentHeader.class);
        query.setParameter("customerNumber", customerNumber);
        query.setParameter("chartCode", processingChartOfAccountCode);
        query.setParameter("orgCode", processingOrganizationCode);
        return query.getResultList();
    }

    @Override
    public Collection<AccountsReceivableDocumentHeader> getARDocumentHeadersIncludingHiddenApplicationByCustomerNumber(String customerNumber) {
        Collection<String> documentNumbers = getARDocumentNumbersIncludingHiddenApplicationByCustomerNumber(customerNumber);
        if (documentNumbers.isEmpty()) {
            return new ArrayList<AccountsReceivableDocumentHeader>();
        }
        TypedQuery<AccountsReceivableDocumentHeader> query = entityManager.createQuery(
                "SELECT h FROM AccountsReceivableDocumentHeader h WHERE h.documentNumber IN :docNumbers",
                AccountsReceivableDocumentHeader.class);
        query.setParameter("docNumbers", documentNumbers);
        return query.getResultList();
    }

    @Override
    public Collection<String> getARDocumentNumbersIncludingHiddenApplicationByCustomerNumber(String customerNumber) {
        List<String> documentNumbers = new ArrayList<String>();

        TypedQuery<String> query = entityManager.createQuery(
                "SELECT DISTINCT h.documentNumber FROM AccountsReceivableDocumentHeader h WHERE h.customerNumber = :customerNumber",
                String.class);
        query.setParameter("customerNumber", customerNumber);
        documentNumbers.addAll(query.getResultList());

        if (!documentNumbers.isEmpty()) {
            TypedQuery<String> appQuery = entityManager.createQuery(
                    "SELECT DISTINCT p.documentNumber FROM InvoicePaidApplied p WHERE p.financialDocumentReferenceInvoiceNumber IN :docNumbers",
                    String.class);
            appQuery.setParameter("docNumbers", documentNumbers);
            List<String> appDocNumbers = appQuery.getResultList();
            Set<String> allNumbers = new HashSet<String>(documentNumbers);
            allNumbers.addAll(appDocNumbers);
            return new ArrayList<String>(allNumbers);
        }

        return documentNumbers;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
