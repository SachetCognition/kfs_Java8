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
package org.kuali.kfs.coa.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Chart of Accounts (COA) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-coa.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.coa.service.impl",
    "org.kuali.kfs.coa.dataaccess.impl"
})
@ImportResource({
    "classpath:org/kuali/kfs/coa/spring-coa.xml",
    "classpath:org/kuali/kfs/coa/spring-coa-bus-exports.xml"
})
public class CoaConfig {

    /**
     * Module configuration for the COA module.
     */
    @Bean(name = "coaModuleConfiguration")
    public FinancialSystemModuleConfiguration coaModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-COA");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.coa"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/coa/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/coa/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/coa/ojb-coa.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/coa/dwr-coa.xml"
        ));
        config.setJobNames(Arrays.asList(
            "populatePriorYearDataJob",
            "addPriorYearAccountsJob"
        ));
        return config;
    }

    /**
     * Module service for the COA module.
     */
    @Bean(name = "coaModuleService")
    public KfsModuleServiceImpl coaModuleService(
            FinancialSystemModuleConfiguration coaModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(coaModuleConfiguration);
        return service;
    }
}
