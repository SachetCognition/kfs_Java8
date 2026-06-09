package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleService;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.AccountAutoCreateDefaults;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

class AccountDefaultServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ContractsAndGrantsModuleService contractsAndGrantsModuleService;
    @InjectMocks private AccountDefaultServiceImpl service;

    @Test
    void testGetAccountDefaults_nullUnit() {
        assertNull(service.getAccountDefaults(null));
    }

    @Test
    void testGetAccountDefaults_emptyUnit() {
        assertNull(service.getAccountDefaults(""));
    }

    @Test
    void testGetAccountDefaults_foundDirectly() {
        AccountAutoCreateDefaults expected = new AccountAutoCreateDefaults();
        when(businessObjectService.findByPrimaryKey(eq(AccountAutoCreateDefaults.class), any(Map.class))).thenReturn(expected);

        AccountAutoCreateDefaults result = service.getAccountDefaults("UNIT1");
        assertSame(expected, result);
    }

    @Test
    void testGetAccountDefaults_notFoundDirectly_foundViaParent() {
        AccountAutoCreateDefaults expected = new AccountAutoCreateDefaults();
        when(businessObjectService.findByPrimaryKey(eq(AccountAutoCreateDefaults.class), any(Map.class)))
                .thenReturn(null)
                .thenReturn(null)
                .thenReturn(expected);
        when(contractsAndGrantsModuleService.getParentUnits("UNIT1"))
                .thenReturn(Arrays.asList("PARENT1", "PARENT2"));

        AccountAutoCreateDefaults result = service.getAccountDefaults("UNIT1");
        assertSame(expected, result);
    }

    @Test
    void testGetAccountDefaults_notFoundDirectly_noParents() {
        when(businessObjectService.findByPrimaryKey(eq(AccountAutoCreateDefaults.class), any(Map.class))).thenReturn(null);
        when(contractsAndGrantsModuleService.getParentUnits("UNIT1")).thenReturn(null);

        assertNull(service.getAccountDefaults("UNIT1"));
    }

    @Test
    void testGetAccountDefaults_notFoundDirectly_emptyParents() {
        when(businessObjectService.findByPrimaryKey(eq(AccountAutoCreateDefaults.class), any(Map.class))).thenReturn(null);
        when(contractsAndGrantsModuleService.getParentUnits("UNIT1")).thenReturn(java.util.Collections.<String>emptyList());

        assertNull(service.getAccountDefaults("UNIT1"));
    }
}
