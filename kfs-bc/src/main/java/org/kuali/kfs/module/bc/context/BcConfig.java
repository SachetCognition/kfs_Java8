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
package org.kuali.kfs.module.bc.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Budget Construction (BC) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-bc.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.module.bc.service.impl",
    "org.kuali.kfs.module.bc.dataaccess.impl",
    "org.kuali.kfs.module.bc.batch"
})
@ImportResource({
    "classpath:org/kuali/kfs/module/bc/spring-bc.xml",
    "classpath:org/kuali/kfs/module/bc/spring-bc-bus-exports.xml"
})
public class BcConfig {

    /**
     * Module configuration for the BC module.
     */
    @Bean(name = "bcModuleConfiguration")
    public FinancialSystemModuleConfiguration bcModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-BC");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.module.bc"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/bc/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/bc/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/bc/ojb-bc.xml"
        ));
        return config;
    }

    /**
     * Module service for the BC module.
     */
    @Bean(name = "bcModuleService")
    public KfsModuleServiceImpl bcModuleService(
            FinancialSystemModuleConfiguration bcModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(bcModuleConfiguration);
        return service;
    }
}
