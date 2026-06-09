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

package org.kuali.kfs.module.cam.document.dataaccess.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.CamsPropertyConstants;
import org.kuali.kfs.module.cam.businessobject.AssetObjectCode;
import org.kuali.kfs.module.cam.document.dataaccess.DepreciableAssetsDao;
import org.kuali.kfs.module.cam.document.dataaccess.DepreciationBatchDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.dataaccess.UniversityDateDao;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class DepreciableAssetsDaoJpa implements DepreciableAssetsDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DepreciableAssetsDaoJpa.class);
    private UniversityDateDao universityDateDao;
    private DepreciationBatchDao depreciationBatchDao;
    private EntityManager entityManager;

    protected final static String[] REPORT_GROUP = { "*** BEFORE RUNNING DEPRECIATION PROCESS ****", "*** AFTER RUNNING DEPRECIATION PROCESS ****" };

    @Override
    public List<String[]> generateStatistics(boolean beforeDepreciationReport, List<String> documentNumbers, Integer fiscalYear, Integer fiscalMonth, Calendar depreciationDate, String depreciationRunDate, Collection<AssetObjectCode> assetObjectCodes, int fiscalStartMonth, String errorMessage) {
        LOG.debug("generateStatistics() -  started");
        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "generating statistics for report - " + (beforeDepreciationReport ? "Before part." : "After part"));

        List<String[]> reportLine = new ArrayList<String[]>();
        boolean processAlreadyRan = false;

        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        KualiDecimal amount = new KualiDecimal(0);
        String[] columns = new String[2];

        columns[1] = "******************";
        if (beforeDepreciationReport)
            columns[0] = REPORT_GROUP[0];
        else
            columns[0] = REPORT_GROUP[1];
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {
            columns[0] = "Depreciation Run Date";
            columns[1] = depreciationRunDate;
            reportLine.add(columns.clone());

            columns[0] = "Fiscal Year";
            columns[1] = (fiscalYear.toString());
            reportLine.add(columns.clone());

            columns[0] = "Fiscal Month";
            columns[1] = (fiscalMonth.toString());
            reportLine.add(columns.clone());

            columns[0] = "Number of assets fully depreciated";
            columns[1] = depreciationBatchDao.getFullyDepreciatedAssetCount().toString();
            reportLine.add(columns.clone());
        }

        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting DocumentHeader row count.");
        Query dhCountQuery = entityManager.createQuery("SELECT COUNT(dh) FROM DocumentHeader dh");
        Object dhCount = dhCountQuery.getSingleResult();
        columns[0] = "Document header table - record count";
        columns[1] = convertCountValueToString(dhCount);
        reportLine.add(columns.clone());

        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting general ledger pending entry row count.");
        Query glpeCountQuery = entityManager.createQuery("SELECT COUNT(g) FROM GeneralLedgerPendingEntry g");
        Object glpeCount = glpeCountQuery.getSingleResult();
        columns[0] = "General ledger pending entry table - record count";
        columns[1] = convertCountValueToString(glpeCount);
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {
            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting assets row count.");
            Query assetCountQuery = entityManager.createQuery("SELECT COUNT(a) FROM Asset a");
            Object assetCount = assetCountQuery.getSingleResult();
            columns[0] = "Asset table - record count";
            columns[1] = convertCountValueToString(assetCount);
            reportLine.add(columns.clone());
        }

        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting assets payment row count, depreciation base amount, accumulated depreciation amount, and every months depreciation amount.");
        Query paymentStatsQuery = entityManager.createQuery(
                "SELECT COUNT(ap), SUM(ap.primaryDepreciationBaseAmount), SUM(ap.accumulatedPrimaryDepreciationAmount), " +
                "SUM(ap.previousYearPrimaryDepreciationAmount), " +
                "SUM(ap.period1Depreciation1Amount), SUM(ap.period2Depreciation1Amount), " +
                "SUM(ap.period3Depreciation1Amount), SUM(ap.period4Depreciation1Amount), " +
                "SUM(ap.period5Depreciation1Amount), SUM(ap.period6Depreciation1Amount), " +
                "SUM(ap.period7Depreciation1Amount), SUM(ap.period8Depreciation1Amount), " +
                "SUM(ap.period9Depreciation1Amount), SUM(ap.period10Depreciation1Amount), " +
                "SUM(ap.period11Depreciation1Amount), SUM(ap.period12Depreciation1Amount) " +
                "FROM AssetPayment ap");
        Object[] data = new Object[16];
        List<?> resultList = paymentStatsQuery.getResultList();
        if (!resultList.isEmpty()) {
            data = (Object[]) resultList.get(0);
        } else {
            for (int c = 0; c < 16; c++)
                data[c] = new KualiDecimal(0);
        }

        // Null-safe handling
        for (int c = 0; c < 16; c++) {
            if (data[c] == null) data[c] = (c == 0) ? Long.valueOf(0) : new KualiDecimal(0);
        }

        if (beforeDepreciationReport) {
            columns[0] = "Asset payment table - record count";
            columns[1] = convertCountValueToString(data[0]);
            reportLine.add(columns.clone());
        }

        columns[0] = "Depreciation base amount";
        columns[1] = (usdFormat.format(data[1]));
        reportLine.add(columns.clone());

        columns[0] = "Current year - accumulated depreciation";
        columns[1] = (usdFormat.format(data[2]));
        reportLine.add(columns.clone());

        columns[0] = "Previous year - accumulated depreciation";
        columns[1] = (usdFormat.format(data[3]));
        reportLine.add(columns.clone());

        processAlreadyRan = false;
        if (fiscalMonth > 1) {
            Object monthData = data[3 + fiscalMonth];
            if (monthData instanceof KualiDecimal && ((KualiDecimal) monthData).compareTo(new KualiDecimal(0)) != 0)
                processAlreadyRan = true;
        }

        KualiDecimal yearToDateDepreciationAmt = new KualiDecimal(0);

        boolean isJanuaryTheFirstFiscalMonth = (fiscalStartMonth == 1);
        int col = 4;
        int currentMonth = fiscalStartMonth - 1;
        for (int monthCounter = 1; monthCounter <= 12; monthCounter++, currentMonth++) {
            columns[0] = CamsConstants.MONTHS[currentMonth] + " depreciation amount";
            columns[1] = (usdFormat.format(data[col]));
            reportLine.add(columns.clone());

            if (data[col] instanceof KualiDecimal) {
                yearToDateDepreciationAmt = yearToDateDepreciationAmt.add((KualiDecimal) data[col]);
            }
            col++;

            if (!isJanuaryTheFirstFiscalMonth) {
                if (currentMonth == 11)
                    currentMonth = -1;
            }
        }

        columns[0] = "Year to date depreciation amount";
        columns[1] = (usdFormat.format(yearToDateDepreciationAmt));
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {
            int federallyOwnedAssetPaymentCount = Integer.valueOf(depreciationBatchDao.getFederallyOwnedAssetAndPaymentCount(fiscalYear, fiscalMonth, depreciationDate)[1].toString());
            int retiredAndTransferredAssetCount = depreciationBatchDao.getTransferDocLockedAssetCount() + depreciationBatchDao.getRetireDocLockedAssetCount();

            columns[0] = "Object code table - record count";
            columns[1] = (convertCountValueToString(this.getAssetObjectCodesCount(fiscalYear)));
            reportLine.add(columns.clone());

            columns[0] = "Plant fund account table - record count";
            columns[1] = (convertCountValueToString(this.getCOAsCount()));
            reportLine.add(columns.clone());

            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting asset payment row count.");
            data = depreciationBatchDao.getAssetAndPaymentCount(fiscalYear, fiscalMonth, depreciationDate, true);
            int eligibleAssetPaymentCount = new Integer(data[1].toString());

            int totalAssetPayments = (eligibleAssetPaymentCount + federallyOwnedAssetPaymentCount);

            columns[0] = "Asset payments eligible for depreciation";
            columns[1] = totalAssetPayments + "";
            reportLine.add(columns.clone());

            columns[0] = "Number of assets with pending AR or AT documents";
            columns[1] = retiredAndTransferredAssetCount + "";
            reportLine.add(columns.clone());

            data = depreciationBatchDao.getAssetAndPaymentCount(fiscalYear, fiscalMonth, depreciationDate, false);
            eligibleAssetPaymentCount = new Integer(data[1].toString());
            columns[0] = "Asset payments eligible for depreciation - After excluding AR and AT";
            columns[1] = "" + (eligibleAssetPaymentCount + federallyOwnedAssetPaymentCount);
            reportLine.add(columns.clone());

            columns[0] = "Asset payments ineligible for depreciation (Federally owned assets)";
            columns[1] = federallyOwnedAssetPaymentCount + "";
            reportLine.add(columns.clone());

            columns[0] = "Asset payments eligible for depreciation - After excluding federally owned assets";
            columns[1] = eligibleAssetPaymentCount + "";
            reportLine.add(columns.clone());

            columns[0] = "Assets eligible for depreciation";
            columns[1] = data[0].toString();
            reportLine.add(columns.clone());
        }

        if (!beforeDepreciationReport) {
            List<String> depreExpObjCodes = this.getExpenseObjectCodes(assetObjectCodes);
            List<String> accumulatedDepreciationObjCodes = this.getAccumulatedDepreciationObjectCodes(assetObjectCodes);

            KualiDecimal debits = new KualiDecimal(0);
            KualiDecimal credits = new KualiDecimal(0);

            columns[0] = "Document Number(s)";
            columns[1] = documentNumbers.toString();
            reportLine.add(columns.clone());

            // Expense Debit
            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "calculating the debit amount for expense object codes.");
            amount = getGlpeSumAmount(depreExpObjCodes, KFSConstants.GL_DEBIT_CODE, documentNumbers);
            KualiDecimal deprAmtDebit = amount;
            columns[0] = "Debit - Depreciation Expense object codes: " + depreExpObjCodes.toString();
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits = debits.add(amount);

            // Accumulated Depreciation credit
            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "calculating the credit amount for accumulated depreciation object codes.");
            amount = getGlpeSumAmount(accumulatedDepreciationObjCodes, KFSConstants.GL_CREDIT_CODE, documentNumbers);
            columns[0] = "Credit - Accumulated depreciation object codes: " + accumulatedDepreciationObjCodes.toString();
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits = credits.add(amount);

            // Accumulated Depreciation debit
            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "calculating the debit amount for accumulated depreciation object codes.");
            amount = getGlpeSumAmount(accumulatedDepreciationObjCodes, KFSConstants.GL_DEBIT_CODE, documentNumbers);
            columns[0] = "Debit - Accumulated depreciation object codes:" + accumulatedDepreciationObjCodes.toString();
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits = debits.add(amount);

            // Expense credit
            LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "calculating the credit amount for expense object codes.");
            amount = getGlpeSumAmount(depreExpObjCodes, KFSConstants.GL_CREDIT_CODE, documentNumbers);
            KualiDecimal deprAmtCredit = amount;
            columns[0] = "Credit - Depreciation Expense object codes:" + depreExpObjCodes.toString();
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits = credits.add(amount);

            columns[0] = "Current Month";
            columns[1] = usdFormat.format(deprAmtDebit.subtract(deprAmtCredit));
            reportLine.add(columns.clone());

            columns[0] = "Total Debits";
            columns[1] = usdFormat.format(debits);
            reportLine.add(columns.clone());

            columns[0] = "Total Credits";
            columns[1] = usdFormat.format(credits);
            reportLine.add(columns.clone());

            columns[0] = "Total Debits - Total Credits";
            columns[1] = usdFormat.format(debits.subtract(credits));
            reportLine.add(columns.clone());
        }
        LOG.debug("generateStatistics() -  ended");

        if (processAlreadyRan && beforeDepreciationReport) {
            throw new IllegalStateException(errorMessage);
        }
        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Finished generating statistics for report - " + (beforeDepreciationReport ? "Before part." : "After part"));
        return reportLine;
    }

    protected KualiDecimal getGlpeSumAmount(List<String> objectCodes, String debitCreditCode, List<String> documentNumbers) {
        Query q = entityManager.createQuery(
                "SELECT SUM(g.transactionLedgerEntryAmount) FROM GeneralLedgerPendingEntry g " +
                "WHERE g.financialObjectCode IN :objCodes AND g.transactionDebitCreditCode = :dcCode AND g.documentNumber IN :docNumbers");
        q.setParameter("objCodes", objectCodes);
        q.setParameter("dcCode", debitCreditCode);
        q.setParameter("docNumbers", documentNumbers);
        Object result = q.getSingleResult();
        return result == null ? new KualiDecimal(0) : (KualiDecimal) result;
    }

    protected Object getCOAsCount() {
        LOG.info(CamsConstants.Depreciation.DEPRECIATION_BATCH + "Getting the number of campus plant fund accounts.");
        Query q = entityManager.createQuery("SELECT COUNT(a) FROM Account a");
        Object result = q.getSingleResult();
        return result != null ? result : new BigDecimal(0);
    }

    protected List<String> getExpenseObjectCodes(Collection<AssetObjectCode> assetObjectCodesCollection) {
        List<String> depreExpObjCodes = new ArrayList<String>();
        for (Iterator<AssetObjectCode> iterator = assetObjectCodesCollection.iterator(); iterator.hasNext();) {
            AssetObjectCode assetObjectCode = iterator.next();
            String objCode = assetObjectCode.getDepreciationExpenseFinancialObjectCode();
            if (objCode != null && !objCode.equals("") && !depreExpObjCodes.contains(objCode)) {
                depreExpObjCodes.add(objCode);
            }
        }
        return depreExpObjCodes;
    }

    protected List<String> getAccumulatedDepreciationObjectCodes(Collection<AssetObjectCode> assetObjectCodesCollection) {
        List<String> accumulatedDepreciationObjCodes = new ArrayList<String>();
        for (Iterator<AssetObjectCode> iterator = assetObjectCodesCollection.iterator(); iterator.hasNext();) {
            AssetObjectCode assetObjectCode = iterator.next();
            String objCode = assetObjectCode.getAccumulatedDepreciationFinancialObjectCode();
            if (objCode != null && !objCode.equals("") && !accumulatedDepreciationObjCodes.contains(objCode)) {
                accumulatedDepreciationObjCodes.add(objCode);
            }
        }
        return accumulatedDepreciationObjCodes;
    }

    protected Object getAssetObjectCodesCount(Integer fiscalYear) {
        Query q = entityManager.createQuery("SELECT COUNT(aoc) FROM AssetObjectCode aoc WHERE aoc.universityFiscalYear = :fy");
        q.setParameter("fy", fiscalYear);
        Object result = q.getSingleResult();
        return result != null ? result : new BigDecimal(0);
    }

    protected String convertCountValueToString(Object fieldValue) {
        if (fieldValue == null)
            return "0.0";
        if (fieldValue instanceof BigDecimal) {
            return ((BigDecimal) fieldValue).toString();
        } else {
            return fieldValue.toString();
        }
    }

    public void setUniversityDateDao(UniversityDateDao universityDateDao) {
        this.universityDateDao = universityDateDao;
    }

    public DepreciationBatchDao getDepreciationBatchDao() {
        return depreciationBatchDao;
    }

    public void setDepreciationBatchDao(DepreciationBatchDao depreciationBatchDao) {
        this.depreciationBatchDao = depreciationBatchDao;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
