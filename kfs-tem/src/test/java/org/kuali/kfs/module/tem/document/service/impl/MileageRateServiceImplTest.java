package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.MileageRate;
import org.kuali.kfs.module.tem.document.service.CachingMileageRateService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class MileageRateServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private MileageRateServiceImpl mileageRateService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private CachingMileageRateService cachingMileageRateService;

    @Test
    void testGetMileageRateByExpenseTypeCode_noOverlap() {
        MileageRate searchRate = new MileageRate();
        searchRate.setId(1);
        searchRate.setExpenseTypeCode("MP");
        searchRate.setActiveFromDate(Date.valueOf("2024-06-01"));
        searchRate.setActiveToDate(Date.valueOf("2024-12-31"));

        Map<String, Object> criteria = new HashMap();
        criteria.put("expenseTypeCode", "MP");

        MileageRate existingRate = new MileageRate();
        existingRate.setId(2);
        existingRate.setExpenseTypeCode("MP");
        existingRate.setActiveFromDate(Date.valueOf("2025-01-01"));
        existingRate.setActiveToDate(Date.valueOf("2025-06-30"));

        List<MileageRate> rates = new ArrayList();
        rates.add(existingRate);

        when(businessObjectService.findMatching(MileageRate.class, criteria)).thenReturn(rates);

        MileageRate result = mileageRateService.getMileageRateByExpenseTypeCode(searchRate);

        assertNull(result);
    }

    @Test
    void testGetMileageRateByExpenseTypeCode_withOverlap() {
        MileageRate searchRate = new MileageRate();
        searchRate.setId(1);
        searchRate.setExpenseTypeCode("MP");
        searchRate.setActiveFromDate(Date.valueOf("2024-06-01"));
        searchRate.setActiveToDate(Date.valueOf("2024-12-31"));

        Map<String, Object> criteria = new HashMap();
        criteria.put("expenseTypeCode", "MP");

        MileageRate existingRate = new MileageRate();
        existingRate.setId(2);
        existingRate.setExpenseTypeCode("MP");
        existingRate.setActiveFromDate(Date.valueOf("2024-10-01"));
        existingRate.setActiveToDate(Date.valueOf("2025-06-30"));

        List<MileageRate> rates = new ArrayList();
        rates.add(existingRate);

        when(businessObjectService.findMatching(MileageRate.class, criteria)).thenReturn(rates);

        MileageRate result = mileageRateService.getMileageRateByExpenseTypeCode(searchRate);

        assertSame(existingRate, result);
    }

    @Test
    void testGetMileageRateByExpenseTypeCode_sameId() {
        MileageRate searchRate = new MileageRate();
        searchRate.setId(1);
        searchRate.setExpenseTypeCode("MP");
        searchRate.setActiveFromDate(Date.valueOf("2024-06-01"));
        searchRate.setActiveToDate(Date.valueOf("2024-12-31"));

        Map<String, Object> criteria = new HashMap();
        criteria.put("expenseTypeCode", "MP");

        MileageRate sameRate = new MileageRate();
        sameRate.setId(1);
        sameRate.setExpenseTypeCode("MP");
        sameRate.setActiveFromDate(Date.valueOf("2024-06-01"));
        sameRate.setActiveToDate(Date.valueOf("2024-12-31"));

        List<MileageRate> rates = new ArrayList();
        rates.add(sameRate);

        when(businessObjectService.findMatching(MileageRate.class, criteria)).thenReturn(rates);

        MileageRate result = mileageRateService.getMileageRateByExpenseTypeCode(searchRate);

        assertNull(result);
    }

    @Test
    void testFindMileageRateByExpenseTypeCodeAndDate_found() {
        MileageRate rate = new MileageRate();
        rate.setExpenseTypeCode("MP");
        rate.setActiveFromDate(Date.valueOf("2024-01-01"));
        rate.setActiveToDate(Date.valueOf("2024-12-31"));

        List<MileageRate> allRates = new ArrayList();
        allRates.add(rate);

        when(cachingMileageRateService.findAllMileageRates()).thenReturn(allRates);

        MileageRate result = mileageRateService.findMileageRateByExpenseTypeCodeAndDate("MP", Date.valueOf("2024-06-15"));

        assertSame(rate, result);
    }

    @Test
    void testFindMileageRateByExpenseTypeCodeAndDate_notFound_wrongType() {
        MileageRate rate = new MileageRate();
        rate.setExpenseTypeCode("MP");
        rate.setActiveFromDate(Date.valueOf("2024-01-01"));
        rate.setActiveToDate(Date.valueOf("2024-12-31"));

        List<MileageRate> allRates = new ArrayList();
        allRates.add(rate);

        when(cachingMileageRateService.findAllMileageRates()).thenReturn(allRates);

        MileageRate result = mileageRateService.findMileageRateByExpenseTypeCodeAndDate("AIR", Date.valueOf("2024-06-15"));

        assertNull(result);
    }

    @Test
    void testFindMileageRateByExpenseTypeCodeAndDate_notFound_outsideRange() {
        MileageRate rate = new MileageRate();
        rate.setExpenseTypeCode("MP");
        rate.setActiveFromDate(Date.valueOf("2024-01-01"));
        rate.setActiveToDate(Date.valueOf("2024-12-31"));

        List<MileageRate> allRates = new ArrayList();
        allRates.add(rate);

        when(cachingMileageRateService.findAllMileageRates()).thenReturn(allRates);

        MileageRate result = mileageRateService.findMileageRateByExpenseTypeCodeAndDate("MP", Date.valueOf("2025-06-15"));

        assertNull(result);
    }

    @Test
    void testFindMileageRateByExpenseTypeCodeAndDate_onStartDate() {
        MileageRate rate = new MileageRate();
        rate.setExpenseTypeCode("MP");
        rate.setActiveFromDate(Date.valueOf("2024-01-01"));
        rate.setActiveToDate(Date.valueOf("2024-12-31"));

        List<MileageRate> allRates = new ArrayList();
        allRates.add(rate);

        when(cachingMileageRateService.findAllMileageRates()).thenReturn(allRates);

        MileageRate result = mileageRateService.findMileageRateByExpenseTypeCodeAndDate("MP", Date.valueOf("2024-01-01"));

        assertSame(rate, result);
    }

    @Test
    void testFindMileageRateByExpenseTypeCodeAndDate_emptyList() {
        when(cachingMileageRateService.findAllMileageRates()).thenReturn(new java.util.ArrayList());

        MileageRate result = mileageRateService.findMileageRateByExpenseTypeCodeAndDate("MP", Date.valueOf("2024-06-15"));

        assertNull(result);
    }
}
