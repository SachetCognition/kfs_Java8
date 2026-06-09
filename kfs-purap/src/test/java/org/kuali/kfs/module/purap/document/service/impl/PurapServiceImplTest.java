package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.document.PurchasingAccountsPayableDocument;
import org.kuali.kfs.module.purap.document.RequisitionDocument;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.module.purap.service.PurapAccountingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.service.PersistenceService;
import org.kuali.rice.kns.service.DataDictionaryService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class PurapServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private DateTimeService dateTimeService;
    @Mock private ParameterService parameterService;
    @Mock private DocumentService documentService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private VendorService vendorService;
    @Mock private PersistenceService persistenceService;
    @Mock private PurchaseOrderService purchaseOrderService;
    @Mock private NoteService noteService;
    @Mock private UniversityDateService universityDateService;
    @Mock private PurapAccountingService purapAccountingService;

    @InjectMocks
    private PurapServiceImpl purapService;

    @Test
    public void testIsDateInPast_pastDate_returnsTrue() {
        Date today = new Date(System.currentTimeMillis());
        when(dateTimeService.getCurrentSqlDate()).thenReturn(today);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        Date pastDate = new Date(cal.getTimeInMillis());
        when(dateTimeService.dateDiff(today, pastDate, false)).thenReturn(-5);

        boolean result = purapService.isDateInPast(pastDate);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsDateInPast_futureDate_returnsFalse() {
        Date today = new Date(System.currentTimeMillis());
        when(dateTimeService.getCurrentSqlDate()).thenReturn(today);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date futureDate = new Date(cal.getTimeInMillis());
        when(dateTimeService.dateDiff(today, futureDate, false)).thenReturn(5);

        boolean result = purapService.isDateInPast(futureDate);

        assertThat(result).isFalse();
    }

    @Test
    public void testGetDateFromOffsetFromToday() {
        Calendar cal = Calendar.getInstance();
        when(dateTimeService.getCurrentCalendar()).thenReturn(cal);

        Date result = purapService.getDateFromOffsetFromToday(10);

        assertThat(result).isNotNull();
    }

    @Test
    public void testIsDateMoreThanANumberOfDaysAway_true() {
        Date todayMidnight = new Date(System.currentTimeMillis());
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(todayMidnight);
        Calendar daysAwayCal = Calendar.getInstance();
        when(dateTimeService.getCalendar(todayMidnight)).thenReturn(daysAwayCal);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date futureDate = new Date(cal.getTimeInMillis());
        Calendar compareCal = Calendar.getInstance();
        compareCal.setTimeInMillis(futureDate.getTime());
        when(dateTimeService.getCalendar(futureDate)).thenReturn(compareCal);

        boolean result = purapService.isDateMoreThanANumberOfDaysAway(futureDate, 5);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsDateMoreThanANumberOfDaysAway_false() {
        Date todayMidnight = new Date(System.currentTimeMillis());
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(todayMidnight);
        Calendar daysAwayCal = Calendar.getInstance();
        when(dateTimeService.getCalendar(todayMidnight)).thenReturn(daysAwayCal);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date nearDate = new Date(cal.getTimeInMillis());
        Calendar compareCal = Calendar.getInstance();
        compareCal.setTimeInMillis(nearDate.getTime());
        when(dateTimeService.getCalendar(nearDate)).thenReturn(compareCal);

        boolean result = purapService.isDateMoreThanANumberOfDaysAway(nearDate, 5);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsDateAYearBeforeToday_true() {
        Calendar currentCal = Calendar.getInstance();
        when(dateTimeService.getCurrentCalendar()).thenReturn(currentCal);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        Date oldDate = new Date(cal.getTimeInMillis());
        when(dateTimeService.dateDiff(any(Date.class), any(Date.class), eq(false))).thenReturn(365);

        boolean result = purapService.isDateAYearBeforeToday(oldDate);

        assertThat(result).isTrue();
    }

    @Test
    public void testGetApoLimit_returnsLimitFromContract() {
        when(vendorService.getApoLimitFromContract(10, "BL", "ACCT")).thenReturn(new KualiDecimal(5000));

        KualiDecimal result = purapService.getApoLimit(10, "BL", "ACCT");

        assertThat(result).isEqualTo(new KualiDecimal(5000));
    }

    @Test
    public void testIsFullDocumentEntryCompleted_requisition_returnsFalse() {
        PurchasingAccountsPayableDocument purapDoc = mock(RequisitionDocument.class);

        boolean result = purapService.isFullDocumentEntryCompleted(purapDoc);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsPaymentRequestFullDocumentEntryCompleted_approvedStatus_returnsTrue() {
        boolean result = purapService.isPaymentRequestFullDocumentEntryCompleted(
                PurapConstants.PaymentRequestStatuses.APPDOC_DEPARTMENT_APPROVED);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsPaymentRequestFullDocumentEntryCompleted_inProcessStatus_returnsFalse() {
        boolean result = purapService.isPaymentRequestFullDocumentEntryCompleted(
                PurapConstants.PaymentRequestStatuses.APPDOC_IN_PROCESS);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsVendorCreditMemoFullDocumentEntryCompleted_approvedStatus_returnsTrue() {
        boolean result = purapService.isVendorCreditMemoFullDocumentEntryCompleted(
                PurapConstants.CreditMemoStatuses.APPDOC_COMPLETE);

        assertThat(result).isTrue();
    }
}
