package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.document.dataaccess.TravelMileageRateDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherTravelServiceImplTest extends KfsUnitTestBase {

    @Mock
    private TravelMileageRateDao travelMileageRateDao;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private DisbursementVoucherTravelServiceImpl disbursementVoucherTravelService;

    @Test
    void calculatePerDiemAmount_sameDateShortTrip_returnsZero() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 15, 8, 0, 0);
        Timestamp start = new Timestamp(cal.getTimeInMillis());
        cal.set(2024, Calendar.JANUARY, 15, 12, 0, 0);
        Timestamp end = new Timestamp(cal.getTimeInMillis());

        KualiDecimal rate = new KualiDecimal(50);
        KualiDecimal result = disbursementVoucherTravelService.calculatePerDiemAmount(start, end, rate);
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void calculatePerDiemAmount_sameDateLongTrip_returnsHalfDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 15, 6, 0, 0);
        Timestamp start = new Timestamp(cal.getTimeInMillis());
        cal.set(2024, Calendar.JANUARY, 15, 19, 0, 0);
        Timestamp end = new Timestamp(cal.getTimeInMillis());

        KualiDecimal rate = new KualiDecimal(100);
        KualiDecimal result = disbursementVoucherTravelService.calculatePerDiemAmount(start, end, rate);
        assertThat(result.isGreaterThan(KualiDecimal.ZERO)).isTrue();
    }
}
