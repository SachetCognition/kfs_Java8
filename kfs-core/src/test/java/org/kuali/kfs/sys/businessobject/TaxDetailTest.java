package org.kuali.kfs.sys.businessobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TaxDetailTest extends KfsUnitTestBase {

    private TaxDetail taxDetail;

    @BeforeEach
    void setUp() {
        taxDetail = new TaxDetail();
    }

    @Test
    void defaultConstructor_initializesDefaults() {
        assertThat(taxDetail.getTaxRate()).isEqualTo(BigDecimal.ZERO);
        assertThat(taxDetail.getTaxAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void setAndGetRateCode() {
        taxDetail.setRateCode("CA");
        assertThat(taxDetail.getRateCode()).isEqualTo("CA");
    }

    @Test
    void setAndGetRateName() {
        taxDetail.setRateName("California");
        assertThat(taxDetail.getRateName()).isEqualTo("California");
    }

    @Test
    void setAndGetTaxRate() {
        taxDetail.setTaxRate(new BigDecimal("0.075"));
        assertThat(taxDetail.getTaxRate()).isEqualByComparingTo("0.075");
    }

    @Test
    void setAndGetTypeCode() {
        taxDetail.setTypeCode("ST");
        assertThat(taxDetail.getTypeCode()).isEqualTo("ST");
    }

    @Test
    void setAndGetTaxAmount() {
        taxDetail.setTaxAmount(new KualiDecimal("100.50"));
        assertThat(taxDetail.getTaxAmount()).isEqualTo(new KualiDecimal("100.50"));
    }

    @Test
    void setAndGetChartOfAccountsCode() {
        taxDetail.setChartOfAccountsCode("BL");
        assertThat(taxDetail.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void setAndGetAccountNumber() {
        taxDetail.setAccountNumber("1234567");
        assertThat(taxDetail.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void setAndGetFinancialObjectCode() {
        taxDetail.setFinancialObjectCode("5000");
        assertThat(taxDetail.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void toString_format() {
        taxDetail.setTypeCode("ST");
        taxDetail.setRateCode("CA");
        taxDetail.setRateName("California");
        taxDetail.setTaxRate(new BigDecimal("0.075"));
        taxDetail.setTaxAmount(new KualiDecimal("7.50"));

        String result = taxDetail.toString();
        assertThat(result).contains("ST").contains("CA").contains("California");
    }
}
