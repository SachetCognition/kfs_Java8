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
package org.kuali.kfs.module.purap.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Purchasing/Accounts Payable (PURAP) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-purap.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.module.purap.service.impl",
    "org.kuali.kfs.module.purap.dataaccess.impl",
    "org.kuali.kfs.module.purap.batch",
    "org.kuali.kfs.module.purap.document.service.impl"
})
@ImportResource({
    "classpath:org/kuali/kfs/module/purap/spring-purap.xml",
    "classpath:org/kuali/kfs/module/purap/spring-purap-bus-exports.xml"
})
public class PurapConfig {

    /**
     * Module configuration for the PURAP module.
     */
    @Bean(name = "purapModuleConfiguration")
    public FinancialSystemModuleConfiguration purapModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-PURAP");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.module.purap"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/purap/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/purap/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/purap/ojb-purap.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/module/purap/dwr-purap.xml"
        ));
        return config;
    }

    /**
     * Module service for the PURAP module.
     */
    @Bean(name = "purapModuleService")
    public KfsModuleServiceImpl purapModuleService(
            FinancialSystemModuleConfiguration purapModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(purapModuleConfiguration);
        return service;
    }
}
