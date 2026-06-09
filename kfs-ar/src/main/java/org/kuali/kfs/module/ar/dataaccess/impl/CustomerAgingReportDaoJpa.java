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

import org.kuali.kfs.module.ar.dataaccess.CustomerAgingReportDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class CustomerAgingReportDaoJpa implements CustomerAgingReportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findInvoiceAmountByChartAndOrg(chart, org, begin, end, true);
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAppliedAmountByChartAndOrg(chart, org, begin, end, true);
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByProcessingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findDiscountAmountByChartAndOrg(chart, org, begin, end, true);
    }

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findInvoiceAmountByChartAndOrg(chart, org, begin, end, false);
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findAppliedAmountByChartAndOrg(chart, org, begin, end, false);
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByBillingChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end) {
        return findDiscountAmountByChartAndOrg(chart, org, begin, end, false);
    }

    @Override
    public HashMap<String, KualiDecimal> findInvoiceAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(d.AR_INV_ITM_TOT_AMT) ");
        sql.append("FROM AR_INV_DTL_T d ");
        sql.append("JOIN AR_INV_DOC_T inv ON d.FDOC_NBR = inv.FDOC_NBR ");
        sql.append("JOIN FS_DOC_HEADER_T dh ON d.FDOC_NBR = dh.FDOC_NBR ");
        sql.append("JOIN AR_DOC_HDR_T h ON d.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("WHERE dh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND inv.AR_OPEN_INV_IND = 'Y' ");
        sql.append("AND d.FIN_COA_CD = ?2 ");
        sql.append("AND d.ACCOUNT_NBR = ?3 ");
        int nextParam = 4;
        if (begin != null) {
            sql.append("AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, account);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    @Override
    public HashMap<String, KualiDecimal> findAppliedAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(pa.AR_INV_ITMAPLD_AMT) ");
        sql.append("FROM AR_INV_PD_APLD_T pa ");
        sql.append("JOIN FS_DOC_HEADER_T pdh ON pa.FDOC_NBR = pdh.FDOC_NBR ");
        sql.append("JOIN AR_INV_DOC_T inv ON pa.FDOC_REF_INV_NBR = inv.FDOC_NBR ");
        sql.append("JOIN FS_DOC_HEADER_T dh ON inv.FDOC_NBR = dh.FDOC_NBR ");
        sql.append("JOIN AR_DOC_HDR_T h ON inv.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("JOIN AR_INV_DTL_T det ON pa.FDOC_REF_INV_NBR = det.FDOC_NBR AND pa.AR_INV_ITM_NBR = det.AR_INV_ITM_NBR ");
        sql.append("WHERE pdh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND dh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND inv.AR_OPEN_INV_IND = 'Y' ");
        sql.append("AND det.FIN_COA_CD = ?2 ");
        sql.append("AND det.ACCOUNT_NBR = ?3 ");
        int nextParam = 4;
        if (begin != null) {
            sql.append("AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, account);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    @Override
    public HashMap<String, KualiDecimal> findDiscountAmountByAccount(String chart, String account, java.sql.Date begin, java.sql.Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(disc.AR_INV_ITM_TOT_AMT) ");
        sql.append("FROM AR_INV_DTL_T disc ");
        sql.append("JOIN AR_DOC_HDR_T h ON disc.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("WHERE disc.AR_INV_ITM_NBR IN ( ");
        sql.append("  SELECT sub.AR_INV_ITM_DSCT_LN_NBR FROM AR_INV_DTL_T sub ");
        sql.append("  JOIN AR_INV_DOC_T inv ON sub.FDOC_NBR = inv.FDOC_NBR ");
        sql.append("  JOIN FS_DOC_HEADER_T dh ON sub.FDOC_NBR = dh.FDOC_NBR ");
        sql.append("  WHERE dh.FDOC_STATUS_CD = ?1 ");
        sql.append("  AND inv.AR_OPEN_INV_IND = 'Y' ");
        sql.append("  AND sub.FIN_COA_CD = ?2 ");
        sql.append("  AND sub.ACCOUNT_NBR = ?3 ");
        sql.append("  AND sub.FDOC_NBR = disc.FDOC_NBR ");
        int nextParam = 4;
        if (begin != null) {
            sql.append("  AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("  AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("  AND sub.AR_INV_ITM_DSCT_LN_NBR IS NOT NULL ");
        sql.append(") ");
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, account);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    @Override
    public KualiDecimal findWriteOffAmountByCustomerNumber(String customerNumber) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT SUM(w.AR_INV_WRTOFF_AMT) ");
        sql.append("FROM AR_WRITEOFF_DOC_T w ");
        sql.append("JOIN AR_INV_DOC_T inv ON w.FDOC_REF_INV_NBR = inv.FDOC_NBR ");
        sql.append("JOIN AR_DOC_HDR_T h ON inv.FDOC_NBR = h.FDOC_NBR ");
        sql.append("WHERE h.CUST_NBR = ?1");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, customerNumber);

        Object result = query.getSingleResult();
        if (result == null) {
            return KualiDecimal.ZERO;
        }
        return new KualiDecimal((BigDecimal) result);
    }

    private HashMap<String, KualiDecimal> findInvoiceAmountByChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end, boolean isProcessing) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(d.AR_INV_ITM_TOT_AMT) ");
        sql.append("FROM AR_INV_DTL_T d ");
        sql.append("JOIN AR_INV_DOC_T inv ON d.FDOC_NBR = inv.FDOC_NBR ");
        sql.append("JOIN FS_DOC_HEADER_T dh ON d.FDOC_NBR = dh.FDOC_NBR ");
        sql.append("JOIN AR_DOC_HDR_T h ON d.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("WHERE dh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND inv.AR_OPEN_INV_IND = 'Y' ");
        if (isProcessing) {
            sql.append("AND h.PRCS_FIN_COA_CD = ?2 ");
            sql.append("AND h.PRCS_ORG_CD = ?3 ");
        } else {
            sql.append("AND inv.AR_BILL_BY_COA_CD = ?2 ");
            sql.append("AND inv.AR_BILL_BY_ORG_CD = ?3 ");
        }
        int nextParam = 4;
        if (begin != null) {
            sql.append("AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, org);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    private HashMap<String, KualiDecimal> findAppliedAmountByChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end, boolean isProcessing) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(pa.AR_INV_ITMAPLD_AMT) ");
        sql.append("FROM AR_INV_PD_APLD_T pa ");
        sql.append("JOIN FS_DOC_HEADER_T pdh ON pa.FDOC_NBR = pdh.FDOC_NBR ");
        sql.append("JOIN AR_INV_DOC_T inv ON pa.FDOC_REF_INV_NBR = inv.FDOC_NBR ");
        sql.append("JOIN FS_DOC_HEADER_T dh ON inv.FDOC_NBR = dh.FDOC_NBR ");
        sql.append("JOIN AR_DOC_HDR_T h ON inv.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("WHERE pdh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND dh.FDOC_STATUS_CD = ?1 ");
        sql.append("AND inv.AR_OPEN_INV_IND = 'Y' ");
        if (isProcessing) {
            sql.append("AND h.PRCS_FIN_COA_CD = ?2 ");
            sql.append("AND h.PRCS_ORG_CD = ?3 ");
        } else {
            sql.append("AND inv.AR_BILL_BY_COA_CD = ?2 ");
            sql.append("AND inv.AR_BILL_BY_ORG_CD = ?3 ");
        }
        int nextParam = 4;
        if (begin != null) {
            sql.append("AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, org);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    private HashMap<String, KualiDecimal> findDiscountAmountByChartAndOrg(String chart, String org, java.sql.Date begin, java.sql.Date end, boolean isProcessing) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT h.CUST_NBR, c.CUST_NM, SUM(disc.AR_INV_ITM_TOT_AMT) ");
        sql.append("FROM AR_INV_DTL_T disc ");
        sql.append("JOIN AR_DOC_HDR_T h ON disc.FDOC_NBR = h.FDOC_NBR ");
        sql.append("JOIN AR_CUST_T c ON h.CUST_NBR = c.CUST_NBR ");
        sql.append("WHERE disc.AR_INV_ITM_NBR IN ( ");
        sql.append("  SELECT sub.AR_INV_ITM_DSCT_LN_NBR FROM AR_INV_DTL_T sub ");
        sql.append("  JOIN AR_INV_DOC_T inv ON sub.FDOC_NBR = inv.FDOC_NBR ");
        sql.append("  JOIN FS_DOC_HEADER_T dh ON sub.FDOC_NBR = dh.FDOC_NBR ");
        if (isProcessing) {
            sql.append("  JOIN AR_DOC_HDR_T h2 ON sub.FDOC_NBR = h2.FDOC_NBR ");
        }
        sql.append("  WHERE dh.FDOC_STATUS_CD = ?1 ");
        sql.append("  AND inv.AR_OPEN_INV_IND = 'Y' ");
        if (isProcessing) {
            sql.append("  AND h2.PRCS_FIN_COA_CD = ?2 ");
            sql.append("  AND h2.PRCS_ORG_CD = ?3 ");
        } else {
            sql.append("  AND inv.AR_BILL_BY_COA_CD = ?2 ");
            sql.append("  AND inv.AR_BILL_BY_ORG_CD = ?3 ");
        }
        int nextParam = 4;
        if (begin != null) {
            sql.append("  AND inv.AR_BILLING_DT >= ?" + nextParam + " ");
            nextParam++;
        }
        if (end != null) {
            sql.append("  AND inv.AR_BILLING_DT <= ?" + nextParam + " ");
            nextParam++;
        }
        sql.append("  AND sub.FDOC_NBR = disc.FDOC_NBR ");
        sql.append("  AND sub.AR_INV_ITM_DSCT_LN_NBR IS NOT NULL ");
        sql.append(") ");
        sql.append("GROUP BY h.CUST_NBR, c.CUST_NM");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, KFSConstants.DocumentStatusCodes.APPROVED);
        query.setParameter(2, chart);
        query.setParameter(3, org);
        int setParam = 4;
        if (begin != null) {
            query.setParameter(setParam++, begin);
        }
        if (end != null) {
            query.setParameter(setParam++, end);
        }

        return extractResults(query);
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, KualiDecimal> extractResults(Query query) {
        HashMap<String, KualiDecimal> map = new HashMap<String, KualiDecimal>();
        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            String customerNumber = (String) row[0];
            String customerName = (String) row[1];
            BigDecimal amount = row[2] != null ? (BigDecimal) row[2] : BigDecimal.ZERO;
            map.put(customerNumber + "-" + customerName, new KualiDecimal(amount));
        }
        return map;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
