package org.kuali.kfs.module.tem.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.CreditCardAgency;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("CreditCardAgencyServiceImpl")
class CreditCardAgencyServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private CreditCardAgencyServiceImpl creditCardAgencyService;

    @Test
    @DisplayName("should return credit card agency by code")
    void testGetCreditCardAgencyByCode() {
        CreditCardAgency agency = new CreditCardAgency();
        agency.setCreditCardOrAgencyCode("AMEX");
        agency.setTravelCardTypeCode("CTS");

        when(businessObjectService.findMatching(eq(CreditCardAgency.class), any()))
                .thenReturn(Arrays.asList(agency));

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("AMEX");
        assertThat(result).isNotNull();
        assertThat(result.getCreditCardOrAgencyCode()).isEqualTo("AMEX");
    }

    @Test
    @DisplayName("should return null when no agency found")
    void testGetCreditCardAgencyByCodeNotFound() {
        when(businessObjectService.findMatching(eq(CreditCardAgency.class), any()))
                .thenReturn(Collections.emptyList());

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("MISSING");
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("should return corporate credit card agency list")
    void testGetCorpCreditCardAgencyList() {
        CreditCardAgency agency1 = new CreditCardAgency();
        agency1.setTravelCardTypeCode("CORP");
        CreditCardAgency agency2 = new CreditCardAgency();
        agency2.setTravelCardTypeCode("CORP");

        when(businessObjectService.findMatching(eq(CreditCardAgency.class), any()))
                .thenReturn(Arrays.asList(agency1, agency2));

        List<CreditCardAgency> result = creditCardAgencyService.getCorpCreditCardAgencyList();
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("should return agency code list from corp agencies")
    void testGetCorpCreditCardAgencyCodeList() {
        CreditCardAgency agency = new CreditCardAgency();
        agency.setTravelCardTypeCode("CTS");

        when(businessObjectService.findMatching(eq(CreditCardAgency.class), any()))
                .thenReturn(Arrays.asList(agency));

        List<String> result = creditCardAgencyService.getCorpCreditCardAgencyCodeList();
        assertThat(result).containsExactly("CTS");
    }

    @Test
    @DisplayName("should return null when agency list is null")
    void testGetCreditCardAgencyByCodeNullList() {
        when(businessObjectService.findMatching(eq(CreditCardAgency.class), any()))
                .thenReturn(null);

        CreditCardAgency result = creditCardAgencyService.getCreditCardAgencyByCode("AMEX");
        assertThat(result).isNull();
    }
}
