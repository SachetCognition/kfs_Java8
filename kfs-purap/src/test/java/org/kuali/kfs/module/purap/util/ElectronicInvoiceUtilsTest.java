package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.MockedStatic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class ElectronicInvoiceUtilsTest extends KfsUnitTestBase {

    @Test
    void stripSplCharsRemovesSpecialCharacters() {
        assertThat(ElectronicInvoiceUtils.stripSplChars("ABC-123!@#")).isEqualTo("ABC123");
    }

    @Test
    void stripSplCharsPreservesAlphanumeric() {
        assertThat(ElectronicInvoiceUtils.stripSplChars("Hello123World")).isEqualTo("Hello123World");
    }

    @Test
    void stripSplCharsReturnsNullForNull() {
        assertThat(ElectronicInvoiceUtils.stripSplChars(null)).isNull();
    }

    @Test
    void stripSplCharsEmptyString() {
        assertThat(ElectronicInvoiceUtils.stripSplChars("")).isEmpty();
    }

    @Test
    void stripSplCharsAllSpecialCharacters() {
        assertThat(ElectronicInvoiceUtils.stripSplChars("!@#$%^&*()")).isEmpty();
    }

    @Test
    void getDateDisplayTextFormatsCorrectly() {
        Calendar cal = new GregorianCalendar(2024, Calendar.MARCH, 15);
        java.util.Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("03/15/2024");
    }

    @Test
    void getDateDisplayTextSingleDigitMonthAndDay() {
        Calendar cal = new GregorianCalendar(2024, Calendar.JANUARY, 5);
        java.util.Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("01/05/2024");
    }

    @Test
    void getDateDisplayTextDecember31() {
        Calendar cal = new GregorianCalendar(2024, Calendar.DECEMBER, 31);
        java.util.Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("12/31/2024");
    }

    @Test
    void getDateReturnsNullForNullInput() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            Date result = ElectronicInvoiceUtils.getDate(null);
            assertThat(result).isNull();
        }
    }

    @Test
    void getDateReturnsNullForEmptyString() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            Date result = ElectronicInvoiceUtils.getDate("");
            assertThat(result).isNull();
        }
    }

    @Test
    void getDateParsesCxmlFormat() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            fmt.when(() -> PurApDateFormatUtils.getSimpleDateFormat(PurapConstants.NamedDateFormats.CXML_SIMPLE_DATE_FORMAT))
               .thenReturn(new SimpleDateFormat("yyyy-MM-dd", Locale.US));

            Date result = ElectronicInvoiceUtils.getDate("2024-03-15");
            assertThat(result).isNotNull();
            assertThat(result.toString()).isEqualTo("2024-03-15");
        }
    }

    @Test
    void getDateParsesCxmlFormatWithTimezoneAppended() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.KUALI_DATE_FORMAT))
               .thenReturn("00/00/0000");
            fmt.when(() -> PurApDateFormatUtils.getSimpleDateFormat(PurapConstants.NamedDateFormats.CXML_SIMPLE_DATE_FORMAT))
               .thenReturn(new SimpleDateFormat("yyyy-MM-dd", Locale.US));

            // cXML dates can have timezone suffix like "2024-03-15T12:00:00-05:00"
            Date result = ElectronicInvoiceUtils.getDate("2024-03-15T12:00:00-05:00");
            assertThat(result).isNotNull();
            assertThat(result.toString()).isEqualTo("2024-03-15");
        }
    }

    @Test
    void getDateReturnsNullForInvalidFormat() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.KUALI_DATE_FORMAT))
               .thenReturn("00/00/0000");

            Date result = ElectronicInvoiceUtils.getDate("not-a-date");
            assertThat(result).isNull();
        }
    }

    @Test
    void getDateReturnsNullForInvalidSeparators() {
        try (MockedStatic<PurApDateFormatUtils> fmt = mockStatic(PurApDateFormatUtils.class)) {
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.CXML_DATE_FORMAT))
               .thenReturn("0000-00-00");
            fmt.when(() -> PurApDateFormatUtils.getFormattingString(PurapConstants.NamedDateFormats.KUALI_DATE_FORMAT))
               .thenReturn("00/00/0000");

            // Same length as CXML format but wrong separators
            Date result = ElectronicInvoiceUtils.getDate("2024/03/15");
            assertThat(result).isNull();
        }
    }
}
