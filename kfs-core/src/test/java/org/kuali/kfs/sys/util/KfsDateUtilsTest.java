package org.kuali.kfs.sys.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class KfsDateUtilsTest extends KfsUnitTestBase {

    // ──────────────────── isSameDay(Date, Date) ────────────────────

    @Test
    void isSameDay_bothNull_returnsTrue() {
        assertThat(KfsDateUtils.isSameDay((Date) null, (Date) null)).isTrue();
    }

    @Test
    void isSameDay_firstNull_returnsFalse() {
        assertThat(KfsDateUtils.isSameDay((Date) null, new Date())).isFalse();
    }

    @Test
    void isSameDay_secondNull_returnsFalse() {
        assertThat(KfsDateUtils.isSameDay(new Date(), (Date) null)).isFalse();
    }

    @Test
    void isSameDay_sameDates_returnsTrue() {
        Date d = new Date();
        assertThat(KfsDateUtils.isSameDay(d, d)).isTrue();
    }

    @Test
    void isSameDay_differentDays_returnsFalse() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        assertThat(KfsDateUtils.isSameDay(today, tomorrow)).isFalse();
    }

    // ──────────────────── isSameDay(Calendar, Calendar) ─────────────

    @Test
    void isSameDay_calendars_bothNull_returnsTrue() {
        assertThat(KfsDateUtils.isSameDay((Calendar) null, (Calendar) null)).isTrue();
    }

    @Test
    void isSameDay_calendars_firstNull_returnsFalse() {
        assertThat(KfsDateUtils.isSameDay((Calendar) null, Calendar.getInstance())).isFalse();
    }

    @Test
    void isSameDay_calendars_secondNull_returnsFalse() {
        assertThat(KfsDateUtils.isSameDay(Calendar.getInstance(), (Calendar) null)).isFalse();
    }

    @Test
    void isSameDay_calendars_sameDay_returnsTrue() {
        Calendar c = Calendar.getInstance();
        assertThat(KfsDateUtils.isSameDay(c, (Calendar) c.clone())).isTrue();
    }

    @Test
    void isSameDay_calendars_differentDay_returnsFalse() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = (Calendar) c1.clone();
        c2.add(Calendar.DAY_OF_MONTH, 1);
        assertThat(KfsDateUtils.isSameDay(c1, c2)).isFalse();
    }

    // ──────────────────── convertToSqlDate ──────────────────────────

    @Test
    void convertToSqlDate_preservesTime() {
        Date utilDate = new Date();
        java.sql.Date sqlDate = KfsDateUtils.convertToSqlDate(utilDate);
        assertThat(sqlDate.getTime()).isEqualTo(utilDate.getTime());
    }

    // ──────────────────── clearTimeFields(java.sql.Date) ────────────

    @Test
    void clearTimeFields_sqlDate_zerosTime() {
        java.sql.Date input = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date cleared = KfsDateUtils.clearTimeFields(input);

        Calendar cal = new GregorianCalendar();
        cal.setTime(cleared);
        assertThat(cal.get(Calendar.HOUR_OF_DAY)).isZero();
        assertThat(cal.get(Calendar.MINUTE)).isZero();
        assertThat(cal.get(Calendar.SECOND)).isZero();
        assertThat(cal.get(Calendar.MILLISECOND)).isZero();
    }

    // ──────────────────── clearTimeFields(java.util.Date) ───────────

    @Test
    void clearTimeFields_utilDate_zerosTime() {
        Date input = new Date();
        Date cleared = KfsDateUtils.clearTimeFields(input);

        Calendar cal = new GregorianCalendar();
        cal.setTime(cleared);
        assertThat(cal.get(Calendar.HOUR_OF_DAY)).isZero();
        assertThat(cal.get(Calendar.MINUTE)).isZero();
        assertThat(cal.get(Calendar.SECOND)).isZero();
        assertThat(cal.get(Calendar.MILLISECOND)).isZero();
    }

    // ──────────────────── getDifferenceInDays ───────────────────────

    @Test
    void getDifferenceInDays_exactlyOneDay_returns1() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp start = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp end = new Timestamp(cal.getTimeInMillis());

        assertThat(KfsDateUtils.getDifferenceInDays(start, end)).isCloseTo(1.0, within(0.001));
    }

    @Test
    void getDifferenceInDays_sameTimestamp_returnsZero() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        assertThat(KfsDateUtils.getDifferenceInDays(ts, ts)).isCloseTo(0.0, within(0.001));
    }

    // ──────────────────── getDifferenceInHours ──────────────────────

    @Test
    void getDifferenceInHours_exactlyOneHour() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp start = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        Timestamp end = new Timestamp(cal.getTimeInMillis());

        assertThat(KfsDateUtils.getDifferenceInHours(start, end)).isCloseTo(1.0, within(0.001));
    }

    // ──────────────────── newDate(year, month, day) ─────────────────

    @Test
    void newDate_validArgs_returnsCorrectDate() {
        java.sql.Date date = KfsDateUtils.newDate(2024, Calendar.JANUARY, 15);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertThat(cal.get(Calendar.YEAR)).isEqualTo(2024);
        assertThat(cal.get(Calendar.MONTH)).isEqualTo(Calendar.JANUARY);
        assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(15);
    }

    @Test
    void newDate_nullYear_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(null, 1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("year");
    }

    @Test
    void newDate_nullMonth_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(2024, null, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("month");
    }

    @Test
    void newDate_nullDay_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(2024, 1, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("day");
    }

    // ──────────── newDate(year, month, day, hour, minute, second) ───

    @Test
    void newDate_withTime_returnsCorrectDate() {
        java.sql.Date date = KfsDateUtils.newDate(2024, Calendar.MARCH, 10, 14, 30, 45);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertThat(cal.get(Calendar.YEAR)).isEqualTo(2024);
        assertThat(cal.get(Calendar.MONTH)).isEqualTo(Calendar.MARCH);
        assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(10);
        assertThat(cal.get(Calendar.HOUR_OF_DAY)).isEqualTo(14);
        assertThat(cal.get(Calendar.MINUTE)).isEqualTo(30);
        assertThat(cal.get(Calendar.SECOND)).isEqualTo(45);
    }

    @Test
    void newDate_withTime_nullHour_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(2024, 1, 1, null, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("hour");
    }

    @Test
    void newDate_withTime_nullMinute_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(2024, 1, 1, 0, null, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("minute");
    }

    @Test
    void newDate_withTime_nullSecond_throws() {
        assertThatThrownBy(() -> KfsDateUtils.newDate(2024, 1, 1, 0, 0, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("second");
    }

    // ──────────────────── isSameDayOrEarlier ────────────────────────

    @Test
    void isSameDayOrEarlier_sameDay_returnsTrue() {
        Date d = new Date();
        assertThat(KfsDateUtils.isSameDayOrEarlier(d, d)).isTrue();
    }

    @Test
    void isSameDayOrEarlier_earlier_returnsTrue() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        assertThat(KfsDateUtils.isSameDayOrEarlier(today, tomorrow)).isTrue();
    }

    @Test
    void isSameDayOrEarlier_later_returnsFalse() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = cal.getTime();
        assertThat(KfsDateUtils.isSameDayOrEarlier(today, yesterday)).isFalse();
    }

    @Test
    void isSameDayOrEarlier_nullFirst_returnsFalse() {
        assertThat(KfsDateUtils.isSameDayOrEarlier(null, new Date())).isFalse();
    }

    @Test
    void isSameDayOrEarlier_nullSecond_returnsFalse() {
        assertThat(KfsDateUtils.isSameDayOrEarlier(new Date(), null)).isFalse();
    }

    // ──────────────────── isSameDayOrLater ──────────────────────────

    @Test
    void isSameDayOrLater_sameDay_returnsTrue() {
        Date d = new Date();
        assertThat(KfsDateUtils.isSameDayOrLater(d, d)).isTrue();
    }

    @Test
    void isSameDayOrLater_later_returnsTrue() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = cal.getTime();
        Date today = new Date();
        assertThat(KfsDateUtils.isSameDayOrLater(today, yesterday)).isTrue();
    }

    @Test
    void isSameDayOrLater_earlier_returnsFalse() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        assertThat(KfsDateUtils.isSameDayOrLater(today, tomorrow)).isFalse();
    }

    @Test
    void isSameDayOrLater_nullFirst_returnsFalse() {
        assertThat(KfsDateUtils.isSameDayOrLater(null, new Date())).isFalse();
    }

    @Test
    void isSameDayOrLater_nullSecond_returnsFalse() {
        assertThat(KfsDateUtils.isSameDayOrLater(new Date(), null)).isFalse();
    }
}
