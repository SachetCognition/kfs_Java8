package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.AccountsReceivableDocumentHeader;
import org.kuali.kfs.module.ar.businessobject.OrganizationOptions;
import org.kuali.kfs.module.ar.businessobject.SystemInformation;
import org.kuali.kfs.module.ar.document.service.SystemInformationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AccountsReceivableDocumentHeaderServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private FinancialSystemUserService financialSystemUserService;

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private SystemInformationService sysInfoService;

    @InjectMocks
    private AccountsReceivableDocumentHeaderServiceImpl service;

    @Test
    void getNewAccountsReceivableDocumentHeader_shouldUseProcessingOrgWhenAvailable() {
        SystemInformation sysInfo = new SystemInformation();
        sysInfo.setProcessingChartOfAccountCode("UA");
        sysInfo.setProcessingOrganizationCode("VPIT");
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(sysInfoService.getByProcessingChartOrgAndFiscalYear("UA", "VPIT", 2024))
                .thenReturn(sysInfo);

        AccountsReceivableDocumentHeader header = service.getNewAccountsReceivableDocumentHeader("UA", "VPIT");
        assertThat(header.getProcessingChartOfAccountCode()).isEqualTo("UA");
        assertThat(header.getProcessingOrganizationCode()).isEqualTo("VPIT");
    }

    @Test
    void getNewAccountsReceivableDocumentHeader_shouldFallBackToOrgOptions() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(sysInfoService.getByProcessingChartOrgAndFiscalYear("UA", "VPIT", 2024))
                .thenReturn(null);

        OrganizationOptions orgOptions = new OrganizationOptions();
        orgOptions.setProcessingChartOfAccountCode("BL");
        orgOptions.setProcessingOrganizationCode("CHEM");
        when(businessObjectService.findByPrimaryKey(eq(OrganizationOptions.class), any(Map.class)))
                .thenReturn(orgOptions);

        AccountsReceivableDocumentHeader header = service.getNewAccountsReceivableDocumentHeader("UA", "VPIT");
        assertThat(header.getProcessingChartOfAccountCode()).isEqualTo("BL");
        assertThat(header.getProcessingOrganizationCode()).isEqualTo("CHEM");
    }

    @Test
    void getNewAccountsReceivableDocumentHeader_shouldThrowWhenNeitherFound() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(sysInfoService.getByProcessingChartOrgAndFiscalYear("UA", "VPIT", 2024))
                .thenReturn(null);
        when(businessObjectService.findByPrimaryKey(eq(OrganizationOptions.class), any(Map.class)))
                .thenReturn(null);

        assertThrows(UnsupportedOperationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                service.getNewAccountsReceivableDocumentHeader("UA", "VPIT");
            }
        });
    }
}
