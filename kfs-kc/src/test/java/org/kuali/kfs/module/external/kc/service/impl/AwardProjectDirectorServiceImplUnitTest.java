package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsProjectDirector;
import org.kuali.kfs.module.external.kc.businessobject.Award;
import org.kuali.kfs.module.external.kc.businessobject.AwardProjectDirector;
import org.kuali.kfs.module.external.kc.service.ExternalizableBusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;

class AwardProjectDirectorServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private ExternalizableBusinessObjectService awardService;
    @InjectMocks private AwardProjectDirectorServiceImpl service;

    @Test
    void testFindByPrimaryKey_awardNull() {
        Map<String, Object> keys = new HashMap<>();
        when(awardService.findByPrimaryKey(keys)).thenReturn(null);
        assertNull(service.findByPrimaryKey(keys));
    }

    @Test
    void testFindByPrimaryKey_returnsProjectDirector() {
        Map<String, Object> keys = new HashMap<>();
        Award award = mock(Award.class);
        AwardProjectDirector director = mock(AwardProjectDirector.class);
        when(award.getAwardPrimaryProjectDirector()).thenReturn(director);
        when(awardService.findByPrimaryKey(keys)).thenReturn(award);

        ExternalizableBusinessObject result = service.findByPrimaryKey(keys);
        assertSame(director, result);
    }

    @Test
    void testFindMatching_emptyList() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(awardService.findMatching(fieldValues)).thenReturn(Collections.emptyList());

        Collection result = service.findMatching(fieldValues);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMatching_multipleAwards() {
        Map<String, Object> fieldValues = new HashMap<>();
        Award award1 = mock(Award.class);
        Award award2 = mock(Award.class);
        AwardProjectDirector dir1 = mock(AwardProjectDirector.class);
        AwardProjectDirector dir2 = mock(AwardProjectDirector.class);
        when(award1.getAwardPrimaryProjectDirector()).thenReturn(dir1);
        when(award2.getAwardPrimaryProjectDirector()).thenReturn(dir2);
        when(awardService.findMatching(fieldValues)).thenReturn(Arrays.asList(award1, award2));

        Collection result = service.findMatching(fieldValues);
        assertEquals(2, result.size());
    }
}
