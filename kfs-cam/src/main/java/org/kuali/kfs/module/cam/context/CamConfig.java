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
package org.kuali.kfs.module.cam.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Capital Asset Management (CAM) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-cam.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.module.cam.service.impl",
    "org.kuali.kfs.module.cam.dataaccess.impl",
    "org.kuali.kfs.module.cam.batch",
    "org.kuali.kfs.module.cam.document.service.impl"
})
@ImportResource({
    "classpath:org/kuali/kfs/module/cam/spring-cam.xml",
    "classpath:org/kuali/kfs/module/cam/spring-cam-bus-exports.xml",
    "classpath:org/kuali/kfs/module/cab/spring-cab.xml"
})
public class CamConfig {

    /**
     * Module configuration for the CAM module.
     */
    @Bean(name = "camModuleConfiguration")
    public FinancialSystemModuleConfiguration camModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-CAM");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(Arrays.asList(
            "org.kuali.kfs.module.cam",
            "org.kuali.kfs.integration.cam"
        ));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/cam/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/cam/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/cam/ojb-cam.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/module/cam/dwr-cam.xml"
        ));
        return config;
    }

    /**
     * Module service for the CAM module.
     */
    @Bean(name = "camModuleService")
    public KfsModuleServiceImpl camModuleService(
            FinancialSystemModuleConfiguration camModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(camModuleConfiguration);
        return service;
    }

    /**
     * Module configuration for the Capital Asset Builder (CAB) module.
     */
    @Bean(name = "cabModuleConfiguration")
    public FinancialSystemModuleConfiguration cabModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-CAB");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(Arrays.asList(
            "org.kuali.kfs.module.cab",
            "org.kuali.kfs.integration.cab"
        ));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/module/cab/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/module/cab/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/module/cab/ojb-cab.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/module/cab/dwr-cab.xml"
        ));
        config.setJobNames(Arrays.asList(
            "cabExtractJob",
            "preAssetTaggingExtractJob"
        ));
        config.setTriggerNames(List.of(
            "preAssetTaggingExtractJobTrigger"
        ));
        return config;
    }

    /**
     * Module service for the CAB module.
     */
    @Bean(name = "cabModuleService")
    public KfsModuleServiceImpl cabModuleService(
            FinancialSystemModuleConfiguration cabModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(cabModuleConfiguration);
        return service;
    }
}
