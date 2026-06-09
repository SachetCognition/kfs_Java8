package org.kuali.kfs.module.ec.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class EffortCertificationReportDefinitionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private EffortCertificationReportDefinitionServiceImpl service;

    @Nested
    @DisplayName("findReportDefinitionByPrimaryKey")
    class FindByPrimaryKey {

        @Test
        void shouldReturnReportDefinitionWhenFound() {
            EffortCertificationReportDefinition expected = new EffortCertificationReportDefinition();
            expected.setUniversityFiscalYear(2024);
            expected.setEffortCertificationReportNumber("A01");

            Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("universityFiscalYear", "2024");
            fieldValues.put("effortCertificationReportNumber", "A01");

            when(businessObjectService.findByPrimaryKey(
                    eq(EffortCertificationReportDefinition.class), any(Map.class)))
                    .thenReturn(expected);

            EffortCertificationReportDefinition result = service.findReportDefinitionByPrimaryKey(fieldValues);

            assertThat(result).isNotNull();
            assertThat(result.getUniversityFiscalYear()).isEqualTo(2024);
            assertThat(result.getEffortCertificationReportNumber()).isEqualTo("A01");
        }

        @Test
        void shouldReturnNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(
                    eq(EffortCertificationReportDefinition.class), any(Map.class)))
                    .thenReturn(null);

            Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("universityFiscalYear", "9999");
            fieldValues.put("effortCertificationReportNumber", "Z99");

            EffortCertificationReportDefinition result = service.findReportDefinitionByPrimaryKey(fieldValues);

            assertThat(result).isNull();
        }
    }
}
