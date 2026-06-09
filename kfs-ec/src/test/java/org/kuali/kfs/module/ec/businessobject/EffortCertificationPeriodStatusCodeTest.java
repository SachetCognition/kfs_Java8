package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationPeriodStatusCodeTest extends KfsUnitTestBase {

    private EffortCertificationPeriodStatusCode statusCode;

    @BeforeEach
    void setUp() {
        statusCode = new EffortCertificationPeriodStatusCode();
    }

    @Test
    @DisplayName("Should set and get status code")
    void shouldSetAndGetStatusCode() {
        statusCode.setEffortCertificationReportPeriodStatusCode("A");
        assertThat(statusCode.getEffortCertificationReportPeriodStatusCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("Should set and get status description")
    void shouldSetAndGetStatusDescription() {
        statusCode.setEffortCertificationReportPeriodStatusDescription("Active");
        assertThat(statusCode.getEffortCertificationReportPeriodStatusDescription()).isEqualTo("Active");
    }

    @Test
    @DisplayName("toStringMapper contains status code key")
    void toStringMapperContainsStatusCode() {
        statusCode.setEffortCertificationReportPeriodStatusCode("C");
        assertThat(statusCode.toStringMapper_RICE20_REFACTORME())
                .containsKey("effortCertificationReportPeriodStatusCode")
                .containsValue("C");
    }
}
