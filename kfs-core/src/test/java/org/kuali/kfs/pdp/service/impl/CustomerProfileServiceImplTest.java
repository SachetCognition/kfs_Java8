package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.CustomerProfile;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerProfileServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private CustomerProfileServiceImpl customerProfileService;

    @BeforeEach
    void setUp() {
        customerProfileService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGet_found() {
        CustomerProfile expected = new CustomerProfile();
        expected.setChartCode("BL");
        expected.setUnitCode("ACCT");
        expected.setSubUnitCode("MAIN");

        when(businessObjectService.findMatching(eq(CustomerProfile.class), anyMap()))
                .thenReturn(Collections.singletonList(expected));

        CustomerProfile result = customerProfileService.get("BL", "ACCT", "MAIN");

        assertThat(result).isNotNull();
        assertThat(result.getChartCode()).isEqualTo("BL");
        verify(businessObjectService).findMatching(eq(CustomerProfile.class), anyMap());
    }

    @Test
    void testGet_notFound() {
        when(businessObjectService.findMatching(eq(CustomerProfile.class), anyMap()))
                .thenReturn(Collections.emptyList());

        CustomerProfile result = customerProfileService.get("XX", "NONE", "NONE");

        assertThat(result).isNull();
    }

    @Test
    void testGet_multipleResults_returnsFirst() {
        CustomerProfile first = new CustomerProfile();
        first.setChartCode("BL");
        CustomerProfile second = new CustomerProfile();
        second.setChartCode("UA");

        when(businessObjectService.findMatching(eq(CustomerProfile.class), anyMap()))
                .thenReturn(Arrays.asList(first, second));

        CustomerProfile result = customerProfileService.get("BL", "ACCT", "MAIN");

        assertThat(result).isSameAs(first);
    }
}
