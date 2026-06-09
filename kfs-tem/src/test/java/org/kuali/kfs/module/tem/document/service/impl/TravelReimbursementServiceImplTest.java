package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.TemSourceAccountingLine;
import org.kuali.kfs.module.tem.businessobject.TripType;
import org.kuali.kfs.module.tem.document.TravelReimbursementDocument;
import org.kuali.kfs.module.tem.document.service.TravelAuthorizationService;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelReimbursementServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelReimbursementServiceImpl reimbursementService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private TravelAuthorizationService travelAuthorizationService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testFindByTravelId() throws Exception {
        String travelDocId = "12345";
        List<TravelReimbursementDocument> expected = new ArrayList();
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);
        expected.add(doc);

        when(travelDocumentService.findReimbursementDocuments(travelDocId)).thenReturn(expected);

        List<TravelReimbursementDocument> result = reimbursementService.findByTravelId(travelDocId);

        assertEquals(1, result.size());
        assertSame(doc, result.get(0));
    }

    @Test
    void testFind() throws Exception {
        String docNumber = "DOC001";
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);

        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(doc);

        TravelReimbursementDocument result = reimbursementService.find(docNumber);

        assertSame(doc, result);
    }

    @Test
    void testAddListenersTo_nullDocument() {
        reimbursementService.addListenersTo(null);
        // no exception expected
    }

    @Test
    void testAddListenersTo_nonNullDocument() {
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);
        List<PropertyChangeListener> listeners = new ArrayList();
        listeners.add(mock(PropertyChangeListener.class));
        reimbursementService.setPropertyChangeListeners(listeners);

        reimbursementService.addListenersTo(doc);

        verify(doc).setPropertyChangeListeners(listeners);
    }

    @Test
    void testCalculateLinesTotal() {
        List<TemSourceAccountingLine> lines = new ArrayList();
        TemSourceAccountingLine line1 = new TemSourceAccountingLine();
        line1.setAmount(new KualiDecimal(100));
        TemSourceAccountingLine line2 = new TemSourceAccountingLine();
        line2.setAmount(new KualiDecimal(250));
        lines.add(line1);
        lines.add(line2);

        KualiDecimal result = reimbursementService.calculateLinesTotal(lines);

        assertEquals(new KualiDecimal(350), result);
    }

    @Test
    void testCalculateLinesTotal_emptyList() {
        List<TemSourceAccountingLine> lines = new ArrayList();

        KualiDecimal result = reimbursementService.calculateLinesTotal(lines);

        assertEquals(KualiDecimal.ZERO, result);
    }

    @Test
    void testDoAllReimbursementTripTypesRequireTravelAuthorization_allRequire() {
        Collection<TripType> tripTypes = new ArrayList();
        TripType type1 = new TripType();
        type1.setTravelAuthorizationRequired(true);
        TripType type2 = new TripType();
        type2.setTravelAuthorizationRequired(true);
        tripTypes.add(type1);
        tripTypes.add(type2);

        when(businessObjectService.findAll(TripType.class)).thenReturn(tripTypes);

        assertTrue(reimbursementService.doAllReimbursementTripTypesRequireTravelAuthorization());
    }

    @Test
    void testDoAllReimbursementTripTypesRequireTravelAuthorization_oneDoesNot() {
        Collection<TripType> tripTypes = new ArrayList();
        TripType type1 = new TripType();
        type1.setTravelAuthorizationRequired(true);
        TripType type2 = new TripType();
        type2.setTravelAuthorizationRequired(false);
        tripTypes.add(type1);
        tripTypes.add(type2);

        when(businessObjectService.findAll(TripType.class)).thenReturn(tripTypes);

        assertFalse(reimbursementService.doAllReimbursementTripTypesRequireTravelAuthorization());
    }

    @Test
    void testDoAllReimbursementTripTypesRequireTravelAuthorization_empty() {
        when(businessObjectService.findAll(TripType.class)).thenReturn(new java.util.ArrayList());

        assertTrue(reimbursementService.doAllReimbursementTripTypesRequireTravelAuthorization());
    }
}
