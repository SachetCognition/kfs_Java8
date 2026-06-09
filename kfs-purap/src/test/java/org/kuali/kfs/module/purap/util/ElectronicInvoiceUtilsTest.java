package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

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
        Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("03/15/2024");
    }

    @Test
    void getDateDisplayTextSingleDigitMonthAndDay() {
        Calendar cal = new GregorianCalendar(2024, Calendar.JANUARY, 5);
        Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("01/05/2024");
    }

    @Test
    void getDateDisplayTextDecember31() {
        Calendar cal = new GregorianCalendar(2024, Calendar.DECEMBER, 31);
        Date date = cal.getTime();
        String result = ElectronicInvoiceUtils.getDateDisplayText(date);
        assertThat(result).isEqualTo("12/31/2024");
    }
}
