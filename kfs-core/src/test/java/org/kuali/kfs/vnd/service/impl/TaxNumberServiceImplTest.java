package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class TaxNumberServiceImplTest extends KfsUnitTestBase {

    private TaxNumberServiceImpl taxNumberService;

    @BeforeEach
    void setUp() {
        taxNumberService = new TaxNumberServiceImpl();
    }

    @Test
    void isStringAllNumbers_allDigits_returnsTrue() {
        assertThat(taxNumberService.isStringAllNumbers("123456789")).isTrue();
    }

    @Test
    void isStringAllNumbers_containsLetter_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers("12345678a")).isFalse();
    }

    @Test
    void isStringAllNumbers_null_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers(null)).isFalse();
    }

    @Test
    void isStringAllNumbers_empty_returnsFalse() {
        assertThat(taxNumberService.isStringAllNumbers("")).isFalse();
    }

    @Test
    void isStringEmpty_null_returnsTrue() {
        assertThat(taxNumberService.isStringEmpty(null)).isTrue();
    }

    @Test
    void isStringEmpty_empty_returnsTrue() {
        assertThat(taxNumberService.isStringEmpty("")).isTrue();
    }

    @Test
    void isStringEmpty_nonEmpty_returnsFalse() {
        assertThat(taxNumberService.isStringEmpty("abc")).isFalse();
    }
}
