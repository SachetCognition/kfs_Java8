package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.businessobject.TravelCardType;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.service.TemRoleService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelServiceImpl travelService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private TemRoleService temRoleService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Test
    void testValidatePhoneNumber_nullReturnsError() {
        String result = travelService.validatePhoneNumber(null, "ERROR");
        assertEquals("ERROR", result);
    }

    @Test
    void testValidatePhoneNumber_validInternationalFormat() {
        String result = travelService.validatePhoneNumber("123-456-7890", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testValidatePhoneNumber_validWithExtension() {
        String result = travelService.validatePhoneNumber("123-456-7890 x1234", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testValidatePhoneNumber_invalidShortNumber() {
        String result = travelService.validatePhoneNumber("1", "ERROR");
        assertEquals("ERROR", result);
    }

    @Test
    void testValidatePhoneNumber_byCountryCode_nullBoth() {
        String result = travelService.validatePhoneNumber(null, null, "ERROR");
        assertEquals("ERROR", result);
    }

    @Test
    void testValidatePhoneNumber_byCountryCode_UKValid() {
        String result = travelService.validatePhoneNumber("UK", "555-1234", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testValidatePhoneNumber_byCountryCode_USValid() {
        String result = travelService.validatePhoneNumber("US", "123-555-1234", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testValidatePhoneNumber_byCountryCode_USValidWithExtension() {
        String result = travelService.validatePhoneNumber("US", "123-555-1234 x1234", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testValidatePhoneNumber_byCountryCode_USInvalid() {
        String result = travelService.validatePhoneNumber("US", "1", "ERROR");
        assertEquals("ERROR", result);
    }

    @Test
    void testValidatePhoneNumber_blankCountryCodeUsesInternational() {
        String result = travelService.validatePhoneNumber("", "555-1234", "ERROR");
        assertEquals("", result);
    }

    @Test
    void testGetTravelCardTypes_returnsCodesList() {
        List<TravelCardType> cardTypes = new ArrayList();
        TravelCardType type1 = new TravelCardType();
        type1.setCode("CTS");
        TravelCardType type2 = new TravelCardType();
        type2.setCode("CORP");
        cardTypes.add(type1);
        cardTypes.add(type2);

        when(businessObjectService.findAll(TravelCardType.class)).thenReturn(cardTypes);

        List<String> result = travelService.getTravelCardTypes();

        assertEquals(2, result.size());
        assertTrue(result.contains("CTS"));
        assertTrue(result.contains("CORP"));
    }

    @Test
    void testGetTravelCardTypes_emptyList() {
        when(businessObjectService.findAll(TravelCardType.class)).thenReturn(new java.util.ArrayList());

        List<String> result = travelService.getTravelCardTypes();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetParentDocumentTypeNames_authorizationDocument() {
        Set<String> result = travelService.getParentDocumentTypeNames(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT);

        assertTrue(result.contains(TemConstants.TravelDocTypes.TEM_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT));
    }

    @Test
    void testGetParentDocumentTypeNames_amendDocument() {
        Set<String> result = travelService.getParentDocumentTypeNames(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_AMEND_DOCUMENT);

        assertTrue(result.contains(TemConstants.TravelDocTypes.TEM_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT));
    }

    @Test
    void testGetParentDocumentTypeNames_reimbursementDocument() {
        Set<String> result = travelService.getParentDocumentTypeNames(TemConstants.TravelDocTypes.TRAVEL_REIMBURSEMENT_DOCUMENT);

        assertTrue(result.contains(TemConstants.TravelDocTypes.TEM_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_REIMBURSEMENT_DOCUMENT));
    }

    @Test
    void testGetParentDocumentTypeNames_otherDocument() {
        Set<String> result = travelService.getParentDocumentTypeNames(TemConstants.TravelDocTypes.TRAVEL_ENTERTAINMENT_DOCUMENT);

        assertTrue(result.contains(TemConstants.TravelDocTypes.TEM_TRANSACTIONAL_DOCUMENT));
        assertTrue(result.contains(TemConstants.TravelDocTypes.TRAVEL_ENTERTAINMENT_DOCUMENT));
        assertFalse(result.contains(TemConstants.TravelDocTypes.TRAVEL_TRANSACTIONAL_DOCUMENT));
    }

    @Test
    void testIsUserInitiatorOrArranger_userIsInitiator() {
        TravelDocument document = mock(TravelDocument.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);
        Person user = mock(Person.class);

        when(document.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(workflowDoc.getInitiatorPrincipalId()).thenReturn("user1");
        when(user.getPrincipalId()).thenReturn("user1");

        boolean result = travelService.isUserInitiatorOrArranger(document, user);

        assertTrue(result);
    }

    @Test
    void testIsUserInitiatorOrArranger_userIsArranger() {
        TravelDocument document = mock(TravelDocument.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);
        Person user = mock(Person.class);

        when(document.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(workflowDoc.getInitiatorPrincipalId()).thenReturn("user1");
        when(workflowDoc.getDocumentTypeName()).thenReturn("TA");
        when(user.getPrincipalId()).thenReturn("user2");
        when(document.getProfileId()).thenReturn(100);
        when(temRoleService.isTravelDocumentArrangerForProfile("TA", "user2", 100)).thenReturn(true);

        boolean result = travelService.isUserInitiatorOrArranger(document, user);

        assertTrue(result);
    }

    @Test
    void testIsUserInitiatorOrArranger_neitherInitiatorNorArranger() {
        TravelDocument document = mock(TravelDocument.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);
        Person user = mock(Person.class);

        when(document.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(workflowDoc.getInitiatorPrincipalId()).thenReturn("user1");
        when(workflowDoc.getDocumentTypeName()).thenReturn("TA");
        when(user.getPrincipalId()).thenReturn("user2");
        when(document.getProfileId()).thenReturn(100);
        when(temRoleService.isTravelDocumentArrangerForProfile("TA", "user2", 100)).thenReturn(false);

        boolean result = travelService.isUserInitiatorOrArranger(document, user);

        assertFalse(result);
    }
}
