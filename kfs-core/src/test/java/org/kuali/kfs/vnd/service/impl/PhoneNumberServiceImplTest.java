package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.VendorParameterConstants;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PhoneNumberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private PhoneNumberServiceImpl phoneNumberService;

    @Test
    void formatNumberIfPossible_nullInput_returnsNull() {
        String result = phoneNumberService.formatNumberIfPossible(null);
        assertThat(result).isNull();
    }

    @Test
    void formatNumberIfPossible_validTenDigits_formatsCorrectly() {
        when(parameterService.getParameterValueAsString(VendorDetail.class, VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS))
                .thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("8005551234");
        assertThat(result).isEqualTo("800-555-1234");
    }

    @Test
    void formatNumberIfPossible_wrongLength_returnsOriginal() {
        when(parameterService.getParameterValueAsString(VendorDetail.class, VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS))
                .thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("12345");
        assertThat(result).isEqualTo("12345");
    }

    @Test
    void formatNumberIfPossible_stripsNonDigitsBeforeFormatting() {
        when(parameterService.getParameterValueAsString(VendorDetail.class, VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS))
                .thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("(800) 555-1234");
        assertThat(result).isEqualTo("800-555-1234");
    }
}
