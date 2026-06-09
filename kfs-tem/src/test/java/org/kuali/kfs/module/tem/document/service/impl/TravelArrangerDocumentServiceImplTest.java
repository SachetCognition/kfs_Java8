package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.TemProfileArranger;
import org.kuali.kfs.module.tem.document.TravelArrangerDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelArrangerDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelArrangerDocumentServiceImpl arrangerService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testCreateTravelProfileArranger_newArranger() {
        TravelArrangerDocument doc = mock(TravelArrangerDocument.class);
        when(doc.getProfileId()).thenReturn(100);
        when(doc.getArrangerId()).thenReturn("user1");
        when(doc.getPrimaryInd()).thenReturn(true);
        when(doc.getTaInd()).thenReturn(true);
        when(doc.getTrInd()).thenReturn(false);

        Map<String, Object> criteria = new HashMap();
        criteria.put("principalId", "user1");
        criteria.put("profileId", 100);
        doReturn(new ArrayList()).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        arrangerService.createTravelProfileArranger(doc);

        verify(businessObjectService).save(any(TemProfileArranger.class));
    }

    @Test
    void testInactivateTravelProfileArranger_found() {
        TravelArrangerDocument doc = mock(TravelArrangerDocument.class);
        when(doc.getProfileId()).thenReturn(100);
        when(doc.getArrangerId()).thenReturn("user1");

        TemProfileArranger arranger = mock(TemProfileArranger.class);

        Map<String, Object> criteria = new HashMap();
        criteria.put("principalId", "user1");
        criteria.put("profileId", 100);
        criteria.put("active", "Y");
        List<TemProfileArranger> arrangers = new ArrayList<TemProfileArranger>();
        arrangers.add(arranger);
        doReturn(arrangers).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        arrangerService.inactivateTravelProfileArranger(doc);

        verify(arranger).setActive(false);
        verify(businessObjectService).save(arranger);
    }

    @Test
    void testInactivateTravelProfileArranger_notFound() {
        TravelArrangerDocument doc = mock(TravelArrangerDocument.class);
        when(doc.getProfileId()).thenReturn(100);
        when(doc.getArrangerId()).thenReturn("user1");

        Map<String, Object> criteria = new HashMap();
        criteria.put("principalId", "user1");
        criteria.put("profileId", 100);
        criteria.put("active", "Y");
        doReturn(new ArrayList()).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        arrangerService.inactivateTravelProfileArranger(doc);

        verify(businessObjectService, never()).save(any(TemProfileArranger.class));
    }

    @Test
    void testFindPrimaryTravelProfileArranger_found() {
        Map<String, Object> criteria = new HashMap();
        criteria.put("profileId", 100);

        TemProfileArranger arranger = mock(TemProfileArranger.class);
        when(arranger.getPrincipalId()).thenReturn("user2");
        when(arranger.getPrimary()).thenReturn(true);

        List<TemProfileArranger> arrangers = new ArrayList<TemProfileArranger>();
        arrangers.add(arranger);
        doReturn(arrangers).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        TemProfileArranger result = arrangerService.findPrimaryTravelProfileArranger("user1", 100);

        assertSame(arranger, result);
    }

    @Test
    void testFindPrimaryTravelProfileArranger_sameArrangerNotReturned() {
        Map<String, Object> criteria = new HashMap();
        criteria.put("profileId", 100);

        TemProfileArranger arranger = mock(TemProfileArranger.class);
        when(arranger.getPrincipalId()).thenReturn("user1");
        when(arranger.getPrimary()).thenReturn(true);

        List<TemProfileArranger> arrangers = new ArrayList<TemProfileArranger>();
        arrangers.add(arranger);
        doReturn(arrangers).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        TemProfileArranger result = arrangerService.findPrimaryTravelProfileArranger("user1", 100);

        assertNull(result);
    }

    @Test
    void testFindPrimaryTravelProfileArranger_nonPrimaryNotReturned() {
        Map<String, Object> criteria = new HashMap();
        criteria.put("profileId", 100);

        TemProfileArranger arranger = mock(TemProfileArranger.class);
        when(arranger.getPrimary()).thenReturn(false);

        List<TemProfileArranger> arrangers = new ArrayList<TemProfileArranger>();
        arrangers.add(arranger);
        doReturn(arrangers).when(businessObjectService).findMatching(TemProfileArranger.class, criteria);

        TemProfileArranger result = arrangerService.findPrimaryTravelProfileArranger("user1", 100);

        assertNull(result);
    }
}
