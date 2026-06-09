package org.kuali.kfs.vnd.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class VendorDetailTest extends KfsUnitTestBase {

    private VendorDetail vendorDetail;

    @BeforeEach
    void setUp() {
        vendorDetail = new VendorDetail();
    }

    @Test
    void defaultConstructorInitializesCollections() {
        assertThat(vendorDetail.getVendorAddresses()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorAliases()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorContacts()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorContracts()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorCustomerNumbers()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorPhoneNumbers()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorShippingSpecialConditions()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorCommodities()).isNotNull().isEmpty();
        assertThat(vendorDetail.getVendorHeader()).isNotNull();
        assertThat(vendorDetail.isVendorParentIndicator()).isTrue();
    }

    @Test
    void getVendorNumberReturnsFormattedNumber() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(1000);
        vendorDetail.setVendorDetailAssignedIdentifier(0);

        assertThat(vendorDetail.getVendorNumber()).isEqualTo("1000-0");
    }

    @Test
    void getVendorNumberReturnsEmptyWhenHeaderNull() {
        vendorDetail.setVendorDetailAssignedIdentifier(0);

        assertThat(vendorDetail.getVendorNumber()).isEmpty();
    }

    @Test
    void getVendorNumberReturnsEmptyWhenDetailNull() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(1000);

        assertThat(vendorDetail.getVendorNumber()).isEmpty();
    }

    @Test
    void getVendorNumberReturnsEmptyWhenBothNull() {
        assertThat(vendorDetail.getVendorNumber()).isEmpty();
    }

    @Test
    void setVendorNumberParsesValidNumber() {
        vendorDetail.setVendorNumber("1234-5");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(1234);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(5);
    }

    @Test
    void setVendorNumberClearsIdsOnEmpty() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(1000);
        vendorDetail.setVendorDetailAssignedIdentifier(0);

        vendorDetail.setVendorNumber("");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isNull();
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isNull();
    }

    @Test
    void setVendorNumberIgnoresInvalidFormat() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(1000);
        vendorDetail.setVendorDetailAssignedIdentifier(0);

        vendorDetail.setVendorNumber("invalid");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(0);
    }

    @Test
    void setVendorNumberIgnoresNonNumericParts() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(1000);
        vendorDetail.setVendorDetailAssignedIdentifier(0);

        vendorDetail.setVendorNumber("abc-def");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(0);
    }

    @Test
    void setVendorNumberIgnoresNoDashFormat() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(100);
        vendorDetail.setVendorDetailAssignedIdentifier(5);

        vendorDetail.setVendorNumber("12345");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(100);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(5);
    }

    @Test
    void setVendorNumberIgnoresLeadingDash() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(100);
        vendorDetail.setVendorDetailAssignedIdentifier(5);

        vendorDetail.setVendorNumber("-123");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(100);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(5);
    }

    @Test
    void setVendorNumberIgnoresTrailingDash() {
        vendorDetail.setVendorHeaderGeneratedIdentifier(100);
        vendorDetail.setVendorDetailAssignedIdentifier(5);

        vendorDetail.setVendorNumber("123-");

        assertThat(vendorDetail.getVendorHeaderGeneratedIdentifier()).isEqualTo(100);
        assertThat(vendorDetail.getVendorDetailAssignedIdentifier()).isEqualTo(5);
    }

    @Test
    void getVendorSoldToNumberReturnsFormattedNumber() {
        vendorDetail.setVendorSoldToGeneratedIdentifier(2000);
        vendorDetail.setVendorSoldToAssignedIdentifier(1);

        assertThat(vendorDetail.getVendorSoldToNumber()).isEqualTo("2000-1");
    }

    @Test
    void getVendorSoldToNumberReturnsEmptyWhenNull() {
        assertThat(vendorDetail.getVendorSoldToNumber()).isEmpty();
    }

    @Test
    void setVendorSoldToNumberParsesValid() {
        vendorDetail.setVendorSoldToNumber("5000-3");

        assertThat(vendorDetail.getVendorSoldToGeneratedIdentifier()).isEqualTo(5000);
        assertThat(vendorDetail.getVendorSoldToAssignedIdentifier()).isEqualTo(3);
    }

    @Test
    void setVendorSoldToNumberClearsOnEmpty() {
        vendorDetail.setVendorSoldToGeneratedIdentifier(5000);
        vendorDetail.setVendorSoldToAssignedIdentifier(3);

        vendorDetail.setVendorSoldToNumber("");

        assertThat(vendorDetail.getVendorSoldToGeneratedIdentifier()).isNull();
        assertThat(vendorDetail.getVendorSoldToAssignedIdentifier()).isNull();
    }

    @Test
    void isVendorDebarredReturnsTrueWhenIndicatorTrue() {
        vendorDetail.getVendorHeader().setVendorDebarredIndicator(true);

        assertThat(vendorDetail.isVendorDebarred()).isTrue();
    }

    @Test
    void isVendorDebarredReturnsFalseWhenIndicatorFalse() {
        vendorDetail.getVendorHeader().setVendorDebarredIndicator(false);

        assertThat(vendorDetail.isVendorDebarred()).isFalse();
    }

    @Test
    void isVendorDebarredReturnsFalseWhenIndicatorNull() {
        vendorDetail.getVendorHeader().setVendorDebarredIndicator(null);

        assertThat(vendorDetail.isVendorDebarred()).isFalse();
    }

    @Test
    void activeIndicatorGetterSetter() {
        vendorDetail.setActiveIndicator(true);
        assertThat(vendorDetail.isActiveIndicator()).isTrue();

        vendorDetail.setActiveIndicator(false);
        assertThat(vendorDetail.isActiveIndicator()).isFalse();
    }

    @Test
    void vendorNameGetterSetter() {
        vendorDetail.setVendorName("Test Vendor");
        assertThat(vendorDetail.getVendorName()).isEqualTo("Test Vendor");
    }

    @Test
    void altVendorNameDelegatesToVendorName() {
        vendorDetail.setAltVendorName("Alt Name");
        assertThat(vendorDetail.getAltVendorName()).isEqualTo("Alt Name");
        assertThat(vendorDetail.getVendorName()).isEqualTo("Alt Name");
    }

    @Test
    void vendorMinimumOrderAmountGetterSetter() {
        KualiDecimal amount = new KualiDecimal("100.00");
        vendorDetail.setVendorMinimumOrderAmount(amount);
        assertThat(vendorDetail.getVendorMinimumOrderAmount()).isEqualTo(amount);
    }

    @Test
    void taxableIndicatorGetterSetter() {
        vendorDetail.setTaxableIndicator(true);
        assertThat(vendorDetail.isTaxableIndicator()).isTrue();
    }

    @Test
    void vendorFirstLastNameIndicatorGetterSetter() {
        vendorDetail.setVendorFirstLastNameIndicator(true);
        assertThat(vendorDetail.isVendorFirstLastNameIndicator()).isTrue();
    }

    @Test
    void vendorDunsNumberGetterSetter() {
        vendorDetail.setVendorDunsNumber("123456789");
        assertThat(vendorDetail.getVendorDunsNumber()).isEqualTo("123456789");
    }

    @Test
    void vendorUrlAddressGetterSetter() {
        vendorDetail.setVendorUrlAddress("http://example.com");
        assertThat(vendorDetail.getVendorUrlAddress()).isEqualTo("http://example.com");
    }

    @Test
    void vendorRemitNameGetterSetter() {
        vendorDetail.setVendorRemitName("Remit Name");
        assertThat(vendorDetail.getVendorRemitName()).isEqualTo("Remit Name");
    }

    @Test
    void vendorRestrictedIndicatorGetterSetter() {
        vendorDetail.setVendorRestrictedIndicator(true);
        assertThat(vendorDetail.getVendorRestrictedIndicator()).isTrue();
    }

    @Test
    void vendorSoldToNameGetterSetter() {
        vendorDetail.setVendorSoldToName("Sold To Vendor");
        assertThat(vendorDetail.getVendorSoldToName()).isEqualTo("Sold To Vendor");
    }

    @Test
    void vendorPaymentTermsCodeGetterSetter() {
        vendorDetail.setVendorPaymentTermsCode("NET30");
        assertThat(vendorDetail.getVendorPaymentTermsCode()).isEqualTo("NET30");
    }

    @Test
    void vendorShippingTitleCodeGetterSetter() {
        vendorDetail.setVendorShippingTitleCode("FOB");
        assertThat(vendorDetail.getVendorShippingTitleCode()).isEqualTo("FOB");
    }

    @Test
    void vendorInactiveReasonCodeGetterSetter() {
        vendorDetail.setVendorInactiveReasonCode("CLOSED");
        assertThat(vendorDetail.getVendorInactiveReasonCode()).isEqualTo("CLOSED");
    }
}
