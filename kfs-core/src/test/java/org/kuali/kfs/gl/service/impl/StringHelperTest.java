package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class StringHelperTest extends KfsUnitTestBase {

    @Test
    void isEmpty_withEmptyString_returnsTrue() {
        assertThat(StringHelper.isEmpty("")).isTrue();
    }

    @Test
    void isEmpty_withNull_returnsFalse() {
        assertThat(StringHelper.isEmpty(null)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "hello", "  spaces  "})
    void isEmpty_withNonEmptyString_returnsFalse(String input) {
        assertThat(StringHelper.isEmpty(input)).isFalse();
    }

    @Test
    void isNullOrEmpty_withNull_returnsTrue() {
        assertThat(StringHelper.isNullOrEmpty(null)).isTrue();
    }

    @Test
    void isNullOrEmpty_withEmptyString_returnsTrue() {
        assertThat(StringHelper.isNullOrEmpty("")).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "hello", "test string"})
    void isNullOrEmpty_withNonEmptyString_returnsFalse(String input) {
        assertThat(StringHelper.isNullOrEmpty(input)).isFalse();
    }
}
