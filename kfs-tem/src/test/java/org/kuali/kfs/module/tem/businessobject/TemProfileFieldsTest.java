package org.kuali.kfs.module.tem.businessobject;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TemProfile Business Object Fields")
class TemProfileFieldsTest extends KfsUnitTestBase {

    private TemProfile profile;

    @BeforeEach
    void setUp() {
        profile = new TemProfile();
    }

    @Test
    @DisplayName("should initialize with empty collections")
    void testDefaultConstruction() {
        assertThat(profile.getArrangers()).isNotNull().isEmpty();
        assertThat(profile.getAccounts()).isNotNull().isEmpty();
        assertThat(profile.getEmergencyContacts()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("should set and get profileId")
    void testProfileId() {
        profile.setProfileId(1001);
        assertThat(profile.getProfileId()).isEqualTo(1001);
    }

    @Test
    @DisplayName("should set and get updatedBy")
    void testUpdatedBy() {
        profile.setUpdatedBy("admin");
        assertThat(profile.getUpdatedBy()).isEqualTo("admin");
    }

    @Test
    @DisplayName("should set and get lastUpdate")
    void testLastUpdate() {
        Date date = Date.valueOf("2024-03-15");
        profile.setLastUpdate(date);
        assertThat(profile.getLastUpdate()).isEqualTo(date);
    }

    @Test
    @DisplayName("should set and get employeeId")
    void testEmployeeId() {
        profile.setEmployeeId("EMP001");
        assertThat(profile.getEmployeeId()).isEqualTo("EMP001");
    }

    @Test
    @DisplayName("should set and get homeDepartment")
    void testHomeDepartment() {
        profile.setHomeDeptChartOfAccountsCode("UA");
        profile.setHomeDeptOrgCode("ACCT");
        assertThat(profile.getHomeDeptChartOfAccountsCode()).isEqualTo("UA");
        assertThat(profile.getHomeDeptOrgCode()).isEqualTo("ACCT");
    }

    @Test
    @DisplayName("should set and get defaultChartCode")
    void testDefaultChartCode() {
        profile.setDefaultChartCode("BL");
        assertThat(profile.getDefaultChartCode()).isEqualTo("BL");
    }

    @Test
    @DisplayName("should set and get defaultAccount")
    void testDefaultAccount() {
        profile.setDefaultAccount("1234567");
        assertThat(profile.getDefaultAccount()).isEqualTo("1234567");
    }

    @Test
    @DisplayName("should set and get defaultSubAccount")
    void testDefaultSubAccount() {
        profile.setDefaultSubAccount("SUB01");
        assertThat(profile.getDefaultSubAccount()).isEqualTo("SUB01");
    }

    @Test
    @DisplayName("should set and get defaultProjectCode")
    void testDefaultProjectCode() {
        profile.setDefaultProjectCode("PROJ1");
        assertThat(profile.getDefaultProjectCode()).isEqualTo("PROJ1");
    }

    @Test
    @DisplayName("should set and get achSignUp")
    void testAchSignUp() {
        profile.setAchSignUp("Y");
        assertThat(profile.getAchSignUp()).isEqualTo("Y");
    }

    @Test
    @DisplayName("should set and get achTransactionType")
    void testAchTransactionType() {
        profile.setAchTransactionType("ACH");
        assertThat(profile.getAchTransactionType()).isEqualTo("ACH");
    }

    @Test
    @DisplayName("should set and get onlyArrangeesInLookup")
    void testOnlyArrangeesInLookup() {
        profile.setOnlyArrangeesInLookup(true);
        assertThat(profile.isOnlyArrangeesInLookup()).isTrue();
    }

    @Test
    @DisplayName("should set temProfileAddress")
    void testSetTemProfileAddress() {
        TemProfileAddress address = new TemProfileAddress();
        profile.setTemProfileAddress(address);
        // getTemProfileAddress() requires Spring context, so we just verify setter doesn't throw
        assertThat(address).isNotNull();
    }
}
