package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.VendorConstants;
import org.kuali.kfs.vnd.VendorParameterConstants;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.core.web.format.FormatException;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class TaxNumberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    private TaxNumberServiceImpl taxNumberService;

    @BeforeEach
    void setUp() {
        taxNumberService = new TaxNumberServiceImpl();
        taxNumberService.setParameterService(parameterService);
        TaxNumberServiceImpl.taxNumberFormats = null;
        TaxNumberServiceImpl.feinNumberFormats = null;
        TaxNumberServiceImpl.notAllowedTaxNumbers = null;
    }

    @Test
    void formatToDefaultFormatReturnsDigitsOnly() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_TAX_NUMBER_DIGITS)).thenReturn("9");

        String result = taxNumberService.formatToDefaultFormat("123-45-6789");
        assertThat(result).isEqualTo("123456789");
    }

    @Test
    void formatToDefaultFormatThrowsWhenTooFew() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_TAX_NUMBER_DIGITS)).thenReturn("9");

        assertThatThrownBy(() -> taxNumberService.formatToDefaultFormat("1234"))
                .isInstanceOf(FormatException.class);
    }

    @Test
    void formatToDefaultFormatThrowsWhenTooMany() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                VendorParameterConstants.DEFAULT_TAX_NUMBER_DIGITS)).thenReturn("9");

        assertThatThrownBy(() -> taxNumberService.formatToDefaultFormat("1234567890"))
                .isInstanceOf(FormatException.class);
    }

    @Test
    void isStringAllNumbersReturnsTrueForDigits() {
        assertThat(taxNumberService.isStringAllNumbers("123456789")).isTrue();
    }

    @Test
    void isStringAllNumbersReturnsFalseForMixed() {
        assertThat(taxNumberService.isStringAllNumbers("123abc")).isFalse();
    }

    @Test
    void isStringAllNumbersReturnsFalseForNull() {
        assertThat(taxNumberService.isStringAllNumbers(null)).isFalse();
    }

    @Test
    void isStringAllNumbersReturnsFalseForEmpty() {
        assertThat(taxNumberService.isStringAllNumbers("")).isFalse();
    }

    @Test
    void isStringAllNumbersTrimsWhitespace() {
        assertThat(taxNumberService.isStringAllNumbers("  12345  ")).isTrue();
    }

    @Test
    void isStringEmptyReturnsTrueForNull() {
        assertThat(taxNumberService.isStringEmpty(null)).isTrue();
    }

    @Test
    void isStringEmptyReturnsTrueForEmpty() {
        assertThat(taxNumberService.isStringEmpty("")).isTrue();
    }

    @Test
    void isStringEmptyReturnsFalseForNonEmpty() {
        assertThat(taxNumberService.isStringEmpty("abc")).isFalse();
    }

    @Test
    void isValidTaxNumberReturnsFalseForWrongLength() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);

        assertThat(taxNumberService.isValidTaxNumber("12345", VendorConstants.TAX_TYPE_SSN)).isFalse();
    }

    @Test
    void isValidTaxNumberReturnsFalseForNonNumeric() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);

        assertThat(taxNumberService.isValidTaxNumber("12345abc9", VendorConstants.TAX_TYPE_SSN)).isFalse();
    }

    @Test
    void isValidTaxNumberReturnsTrueForValidSSN() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("(?!000)(?!666)(\\d{3})(?!00)(\\d{2})(?!0000)(\\d{4})");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);

        assertThat(taxNumberService.isValidTaxNumber("123456789", VendorConstants.TAX_TYPE_SSN)).isTrue();
    }

    @Test
    void isValidTaxNumberReturnsFalseForSSNStartingWith000() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("(?!000)(?!666)(\\d{3})(?!00)(\\d{2})(?!0000)(\\d{4})");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);

        assertThat(taxNumberService.isValidTaxNumber("000456789", VendorConstants.TAX_TYPE_SSN)).isFalse();
    }

    @Test
    void isValidTaxNumberReturnsTrueForValidFEIN() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        List<String> feinFormats = Arrays.asList("(?!00)(\\d{3})(\\d{2})(?!0000)(\\d{4})");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_FEIN_NUMBER_FORMATS)).thenReturn(feinFormats);

        assertThat(taxNumberService.isValidTaxNumber("123456789", VendorConstants.TAX_TYPE_FEIN)).isTrue();
    }

    @Test
    void isValidTaxNumberReturnsFalseForInvalidFEIN() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        List<String> feinFormats = Arrays.asList("(?!00)(\\d{3})(\\d{2})(?!0000)(\\d{4})");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_FEIN_NUMBER_FORMATS)).thenReturn(feinFormats);

        assertThat(taxNumberService.isValidTaxNumber("001230000", VendorConstants.TAX_TYPE_FEIN)).isFalse();
    }

    @Test
    void isValidTaxNumberReturnsTrueForUnknownType() {
        when(parameterService.getParameterValueAsString(VendorDetail.class,
                "DEFAULT_TAX_NUMBER_DIGITS")).thenReturn("9");
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        List<String> feinFormats = Arrays.asList("\\d{9}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_FEIN_NUMBER_FORMATS)).thenReturn(feinFormats);

        assertThat(taxNumberService.isValidTaxNumber("123456789", "OTHER")).isTrue();
    }

    @Test
    void isAllowedTaxNumberReturnsTrueForAllowed() {
        List<String> notAllowed = Arrays.asList("000000000", "111111111", "999999999");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.NOT_ALLOWED_TAX_NUMBERS)).thenReturn(notAllowed);

        assertThat(taxNumberService.isAllowedTaxNumber("123456789")).isTrue();
    }

    @Test
    void isAllowedTaxNumberReturnsFalseForNotAllowed() {
        List<String> notAllowed = Arrays.asList("000000000", "111111111", "999999999");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.NOT_ALLOWED_TAX_NUMBERS)).thenReturn(notAllowed);

        assertThat(taxNumberService.isAllowedTaxNumber("000000000")).isFalse();
    }

    @Test
    void parseSSNFormatsCachesFormats() {
        List<String> ssnFormats = Arrays.asList("\\d{9}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_SSN_NUMBER_FORMATS)).thenReturn(ssnFormats);

        String[] result1 = taxNumberService.parseSSNFormats();
        String[] result2 = taxNumberService.parseSSNFormats();

        assertThat(result1).containsExactly("\\d{9}");
        assertThat(result2).containsExactly("\\d{9}");
    }

    @Test
    void parseFEINFormatsCachesFormats() {
        List<String> feinFormats = Arrays.asList("\\d{2}-\\d{7}");
        when(parameterService.getParameterValuesAsString(VendorDetail.class,
                VendorParameterConstants.TAX_FEIN_NUMBER_FORMATS)).thenReturn(feinFormats);

        String[] result = taxNumberService.parseFEINFormats();
        assertThat(result).containsExactly("\\d{2}-\\d{7}");
    }
}
