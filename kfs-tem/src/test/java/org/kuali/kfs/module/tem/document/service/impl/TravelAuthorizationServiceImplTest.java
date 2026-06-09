package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.module.tem.dataaccess.TravelAuthorizationDao;
import org.kuali.kfs.module.tem.document.TravelAuthorizationAmendmentDocument;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.module.tem.document.TravelReimbursementDocument;
import org.kuali.kfs.module.tem.document.service.AccountingDocumentRelationshipService;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.module.tem.service.TemProfileService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelAuthorizationServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelAuthorizationServiceImpl travelAuthorizationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private AccountingDocumentRelationshipService accountingDocumentRelationshipService;

    @Mock
    private TemProfileService temProfileService;

    @Mock
    private TravelAuthorizationDao travelAuthorizationDao;

    @Test
    void testFind_returnsMatchingDocuments() {
        String travelDocId = "12345";
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        Collection<TravelAuthorizationDocument> expected = new ArrayList();
        expected.add(mock(TravelAuthorizationDocument.class));

        when(businessObjectService.findMatching(TravelAuthorizationDocument.class, criteria)).thenReturn(expected);

        Collection<TravelAuthorizationDocument> result = travelAuthorizationService.find(travelDocId);

        assertEquals(1, result.size());
    }

    @Test
    void testFindAmendment_returnsMatchingDocuments() {
        Integer travelDocId = 12345;
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        Collection<TravelAuthorizationAmendmentDocument> expected = new ArrayList();
        expected.add(mock(TravelAuthorizationAmendmentDocument.class));

        when(businessObjectService.findMatching(TravelAuthorizationAmendmentDocument.class, criteria)).thenReturn(expected);

        Collection<TravelAuthorizationAmendmentDocument> result = travelAuthorizationService.findAmendment(travelDocId);

        assertEquals(1, result.size());
    }

    @Test
    void testAddListenersTo() {
        TravelAuthorizationDocument authorization = mock(TravelAuthorizationDocument.class);
        List<PropertyChangeListener> listeners = new ArrayList();
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        listeners.add(listener);
        travelAuthorizationService.setPropertyChangeListeners(listeners);

        travelAuthorizationService.addListenersTo(authorization);

        verify(authorization).setPropertyChangeListeners(listeners);
    }

    @Test
    void testGetTravelAuthorizationBy_nullDocumentNumber() {
        TravelAuthorizationDocument result = travelAuthorizationService.getTravelAuthorizationBy(null);
        assertNull(result);
    }

    @Test
    void testDoesDatesOverlap_beginOverlaps() {
        Date tripBeginBuffer = Date.valueOf("2024-01-01");
        Date tripEndBuffer = Date.valueOf("2024-01-10");
        Date tripBeginDate = Date.valueOf("2024-01-05");
        Date tripEndDate = Date.valueOf("2024-01-15");

        assertTrue(travelAuthorizationService.doesDatesOverlap(tripBeginBuffer, tripEndBuffer, tripBeginDate, tripEndDate));
    }

    @Test
    void testDoesDatesOverlap_endOverlaps() {
        Date tripBeginBuffer = Date.valueOf("2024-01-05");
        Date tripEndBuffer = Date.valueOf("2024-01-15");
        Date tripBeginDate = Date.valueOf("2024-01-01");
        Date tripEndDate = Date.valueOf("2024-01-10");

        assertTrue(travelAuthorizationService.doesDatesOverlap(tripBeginBuffer, tripEndBuffer, tripBeginDate, tripEndDate));
    }

    @Test
    void testDoesDatesOverlap_noOverlap() {
        Date tripBeginBuffer = Date.valueOf("2024-01-01");
        Date tripEndBuffer = Date.valueOf("2024-01-05");
        Date tripBeginDate = Date.valueOf("2024-01-10");
        Date tripEndDate = Date.valueOf("2024-01-15");

        assertFalse(travelAuthorizationService.doesDatesOverlap(tripBeginBuffer, tripEndBuffer, tripBeginDate, tripEndDate));
    }

    @Test
    void testDoesDatesOverlap_exactSameDates() {
        Date tripBeginBuffer = Date.valueOf("2024-01-01");
        Date tripEndBuffer = Date.valueOf("2024-01-10");
        Date tripBeginDate = Date.valueOf("2024-01-01");
        Date tripEndDate = Date.valueOf("2024-01-10");

        assertTrue(travelAuthorizationService.doesDatesOverlap(tripBeginBuffer, tripEndBuffer, tripBeginDate, tripEndDate));
    }

    @Test
    void testFindMatchingTrips_nullTripBeginReturnsEmpty() {
        TravelAuthorizationDocument authorization = mock(TravelAuthorizationDocument.class);
        when(authorization.getTripBegin()).thenReturn(null);

        List<String> result = travelAuthorizationService.findMatchingTrips(authorization);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindEnrouteOrProcessedTravelReimbursement_noDocuments() {
        TravelAuthorizationDocument authorization = mock(TravelAuthorizationDocument.class);
        when(authorization.getTravelDocumentIdentifier()).thenReturn("12345");
        when(travelDocumentService.findReimbursementDocuments("12345")).thenReturn(new java.util.ArrayList());

        TravelReimbursementDocument result = travelAuthorizationService.findEnrouteOrProcessedTravelReimbursement(authorization);

        assertNull(result);
    }

    @Test
    void testFindEnrouteOrProcessedTravelReimbursement_enrouteDocument() {
        TravelAuthorizationDocument authorization = mock(TravelAuthorizationDocument.class);
        when(authorization.getTravelDocumentIdentifier()).thenReturn("12345");

        TravelReimbursementDocument reimbursement = mock(TravelReimbursementDocument.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);

        when(reimbursement.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(workflowDoc.isEnroute()).thenReturn(true);

        List<TravelReimbursementDocument> list = new ArrayList();
        list.add(reimbursement);
        when(travelDocumentService.findReimbursementDocuments("12345")).thenReturn(list);

        TravelReimbursementDocument result = travelAuthorizationService.findEnrouteOrProcessedTravelReimbursement(authorization);

        assertSame(reimbursement, result);
    }

    @Test
    void testFindEnrouteOrProcessedTravelReimbursement_nonEnrouteDocument() {
        TravelAuthorizationDocument authorization = mock(TravelAuthorizationDocument.class);
        when(authorization.getTravelDocumentIdentifier()).thenReturn("12345");

        TravelReimbursementDocument reimbursement = mock(TravelReimbursementDocument.class);
        DocumentHeader docHeader = mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = mock(WorkflowDocument.class);

        when(reimbursement.getDocumentHeader()).thenReturn(docHeader);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(workflowDoc.isEnroute()).thenReturn(false);
        when(workflowDoc.isFinal()).thenReturn(false);
        when(workflowDoc.isProcessed()).thenReturn(false);

        List<TravelReimbursementDocument> list = new ArrayList();
        list.add(reimbursement);
        when(travelDocumentService.findReimbursementDocuments("12345")).thenReturn(list);

        TravelReimbursementDocument result = travelAuthorizationService.findEnrouteOrProcessedTravelReimbursement(authorization);

        assertNull(result);
    }
}
