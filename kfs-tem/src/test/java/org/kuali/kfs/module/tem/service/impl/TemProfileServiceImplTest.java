package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants.TemProfileProperties;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.module.tem.businessobject.TemProfileAddress;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TemProfileServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TemProfileServiceImpl temProfileService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersonService personService;

    @Test
    void testFindTemProfileByPrincipalId_found() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemProfileProperties.PRINCIPAL_ID, "P001");

        TemProfile profile = new TemProfile();
        Collection<TemProfile> profiles = new ArrayList();
        profiles.add(profile);

        when(businessObjectService.findMatching(TemProfile.class, criteria)).thenReturn(profiles);

        TemProfile result = temProfileService.findTemProfileByPrincipalId("P001");

        assertSame(profile, result);
    }

    @Test
    void testFindTemProfileByPrincipalId_notFound() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemProfileProperties.PRINCIPAL_ID, "P002");

        when(businessObjectService.findMatching(TemProfile.class, criteria)).thenReturn(new java.util.ArrayList());

        TemProfile result = temProfileService.findTemProfileByPrincipalId("P002");

        assertNull(result);
    }

    @Test
    void testFindTemProfileById_found() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemProfileProperties.PROFILE_ID, "100");

        TemProfile profile = new TemProfile();
        Collection<TemProfile> profiles = new ArrayList();
        profiles.add(profile);

        when(businessObjectService.findMatching(TemProfile.class, criteria)).thenReturn(profiles);

        TemProfile result = temProfileService.findTemProfileById(100);

        assertSame(profile, result);
    }

    @Test
    void testFindTemProfileById_notFound() {
        Map<String, String> criteria = new HashMap();
        criteria.put(TemProfileProperties.PROFILE_ID, "999");

        when(businessObjectService.findMatching(TemProfile.class, criteria)).thenReturn(new java.util.ArrayList());

        TemProfile result = temProfileService.findTemProfileById(999);

        assertNull(result);
    }

    @Test
    void testFindTemProfile_nullResults() {
        Map<String, String> criteria = new HashMap();
        criteria.put("key", "value");

        when(businessObjectService.findMatching(TemProfile.class, criteria)).thenReturn(null);

        TemProfile result = temProfileService.findTemProfile(criteria);

        assertNull(result);
    }

    @Test
    void testGetAddressFromProfile_nullDefaultAddress() {
        TemProfile profile = new TemProfile();
        profile.setPrincipalId("");

        TemProfileAddress result = temProfileService.getAddressFromProfile(profile, null);

        assertNotNull(result);
    }

    @Test
    void testGetAddressFromProfile_emptyPrincipalId() {
        TemProfile profile = new TemProfile();
        profile.setPrincipalId("");

        TemProfileAddress defaultAddress = new TemProfileAddress();

        TemProfileAddress result = temProfileService.getAddressFromProfile(profile, defaultAddress);

        assertSame(defaultAddress, result);
    }
}
