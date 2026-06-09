package org.kuali.kfs.module.tem.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants.TemProfileProperties;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.module.tem.businessobject.TemProfileAddress;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("TemProfileServiceImpl")
class TemProfileServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private TemProfileServiceImpl temProfileService;

    private TemProfile testProfile;

    @BeforeEach
    void setUp() {
        testProfile = new TemProfile();
        testProfile.setProfileId(1001);
        testProfile.setPrincipalId("P001");
        testProfile.setEmployeeId("EMP001");
    }

    @Test
    @DisplayName("should find profile by principalId")
    void testFindTemProfileByPrincipalId() {
        Map<String, String> criteria = new HashMap<>();
        criteria.put(TemProfileProperties.PRINCIPAL_ID, "P001");

        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(Arrays.asList(testProfile));

        TemProfile result = temProfileService.findTemProfileByPrincipalId("P001");
        assertThat(result).isNotNull();
        assertThat(result.getPrincipalId()).isEqualTo("P001");
    }

    @Test
    @DisplayName("should return null when no profile found by principalId")
    void testFindTemProfileByPrincipalIdNotFound() {
        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        TemProfile result = temProfileService.findTemProfileByPrincipalId("MISSING");
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("should find profile by profileId")
    void testFindTemProfileById() {
        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(Arrays.asList(testProfile));

        TemProfile result = temProfileService.findTemProfileById(1001);
        assertThat(result).isNotNull();
        assertThat(result.getProfileId()).isEqualTo(1001);
    }

    @Test
    @DisplayName("should return null when no profile found by id")
    void testFindTemProfileByIdNotFound() {
        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        TemProfile result = temProfileService.findTemProfileById(9999);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("should find profile with generic criteria map")
    void testFindTemProfile() {
        Map<String, String> criteria = new HashMap<>();
        criteria.put("employeeId", "EMP001");

        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(Arrays.asList(testProfile));

        TemProfile result = temProfileService.findTemProfile(criteria);
        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo("EMP001");
    }

    @Test
    @DisplayName("should create address from person")
    void testCreateTemProfileAddressFromPerson() {
        Person person = mock(Person.class);
        when(person.getAddressLine1()).thenReturn("123 Main St");
        when(person.getAddressLine2()).thenReturn("Suite 100");
        when(person.getAddressCity()).thenReturn("Bloomington");
        when(person.getAddressStateProvinceCode()).thenReturn("IN");
        when(person.getAddressPostalCode()).thenReturn("47405");
        when(person.getAddressCountryCode()).thenReturn("US");

        TemProfileAddress defaultAddress = new TemProfileAddress();
        TemProfileAddress result = temProfileService.createTemProfileAddressFromPerson(
                person, 1001, defaultAddress);

        assertThat(result.getProfileId()).isEqualTo(1001);
        assertThat(result.getStreetAddressLine1()).isEqualTo("123 MAIN ST");
        assertThat(result.getStreetAddressLine2()).isEqualTo("SUITE 100");
        assertThat(result.getCityName()).isEqualTo("BLOOMINGTON");
        assertThat(result.getStateCode()).isEqualTo("IN");
        assertThat(result.getZipCode()).isEqualTo("47405");
        assertThat(result.getCountryCode()).isEqualTo("US");
    }

    @Test
    @DisplayName("should return default address when principalId is empty")
    void testGetAddressFromProfileWithEmptyPrincipalId() {
        TemProfile profile = new TemProfile();
        profile.setPrincipalId("");
        TemProfileAddress defaultAddress = new TemProfileAddress();

        TemProfileAddress result = temProfileService.getAddressFromProfile(profile, defaultAddress);
        assertThat(result).isSameAs(defaultAddress);
    }

    @Test
    @DisplayName("should create new default address when null passed")
    void testGetAddressFromProfileWithNullDefault() {
        TemProfile profile = new TemProfile();
        profile.setPrincipalId("");

        TemProfileAddress result = temProfileService.getAddressFromProfile(profile, null);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("should handle null collection from businessObjectService")
    void testFindTemProfileNullCollection() {
        when(businessObjectService.findMatching(eq(TemProfile.class), any(Map.class)))
                .thenReturn(null);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("profileId", "999");
        TemProfile result = temProfileService.findTemProfile(criteria);
        assertThat(result).isNull();
    }
}
