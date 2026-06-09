package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.BenefitsType;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LaborBenefitsTypeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private LaborBenefitsTypeServiceImpl laborBenefitsTypeService;

    @Test
    void testGetBenefitsType_returnsCollection() {
        Collection<BenefitsType> expected = new ArrayList<BenefitsType>();
        BenefitsType bt = new BenefitsType();
        expected.add(bt);

        when(businessObjectService.findMatching(eq(BenefitsType.class), any(Map.class))).thenReturn(expected);

        Collection<BenefitsType> result = laborBenefitsTypeService.getBenefitsType();
        assertEquals(expected, result);
        verify(businessObjectService).findMatching(eq(BenefitsType.class), any(Map.class));
    }

    @Test
    void testGetBenefitsType_emptyCollection() {
        Collection<BenefitsType> expected = new ArrayList<BenefitsType>();
        when(businessObjectService.findMatching(eq(BenefitsType.class), any(Map.class))).thenReturn(expected);

        Collection<BenefitsType> result = laborBenefitsTypeService.getBenefitsType();
        assertTrue(result.isEmpty());
    }
}
