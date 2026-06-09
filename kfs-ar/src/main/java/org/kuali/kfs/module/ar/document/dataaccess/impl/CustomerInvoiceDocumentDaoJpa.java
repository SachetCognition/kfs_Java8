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

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.dataaccess.CustomerInvoiceDocumentDao;
import org.kuali.kfs.sys.KFSConstants;

public class CustomerInvoiceDocumentDaoJpa implements CustomerInvoiceDocumentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> getPrintableCustomerInvoiceDocumentNumbersFromUserQueue() {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.printInvoiceIndicator = :printIndicator " +
                "AND d.documentHeader.financialDocumentStatusCode = :status",
                String.class);
        query.setParameter("printIndicator", KFSConstants.OptionLabels.YES);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        return query.getResultList();
    }

    @Override
    public List<String> getPrintableCustomerInvoiceDocumentNumbersByProcessingChartAndOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.printInvoiceIndicator = :printIndicator " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.accountsReceivableDocumentHeader.processingChartOfAccountCode = :chart " +
                "AND d.accountsReceivableDocumentHeader.processingOrganizationCode = :org",
                String.class);
        query.setParameter("printIndicator", KFSConstants.OptionLabels.YES);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public List<String> getPrintableCustomerInvoiceDocumentNumbersByBillingChartAndOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.printInvoiceIndicator = :printIndicator " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.billByChartOfAccountCode = :chart " +
                "AND d.billedByOrganizationCode = :org",
                String.class);
        query.setParameter("printIndicator", KFSConstants.OptionLabels.YES);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public List<String> getPrintableCustomerInvoiceDocumentNumbersForBillingStatementByBillingChartAndOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.billByChartOfAccountCode = :chart " +
                "AND d.billedByOrganizationCode = :org",
                String.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public List<String> getCustomerInvoiceDocumentNumbersByProcessingChartAndOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.accountsReceivableDocumentHeader.processingChartOfAccountCode = :chart " +
                "AND d.accountsReceivableDocumentHeader.processingOrganizationCode = :org",
                String.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public List<String> getCustomerInvoiceDocumentNumbersByBillingChartAndOrg(String chartOfAccountsCode, String organizationCode) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT d.documentNumber FROM CustomerInvoiceDocument d " +
                "WHERE d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.billByChartOfAccountCode = :chart " +
                "AND d.billedByOrganizationCode = :org",
                String.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chartOfAccountsCode);
        query.setParameter("org", organizationCode);
        return query.getResultList();
    }

    @Override
    public Collection getAllOpen() {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true " +
                "AND d.documentHeader.financialDocumentStatusCode = :status",
                CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        return query.getResultList();
    }

    @Override
    public Collection getOpenByCustomerNumber(String customerNumber) {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.accountsReceivableDocumentHeader.customerNumber = :customerNumber",
                CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("customerNumber", customerNumber);
        return query.getResultList();
    }

    @Override
    public Collection getOpenByCustomerNameByCustomerType(String customerName, String customerTypeCode) {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.customer.customerName LIKE :customerName " +
                "AND d.customer.customerTypeCode = :customerTypeCode",
                CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("customerName", customerName + "%");
        query.setParameter("customerTypeCode", customerTypeCode);
        return query.getResultList();
    }

    @Override
    public Collection getOpenByCustomerName(String customerName) {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.customer.customerName LIKE :customerName",
                CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("customerName", customerName + "%");
        return query.getResultList();
    }

    @Override
    public Collection getOpenByCustomerType(String customerTypeCode) {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true " +
                "AND d.documentHeader.financialDocumentStatusCode = :status " +
                "AND d.customer.customerTypeCode = :customerTypeCode",
                CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("customerTypeCode", customerTypeCode);
        return query.getResultList();
    }

    @Override
    public CustomerInvoiceDocument getInvoiceByOrganizationInvoiceNumber(String organizationInvoiceNumber) {
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(
                "SELECT d FROM CustomerInvoiceDocument d WHERE d.organizationInvoiceNumber = :invoiceNumber",
                CustomerInvoiceDocument.class);
        query.setParameter("invoiceNumber", organizationInvoiceNumber);
        List<CustomerInvoiceDocument> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public CustomerInvoiceDocument getInvoiceByInvoiceDocumentNumber(String documentNumber) {
        return entityManager.find(CustomerInvoiceDocument.class, documentNumber);
    }

    @Override
    public Collection<CustomerInvoiceDocument> getAllAgingInvoiceDocumentsByBilling(List<String> charts, List<String> organizations, Date invoiceBillingDateFrom, Date invoiceBillingDateTo) {
        if (charts == null || charts.isEmpty() || organizations == null || organizations.isEmpty()) {
            return Collections.emptyList();
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true ");
        jpql.append("AND d.documentHeader.financialDocumentStatusCode = :status ");
        jpql.append("AND d.billByChartOfAccountCode IN :charts ");
        jpql.append("AND d.billedByOrganizationCode IN :organizations ");
        if (invoiceBillingDateFrom != null) {
            jpql.append("AND d.billingDate >= :fromDate ");
        }
        if (invoiceBillingDateTo != null) {
            jpql.append("AND d.billingDate < :toDate ");
        }
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(jpql.toString(), CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("charts", charts);
        query.setParameter("organizations", organizations);
        if (invoiceBillingDateFrom != null) {
            query.setParameter("fromDate", invoiceBillingDateFrom);
        }
        if (invoiceBillingDateTo != null) {
            query.setParameter("toDate", invoiceBillingDateTo);
        }
        return query.getResultList();
    }

    @Override
    public Collection<CustomerInvoiceDocument> getAllAgingInvoiceDocumentsByProcessing(List<String> charts, List<String> organizations, Date invoiceBillingDateFrom, Date invoiceBillingDateTo) {
        if (charts == null || charts.isEmpty() || organizations == null || organizations.isEmpty()) {
            return Collections.emptyList();
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true ");
        jpql.append("AND d.documentHeader.financialDocumentStatusCode = :status ");
        jpql.append("AND d.accountsReceivableDocumentHeader.processingChartOfAccountCode IN :charts ");
        jpql.append("AND d.accountsReceivableDocumentHeader.processingOrganizationCode IN :organizations ");
        if (invoiceBillingDateFrom != null) {
            jpql.append("AND d.billingDate >= :fromDate ");
        }
        if (invoiceBillingDateTo != null) {
            jpql.append("AND d.billingDate < :toDate ");
        }
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(jpql.toString(), CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("charts", charts);
        query.setParameter("organizations", organizations);
        if (invoiceBillingDateFrom != null) {
            query.setParameter("fromDate", invoiceBillingDateFrom);
        }
        if (invoiceBillingDateTo != null) {
            query.setParameter("toDate", invoiceBillingDateTo);
        }
        return query.getResultList();
    }

    @Override
    public Collection<CustomerInvoiceDocument> getAllAgingInvoiceDocumentsByAccounts(List<String> charts, List<String> accounts, Date invoiceBillingDateFrom, Date invoiceBillingDateTo) {
        if (charts == null || charts.isEmpty() || accounts == null || accounts.isEmpty()) {
            return Collections.emptyList();
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true ");
        jpql.append("AND d.documentHeader.financialDocumentStatusCode = :status ");
        jpql.append("AND d.billByChartOfAccountCode IN :charts ");
        jpql.append("AND d.billedByOrganizationCode IN :accounts ");
        if (invoiceBillingDateFrom != null) {
            jpql.append("AND d.billingDate >= :fromDate ");
        }
        if (invoiceBillingDateTo != null) {
            jpql.append("AND d.billingDate < :toDate ");
        }
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(jpql.toString(), CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("charts", charts);
        query.setParameter("accounts", accounts);
        if (invoiceBillingDateFrom != null) {
            query.setParameter("fromDate", invoiceBillingDateFrom);
        }
        if (invoiceBillingDateTo != null) {
            query.setParameter("toDate", invoiceBillingDateTo);
        }
        return query.getResultList();
    }

    @Override
    public Collection<CustomerInvoiceDocument> getAllAgingInvoiceDocumentsByCustomerTypes(List<String> customerTypes, Date invoiceDueDateFrom, Date invoiceDueDateTo) {
        if (customerTypes == null || customerTypes.isEmpty()) {
            return Collections.emptyList();
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d FROM CustomerInvoiceDocument d WHERE d.openInvoiceIndicator = true ");
        jpql.append("AND d.documentHeader.financialDocumentStatusCode = :status ");
        jpql.append("AND d.customer.customerTypeCode IN :customerTypes ");
        if (invoiceDueDateFrom != null) {
            jpql.append("AND d.invoiceDueDate >= :fromDate ");
        }
        if (invoiceDueDateTo != null) {
            jpql.append("AND d.invoiceDueDate < :toDate ");
        }
        TypedQuery<CustomerInvoiceDocument> query = entityManager.createQuery(jpql.toString(), CustomerInvoiceDocument.class);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("customerTypes", customerTypes);
        if (invoiceDueDateFrom != null) {
            query.setParameter("fromDate", invoiceDueDateFrom);
        }
        if (invoiceDueDateTo != null) {
            query.setParameter("toDate", invoiceDueDateTo);
        }
        return query.getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
