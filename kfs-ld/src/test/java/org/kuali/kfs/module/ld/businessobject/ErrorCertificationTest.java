package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorCertificationTest extends KfsUnitTestBase {

    private ErrorCertification errorCertification;

    @BeforeEach
    void setUp() {
        errorCertification = new ErrorCertification();
    }

    @Test
    void testSetAndGetDocumentNumber() {
        errorCertification.setDocumentNumber("DOC12345");
        assertThat(errorCertification.getDocumentNumber()).isEqualTo("DOC12345");
    }

    @Test
    void testSetAndGetExpenditureDescription() {
        errorCertification.setExpenditureDescription("Travel expenditure");
        assertThat(errorCertification.getExpenditureDescription()).isEqualTo("Travel expenditure");
    }

    @Test
    void testSetAndGetExpenditureProjectBenefit() {
        errorCertification.setExpenditureProjectBenefit("Project ABC");
        assertThat(errorCertification.getExpenditureProjectBenefit()).isEqualTo("Project ABC");
    }

    @Test
    void testSetAndGetErrorDescription() {
        errorCertification.setErrorDescription("Wrong account charged");
        assertThat(errorCertification.getErrorDescription()).isEqualTo("Wrong account charged");
    }

    @Test
    void testSetAndGetErrorCorrectionReason() {
        errorCertification.setErrorCorrectionReason("Correct to proper account");
        assertThat(errorCertification.getErrorCorrectionReason()).isEqualTo("Correct to proper account");
    }

    @Test
    void testAllFieldsNullByDefault() {
        assertThat(errorCertification.getDocumentNumber()).isNull();
        assertThat(errorCertification.getExpenditureDescription()).isNull();
        assertThat(errorCertification.getExpenditureProjectBenefit()).isNull();
        assertThat(errorCertification.getErrorDescription()).isNull();
        assertThat(errorCertification.getErrorCorrectionReason()).isNull();
    }
}
