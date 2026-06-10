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

import java.util.Properties;

import javax.sql.DataSource;

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.ksb.messaging.quartz.MessageServiceExecutorJobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

/**
 * This class wraps the spring version to allow deploy time determination of whether to actually create a scheduler and whether to
 * use the jdbc or ram job store.
 */
public class SchedulerFactoryBean extends org.springframework.scheduling.quartz.SchedulerFactoryBean {
    private static final Scheduler SCHEDULER_DUMMY = new SchedulerDummy();
    private boolean useQuartzScheduling;
    private boolean useJdbcJobstore;
    private Properties quartzPropertiesReference;

    @Override
    public void destroy() throws SchedulerException {  }

    @Override
    public void afterPropertiesSet() throws Exception {  }

    @Override
    public Scheduler getObject() { return null; }

    /**
     * @see org.springframework.scheduling.quartz.SchedulerFactoryBean#createScheduler(org.quartz.SchedulerFactory, java.lang.String)
     */
    @Override
    protected Scheduler createScheduler(SchedulerFactory schedulerFactory, String schedulerName) throws SchedulerException { return null; }

    /**
     * Sets the useJdbcJobstore attribute value.
     *
     * @param useJdbcJobstore The useJdbcJobstore to set.
     */
    public void setUseJdbcJobstore(boolean useJdbcJobstore) {  }

    /**
     * Sets the useQuartzScheduling attribute value.
     *
     * @param useQuartzScheduling The useQuartzScheduling to set.
     */
    public void setUseQuartzScheduling(boolean useQuartzScheduling) {  }

    /**
     * Sets the quartzPropertiesReference attribute value.
     *
     * @param quartzPropertiesReference The quartzPropertiesReference to set.
     */
    public void setQuartzPropertiesReference(Properties quartzPropertiesReference) {  }
}
