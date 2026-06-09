package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.integration.cg.dto.AccountCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.AccountParametersDTO;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.AccountAutoCreateDefaults;
import org.kuali.kfs.module.external.kc.service.AccountDefaultsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class AccountCreationServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private DocumentService documentService;
    @Mock private ParameterService parameterService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private AccountDefaultsService accountDefaultsService;
    @Mock private KualiRuleService kualiRuleService;
    @Mock private MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;
    @Mock private AccountService accountService;
    @Mock private ChartService chartService;
    @Mock private PersonService personService;

    private AccountCreationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = spy(new AccountCreationServiceImpl());
        service.setDocumentService(documentService);
        service.setParameterService(parameterService);
        service.setDataDictionaryService(dataDictionaryService);
        service.setBusinessObjectService(businessObjectService);
        service.setAccountDefaultsService(accountDefaultsService);
        service.setKualiRuleService(kualiRuleService);
        service.setMaintenanceDocumentDictionaryService(maintenanceDocumentDictionaryService);
        service.setAccountService(accountService);
        service.setChartService(chartService);
        service.setPersonService(personService);
    }

    private AccountParametersDTO createAccountParameters(String principalId, String unit) {
        AccountParametersDTO params = new AccountParametersDTO();
        params.setPrincipalId(principalId);
        params.setUnit(unit);
        params.setAccountNumber("1031400");
        params.setAccountName("Test Account");
        params.setExpenseGuidelineText("expense");
        params.setIncomeGuidelineText("income");
        params.setPurposeText("purpose");
        params.setEffectiveDate(new Date());
        params.setExpirationDate(new Date());
        return params;
    }

    @Test
    void testCreateAccountObject() {
        AccountParametersDTO params = createAccountParameters("USER1", "UNIT1");
        params.setOffCampusIndicator(true);
        params.setCfdaNumber("12.345");

        AccountAutoCreateDefaults defaults = new AccountAutoCreateDefaults();
        defaults.setChartOfAccountsCode("BL");
        defaults.setOrganizationCode("ORG1");
        defaults.setAccountPhysicalCampusCode("BL");
        defaults.setAccountTypeCode("EX");
        defaults.setSubFundGroupCode("CG");
        defaults.setBudgetRecordingLevelCode("A");
        defaults.setAccountSufficientFundsCode("C");

        when(parameterService.getParameterValueAsBoolean(any(Class.class), anyString())).thenReturn(false);

        Account account = service.createAccountObject(params, defaults);
        assertEquals("BL", account.getChartOfAccountsCode());
        assertEquals("ORG1", account.getOrganizationCode());
        assertEquals("1031400", account.getAccountNumber());
        assertEquals("Test Account", account.getAccountName());
        assertTrue(account.isAccountOffCampusIndicator());
        assertEquals("12.345", account.getAccountCfdaNumber());
        assertFalse(account.isClosed());
    }

    @Test
    void testCreateAccountObject_kcOverridesKfs_piAddress() {
        AccountParametersDTO params = createAccountParameters("USER1", "UNIT1");
        params.setDefaultAddressStreetAddress("123 Main St");
        params.setDefaultAddressCityName("Springfield");
        params.setDefaultAddressStateCode("IL");
        params.setDefaultAddressZipCode("62701");

        AccountAutoCreateDefaults defaults = new AccountAutoCreateDefaults();
        defaults.setChartOfAccountsCode("BL");
        defaults.setOrganizationCode("ORG1");
        defaults.setAccountPhysicalCampusCode("BL");

        when(parameterService.getParameterValueAsBoolean(any(Class.class), eq(KcConstants.AccountCreationService.PARAMETER_KC_OVERRIDES_KFS_DEFAULT_ACCOUNT_IND)))
                .thenReturn(true);
        when(parameterService.getParameterValuesAsString(any(Class.class), eq(KcConstants.AccountCreationService.PARAMETER_KC_ACCOUNT_ADDRESS_TYPE)))
                .thenReturn(java.util.Arrays.asList(KcConstants.AccountCreationService.PI_ADDRESS_TYPE));

        Account account = service.createAccountObject(params, defaults);
        assertEquals("123 Main St", account.getAccountStreetAddress());
        assertEquals("Springfield", account.getAccountCityName());
    }

    @Test
    void testSetFailStatus() {
        AccountCreationStatusDTO status = new AccountCreationStatusDTO();
        status.setErrorMessages(new java.util.ArrayList<String>());
        status.setStatus(KcConstants.KcWebService.STATUS_KC_SUCCESS);

        service.setFailStatus(status, "Error occurred");
        assertEquals(KcConstants.KcWebService.STATUS_KC_FAILURE, status.getStatus());
        assertTrue(status.getErrorMessages().contains("Error occurred"));
    }

}
