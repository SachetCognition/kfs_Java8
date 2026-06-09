package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.SystemInformation;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class SystemInformationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private SystemInformationServiceImpl systemInformationService;

    @Test
    void getByLockboxNumberForCurrentFiscalYear_shouldDelegateToGetByLockboxNumber() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        SystemInformation sysInfo = new SystemInformation();
        sysInfo.setLockboxNumber("12345");
        Collection<SystemInformation> results = Arrays.asList(sysInfo);
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class))).thenReturn(results);

        SystemInformation result = systemInformationService.getByLockboxNumberForCurrentFiscalYear("12345");
        assertThat(result).isNotNull();
        assertThat(result.getLockboxNumber()).isEqualTo("12345");
    }

    @Test
    void getByLockboxNumber_shouldReturnNullWhenNoResults() {
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(new ArrayList<>());

        SystemInformation result = systemInformationService.getByLockboxNumber("99999", 2024);
        assertThat(result).isNull();
    }

    @Test
    void getByLockboxNumber_shouldReturnSingleResult() {
        SystemInformation sysInfo = new SystemInformation();
        sysInfo.setLockboxNumber("12345");
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(Arrays.asList(sysInfo));

        SystemInformation result = systemInformationService.getByLockboxNumber("12345", 2024);
        assertThat(result).isSameAs(sysInfo);
    }

    @Test
    void getByProcessingChartOrgAndFiscalYear_shouldReturnMatchingResult() {
        SystemInformation sysInfo = new SystemInformation();
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(Arrays.asList(sysInfo));

        SystemInformation result = systemInformationService.getByProcessingChartOrgAndFiscalYear("UA", "VPIT", 2024);
        assertThat(result).isSameAs(sysInfo);
    }

    @Test
    void getByProcessingChartOrgAndFiscalYear_shouldReturnNullWhenNoMatch() {
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(new ArrayList<>());

        SystemInformation result = systemInformationService.getByProcessingChartOrgAndFiscalYear("UA", "VPIT", 2024);
        assertThat(result).isNull();
    }

    @Test
    void getCountByChartOrgAndLockboxNumber_shouldReturnZeroWhenAllMatchChartOrg() {
        SystemInformation sysInfo = new SystemInformation();
        sysInfo.setProcessingChartOfAccountCode("UA");
        sysInfo.setProcessingOrganizationCode("VPIT");
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(Arrays.asList(sysInfo));

        int count = systemInformationService.getCountByChartOrgAndLockboxNumber("UA", "VPIT", "12345");
        assertThat(count).isZero();
    }

    @Test
    void getCountByChartOrgAndLockboxNumber_shouldCountNonMatchingChartOrg() {
        SystemInformation sysInfo = new SystemInformation();
        sysInfo.setProcessingChartOfAccountCode("BL");
        sysInfo.setProcessingOrganizationCode("OTHER");
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(Arrays.asList(sysInfo));

        int count = systemInformationService.getCountByChartOrgAndLockboxNumber("UA", "VPIT", "12345");
        assertThat(count).isEqualTo(1);
    }

    @Test
    void getCountByChartOrgAndLockboxNumber_shouldReturnZeroWhenNoResults() {
        when(businessObjectService.findMatching(eq(SystemInformation.class), any(Map.class)))
                .thenReturn(new ArrayList<>());

        int count = systemInformationService.getCountByChartOrgAndLockboxNumber("UA", "VPIT", "12345");
        assertThat(count).isZero();
    }
}
