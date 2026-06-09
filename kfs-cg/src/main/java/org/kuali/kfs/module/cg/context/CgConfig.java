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
package org.kuali.kfs.module.cg.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Contracts and Grants (CG) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-cg.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.module.cg.service.impl",
    "org.kuali.kfs.module.cg.dataaccess.impl",
    "org.kuali.kfs.module.cg.batch"
})
@ImportResource({
    "classpath:org/kuali/kfs/module/cg/spring-cg.xml",
    "classpath:org/kuali/kfs/module/cg/spring-cg-bus-exports.xml"
})
public class CgConfig {

    /**
     * Module configuration for the CG module.
     */
    @Bean(name = "cgModuleConfiguration")
    public FinancialSystemModuleConfiguration cgModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-CG");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.module.cg"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/cg/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/cg/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/cg/ojb-cg.xml"
        ));
        return config;
    }

    /**
     * Module service for the CG module.
     */
    @Bean(name = "cgModuleService")
    public KfsModuleServiceImpl cgModuleService(
            FinancialSystemModuleConfiguration cgModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(cgModuleConfiguration);
        return service;
    }
}
