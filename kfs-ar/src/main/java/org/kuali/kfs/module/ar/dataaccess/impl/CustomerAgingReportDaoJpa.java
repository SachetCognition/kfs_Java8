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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.kfs.module.ar.ArPropertyConstants;
import org.kuali.kfs.module.ar.dataaccess.CustomerAgingReportDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class CustomerAgingReportDaoJpa implements CustomerAgingReportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, true, "invoiceAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, true, "appliedAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, true, "discountAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, false, "invoiceAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, false, "appliedAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAmountByChartAndOrg(chart, org, begin, end, false, "discountAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        return findAmountByAccount(chart, account, begin, end, "invoiceAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        return findAmountByAccount(chart, account, begin, end, "appliedAmount");
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        return findAmountByAccount(chart, account, begin, end, "discountAmount");
    }

    private HashMap<String, KualiDecimal> findAmountByChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end, boolean isProcessing, String amountType) {
        HashMap<String, KualiDecimal> map = new HashMap<String, KualiDecimal>();

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d.customerNumber, SUM(d.amount) FROM CustomerInvoiceDetail d ");
        jpql.append("WHERE d.documentHeader.financialDocumentStatusCode = :status ");
        if (isProcessing) {
            jpql.append("AND d.accountsReceivableDocumentHeader.processingChartOfAccountCode = :chart ");
            jpql.append("AND d.accountsReceivableDocumentHeader.processingOrganizationCode = :org ");
        } else {
            jpql.append("AND d.billByChartOfAccountCode = :chart ");
            jpql.append("AND d.billedByOrganizationCode = :org ");
        }
        if (begin != null) {
            jpql.append("AND d.billingDate >= :begin ");
        }
        if (end != null) {
            jpql.append("AND d.billingDate <= :end ");
        }
        jpql.append("GROUP BY d.customerNumber");

        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chart);
        query.setParameter("org", org);
        if (begin != null) {
            query.setParameter("begin", begin);
        }
        if (end != null) {
            query.setParameter("end", end);
        }

        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            String customerNumber = (String) row[0];
            BigDecimal amount = row[1] != null ? (BigDecimal) row[1] : BigDecimal.ZERO;
            map.put(customerNumber, new KualiDecimal(amount));
        }

        return map;
    }

    private HashMap<String, KualiDecimal> findAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end, String amountType) {
        HashMap<String, KualiDecimal> map = new HashMap<String, KualiDecimal>();

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT d.customerNumber, SUM(d.amount) FROM CustomerInvoiceDetail d ");
        jpql.append("WHERE d.documentHeader.financialDocumentStatusCode = :status ");
        jpql.append("AND d.chartOfAccountsCode = :chart ");
        jpql.append("AND d.accountNumber = :account ");
        if (begin != null) {
            jpql.append("AND d.billingDate >= :begin ");
        }
        if (end != null) {
            jpql.append("AND d.billingDate <= :end ");
        }
        jpql.append("GROUP BY d.customerNumber");

        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter("chart", chart);
        query.setParameter("account", account);
        if (begin != null) {
            query.setParameter("begin", begin);
        }
        if (end != null) {
            query.setParameter("end", end);
        }

        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            String customerNumber = (String) row[0];
            BigDecimal amount = row[1] != null ? (BigDecimal) row[1] : BigDecimal.ZERO;
            map.put(customerNumber, new KualiDecimal(amount));
        }

        return map;
    }

    @Override
    public KualiDecimal findWriteOffAmountByCustomerNumber(String customerNumber) {
        Query query = entityManager.createQuery(
                "SELECT SUM(d.amount) FROM CustomerInvoiceDetail d, CustomerInvoiceWriteoffDocument w " +
                "WHERE d.documentNumber = w.documentNumber " +
                "AND w.financialDocumentReferenceInvoiceNumber IN " +
                "(SELECT h.documentNumber FROM AccountsReceivableDocumentHeader h WHERE h.customerNumber = :customerNumber) " +
                "AND d.documentHeader.financialDocumentStatusCode = :status");
        query.setParameter("customerNumber", customerNumber);
        query.setParameter("status", KFSConstants.DocumentStatusCodes.APPROVED);

        Object result = query.getSingleResult();
        if (result == null) {
            return KualiDecimal.ZERO;
        }
        return new KualiDecimal((BigDecimal) result);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
