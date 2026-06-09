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
package org.kuali.kfs.vnd.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Vendor (VND) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-vnd.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.vnd.service.impl",
    "org.kuali.kfs.vnd.dataaccess.impl",
    "org.kuali.kfs.vnd.batch"
})
@ImportResource({
    "classpath:org/kuali/kfs/vnd/spring-vnd.xml",
    "classpath:org/kuali/kfs/vnd/spring-vnd-bus-exports.xml"
})
public class VndConfig {

    /**
     * Module configuration for the VND module.
     */
    @Bean(name = "vndModuleConfiguration")
    public FinancialSystemModuleConfiguration vndModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-VND");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.vnd"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/vnd/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/vnd/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/vnd/ojb-vnd.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/vnd/dwr-vnd.xml"
        ));
        config.setJobNames(List.of("vendorExcludeJob"));
        return config;
    }

    /**
     * Module service for the VND module.
     */
    @Bean(name = "vndModuleService")
    public KfsModuleServiceImpl vndModuleService(
            FinancialSystemModuleConfiguration vndModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(vndModuleConfiguration);
        return service;
    }
}
