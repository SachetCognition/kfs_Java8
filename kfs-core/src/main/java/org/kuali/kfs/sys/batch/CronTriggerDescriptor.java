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
package org.kuali.kfs.sys.batch;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class CronTriggerDescriptor extends TriggerDescriptor {
    private String cronExpression;

    @Override
    @SuppressWarnings("unchecked")
    protected Trigger completeTriggerDescription(TriggerBuilder triggerBuilder) {
        String expression;
        if (!isTestMode()) {
            expression = cronExpression;
        } else {
            expression = "0 59 23 31 12 ? 2099";
        }
        return triggerBuilder
                .withSchedule(CronScheduleBuilder.cronSchedule(expression)
                        .inTimeZone(getDateTimeService().getCurrentCalendar().getTimeZone()))
                .build();
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
