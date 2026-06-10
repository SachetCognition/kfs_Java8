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
package org.kuali.kfs.fp.context;

import org.kuali.kfs.sys.FinancialSystemModuleConfiguration;
import org.kuali.kfs.sys.service.impl.KfsModuleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Java configuration for the Financial Processing (FP) module.
 *
 * <p>Replaces bean definitions previously in {@code spring-fp.xml}.
 * The XML file is retained via {@link ImportResource} for gradual migration.</p>
 */
@Configuration
@ComponentScan(basePackages = {
    "org.kuali.kfs.fp.service.impl",
    "org.kuali.kfs.fp.document.service.impl",
    "org.kuali.kfs.fp.dataaccess.impl",
    "org.kuali.kfs.fp.batch"
})
@ImportResource({
    "classpath:org/kuali/kfs/fp/spring-fp.xml",
    "classpath:org/kuali/kfs/fp/spring-fp-bus-exports.xml"
})
public class FpConfig {

    /**
     * Module configuration for the FP module.
     */
    @Bean(name = "fpModuleConfiguration")
    public FinancialSystemModuleConfiguration fpModuleConfiguration() {
        FinancialSystemModuleConfiguration config = new FinancialSystemModuleConfiguration();
        config.setNamespaceCode("KFS-FP");
        config.setInitializeDataDictionary(true);
        config.setPackagePrefixes(List.of("org.kuali.kfs.fp"));
        config.setDataDictionaryPackages(Arrays.asList(
            "classpath:org/kuali/kfs/fp/businessobject/datadictionary/*.xml",
            "classpath:org/kuali/kfs/fp/document/datadictionary/*.xml"
        ));
        config.setDatabaseRepositoryFilePaths(List.of(
            "org/kuali/kfs/fp/ojb-fp.xml"
        ));
        config.setScriptConfigurationFilePaths(List.of(
            "org/kuali/kfs/fp/dwr-fp.xml"
        ));
        config.setJobNames(Arrays.asList(
            "procurementCardDocumentJob",
            "disbursementVoucherPreDisbursementProcessorExtractJob",
            "populateProcurementCardDefaultIdsJob"
        ));
        return config;
    }

    /**
     * Module service for the FP module.
     */
    @Bean(name = "fpModuleService")
    public KfsModuleServiceImpl fpModuleService(
            FinancialSystemModuleConfiguration fpModuleConfiguration) {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        service.setModuleConfiguration(fpModuleConfiguration);
        return service;
    }
}
