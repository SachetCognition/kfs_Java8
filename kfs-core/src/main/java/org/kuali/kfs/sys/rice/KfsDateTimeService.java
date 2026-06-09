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
package org.kuali.kfs.sys.rice;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * KFS-owned date/time service interface. Mirrors Rice DateTimeService
 * to decouple KFS from direct Rice API dependency.
 */
public interface KfsDateTimeService {

    String toDateString(Date date);

    String toDateTimeString(Date date);

    String toString(Date date, String pattern);

    Date getCurrentDate();

    Timestamp getCurrentTimestamp();

    java.sql.Date getCurrentSqlDate();

    java.sql.Date getCurrentSqlDateMidnight();

    Calendar getCurrentCalendar();

    Calendar getCalendar(Date date);

    Date convertToDate(String dateString) throws ParseException;

    Date convertToDateTime(String dateTimeString) throws ParseException;

    Timestamp convertToSqlTimestamp(String timestampString) throws ParseException;

    java.sql.Date convertToSqlDate(String dateString) throws ParseException;

    java.sql.Date convertToSqlDate(Timestamp timestamp) throws ParseException;

    int dateDiff(Date startDate, Date endDate, boolean inclusive);

    String toDateStringForFilename(Date date);

    String toDateTimeStringForFilename(Date date);
}
