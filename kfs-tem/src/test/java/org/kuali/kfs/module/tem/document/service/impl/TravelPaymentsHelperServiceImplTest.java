package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.TravelPayment;
import org.kuali.kfs.module.tem.businessobject.TravelerDetail;
import org.kuali.kfs.module.tem.service.TravelerService;
import org.kuali.kfs.pdp.businessobject.PaymentGroup;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelPaymentsHelperServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelPaymentsHelperServiceImpl paymentsHelperService;

    @Mock
    private PersonService personService;

    @Mock
    private WorkflowDocumentService workflowDocumentService;

    @Mock
    private TravelerService travelerService;

    @Test
    void testFindCampusForInitiator_cached() {
        Map<String, String> initiatorCampuses = new HashMap();
        initiatorCampuses.put("user1", "BL");

        String result = paymentsHelperService.findCampusForInitiator("user1", initiatorCampuses);

        assertEquals("BL", result);
        verifyNoInteractions(personService);
    }

    @Test
    void testFindCampusForInitiator_notCached() {
        Map<String, String> initiatorCampuses = new HashMap();

        Person person = mock(Person.class);
        when(person.getCampusCode()).thenReturn("IN");
        when(personService.getPerson("user1")).thenReturn(person);

        String result = paymentsHelperService.findCampusForInitiator("user1", initiatorCampuses);

        assertEquals("IN", result);
        assertEquals("IN", initiatorCampuses.get("user1"));
    }

    @Test
    void testBuildGenericPaymentGroup() {
        TravelerDetail traveler = new TravelerDetail();
        traveler.setCityName("Bloomington");
        traveler.setCountryCode("US");
        traveler.setStreetAddressLine1("123 Main St");
        traveler.setStreetAddressLine2("Suite 100");
        traveler.setFirstName("John");
        traveler.setLastName("Doe");
        traveler.setStateCode("IN");
        traveler.setZipCode("47405");

        TemProfile profile = new TemProfile();

        TravelPayment payment = new TravelPayment();
        payment.setDueDate(new java.sql.Date(System.currentTimeMillis()));
        payment.setImmediatePaymentIndicator(false);
        payment.setAttachmentCode(false);
        payment.setSpecialHandlingCode(false);
        payment.setAlienPaymentCode(false);

        PaymentGroup result = paymentsHelperService.buildGenericPaymentGroup(traveler, profile, payment, "BANK1");

        assertEquals("Bloomington", result.getCity());
        assertEquals("US", result.getCountry());
        assertEquals("123 Main St", result.getLine1Address());
        assertEquals("John Doe", result.getPayeeName());
        assertEquals("IN", result.getState());
        assertEquals("47405", result.getZipCd());
        assertEquals("BANK1", result.getBankCode());
    }
}
