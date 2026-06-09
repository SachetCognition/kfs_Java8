package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemParameterConstants;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.module.tem.businessobject.TravelerDetail;
import org.kuali.kfs.module.tem.businessobject.TravelerDetailEmergencyContact;
import org.kuali.kfs.module.tem.service.TemRoleService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelerServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelerServiceImpl travelerService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private PersonService personService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private TemRoleService temRoleService;

    @Test
    void testConvertToTraveler_fromPerson() {
        Person person = mock(Person.class);
        when(person.getPrincipalId()).thenReturn("P001");
        when(person.getPrincipalName()).thenReturn("jdoe");
        when(person.getFirstName()).thenReturn("John");
        when(person.getLastName()).thenReturn("Doe");
        when(person.getAddressLine1()).thenReturn("123 Main St");
        when(person.getAddressLine2()).thenReturn("Apt 4");
        when(person.getAddressStateProvinceCode()).thenReturn("CA");
        when(person.getAddressPostalCode()).thenReturn("90210");
        when(person.getAddressCity()).thenReturn("Los Angeles");
        when(person.getAddressCountryCode()).thenReturn("US");
        when(person.getEmailAddress()).thenReturn("jdoe@test.com");
        when(person.getPhoneNumber()).thenReturn("555-1234");

        TravelerDetail result = travelerService.convertToTraveler(person);

        assertEquals("P001", result.getPrincipalId());
        assertEquals("jdoe", result.getPrincipalName());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123 Main St", result.getStreetAddressLine1());
        assertEquals("Apt 4", result.getStreetAddressLine2());
        assertEquals("CA", result.getStateCode());
        assertEquals("90210", result.getZipCode());
        assertEquals("Los Angeles", result.getCityName());
        assertEquals("US", result.getCountryCode());
        assertEquals("jdoe@test.com", result.getEmailAddress());
        assertEquals("555-1234", result.getPhoneNumber());
        assertEquals(TemConstants.EMP_TRAVELER_TYP_CD, result.getTravelerTypeCode());
    }

    @Test
    void testIsEmployee_true() {
        when(parameterService.getParameterValueAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.EMPLOYEE_TRAVELER_TYPE_CODES))
                .thenReturn("EMP,FACL");

        TravelerDetail traveler = new TravelerDetail();
        traveler.setTravelerTypeCode("EMP");

        assertTrue(travelerService.isEmployee(traveler));
    }

    @Test
    void testIsEmployee_false() {
        when(parameterService.getParameterValueAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.EMPLOYEE_TRAVELER_TYPE_CODES))
                .thenReturn("EMP,FACL");

        TravelerDetail traveler = new TravelerDetail();
        traveler.setTravelerTypeCode("NON");

        assertFalse(travelerService.isEmployee(traveler));
    }

    @Test
    void testIsEmployee_nullTravelerTypeCode() {
        when(parameterService.getParameterValueAsString(TemParameterConstants.TEM_DOCUMENT.class, TemConstants.TravelParameters.EMPLOYEE_TRAVELER_TYPE_CODES))
                .thenReturn("EMP,FACL");

        TravelerDetail traveler = new TravelerDetail();
        traveler.setTravelerTypeCode(null);

        assertFalse(travelerService.isEmployee(traveler));
    }

    @Test
    void testCopyTravelerDetailEmergencyContact_nullList() {
        List<TravelerDetailEmergencyContact> result = travelerService.copyTravelerDetailEmergencyContact(null, "DOC001");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCopyTravelerDetailEmergencyContact_emptyList() {
        List<TravelerDetailEmergencyContact> result = travelerService.copyTravelerDetailEmergencyContact(new ArrayList(), "DOC001");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCanIncludeProfileInSearch_arrangerDoc() {
        TemProfile profile = new TemProfile();
        Person user = mock(Person.class);

        assertTrue(travelerService.canIncludeProfileInSearch(profile, "TA", user, false, false, false, true, false));
    }

    @Test
    void testCanIncludeProfileInSearch_riskManagement() {
        TemProfile profile = new TemProfile();
        Person user = mock(Person.class);

        assertTrue(travelerService.canIncludeProfileInSearch(profile, "TA", user, false, false, false, false, true));
    }

    @Test
    void testCanIncludeProfileInSearch_selfLookup() {
        TemProfile profile = new TemProfile();
        profile.setPrincipalId("user1");
        Person user = mock(Person.class);
        when(user.getPrincipalId()).thenReturn("user1");

        assertTrue(travelerService.canIncludeProfileInSearch(profile, "TA", user, false, false, false, false, false));
    }

    @Test
    void testConvertToTemProfileFromKim() {
        Person person = mock(Person.class);
        when(person.getPrincipalId()).thenReturn("P001");
        when(person.getEmployeeId()).thenReturn("E001");
        when(person.getPrincipalName()).thenReturn("jdoe");
        when(person.getEntityId()).thenReturn("ENT001");
        when(person.getFirstNameUnmasked()).thenReturn("John");
        when(person.getMiddleNameUnmasked()).thenReturn("M");
        when(person.getLastNameUnmasked()).thenReturn("Doe");
        when(person.getEmailAddressUnmasked()).thenReturn("jdoe@test.com");
        when(person.getPhoneNumber()).thenReturn("555-1234");
        when(person.getEmployeeStatusCode()).thenReturn("A");
        when(person.getEmployeeTypeCode()).thenReturn("P");
        when(person.getPrimaryDepartmentCode()).thenReturn("CS-CSE");
        when(person.getCampusCode()).thenReturn("BL");
        when(person.isActive()).thenReturn(true);

        org.kuali.kfs.module.tem.businessobject.TemProfileFromKimPerson result = travelerService.convertToTemProfileFromKim(person);

        assertEquals("P001", result.getPrincipalId());
        assertEquals("E001", result.getEmployeeId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("jdoe@test.com", result.getEmailAddress());
        assertTrue(result.isActive());
    }
}
