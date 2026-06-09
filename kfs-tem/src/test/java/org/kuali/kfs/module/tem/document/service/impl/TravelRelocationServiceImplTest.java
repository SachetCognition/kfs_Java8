package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.document.TravelRelocationDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelRelocationServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelRelocationServiceImpl relocationService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testFindByIdentifier() {
        String travelDocId = "12345";
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        Collection<TravelRelocationDocument> expected = new ArrayList<TravelRelocationDocument>();
        expected.add(mock(TravelRelocationDocument.class));

        when(businessObjectService.findMatching(TravelRelocationDocument.class, criteria)).thenReturn(expected);

        Collection<TravelRelocationDocument> result = relocationService.findByIdentifier(travelDocId);

        assertEquals(1, result.size());
    }

    @Test
    void testFindByIdentifier_notFound() {
        String travelDocId = "99999";
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        when(businessObjectService.findMatching(TravelRelocationDocument.class, criteria)).thenReturn(new ArrayList<TravelRelocationDocument>());

        Collection<TravelRelocationDocument> result = relocationService.findByIdentifier(travelDocId);

        assertTrue(result.isEmpty());
    }
}
