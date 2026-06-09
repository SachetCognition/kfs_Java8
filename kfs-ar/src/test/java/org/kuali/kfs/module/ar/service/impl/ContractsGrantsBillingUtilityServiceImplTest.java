package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerAddress;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsGrantsBillingUtilityServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private ContractsGrantsBillingUtilityServiceImpl service;

    @Test
    void formatForCurrency_shouldReturnEmptyForNull() {
        assertThat(service.formatForCurrency(null)).isEmpty();
    }

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }

    @Test
    void buildFullAddress_shouldReturnEmptyForNull() {
        assertThat(service.buildFullAddress(null)).isEmpty();
    }

    @Test
    void buildFullAddress_shouldIncludeLine1() {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerLine1StreetAddress("123 Main St");
        address.setCustomerCityName("Springfield");
        address.setCustomerStateCode("IL");
        address.setCustomerZipCode("62701");

        String result = service.buildFullAddress(address);
        assertThat(result).contains("123 Main St");
        assertThat(result).contains("Springfield");
        assertThat(result).contains("IL");
    }

    @Test
    void buildFullAddress_shouldHandleMissingFields() {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerLine1StreetAddress("123 Main St");

        String result = service.buildFullAddress(address);
        assertThat(result).contains("123 Main St");
    }
}
