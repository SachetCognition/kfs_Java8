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
package org.kuali.kfs.sys.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the KFS System (SYS) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-sys.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration
 * of remaining beans.</p>
 *
 * @see org.kuali.kfs.sys.FinancialSystemModuleConfiguration
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.sys.service.impl",
    "org.kuali.kfs.sys.batch",
    "org.kuali.kfs.sys.document.service.impl"
})
@ImportResource({
    "classpath:org/kuali/kfs/sys/spring-sys.xml",
    "classpath:org/kuali/kfs/sys/spring-sys-bus-exports.xml"
})
public class SysConfig {

    /**
     * Module configuration for the Financial System (SYS) module.
     */
    @Bean(name = "financialSystemModuleConfiguration")
    public FinancialSystemModuleConfiguration financialSystemModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-SYS");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(Arrays.asList(
            "org.kuali.kfs.sys",
            "org.kuali.kfs.sys.businessobject"
        ));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/sys/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/sys/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/sys/ojb-sys.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/sys/dwr-sys.xml"
        ));
        config.setJobNames(Arrays.asList(
            "scheduleJob", "purgeJob", "fiscalYearMakerJob", "clearCacheJob",
            "purgeReportsAndStagingJob", "autoDisapproveJob", "dailyEmailJob",
            "weeklyEmailJob", "modulesLockJob", "modulesUnlockJob",
            "populateFinancialSystemDocumentHeadersFromKewJob"
        ));
        config.setTriggerNames(List.of("scheduleJobTrigger"));
        return config;
    }

    /**
     * Module service for the SYS module.
     */
    @Bean(name = "financialSystemModuleService")
    public KfsModuleServiceImpl financialSystemModuleService(
            FinancialSystemModuleConfiguration financialSystemModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(financialSystemModuleConfiguration);
        return service;
    }
}
