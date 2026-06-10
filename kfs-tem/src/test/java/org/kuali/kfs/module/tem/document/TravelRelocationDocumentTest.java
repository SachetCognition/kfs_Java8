package org.kuali.kfs.module.tem.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelRelocationDocument")
class TravelRelocationDocumentTest extends KfsUnitTestBase {

    private TravelRelocationDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(TravelRelocationDocument.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("should set and get from city")
    void testFromCity() {
        document.setFromCity("New York");
        assertThat(document.getFromCity()).isEqualTo("New York");
    }

    @Test
    @DisplayName("should set and get to city")
    void testToCity() {
        document.setToCity("Los Angeles");
        assertThat(document.getToCity()).isEqualTo("Los Angeles");
    }

    @Test
    @DisplayName("should set and get from state code")
    void testFromStateCode() {
        document.setFromStateCode("NY");
        assertThat(document.getFromStateCode()).isEqualTo("NY");
    }

    @Test
    @DisplayName("should set and get to state code")
    void testToStateCode() {
        document.setToStateCode("CA");
        assertThat(document.getToStateCode()).isEqualTo("CA");
    }

    @Test
    @DisplayName("should set and get from country code")
    void testFromCountryCode() {
        document.setFromCountryCode("US");
        assertThat(document.getFromCountryCode()).isEqualTo("US");
    }

    @Test
    @DisplayName("should set and get to country code")
    void testToCountryCode() {
        document.setToCountryCode("US");
        assertThat(document.getToCountryCode()).isEqualTo("US");
    }

    @Test
    @DisplayName("should set and get reason code")
    void testReasonCode() {
        document.setReasonCode("RLN");
        assertThat(document.getReasonCode()).isEqualTo("RLN");
    }

    @Test
    @DisplayName("should set and get job classification code")
    void testJobClsCode() {
        document.setJobClsCode("PRF");
        assertThat(document.getJobClsCode()).isEqualTo("PRF");
    }
}
