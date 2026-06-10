package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.VendorParameterConstants;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PhoneNumberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    private PhoneNumberServiceImpl phoneNumberService;

    @BeforeEach
    void setUp() {
        phoneNumberService = new PhoneNumberServiceImpl();
        phoneNumberService.setParameterService(parameterService);
        phoneNumberService.phoneNumberFormats = null;
    }

    @Test
    void formatNumberIfPossibleReturnsNullForNull() {
        assertThat(phoneNumberService.formatNumberIfPossible(null)).isNull();
    }

    @Test
    void formatNumberIfPossibleFormats10DigitNumber() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS)).thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("8005551234");
        assertThat(result).isEqualTo("800-555-1234");
    }

    @Test
    void formatNumberIfPossibleFormatsNumberWithExistingFormatting() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS)).thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("(800) 555-1234");
        assertThat(result).isEqualTo("800-555-1234");
    }

    @Test
    void formatNumberIfPossibleReturnsOriginalWhenWrongLength() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS)).thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("12345");
        assertThat(result).isEqualTo("12345");
    }

    @Test
    void formatNumberIfPossibleReturnsOriginalWhenTooLong() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS)).thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("800-555-12345");
        assertThat(result).isEqualTo("800-555-12345");
    }

    @Test
    void isValidPhoneNumberReturnsTrueForMatchingFormat() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}", "\\(\\d{3}\\) \\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        assertThat(phoneNumberService.isValidPhoneNumber("800-555-1234")).isTrue();
    }

    @Test
    void isValidPhoneNumberReturnsTrueForSecondFormat() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}", "\\(\\d{3}\\) \\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        assertThat(phoneNumberService.isValidPhoneNumber("(800) 555-1234")).isTrue();
    }

    @Test
    void isValidPhoneNumberReturnsFalseForNonMatchingFormat() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        assertThat(phoneNumberService.isValidPhoneNumber("8005551234")).isFalse();
    }

    @Test
    void isDefaultFormatPhoneNumberReturnsTrueForDefaultFormat() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}", "\\(\\d{3}\\) \\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        assertThat(phoneNumberService.isDefaultFormatPhoneNumber("800-555-1234")).isTrue();
    }

    @Test
    void isDefaultFormatPhoneNumberReturnsFalseForNonDefaultFormat() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}", "\\(\\d{3}\\) \\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        assertThat(phoneNumberService.isDefaultFormatPhoneNumber("(800) 555-1234")).isFalse();
    }

    @Test
    void parseFormatsCachesFormats() {
        List<String> formats = Arrays.asList("\\d{3}-\\d{3}-\\d{4}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.PHONE_NUMBER_FORMATS)).thenReturn(formats);

        phoneNumberService.isValidPhoneNumber("800-555-1234");
        phoneNumberService.isValidPhoneNumber("800-555-1234");

        assertThat(phoneNumberService.phoneNumberFormats).isNotNull();
    }

    @Test
    void formatNumberStripsNonDigits() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_PHONE_NUMBER_DIGITS)).thenReturn("10");

        String result = phoneNumberService.formatNumberIfPossible("800,555-1234");
        assertThat(result).isEqualTo("800-555-1234");
    }
}
