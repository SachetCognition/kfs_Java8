package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerAddress;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CustomerAddressServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private SequenceAccessorService sequenceAccessorService;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private CustomerAddressServiceImpl service;

    @Test
    void getByPrimaryKey_shouldReturnAddressWhenFound() {
        CustomerAddress addr = new CustomerAddress();
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(addr);

        CustomerAddress result = service.getByPrimaryKey("CUST001", 1);
        assertThat(result).isSameAs(addr);
    }

    @Test
    void getByPrimaryKey_shouldReturnNullForBlankCustomerNumber() {
        CustomerAddress result = service.getByPrimaryKey("", 1);
        assertThat(result).isNull();
    }

    @Test
    void getByPrimaryKey_shouldReturnNullForNullIdentifier() {
        CustomerAddress result = service.getByPrimaryKey("CUST001", null);
        assertThat(result).isNull();
    }

    @Test
    void getPrimaryAddress_shouldReturnAddressWhenFound() {
        CustomerAddress addr = new CustomerAddress();
        when(businessObjectService.findMatching(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(Arrays.asList(addr));

        CustomerAddress result = service.getPrimaryAddress("CUST001");
        assertThat(result).isNotNull();
    }

    @Test
    void getPrimaryAddress_shouldReturnNullForBlankCustomerNumber() {
        CustomerAddress result = service.getPrimaryAddress("");
        assertThat(result).isNull();
    }

    @Test
    void getPrimaryAddress_shouldReturnNullWhenNoResults() {
        when(businessObjectService.findMatching(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        CustomerAddress result = service.getPrimaryAddress("CUST001");
        assertThat(result).isNull();
    }

    @Test
    void customerAddressActive_shouldReturnTrueWhenAddressNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(null);

        boolean result = service.customerAddressActive("CUST001", 1);
        assertThat(result).isTrue();
    }

    @Test
    void customerAddressActive_shouldReturnTrueWhenNoEndDate() {
        CustomerAddress addr = new CustomerAddress();
        addr.setCustomerAddressEndDate(null);
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(addr);

        boolean result = service.customerAddressActive("CUST001", 1);
        assertThat(result).isTrue();
    }

    @Test
    void customerAddressActive_shouldReturnFalseWhenEndDateInPast() {
        CustomerAddress addr = new CustomerAddress();
        addr.setCustomerAddressEndDate(Date.valueOf("2020-01-01"));
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(addr);
        when(dateTimeService.getCurrentDate()).thenReturn(new java.util.Date());

        boolean result = service.customerAddressActive("CUST001", 1);
        assertThat(result).isFalse();
    }

    @Test
    void customerAddressActive_shouldReturnTrueWhenEndDateInFuture() {
        CustomerAddress addr = new CustomerAddress();
        addr.setCustomerAddressEndDate(Date.valueOf("2099-12-31"));
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(addr);
        when(dateTimeService.getCurrentDate()).thenReturn(new java.util.Date());

        boolean result = service.customerAddressActive("CUST001", 1);
        assertThat(result).isTrue();
    }

    @Test
    void customerAddressExists_shouldReturnTrueWhenFound() {
        CustomerAddress addr = new CustomerAddress();
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(addr);

        boolean result = service.customerAddressExists("CUST001", 1);
        assertThat(result).isTrue();
    }

    @Test
    void customerAddressExists_shouldReturnFalseWhenNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(CustomerAddress.class), any(Map.class)))
                .thenReturn(null);

        boolean result = service.customerAddressExists("CUST001", 1);
        assertThat(result).isFalse();
    }

    @Test
    void getNextCustomerAddressIdentifier_shouldReturnSequenceValue() {
        when(sequenceAccessorService.getNextAvailableSequenceNumber(eq("CUST_ADDR_ID_SEQ"), eq(CustomerAddress.class)))
                .thenReturn(42L);

        Integer result = service.getNextCustomerAddressIdentifier();
        assertThat(result).isEqualTo(42);
    }
}
