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
package org.kuali.kfs.pdp.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Pre-Disbursement Processor (PDP) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-pdp.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.pdp.service.impl",
    "org.kuali.kfs.pdp.dataaccess.impl",
    "org.kuali.kfs.pdp.batch"
})
@ImportResource({
    "classpath:org/kuali/kfs/pdp/spring-pdp.xml",
    "classpath:org/kuali/kfs/pdp/spring-pdp-bus-exports.xml"
})
public class PdpConfig {

    /**
     * Module configuration for the PDP module.
     */
    @Bean(name = "pdpModuleConfiguration")
    public FinancialSystemModuleConfiguration pdpModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-PDP");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.pdp"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/pdp/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/pdp/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/pdp/ojb-pdp.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/pdp/dwr-pdp.xml"
        ));
        config.setJobNames(Arrays.asList(
            "pdpLoadFederalReserveBankDataJob",
            "pdpInactivatePayeeAchAccountsJob",
            "pdpExtractGlTransactionsStepJob",
            "pdpExtractAchPaymentsJob",
            "pdpExtractCanceledChecksJob",
            "pdpExtractChecksJob",
            "pdpDailyReportJob",
            "pdpLoadPaymentsJob",
            "pdpNightlyLoadPaymentsJob",
            "pdpClearPendingTransactionsJob",
            "processPdpCancelsAndPaidJob",
            "pdpSendAchAdviceNotificationsJob"
        ));
        return config;
    }

    /**
     * Module service for the PDP module.
     */
    @Bean(name = "pdpModuleService")
    public KfsModuleServiceImpl pdpModuleService(
            FinancialSystemModuleConfiguration pdpModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(pdpModuleConfiguration);
        return service;
    }
}
