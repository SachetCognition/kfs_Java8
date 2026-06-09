package org.kuali.rice.core.api.datetime;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public interface DateTimeService {
    Date getCurrentDate();
    Timestamp getCurrentTimestamp();
    java.sql.Date getCurrentSqlDate();
    Calendar getCurrentCalendar();
    Calendar getCalendar(Date date);
    String toDateString(Date date);
    String toDateTimeString(Date date);
    Date convertToDate(String dateString) throws java.text.ParseException;
    Timestamp convertToSqlTimestamp(String dateString) throws java.text.ParseException;
    java.sql.Date convertToSqlDate(String dateString) throws java.text.ParseException;
    Date convertToDateTime(String dateString) throws java.text.ParseException;
    int dateDiff(Date startDate, Date endDate, boolean resetTime);
    String toDateStringForFilename(Date date);
}
