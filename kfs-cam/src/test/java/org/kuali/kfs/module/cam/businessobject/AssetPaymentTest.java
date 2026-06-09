package org.kuali.kfs.module.cam.businessobject;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetPaymentTest extends KfsUnitTestBase {

    private AssetPayment payment;

    @BeforeEach
    void setUp() {
        payment = new AssetPayment();
    }

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        assertThat(payment.getCapitalAssetNumber()).isNull();
        assertThat(payment.getPaymentSequenceNumber()).isNull();
        assertThat(payment.getAccountChargeAmount()).isNull();
    }

    @Test
    @DisplayName("copy constructor without amounts: copies non-amount fields only")
    void copyConstructor_withoutAmounts() {
        payment.setCapitalAssetNumber(1001L);
        payment.setPaymentSequenceNumber(1);
        payment.setChartOfAccountsCode("UA");
        payment.setAccountNumber("1234567");
        payment.setSubAccountNumber("12345");
        payment.setFinancialObjectCode("7000");
        payment.setFinancialSubObjectCode("001");
        payment.setDocumentNumber("DOC001");
        payment.setFinancialDocumentPostingYear(2024);
        payment.setFinancialDocumentPostingPeriodCode("01");
        payment.setProjectCode("PROJ1");
        payment.setAccountChargeAmount(new KualiDecimal(5000));
        payment.setPrimaryDepreciationBaseAmount(new KualiDecimal(4500));

        AssetPayment copy = new AssetPayment(payment, false);

        assertThat(copy.getCapitalAssetNumber()).isEqualTo(1001L);
        assertThat(copy.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(copy.getAccountNumber()).isEqualTo("1234567");
        assertThat(copy.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(copy.getAccountChargeAmount()).isNull();
        assertThat(copy.getPrimaryDepreciationBaseAmount()).isNull();
    }

    @Test
    @DisplayName("copy constructor with amounts: copies all fields including amounts")
    void copyConstructor_withAmounts() {
        payment.setCapitalAssetNumber(1001L);
        payment.setPaymentSequenceNumber(1);
        payment.setChartOfAccountsCode("UA");
        payment.setAccountNumber("1234567");
        payment.setAccountChargeAmount(new KualiDecimal(5000));
        payment.setPrimaryDepreciationBaseAmount(new KualiDecimal(4500));
        payment.setAccumulatedPrimaryDepreciationAmount(new KualiDecimal(1000));
        payment.setPreviousYearPrimaryDepreciationAmount(new KualiDecimal(500));
        payment.setPeriod1Depreciation1Amount(new KualiDecimal(100));
        payment.setPeriod12Depreciation1Amount(new KualiDecimal(1200));

        AssetPayment copy = new AssetPayment(payment, true);

        assertThat(copy.getCapitalAssetNumber()).isEqualTo(1001L);
        assertThat(copy.getAccountChargeAmount()).isEqualTo(new KualiDecimal(5000));
        assertThat(copy.getPrimaryDepreciationBaseAmount()).isEqualTo(new KualiDecimal(4500));
        assertThat(copy.getAccumulatedPrimaryDepreciationAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(copy.getPreviousYearPrimaryDepreciationAmount()).isEqualTo(new KualiDecimal(500));
        assertThat(copy.getPeriod1Depreciation1Amount()).isEqualTo(new KualiDecimal(100));
        assertThat(copy.getPeriod12Depreciation1Amount()).isEqualTo(new KualiDecimal(1200));
    }

    @Test
    @DisplayName("all period depreciation getters/setters")
    void periodDepreciation() {
        for (int i = 1; i <= 12; i++) {
            KualiDecimal amount = new KualiDecimal(i * 100);
            switch (i) {
                case 1: payment.setPeriod1Depreciation1Amount(amount); break;
                case 2: payment.setPeriod2Depreciation1Amount(amount); break;
                case 3: payment.setPeriod3Depreciation1Amount(amount); break;
                case 4: payment.setPeriod4Depreciation1Amount(amount); break;
                case 5: payment.setPeriod5Depreciation1Amount(amount); break;
                case 6: payment.setPeriod6Depreciation1Amount(amount); break;
                case 7: payment.setPeriod7Depreciation1Amount(amount); break;
                case 8: payment.setPeriod8Depreciation1Amount(amount); break;
                case 9: payment.setPeriod9Depreciation1Amount(amount); break;
                case 10: payment.setPeriod10Depreciation1Amount(amount); break;
                case 11: payment.setPeriod11Depreciation1Amount(amount); break;
                case 12: payment.setPeriod12Depreciation1Amount(amount); break;
            }
        }
        assertThat(payment.getPeriod1Depreciation1Amount()).isEqualTo(new KualiDecimal(100));
        assertThat(payment.getPeriod6Depreciation1Amount()).isEqualTo(new KualiDecimal(600));
        assertThat(payment.getPeriod12Depreciation1Amount()).isEqualTo(new KualiDecimal(1200));
    }

    @Test
    @DisplayName("financialDocumentPostingDate getter/setter")
    void financialDocumentPostingDate() {
        Date date = Date.valueOf("2024-06-15");
        payment.setFinancialDocumentPostingDate(date);
        assertThat(payment.getFinancialDocumentPostingDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("transferPaymentCode getter/setter")
    void transferPaymentCode() {
        payment.setTransferPaymentCode("Y");
        assertThat(payment.getTransferPaymentCode()).isEqualTo("Y");
    }

    @Test
    @DisplayName("purchaseOrderNumber getter/setter")
    void purchaseOrderNumber() {
        payment.setPurchaseOrderNumber("PO-001");
        assertThat(payment.getPurchaseOrderNumber()).isEqualTo("PO-001");
    }

    @Test
    @DisplayName("requisitionNumber getter/setter")
    void requisitionNumber() {
        payment.setRequisitionNumber("REQ-001");
        assertThat(payment.getRequisitionNumber()).isEqualTo("REQ-001");
    }

    @Test
    @DisplayName("organizationReferenceId getter/setter")
    void organizationReferenceId() {
        payment.setOrganizationReferenceId("ORG-REF");
        assertThat(payment.getOrganizationReferenceId()).isEqualTo("ORG-REF");
    }

    @Test
    @DisplayName("financialSystemOriginationCode getter/setter")
    void financialSystemOriginationCode() {
        payment.setFinancialSystemOriginationCode("01");
        assertThat(payment.getFinancialSystemOriginationCode()).isEqualTo("01");
    }

    @Test
    @DisplayName("financialDocumentTypeCode getter/setter")
    void financialDocumentTypeCode() {
        payment.setFinancialDocumentTypeCode("MPAY");
        assertThat(payment.getFinancialDocumentTypeCode()).isEqualTo("MPAY");
    }
}
