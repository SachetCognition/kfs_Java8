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
package org.kuali.kfs.sys.rice.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.kuali.kfs.sys.rice.KfsDateTimeService;
import org.kuali.rice.core.api.datetime.DateTimeService;

/**
 * Delegates to Rice DateTimeService.
 */
public class KfsDateTimeServiceImpl implements KfsDateTimeService {

    private DateTimeService dateTimeService;

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public String toDateString(Date date) {
        return dateTimeService.toDateString(date);
    }

    @Override
    public String toDateTimeString(Date date) {
        return dateTimeService.toDateTimeString(date);
    }

    @Override
    public String toString(Date date, String pattern) {
        return dateTimeService.toString(date, pattern);
    }

    @Override
    public Date getCurrentDate() {
        return dateTimeService.getCurrentDate();
    }

    @Override
    public Timestamp getCurrentTimestamp() {
        return dateTimeService.getCurrentTimestamp();
    }

    @Override
    public java.sql.Date getCurrentSqlDate() {
        return dateTimeService.getCurrentSqlDate();
    }

    @Override
    public java.sql.Date getCurrentSqlDateMidnight() {
        return dateTimeService.getCurrentSqlDateMidnight();
    }

    @Override
    public Calendar getCurrentCalendar() {
        return dateTimeService.getCurrentCalendar();
    }

    @Override
    public Calendar getCalendar(Date date) {
        return dateTimeService.getCalendar(date);
    }

    @Override
    public Date convertToDate(String dateString) throws ParseException {
        return dateTimeService.convertToDate(dateString);
    }

    @Override
    public Date convertToDateTime(String dateTimeString) throws ParseException {
        return dateTimeService.convertToDateTime(dateTimeString);
    }

    @Override
    public Timestamp convertToSqlTimestamp(String timestampString) throws ParseException {
        return dateTimeService.convertToSqlTimestamp(timestampString);
    }

    @Override
    public java.sql.Date convertToSqlDate(String dateString) throws ParseException {
        return dateTimeService.convertToSqlDate(dateString);
    }

    @Override
    public java.sql.Date convertToSqlDate(Timestamp timestamp) throws ParseException {
        return dateTimeService.convertToSqlDate(timestamp);
    }

    @Override
    public int dateDiff(Date startDate, Date endDate, boolean inclusive) {
        return dateTimeService.dateDiff(startDate, endDate, inclusive);
    }

    @Override
    public String toDateStringForFilename(Date date) {
        return dateTimeService.toDateStringForFilename(date);
    }

    @Override
    public String toDateTimeStringForFilename(Date date) {
        return dateTimeService.toDateTimeStringForFilename(date);
    }
}
