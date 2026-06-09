package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerProfileTest extends KfsUnitTestBase {

    private CustomerProfile profile;

    @BeforeEach
    void setUp() {
        profile = new CustomerProfile();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(profile).isNotNull();
    }

    @Test
    void testGetCustomerShortName() {
        profile.setChartCode("BL");
        profile.setUnitCode("ACCT");
        profile.setSubUnitCode("MAIN");
        assertThat(profile.getCustomerShortName()).isEqualTo("BL-ACCT-MAIN");
    }

    @Test
    void testSetCustomerShortName_isNoOp() {
        // setCustomerShortName is an empty method; getCustomerShortName derives from chart+unit+subUnit
        profile.setChartCode("UA");
        profile.setUnitCode("VPIT");
        profile.setSubUnitCode("VPSS");
        profile.setCustomerShortName("XX-YY-ZZ");
        // setCustomerShortName does not actually parse; shortName is still derived from chart/unit/subunit
        assertThat(profile.getCustomerShortName()).isEqualTo("UA-VPIT-VPSS");
    }

    @Test
    void testGetSortName() {
        profile.setChartCode("BL");
        profile.setUnitCode("ACCT");
        profile.setSubUnitCode("MAIN");
        String sortName = profile.getSortName();
        assertThat(sortName).contains("BL").contains("ACCT").contains("MAIN");
    }

    @Test
    void testGettersAndSetters_basicFields() {
        KualiInteger id = new KualiInteger(1);
        profile.setId(id);
        profile.setChartCode("BL");
        profile.setUnitCode("ACCT");
        profile.setSubUnitCode("MAIN");
        profile.setCustomerDescription("Test Customer");
        profile.setContactFullName("John Doe");
        profile.setProcessingEmailAddr("test@example.com");

        assertThat(profile.getId()).isEqualTo(id);
        assertThat(profile.getChartCode()).isEqualTo("BL");
        assertThat(profile.getUnitCode()).isEqualTo("ACCT");
        assertThat(profile.getSubUnitCode()).isEqualTo("MAIN");
        assertThat(profile.getCustomerDescription()).isEqualTo("Test Customer");
        assertThat(profile.getContactFullName()).isEqualTo("John Doe");
        assertThat(profile.getProcessingEmailAddr()).isEqualTo("test@example.com");
    }

    @Test
    void testGettersAndSetters_addressFields() {
        profile.setAddress1("123 Main St");
        profile.setAddress2("Suite 100");
        profile.setAddress3("Building A");
        profile.setAddress4("Floor 3");
        profile.setCity("Anytown");
        profile.setStateCode("IN");
        profile.setZipCode("46202");
        profile.setCountryCode("US");

        assertThat(profile.getAddress1()).isEqualTo("123 Main St");
        assertThat(profile.getAddress2()).isEqualTo("Suite 100");
        assertThat(profile.getAddress3()).isEqualTo("Building A");
        assertThat(profile.getAddress4()).isEqualTo("Floor 3");
        assertThat(profile.getCity()).isEqualTo("Anytown");
        assertThat(profile.getStateCode()).isEqualTo("IN");
        assertThat(profile.getZipCode()).isEqualTo("46202");
        assertThat(profile.getCountryCode()).isEqualTo("US");
    }

    @Test
    void testGettersAndSetters_thresholds() {
        profile.setFileThresholdAmount(new KualiDecimal(10000));
        profile.setFileThresholdEmailAddress("threshold@example.com");
        profile.setPaymentThresholdAmount(new KualiDecimal(5000));
        profile.setPaymentThresholdEmailAddress("pmt-threshold@example.com");

        assertThat(profile.getFileThresholdAmount()).isEqualTo(new KualiDecimal(10000));
        assertThat(profile.getFileThresholdEmailAddress()).isEqualTo("threshold@example.com");
        assertThat(profile.getPaymentThresholdAmount()).isEqualTo(new KualiDecimal(5000));
        assertThat(profile.getPaymentThresholdEmailAddress()).isEqualTo("pmt-threshold@example.com");
    }

    @Test
    void testGettersAndSetters_booleanFlags() {
        profile.setAdviceCreate(true);
        profile.setEmployeeCheck(true);
        profile.setNraReview(true);
        profile.setOwnershipCodeRequired(true);
        profile.setPayeeIdRequired(true);
        profile.setAccountingEditRequired(true);
        profile.setRelieveLiabilities(true);
        profile.setActive(true);

        assertThat(profile.getAdviceCreate()).isTrue();
        assertThat(profile.getEmployeeCheck()).isTrue();
        assertThat(profile.getNraReview()).isTrue();
        assertThat(profile.getOwnershipCodeRequired()).isTrue();
        assertThat(profile.getPayeeIdRequired()).isTrue();
        assertThat(profile.getAccountingEditRequired()).isTrue();
        assertThat(profile.getRelieveLiabilities()).isTrue();
        assertThat(profile.isActive()).isTrue();
    }

    @Test
    void testGettersAndSetters_defaults() {
        profile.setDefaultChartCode("BL");
        profile.setDefaultAccountNumber("1234567");
        profile.setDefaultSubAccountNumber("001");
        profile.setDefaultObjectCode("5000");
        profile.setDefaultSubObjectCode("100");
        profile.setDefaultPhysicalCampusProcessingCode("BL");

        assertThat(profile.getDefaultChartCode()).isEqualTo("BL");
        assertThat(profile.getDefaultAccountNumber()).isEqualTo("1234567");
        assertThat(profile.getDefaultSubAccountNumber()).isEqualTo("001");
        assertThat(profile.getDefaultObjectCode()).isEqualTo("5000");
        assertThat(profile.getDefaultSubObjectCode()).isEqualTo("100");
        assertThat(profile.getDefaultPhysicalCampusProcessingCode()).isEqualTo("BL");
    }

    @Test
    void testGettersAndSetters_achAndAdvice() {
        profile.setAchPaymentDescription("ACH Payment");
        profile.setAdviceHeaderText("Advice Header");
        profile.setAdviceSubjectLine("Subject");
        profile.setAdviceReturnEmailAddr("advice@example.com");
        profile.setAchTransactionType("22");

        assertThat(profile.getAchPaymentDescription()).isEqualTo("ACH Payment");
        assertThat(profile.getAdviceHeaderText()).isEqualTo("Advice Header");
        assertThat(profile.getAdviceSubjectLine()).isEqualTo("Subject");
        assertThat(profile.getAdviceReturnEmailAddr()).isEqualTo("advice@example.com");
        assertThat(profile.getAchTransactionType()).isEqualTo("22");
    }

    @Test
    void testGettersAndSetters_checkNoteLines() {
        profile.setCheckHeaderNoteTextLine1("Line1");
        profile.setCheckHeaderNoteTextLine2("Line2");
        profile.setCheckHeaderNoteTextLine3("Line3");
        profile.setCheckHeaderNoteTextLine4("Line4");
        profile.setAdditionalCheckNoteTextLine1("Add1");
        profile.setAdditionalCheckNoteTextLine2("Add2");
        profile.setAdditionalCheckNoteTextLine3("Add3");
        profile.setAdditionalCheckNoteTextLine4("Add4");

        assertThat(profile.getCheckHeaderNoteTextLine1()).isEqualTo("Line1");
        assertThat(profile.getCheckHeaderNoteTextLine2()).isEqualTo("Line2");
        assertThat(profile.getCheckHeaderNoteTextLine3()).isEqualTo("Line3");
        assertThat(profile.getCheckHeaderNoteTextLine4()).isEqualTo("Line4");
        assertThat(profile.getAdditionalCheckNoteTextLine1()).isEqualTo("Add1");
        assertThat(profile.getAdditionalCheckNoteTextLine2()).isEqualTo("Add2");
        assertThat(profile.getAdditionalCheckNoteTextLine3()).isEqualTo("Add3");
        assertThat(profile.getAdditionalCheckNoteTextLine4()).isEqualTo("Add4");
    }

    @Test
    void testEquals_sameValues() {
        CustomerProfile p1 = new CustomerProfile();
        p1.setChartCode("BL");
        p1.setUnitCode("ACCT");
        p1.setSubUnitCode("MAIN");

        CustomerProfile p2 = new CustomerProfile();
        p2.setChartCode("BL");
        p2.setUnitCode("ACCT");
        p2.setSubUnitCode("MAIN");

        assertThat(p1.equals(p2)).isTrue();
    }

    @Test
    void testEquals_differentValues() {
        CustomerProfile p1 = new CustomerProfile();
        p1.setChartCode("BL");
        p1.setUnitCode("ACCT");
        p1.setSubUnitCode("MAIN");

        CustomerProfile p2 = new CustomerProfile();
        p2.setChartCode("UA");
        p2.setUnitCode("VPIT");
        p2.setSubUnitCode("VPSS");

        assertThat(p1.equals(p2)).isFalse();
    }

    @Test
    void testGetCustomerBankByDisbursementType_found() {
        DisbursementType dtChck = new DisbursementType();
        dtChck.setCode("CHCK");
        CustomerBank cb1 = new CustomerBank();
        cb1.setDisbursementTypeCode("CHCK");
        cb1.setDisbursementType(dtChck);

        DisbursementType dtAch = new DisbursementType();
        dtAch.setCode("ACH");
        CustomerBank cb2 = new CustomerBank();
        cb2.setDisbursementTypeCode("ACH");
        cb2.setDisbursementType(dtAch);

        List<CustomerBank> banks = new ArrayList<>();
        banks.add(cb1);
        banks.add(cb2);
        profile.setCustomerBanks(banks);

        assertThat(profile.getCustomerBankByDisbursementType("ACH")).isSameAs(cb2);
    }

    @Test
    void testGetCustomerBankByDisbursementType_notFound() {
        List<CustomerBank> banks = new ArrayList<>();
        profile.setCustomerBanks(banks);
        assertThat(profile.getCustomerBankByDisbursementType("WIRE")).isNull();
    }

    @Test
    void testCustomerBanksList() {
        List<CustomerBank> banks = new ArrayList<>();
        banks.add(new CustomerBank());
        profile.setCustomerBanks(banks);
        assertThat(profile.getCustomerBanks()).hasSize(1);
    }
}
