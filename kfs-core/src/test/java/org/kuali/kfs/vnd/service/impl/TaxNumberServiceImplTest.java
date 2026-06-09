package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class TaxNumberServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private TaxNumberServiceImpl taxNumberService;

    @Test
    void isStringAllNumbers_validNumber_returnsTrue() {
        assertThat(taxNumberService.isStringAllNumbers("123456789")).isTrue();
    }

    @Test
    void isStringAllNumbers_containsLetters_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers("12-34-5678")).isFalse();
    }

    @Test
    void isStringAllNumbers_empty_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers("")).isFalse();
    }

    @Test
    void isStringAllNumbers_null_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers(null)).isFalse();
    }
}
