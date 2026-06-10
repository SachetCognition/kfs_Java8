package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.Bill;
import org.kuali.kfs.module.ar.businessobject.CustomerAddress;
import org.kuali.kfs.module.ar.businessobject.Milestone;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ContractsGrantsBillingUtilityServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ContractsGrantsBillingUtilityServiceImpl service;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testFormatForCurrencyWithNull() {
        String result = service.formatForCurrency(null);
        assertThat(result).isEmpty();
    }

    @Test
    void testBuildFullAddressWithAllFields() {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerLine1StreetAddress("123 Main St");
        address.setCustomerLine2StreetAddress("Suite 200");
        address.setCustomerCityName("Bloomington");
        address.setCustomerStateCode("IN");
        address.setCustomerZipCode("47401");

        String result = service.buildFullAddress(address);
        assertThat(result).contains("123 Main St");
        assertThat(result).contains("Suite 200");
        assertThat(result).contains("Bloomington");
        assertThat(result).contains("IN");
        assertThat(result).contains("47401");
    }

    @Test
    void testBuildFullAddressWithOnlyLine1AndCity() {
        CustomerAddress address = new CustomerAddress();
        address.setCustomerLine1StreetAddress("456 Oak Ave");
        address.setCustomerCityName("Indianapolis");

        String result = service.buildFullAddress(address);
        assertThat(result).contains("456 Oak Ave");
        assertThat(result).contains("Indianapolis");
    }

    @Test
    void testBuildFullAddressWithNull() {
        String result = service.buildFullAddress(null);
        assertThat(result).isEmpty();
    }

    @Test
    void testBuildFullAddressWithEmptyAddress() {
        CustomerAddress address = new CustomerAddress();
        String result = service.buildFullAddress(address);
        assertThat(result).isEmpty();
    }

    @Test
    void testPutValueOrEmptyStringWithValue() {
        Map<String, String> map = new HashMap<>();
        service.putValueOrEmptyString(map, "key1", "value1");
        assertThat(map.get("key1")).isEqualTo("value1");
    }

    @Test
    void testPutValueOrEmptyStringWithNull() {
        Map<String, String> map = new HashMap<>();
        service.putValueOrEmptyString(map, "key1", null);
        assertThat(map.get("key1")).isEmpty();
    }

    @Test
    void testGetActiveBillsForProposalNumber() {
        List<Bill> expectedBills = new ArrayList<>();
        expectedBills.add(new Bill());
        when(businessObjectService.findMatching(eq(Bill.class), any(Map.class))).thenReturn(expectedBills);

        List<Bill> result = service.getActiveBillsForProposalNumber(12345L);
        assertThat(result).hasSize(1);
    }

    @Test
    void testGetActiveBillsForProposalNumberWithNull() {
        assertThatThrownBy(() -> service.getActiveBillsForProposalNumber(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetActiveMilestonesForProposalNumber() {
        List<Milestone> expectedMilestones = new ArrayList<>();
        expectedMilestones.add(new Milestone());
        when(businessObjectService.findMatching(eq(Milestone.class), any(Map.class))).thenReturn(expectedMilestones);

        List<Milestone> result = service.getActiveMilestonesForProposalNumber(12345L);
        assertThat(result).hasSize(1);
    }

    @Test
    void testGetActiveMilestonesForProposalNumberWithNull() {
        assertThatThrownBy(() -> service.getActiveMilestonesForProposalNumber(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testGetBusinessObjectService() {
        assertThat(service.getBusinessObjectService()).isEqualTo(businessObjectService);
    }
}
