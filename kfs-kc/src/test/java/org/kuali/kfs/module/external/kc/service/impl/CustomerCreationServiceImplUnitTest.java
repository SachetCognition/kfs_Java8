package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableCustomerType;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAgency;
import org.kuali.kfs.module.external.kc.dto.CustomerCreationStatusDto;
import org.kuali.kfs.module.external.kc.dto.CustomerTypeDto;
import org.kuali.kfs.module.external.kc.dto.RolodexDTO;
import org.kuali.kfs.module.external.kc.dto.SponsorDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.KeyValuesService;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerCreationServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private ConfigurationService configurationService;
    @Mock private AccountsReceivableModuleService accountsReceivableModuleService;
    @Mock private KeyValuesService keyValuesService;

    private CustomerCreationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CustomerCreationServiceImpl();
        service.setConfigurationService(configurationService);
        service.setAccountsReceivableModuleService(accountsReceivableModuleService);
        service.setKeyValuesService(keyValuesService);
    }

    @Test
    void testCreateCustomer_catchesExceptionAndReturnsErrors() throws Exception {
        SponsorDTO sponsor = new SponsorDTO();
        CustomerCreationStatusDto result = service.createCustomer(sponsor, "testUser");
        assertNotNull(result);
        assertNull(result.getCustomerNumber());
        assertNotNull(result.getErrors());
        assertFalse(result.getErrors().isEmpty());
    }

    @Test
    void testGetCustomerTypes_empty() {
        when(keyValuesService.findAll(AccountsReceivableCustomerType.class)).thenReturn(new ArrayList());

        List<CustomerTypeDto> result = service.getCustomerTypes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCustomerTypes_withTypes() {
        AccountsReceivableCustomerType type1 = mock(AccountsReceivableCustomerType.class);
        when(type1.getCustomerTypeCode()).thenReturn("GOV");
        when(type1.getCustomerTypeDescription()).thenReturn("Government");

        AccountsReceivableCustomerType type2 = mock(AccountsReceivableCustomerType.class);
        when(type2.getCustomerTypeCode()).thenReturn("EDU");
        when(type2.getCustomerTypeDescription()).thenReturn("Education");

        Collection types = Arrays.asList(type1, type2);
        when(keyValuesService.findAll(AccountsReceivableCustomerType.class)).thenReturn(types);

        List<CustomerTypeDto> result = service.getCustomerTypes();
        assertEquals(2, result.size());
        assertEquals("GOV", result.get(0).getCustomerTypeCode());
        assertEquals("Education", result.get(1).getCustomerTypeDescription());
    }
}
