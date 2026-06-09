package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.MileageRate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CachingMileageRateServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CachingMileageRateServiceImpl cachingMileageRateService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testFindAllMileageRates_withData() {
        List<MileageRate> rates = new ArrayList();
        MileageRate rate = new MileageRate();
        rate.setExpenseTypeCode("MIL");
        rates.add(rate);

        when(businessObjectService.findAll(MileageRate.class)).thenReturn(rates);

        List<MileageRate> result = cachingMileageRateService.findAllMileageRates();

        assertEquals(1, result.size());
        assertEquals("MIL", result.get(0).getExpenseTypeCode());
    }

    @Test
    void testFindAllMileageRates_empty() {
        when(businessObjectService.findAll(MileageRate.class)).thenReturn(new java.util.ArrayList());

        List<MileageRate> result = cachingMileageRateService.findAllMileageRates();

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindAllMileageRates_multipleRates() {
        List<MileageRate> rates = new ArrayList();
        rates.add(new MileageRate());
        rates.add(new MileageRate());
        rates.add(new MileageRate());

        when(businessObjectService.findAll(MileageRate.class)).thenReturn(rates);

        List<MileageRate> result = cachingMileageRateService.findAllMileageRates();

        assertEquals(3, result.size());
    }
}
