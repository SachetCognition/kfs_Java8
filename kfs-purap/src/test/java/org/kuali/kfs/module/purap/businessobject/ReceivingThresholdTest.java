package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ReceivingThresholdTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ReceivingThreshold threshold = new ReceivingThreshold();
        assertThat(threshold.getThresholdIdentifier()).isNull();
        assertThat(threshold.getChartOfAccountsCode()).isNull();
        assertThat(threshold.getThresholdAmount()).isNull();
        assertThat(threshold.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        ReceivingThreshold threshold = new ReceivingThreshold();
        threshold.setThresholdIdentifier(1);
        threshold.setChartOfAccountsCode("BL");
        threshold.setAccountTypeCode("EX");
        threshold.setSubFundGroupCode("GENFND");
        threshold.setFinancialObjectCode("5000");
        threshold.setOrganizationCode("BIOL");
        threshold.setThresholdAmount(new KualiDecimal(1000));
        threshold.setPurchasingCommodityCode("14111500");
        threshold.setVendorHeaderGeneratedIdentifier(1000);
        threshold.setVendorDetailAssignedIdentifier(0);
        threshold.setActive(true);

        assertThat(threshold.getThresholdIdentifier()).isEqualTo(1);
        assertThat(threshold.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(threshold.getAccountTypeCode()).isEqualTo("EX");
        assertThat(threshold.getSubFundGroupCode()).isEqualTo("GENFND");
        assertThat(threshold.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(threshold.getOrganizationCode()).isEqualTo("BIOL");
        assertThat(threshold.getThresholdAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(threshold.getPurchasingCommodityCode()).isEqualTo("14111500");
        assertThat(threshold.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(threshold.getVendorDetailAssignedIdentifier()).isEqualTo(0);
        assertThat(threshold.isActive()).isTrue();
    }
}
