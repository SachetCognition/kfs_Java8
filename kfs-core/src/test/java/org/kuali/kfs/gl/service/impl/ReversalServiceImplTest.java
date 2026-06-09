package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.gl.businessobject.LedgerEntryHolder;
import org.kuali.kfs.gl.businessobject.Reversal;
import org.kuali.kfs.gl.dataaccess.ReversalDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReversalServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ReversalServiceImpl reversalService;

    @Mock
    private ReversalDao reversalDao;

    @Mock
    private AccountingPeriodService accountingPeriodService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private Reversal mockReversal;

    @Test
    void delete_delegatesToDao() {
        Reversal reversal = new Reversal();
        reversalService.delete(reversal);

        verify(reversalDao).delete(reversal);
    }

    @Test
    void getByDate_delegatesToDao() {
        java.util.Date before = new java.util.Date();
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(reversalDao.getByDate(before)).thenReturn(mockIterator);

        Iterator<?> result = reversalService.getByDate(before);

        assertThat(result).isSameAs(mockIterator);
        verify(reversalDao).getByDate(before);
    }

    @Test
    void getByTransaction_delegatesToDao() {
        when(reversalDao.getByTransaction(mockReversal)).thenReturn(new Reversal());

        Reversal result = reversalService.getByTransaction(mockReversal);

        assertThat(result).isNotNull();
        verify(reversalDao).getByTransaction(mockReversal);
    }

    @Test
    void getSummaryByDate_withNoReversals_returnsEmptyHolder() {
        java.util.Date before = new java.util.Date();
        when(reversalDao.getByDate(before)).thenReturn(Collections.emptyIterator());

        LedgerEntryHolder holder = reversalService.getSummaryByDate(before);

        assertThat(holder).isNotNull();
        assertThat(holder.getLedgerEntries()).isEmpty();
    }

    @Test
    void getSummaryByDate_withCreditReversal_populatesHolder() {
        java.util.Date before = new java.util.Date();
        Date reversalDate = Date.valueOf("2024-06-15");
        AccountingPeriod period = new AccountingPeriod();
        period.setUniversityFiscalPeriodCode("06");

        Reversal reversal = new Reversal();
        reversal.setFinancialDocumentReversalDate(reversalDate);
        reversal.setFinancialBalanceTypeCode("AC");
        reversal.setFinancialSystemOriginationCode("GN");
        reversal.setTransactionDebitCreditCode(KFSConstants.GL_CREDIT_CODE);
        reversal.setTransactionLedgerEntryAmount(new KualiDecimal(100));

        when(reversalDao.getByDate(before)).thenReturn(Arrays.asList((Object)reversal).iterator());
        when(universityDateService.getFiscalYear(reversalDate)).thenReturn(2024);
        when(accountingPeriodService.getByDate(reversalDate)).thenReturn(period);

        LedgerEntryHolder holder = reversalService.getSummaryByDate(before);

        assertThat(holder.getLedgerEntries()).isNotEmpty();
        assertThat(holder.getGrandTotal().getCreditAmount()).isEqualTo(new KualiDecimal(100));
    }

    @Test
    void getSummaryByDate_withDebitReversal_populatesHolder() {
        java.util.Date before = new java.util.Date();
        Date reversalDate = Date.valueOf("2024-03-15");
        AccountingPeriod period = new AccountingPeriod();
        period.setUniversityFiscalPeriodCode("03");

        Reversal reversal = new Reversal();
        reversal.setFinancialDocumentReversalDate(reversalDate);
        reversal.setFinancialBalanceTypeCode("AC");
        reversal.setFinancialSystemOriginationCode("01");
        reversal.setTransactionDebitCreditCode(KFSConstants.GL_DEBIT_CODE);
        reversal.setTransactionLedgerEntryAmount(new KualiDecimal(250));

        when(reversalDao.getByDate(before)).thenReturn(Arrays.asList((Object)reversal).iterator());
        when(universityDateService.getFiscalYear(reversalDate)).thenReturn(2024);
        when(accountingPeriodService.getByDate(reversalDate)).thenReturn(period);

        LedgerEntryHolder holder = reversalService.getSummaryByDate(before);

        assertThat(holder.getGrandTotal().getDebitAmount()).isEqualTo(new KualiDecimal(250));
    }
}
