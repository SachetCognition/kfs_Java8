package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class OrganizationTest extends KfsUnitTestBase {

    private Organization org;

    @BeforeEach
    void setUp() {
        org = new Organization();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            org.setChartOfAccountsCode("BL");
            assertThat(org.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void organizationCode() {
            org.setOrganizationCode("CHEM");
            assertThat(org.getOrganizationCode()).isEqualTo("CHEM");
        }

        @Test
        void organizationName() {
            org.setOrganizationName("Chemistry Department");
            assertThat(org.getOrganizationName()).isEqualTo("Chemistry Department");
        }

        @Test
        void active() {
            org.setActive(true);
            assertThat(org.isActive()).isTrue();
            org.setActive(false);
            assertThat(org.isActive()).isFalse();
        }

        @Test
        void organizationTypeCode() {
            org.setOrganizationTypeCode("D");
            assertThat(org.getOrganizationTypeCode()).isEqualTo("D");
        }

        @Test
        void reportsToChartOfAccountsCode() {
            org.setReportsToChartOfAccountsCode("BL");
            assertThat(org.getReportsToChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void reportsToOrganizationCode() {
            org.setReportsToOrganizationCode("ARSC");
            assertThat(org.getReportsToOrganizationCode()).isEqualTo("ARSC");
        }

        @Test
        void organizationPhysicalCampusCode() {
            org.setOrganizationPhysicalCampusCode("BL");
            assertThat(org.getOrganizationPhysicalCampusCode()).isEqualTo("BL");
        }

        @Test
        void responsibilityCenterCode() {
            org.setResponsibilityCenterCode("1");
            assertThat(org.getResponsibilityCenterCode()).isEqualTo("1");
        }

        @Test
        void organizationDefaultAccountNumber() {
            org.setOrganizationDefaultAccountNumber("1234567");
            assertThat(org.getOrganizationDefaultAccountNumber()).isEqualTo("1234567");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chartOfAccounts() {
            Chart chart = new Chart();
            org.setChartOfAccounts(chart);
            assertThat(org.getChartOfAccounts()).isSameAs(chart);
        }

        @Test
        void reportsToOrganization() {
            Organization parent = new Organization();
            org.setReportsToOrganizationCode("ARSC");
            assertThat(org.getReportsToOrganizationCode()).isEqualTo("ARSC");
        }
    }
}
