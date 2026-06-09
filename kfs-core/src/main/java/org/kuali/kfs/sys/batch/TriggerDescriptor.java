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

import org.kuali.rice.core.api.datetime.DateTimeService;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.BeanNameAware;

public abstract class TriggerDescriptor implements BeanNameAware {
    private String name;
    private String group;
    private String jobName;
    private DateTimeService dateTimeService;
    private boolean testMode = false;

    @SuppressWarnings("unchecked")
    protected abstract Trigger completeTriggerDescription(TriggerBuilder triggerBuilder);

    public Trigger getTrigger() {
        @SuppressWarnings("unchecked")
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .forJob(jobName, group)
                .startAt(dateTimeService.getCurrentDate());
        return completeTriggerDescription(triggerBuilder);
    }

    /**
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    public void setBeanName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    protected String getJobName() {
        return jobName;
    }

    protected String getName() {
        return name;
    }

    protected String getGroup() {
        return group;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    protected DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
