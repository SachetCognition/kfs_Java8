package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class OrganizationOptionsTest extends KfsUnitTestBase {

    private OrganizationOptions options;

    @BeforeEach
    void setUp() {
        options = new OrganizationOptions();
    }

    @Test
    void testChartOfAccountsCode() {
        options.setChartOfAccountsCode("UA");
        assertThat(options.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void testOrganizationCode() {
        options.setOrganizationCode("ACCT");
        assertThat(options.getOrganizationCode()).isEqualTo("ACCT");
    }

    @Test
    void testOrganizationPhoneNumber() {
        options.setOrganizationPhoneNumber("812-555-1234");
        assertThat(options.getOrganizationPhoneNumber()).isEqualTo("812-555-1234");
    }

    @Test
    void testOrganizationFaxNumber() {
        options.setOrganizationFaxNumber("812-555-5678");
        assertThat(options.getOrganizationFaxNumber()).isEqualTo("812-555-5678");
    }

    @Test
    void testOrganizationMessageText() {
        options.setOrganizationMessageText("Thank you for your payment");
        assertThat(options.getOrganizationMessageText()).isEqualTo("Thank you for your payment");
    }

    @Test
    void testProcessingChartOfAccountCode() {
        options.setProcessingChartOfAccountCode("BL");
        assertThat(options.getProcessingChartOfAccountCode()).isEqualTo("BL");
    }

    @Test
    void testProcessingOrganizationCode() {
        options.setProcessingOrganizationCode("PROC");
        assertThat(options.getProcessingOrganizationCode()).isEqualTo("PROC");
    }

    @Test
    void testCgBillerIndicator() {
        options.setCgBillerIndicator(true);
        assertThat(options.isCgBillerIndicator()).isTrue();
        options.setCgBillerIndicator(false);
        assertThat(options.isCgBillerIndicator()).isFalse();
    }

    @Test
    void testOrganizationPostalZipCode() {
        options.setOrganizationPostalZipCode("47405");
        assertThat(options.getOrganizationPostalZipCode()).isEqualTo("47405");
    }
}
