package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.document.TravelEntertainmentDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelEntertainmentDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelEntertainmentDocumentServiceImpl entertainmentService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testFindByTravelId() {
        String travelDocId = "12345";
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        Collection<TravelEntertainmentDocument> expected = new ArrayList();
        expected.add(mock(TravelEntertainmentDocument.class));

        when(businessObjectService.findMatching(TravelEntertainmentDocument.class, criteria)).thenReturn(expected);

        Collection<TravelEntertainmentDocument> result = entertainmentService.findByTravelId(travelDocId);

        assertEquals(1, result.size());
    }

    @Test
    void testFindByTravelId_notFound() {
        String travelDocId = "99999";
        Map<String, Object> criteria = new HashMap();
        criteria.put(TemPropertyConstants.TRAVEL_DOCUMENT_IDENTIFIER, travelDocId);

        when(businessObjectService.findMatching(TravelEntertainmentDocument.class, criteria)).thenReturn(new java.util.ArrayList());

        Collection<TravelEntertainmentDocument> result = entertainmentService.findByTravelId(travelDocId);

        assertTrue(result.isEmpty());
    }
}
