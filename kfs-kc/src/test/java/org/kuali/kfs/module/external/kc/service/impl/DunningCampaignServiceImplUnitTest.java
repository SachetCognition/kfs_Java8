package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableDunningCampaign;
import org.kuali.kfs.integration.cg.dto.HashMapElement;
import org.kuali.kfs.module.external.kc.dto.DunningCampaignDTO;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.ModuleService;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

class DunningCampaignServiceImplUnitTest extends KfsUnitTestBase {

    private DunningCampaignServiceImpl service;

    @Mock private KualiModuleService kualiModuleService;
    @Mock private ModuleService moduleService;

    @BeforeEach
    void setUp() {
        service = new DunningCampaignServiceImpl();
    }

    @Test
    void testGetDunningCampaignDTO_fromSingleObject_null() {
        DunningCampaignDTO result = service.getDunningCampaignDTO((AccountsReceivableDunningCampaign) null);
        assertNull(result);
    }

    @Test
    void testGetDunningCampaignDTO_fromSingleObject_nonNull() {
        AccountsReceivableDunningCampaign campaign = mock(AccountsReceivableDunningCampaign.class);
        when(campaign.getCampaignID()).thenReturn("CAMP1");
        when(campaign.getCampaignDescription()).thenReturn("Test Campaign");
        when(campaign.isActive()).thenReturn(true);

        DunningCampaignDTO result = service.getDunningCampaignDTO(campaign);
        assertNotNull(result);
        assertEquals("CAMP1", result.getCampaignID());
        assertEquals("Test Campaign", result.getCampaignDescription());
        assertTrue(result.isActive());
    }

    @Test
    void testGetDunningCampaignDTO_fromList_null() {
        List<DunningCampaignDTO> result = service.getDunningCampaignDTO((List<AccountsReceivableDunningCampaign>) null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetDunningCampaignDTO_fromList_nonEmpty() {
        AccountsReceivableDunningCampaign c1 = mock(AccountsReceivableDunningCampaign.class);
        when(c1.getCampaignID()).thenReturn("C1");
        when(c1.getCampaignDescription()).thenReturn("Campaign 1");
        when(c1.isActive()).thenReturn(true);

        AccountsReceivableDunningCampaign c2 = mock(AccountsReceivableDunningCampaign.class);
        when(c2.getCampaignID()).thenReturn("C2");
        when(c2.getCampaignDescription()).thenReturn("Campaign 2");
        when(c2.isActive()).thenReturn(false);

        List<DunningCampaignDTO> result = service.getDunningCampaignDTO(Arrays.asList(c1, c2));
        assertEquals(2, result.size());
        assertEquals("C1", result.get(0).getCampaignID());
        assertFalse(result.get(1).isActive());
    }

    @Test
    void testGetDunningCampaignDTO_fromList_empty() {
        List<DunningCampaignDTO> result = service.getDunningCampaignDTO(new ArrayList<AccountsReceivableDunningCampaign>());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
