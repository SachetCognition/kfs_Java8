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
package org.kuali.kfs.module.ec.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Effort Certification (EC) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-ec.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.module.ec.service.impl",
    "org.kuali.kfs.module.ec.dataaccess.impl",
    "org.kuali.kfs.module.ec.batch"
})
@ImportResource("classpath:org/kuali/kfs/module/ec/spring-ec.xml")
public class EcConfig {

    /**
     * Module configuration for the EC module.
     */
    @Bean(name = "ecModuleConfiguration")
    public FinancialSystemModuleConfiguration ecModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-EC");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(Arrays.asList(
            "org.kuali.kfs.module.ec",
            "org.kuali.kfs.integration.ec"
        ));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/ec/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/ec/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/ec/ojb-ec.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/module/ec/dwr-ec.xml"
        ));
        return config;
    }

    /**
     * Module service for the EC module.
     */
    @Bean(name = "ecModuleService")
    public KfsModuleServiceImpl ecModuleService(
            FinancialSystemModuleConfiguration ecModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(ecModuleConfiguration);
        return service;
    }
}
