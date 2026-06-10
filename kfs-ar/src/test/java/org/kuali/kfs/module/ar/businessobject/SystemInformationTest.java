package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SystemInformationTest extends KfsUnitTestBase {

    private SystemInformation systemInformation;

    @BeforeEach
    void setUp() {
        systemInformation = new SystemInformation();
    }

    @Test
    void testUniversityFiscalYear() {
        systemInformation.setUniversityFiscalYear(2024);
        assertThat(systemInformation.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void testProcessingChartOfAccountCode() {
        systemInformation.setProcessingChartOfAccountCode("UA");
        assertThat(systemInformation.getProcessingChartOfAccountCode()).isEqualTo("UA");
    }

    @Test
    void testProcessingOrganizationCode() {
        systemInformation.setProcessingOrganizationCode("ACCT");
        assertThat(systemInformation.getProcessingOrganizationCode()).isEqualTo("ACCT");
    }

    @Test
    void testUniversityFederalEmployerIdentificationNumber() {
        systemInformation.setUniversityFederalEmployerIdentificationNumber("12-3456789");
        assertThat(systemInformation.getUniversityFederalEmployerIdentificationNumber()).isEqualTo("12-3456789");
    }

    @Test
    void testDiscountObjectCode() {
        systemInformation.setDiscountObjectCode("5555");
        assertThat(systemInformation.getDiscountObjectCode()).isEqualTo("5555");
    }

    @Test
    void testCreditCardObjectCode() {
        systemInformation.setCreditCardObjectCode("7777");
        assertThat(systemInformation.getCreditCardObjectCode()).isEqualTo("7777");
    }

    @Test
    void testOrganizationRemitToAddressName() {
        systemInformation.setOrganizationRemitToAddressName("IU Bursar");
        assertThat(systemInformation.getOrganizationRemitToAddressName()).isEqualTo("IU Bursar");
    }

    @Test
    void testOrganizationRemitToCityName() {
        systemInformation.setOrganizationRemitToCityName("Bloomington");
        assertThat(systemInformation.getOrganizationRemitToCityName()).isEqualTo("Bloomington");
    }

    @Test
    void testOrganizationRemitToStateCode() {
        systemInformation.setOrganizationRemitToStateCode("IN");
        assertThat(systemInformation.getOrganizationRemitToStateCode()).isEqualTo("IN");
    }

    @Test
    void testOrganizationRemitToZipCode() {
        systemInformation.setOrganizationRemitToZipCode("47405");
        assertThat(systemInformation.getOrganizationRemitToZipCode()).isEqualTo("47405");
    }

    @Test
    void testActive() {
        systemInformation.setActive(true);
        assertThat(systemInformation.isActive()).isTrue();

        systemInformation.setActive(false);
        assertThat(systemInformation.isActive()).isFalse();
    }
}
