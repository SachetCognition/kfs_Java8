package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.dataaccess.UniversityDateDao;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

class UniversityDateServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateDao universityDateDao;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private UniversityDateServiceImpl universityDateService;

    @Test
    void getFirstDateOfFiscalYear_delegatesToDao() {
        UniversityDate uDate = new UniversityDate();
        Date expected = new Date(System.currentTimeMillis());
        uDate.setUniversityDate(expected);
        when(universityDateDao.getFirstFiscalYearDate(Integer.valueOf(2024))).thenReturn(uDate);

        java.util.Date result = universityDateService.getFirstDateOfFiscalYear(Integer.valueOf(2024));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getFirstDateOfFiscalYear_nullResult_returnsNull() {
        when(universityDateDao.getFirstFiscalYearDate(Integer.valueOf(1900))).thenReturn(null);

        java.util.Date result = universityDateService.getFirstDateOfFiscalYear(Integer.valueOf(1900));
        assertThat(result).isNull();
    }

    @Test
    void getLastDateOfFiscalYear_delegatesToDao() {
        UniversityDate uDate = new UniversityDate();
        Date expected = new Date(System.currentTimeMillis());
        uDate.setUniversityDate(expected);
        when(universityDateDao.getLastFiscalYearDate(Integer.valueOf(2024))).thenReturn(uDate);

        java.util.Date result = universityDateService.getLastDateOfFiscalYear(Integer.valueOf(2024));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getLastDateOfFiscalYear_nullResult_returnsNull() {
        when(universityDateDao.getLastFiscalYearDate(Integer.valueOf(1900))).thenReturn(null);

        java.util.Date result = universityDateService.getLastDateOfFiscalYear(Integer.valueOf(1900));
        assertThat(result).isNull();
    }

    @Test
    void getFiscalYear_nullDate_throwsException() {
        try {
            universityDateService.getFiscalYear(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("null");
        }
    }
}
