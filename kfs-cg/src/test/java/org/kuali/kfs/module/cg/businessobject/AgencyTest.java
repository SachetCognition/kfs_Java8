package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AgencyTest extends KfsUnitTestBase {

    private Agency agency;

    @BeforeEach
    void setUp() {
        agency = new Agency();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(agency.getAgencyAddresses()).isNotNull();
        assertThat(agency.getAgencyAddresses()).isEmpty();
    }

    @Test
    void testAgencyNumber() {
        agency.setAgencyNumber("12345");
        assertThat(agency.getAgencyNumber()).isEqualTo("12345");
    }

    @Test
    void testReportingName() {
        agency.setReportingName("Test Agency");
        assertThat(agency.getReportingName()).isEqualTo("Test Agency");
    }

    @Test
    void testFullName() {
        agency.setFullName("Test Agency Full Name");
        assertThat(agency.getFullName()).isEqualTo("Test Agency Full Name");
    }

    @Test
    void testAgencyTypeCode() {
        agency.setAgencyTypeCode("F");
        assertThat(agency.getAgencyTypeCode()).isEqualTo("F");
    }

    @Test
    void testReportsToAgencyNumber() {
        agency.setReportsToAgencyNumber("99999");
        assertThat(agency.getReportsToAgencyNumber()).isEqualTo("99999");
    }

    @Test
    void testIndirectAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        agency.setIndirectAmount(amount);
        assertThat(agency.getIndirectAmount()).isEqualTo(amount);
    }

    @Test
    void testInStateIndicator() {
        agency.setInStateIndicator(true);
        assertThat(agency.isInStateIndicator()).isTrue();

        agency.setInStateIndicator(false);
        assertThat(agency.isInStateIndicator()).isFalse();
    }

    @Test
    void testActive() {
        agency.setActive(true);
        assertThat(agency.isActive()).isTrue();

        agency.setActive(false);
        assertThat(agency.isActive()).isFalse();
    }

    @Test
    void testCageNumber() {
        agency.setCageNumber("CAGE1");
        assertThat(agency.getCageNumber()).isEqualTo("CAGE1");
    }

    @Test
    void testDodacNumber() {
        agency.setDodacNumber("DODAC1");
        assertThat(agency.getDodacNumber()).isEqualTo("DODAC1");
    }

    @Test
    void testDunAndBradstreetNumber() {
        agency.setDunAndBradstreetNumber("123456789");
        assertThat(agency.getDunAndBradstreetNumber()).isEqualTo("123456789");
    }

    @Test
    void testDunsPlusFourNumber() {
        agency.setDunsPlusFourNumber("1234");
        assertThat(agency.getDunsPlusFourNumber()).isEqualTo("1234");
    }

    @Test
    void testStateAgencyIndicator() {
        agency.setStateAgencyIndicator(true);
        assertThat(agency.isStateAgencyIndicator()).isTrue();

        agency.setStateAgencyIndicator(false);
        assertThat(agency.isStateAgencyIndicator()).isFalse();
    }

    @Test
    void testCustomerCreationOptionCode() {
        agency.setCustomerCreationOptionCode("CUST");
        assertThat(agency.getCustomerCreationOptionCode()).isEqualTo("CUST");
    }

    @Test
    void testCustomerNumber() {
        agency.setCustomerNumber("CUST001");
        assertThat(agency.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testCustomerTypeCode() {
        agency.setCustomerTypeCode("GVT");
        assertThat(agency.getCustomerTypeCode()).isEqualTo("GVT");
    }

    @Test
    void testDunningCampaign() {
        agency.setDunningCampaign("CAMP01");
        assertThat(agency.getDunningCampaign()).isEqualTo("CAMP01");
    }

    @Test
    void testAgencyAddresses() {
        List<AgencyAddress> addresses = new ArrayList<>();
        AgencyAddress address = new AgencyAddress();
        addresses.add(address);
        agency.setAgencyAddresses(addresses);
        assertThat(agency.getAgencyAddresses()).hasSize(1);
    }

    @Test
    void testReportsToAgency() {
        Agency parent = new Agency();
        parent.setAgencyNumber("PARENT");
        agency.setReportsToAgency(parent);
        assertThat(agency.getReportsToAgency()).isSameAs(parent);
    }

    @Test
    void testAgencyType() {
        AgencyType type = new AgencyType();
        agency.setAgencyType(type);
        assertThat(agency.getAgencyType()).isSameAs(type);
    }
}
