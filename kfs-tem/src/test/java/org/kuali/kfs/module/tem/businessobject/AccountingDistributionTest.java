package org.kuali.kfs.module.tem.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AccountingDistribution Business Object")
class AccountingDistributionTest extends KfsUnitTestBase {

    private AccountingDistribution distribution;

    @BeforeEach
    void setUp() {
        distribution = new AccountingDistribution();
    }

    @Test
    @DisplayName("should initialize with zero subTotal and remainingAmount")
    void testDefaultConstruction() {
        assertThat(distribution.getSubTotal()).isEqualTo(KualiDecimal.ZERO);
        assertThat(distribution.getRemainingAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    @DisplayName("should set and get objectCode")
    void testObjectCode() {
        distribution.setObjectCode("5000");
        assertThat(distribution.getObjectCode()).isEqualTo("5000");
    }

    @Test
    @DisplayName("should set and get objectCodeName")
    void testObjectCodeName() {
        distribution.setObjectCodeName("Travel Expense");
        assertThat(distribution.getObjectCodeName()).isEqualTo("Travel Expense");
    }

    @Test
    @DisplayName("should set and get cardType")
    void testCardType() {
        distribution.setCardType("CTS");
        assertThat(distribution.getCardType()).isEqualTo("CTS");
    }

    @Test
    @DisplayName("should set subTotal correctly")
    void testSetSubTotal() {
        KualiDecimal amount = new KualiDecimal(150.00);
        distribution.setSubTotal(amount);
        assertThat(distribution.getSubTotal()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should not set null subTotal")
    void testSetNullSubTotal() {
        distribution.setSubTotal(new KualiDecimal(100));
        distribution.setSubTotal(null);
        assertThat(distribution.getSubTotal()).isEqualTo(new KualiDecimal(100));
    }

    @Test
    @DisplayName("should set remainingAmount when non-negative")
    void testSetRemainingAmount() {
        KualiDecimal amount = new KualiDecimal(200.00);
        distribution.setRemainingAmount(amount);
        assertThat(distribution.getRemainingAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("should not set negative remainingAmount")
    void testSetNegativeRemainingAmount() {
        distribution.setRemainingAmount(new KualiDecimal(50));
        distribution.setRemainingAmount(new KualiDecimal(-10));
        assertThat(distribution.getRemainingAmount()).isEqualTo(new KualiDecimal(50));
    }

    @Test
    @DisplayName("should not set null remainingAmount")
    void testSetNullRemainingAmount() {
        distribution.setRemainingAmount(new KualiDecimal(75));
        distribution.setRemainingAmount(null);
        assertThat(distribution.getRemainingAmount()).isEqualTo(new KualiDecimal(75));
    }

    @Test
    @DisplayName("should return selected true when selected and remainingAmount > 0")
    void testGetSelectedWhenPositiveRemaining() {
        distribution.setSelected(Boolean.TRUE);
        distribution.setRemainingAmount(new KualiDecimal(100));
        assertThat(distribution.getSelected()).isTrue();
    }

    @Test
    @DisplayName("should return selected false when remainingAmount is zero")
    void testGetSelectedWhenZeroRemaining() {
        distribution.setSelected(Boolean.TRUE);
        distribution.setRemainingAmount(KualiDecimal.ZERO);
        assertThat(distribution.getSelected()).isFalse();
    }

    @Test
    @DisplayName("should return selected false when explicitly set to false")
    void testGetSelectedWhenSetFalse() {
        distribution.setSelected(Boolean.FALSE);
        distribution.setRemainingAmount(new KualiDecimal(100));
        assertThat(distribution.getSelected()).isFalse();
    }

    @Test
    @DisplayName("should set and get disabled flag")
    void testDisabled() {
        assertThat(distribution.getDisabled()).isFalse();
        distribution.setDisabled(Boolean.TRUE);
        assertThat(distribution.getDisabled()).isTrue();
    }

    @Test
    @DisplayName("should allow zero as remainingAmount")
    void testSetZeroRemainingAmount() {
        distribution.setRemainingAmount(KualiDecimal.ZERO);
        assertThat(distribution.getRemainingAmount()).isEqualTo(KualiDecimal.ZERO);
    }
}
