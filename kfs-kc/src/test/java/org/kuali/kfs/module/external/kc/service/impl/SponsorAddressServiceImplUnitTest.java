package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.external.kc.businessobject.Agency;
import org.kuali.kfs.module.external.kc.businessobject.AgencyAddress;
import org.kuali.kfs.module.external.kc.service.ExternalizableBusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;

class SponsorAddressServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private ExternalizableBusinessObjectService sponsorService;
    @InjectMocks private SponsorAddressServiceImpl service;

    @Test
    void testFindByPrimaryKey_agencyNull() {
        Map<String, Object> keys = new HashMap<>();
        when(sponsorService.findByPrimaryKey(keys)).thenReturn(null);
        assertNull(service.findByPrimaryKey(keys));
    }

    @Test
    void testFindByPrimaryKey_agencyHasNoAddresses() {
        Map<String, Object> keys = new HashMap<>();
        Agency agency = mock(Agency.class);
        when(agency.getAgencyAddresses()).thenReturn(new ArrayList<AgencyAddress>());
        when(sponsorService.findByPrimaryKey(keys)).thenReturn(agency);
        assertNull(service.findByPrimaryKey(keys));
    }

    @Test
    void testFindByPrimaryKey_agencyHasNullAddresses() {
        Map<String, Object> keys = new HashMap<>();
        Agency agency = mock(Agency.class);
        when(agency.getAgencyAddresses()).thenReturn(null);
        when(sponsorService.findByPrimaryKey(keys)).thenReturn(agency);
        assertNull(service.findByPrimaryKey(keys));
    }

    @Test
    void testFindByPrimaryKey_returnsFirstAddress() {
        Map<String, Object> keys = new HashMap<>();
        Agency agency = mock(Agency.class);
        AgencyAddress addr1 = mock(AgencyAddress.class);
        AgencyAddress addr2 = mock(AgencyAddress.class);
        when(agency.getAgencyAddresses()).thenReturn(Arrays.asList(addr1, addr2));
        when(sponsorService.findByPrimaryKey(keys)).thenReturn(agency);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertSame(addr1, result);
    }

    @Test
    void testFindMatching_collectsAddressesFromMultipleAgencies() {
        Map<String, Object> fieldValues = new HashMap<>();
        Agency agency1 = mock(Agency.class);
        Agency agency2 = mock(Agency.class);
        AgencyAddress addr1 = mock(AgencyAddress.class);
        AgencyAddress addr2 = mock(AgencyAddress.class);
        AgencyAddress addr3 = mock(AgencyAddress.class);
        when(agency1.getAgencyAddresses()).thenReturn(Arrays.asList(addr1, addr2));
        when(agency2.getAgencyAddresses()).thenReturn(Arrays.asList(addr3));
        when(sponsorService.findMatching(fieldValues)).thenReturn(Arrays.asList(agency1, agency2));

        Collection result = service.findMatching(fieldValues);
        assertEquals(3, result.size());
    }

    @Test
    void testFindMatching_emptyResults() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(sponsorService.findMatching(fieldValues)).thenReturn(Collections.emptyList());
        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }
}
