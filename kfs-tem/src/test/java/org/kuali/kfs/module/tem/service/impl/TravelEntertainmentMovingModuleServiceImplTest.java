package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.document.TravelReimbursementDocument;
import org.kuali.kfs.module.tem.document.service.AccountingDocumentRelationshipService;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.module.tem.service.TravelerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelEntertainmentMovingModuleServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelEntertainmentMovingModuleServiceImpl moduleService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private TravelerService travelerService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private AccountingDocumentRelationshipService accountingDocumentRelationshipService;

    @Test
    void testIsTEMDocument_true() {
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);
        List<TravelReimbursementDocument> results = new ArrayList();
        results.add(doc);
        when(travelDocumentService.findReimbursementDocuments("DOC001")).thenReturn(results);

        assertTrue(moduleService.isTEMDocument("DOC001"));
    }

    @Test
    void testGetTEMDocument_found() {
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);
        List<TravelReimbursementDocument> results = new ArrayList();
        results.add(doc);
        when(travelDocumentService.findReimbursementDocuments("DOC001")).thenReturn(results);

        assertSame(doc, moduleService.getTEMDocument("DOC001"));
    }

    @Test
    void testGetTEMDocument_notFound() {
        when(travelDocumentService.findReimbursementDocuments("DOC001")).thenReturn(new java.util.ArrayList());

        assertNull(moduleService.getTEMDocument("DOC001"));
    }

    @Test
    void testIsTravelManager() {
        Person user = mock(Person.class);
        when(travelDocumentService.isTravelManager(user)).thenReturn(true);

        assertTrue(moduleService.isTravelManager(user));
    }

    @Test
    void testIsTravelReimbursementDocument_true() {
        TravelReimbursementDocument doc = mock(TravelReimbursementDocument.class);
        assertTrue(moduleService.isTravelReimbursementDocument(doc));
    }

    @Test
    void testCreateAccountingDocumentRelationship() {
        moduleService.createAccountingDocumentRelationship("DOC001", "DOC002", "Related");
        verify(accountingDocumentRelationshipService).save(any(org.kuali.kfs.module.tem.businessobject.AccountingDocumentRelationship.class));
    }
}
