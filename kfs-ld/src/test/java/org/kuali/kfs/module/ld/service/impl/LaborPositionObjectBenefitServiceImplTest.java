package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.PositionObjectBenefit;
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

class LaborPositionObjectBenefitServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private LaborPositionObjectBenefitServiceImpl service;

    @Test
    void testGetPositionObjectBenefits() {
        Collection<PositionObjectBenefit> expected = new ArrayList<PositionObjectBenefit>();
        expected.add(new PositionObjectBenefit());

        when(businessObjectService.findMatching(eq(PositionObjectBenefit.class), any(Map.class))).thenReturn(expected);

        Collection<PositionObjectBenefit> result = service.getPositionObjectBenefits(2014, "BL", "5000");
        assertEquals(expected, result);
        verify(businessObjectService).findMatching(eq(PositionObjectBenefit.class), any(Map.class));
    }

    @Test
    void testGetPositionObjectBenefits_empty() {
        when(businessObjectService.findMatching(eq(PositionObjectBenefit.class), any(Map.class))).thenReturn(new ArrayList<PositionObjectBenefit>());

        Collection<PositionObjectBenefit> result = service.getPositionObjectBenefits(2014, "BL", "5000");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetActivePositionObjectBenefits() {
        Collection<PositionObjectBenefit> expected = new ArrayList<PositionObjectBenefit>();
        PositionObjectBenefit pob = new PositionObjectBenefit();
        expected.add(pob);

        when(businessObjectService.findMatching(eq(PositionObjectBenefit.class), any(Map.class))).thenReturn(expected);

        Collection<PositionObjectBenefit> result = service.getActivePositionObjectBenefits(2014, "BL", "5000");
        assertEquals(expected, result);
    }

    @Test
    void testGetActivePositionObjectBenefits_verifyActiveFilter() {
        when(businessObjectService.findMatching(eq(PositionObjectBenefit.class), any(Map.class))).thenReturn(new ArrayList<PositionObjectBenefit>());

        service.getActivePositionObjectBenefits(2014, "BL", "5000");
        verify(businessObjectService).findMatching(eq(PositionObjectBenefit.class), any(Map.class));
    }
}
