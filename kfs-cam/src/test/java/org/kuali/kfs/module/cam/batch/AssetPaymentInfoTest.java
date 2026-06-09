package org.kuali.kfs.module.cam.batch;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetPaymentInfoTest extends KfsUnitTestBase {

    private AssetPaymentInfo info;

    @BeforeEach
    void setUp() {
        info = new AssetPaymentInfo();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        info.setCapitalAssetNumber(12345L);
        assertThat(info.getCapitalAssetNumber()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("paymentSequenceNumber getter/setter")
    void paymentSequenceNumber() {
        info.setPaymentSequenceNumber(1);
        assertThat(info.getPaymentSequenceNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("depreciationDate getter/setter")
    void depreciationDate() {
        Date date = Date.valueOf("2024-01-15");
        info.setDepreciationDate(date);
        assertThat(info.getDepreciationDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("depreciableLifeLimit getter/setter")
    void depreciableLifeLimit() {
        info.setDepreciableLifeLimit(10);
        assertThat(info.getDepreciableLifeLimit()).isEqualTo(10);
    }

    @Test
    @DisplayName("organizationPlantAccountNumber getter/setter")
    void organizationPlantAccountNumber() {
        info.setOrganizationPlantAccountNumber("1234567");
        assertThat(info.getOrganizationPlantAccountNumber()).isEqualTo("1234567");
    }

    @Test
    @DisplayName("organizationPlantChartCode getter/setter")
    void organizationPlantChartCode() {
        info.setOrganizationPlantChartCode("UA");
        assertThat(info.getOrganizationPlantChartCode()).isEqualTo("UA");
    }

    @Test
    @DisplayName("campusPlantChartCode getter/setter")
    void campusPlantChartCode() {
        info.setCampusPlantChartCode("BL");
        assertThat(info.getCampusPlantChartCode()).isEqualTo("BL");
    }

    @Test
    @DisplayName("campusPlantAccountNumber getter/setter")
    void campusPlantAccountNumber() {
        info.setCampusPlantAccountNumber("7654321");
        assertThat(info.getCampusPlantAccountNumber()).isEqualTo("7654321");
    }

    @Test
    @DisplayName("financialObjectTypeCode getter/setter")
    void financialObjectTypeCode() {
        info.setFinancialObjectTypeCode("EE");
        assertThat(info.getFinancialObjectTypeCode()).isEqualTo("EE");
    }

    @Test
    @DisplayName("financialObjectSubTypeCode getter/setter")
    void financialObjectSubTypeCode() {
        info.setFinancialObjectSubTypeCode("CM");
        assertThat(info.getFinancialObjectSubTypeCode()).isEqualTo("CM");
    }

    @Test
    @DisplayName("primaryDepreciationMethodCode getter/setter")
    void primaryDepreciationMethodCode() {
        info.setPrimaryDepreciationMethodCode("SL");
        assertThat(info.getPrimaryDepreciationMethodCode()).isEqualTo("SL");
    }

    @Test
    @DisplayName("salvageAmount getter/setter")
    void salvageAmount() {
        KualiDecimal amount = new KualiDecimal(500);
        info.setSalvageAmount(amount);
        assertThat(info.getSalvageAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("primaryDepreciationBaseAmount getter/setter")
    void primaryDepreciationBaseAmount() {
        KualiDecimal amount = new KualiDecimal(10000);
        info.setPrimaryDepreciationBaseAmount(amount);
        assertThat(info.getPrimaryDepreciationBaseAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("financialObjectCode getter/setter")
    void financialObjectCode() {
        info.setFinancialObjectCode("7000");
        assertThat(info.getFinancialObjectCode()).isEqualTo("7000");
    }

    @Test
    @DisplayName("accumulatedPrimaryDepreciationAmount getter/setter")
    void accumulatedPrimaryDepreciationAmount() {
        KualiDecimal amount = new KualiDecimal(3000);
        info.setAccumulatedPrimaryDepreciationAmount(amount);
        assertThat(info.getAccumulatedPrimaryDepreciationAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("subAccountNumber getter/setter")
    void subAccountNumber() {
        info.setSubAccountNumber("12345");
        assertThat(info.getSubAccountNumber()).isEqualTo("12345");
    }

    @Test
    @DisplayName("financialSubObjectCode getter/setter")
    void financialSubObjectCode() {
        info.setFinancialSubObjectCode("001");
        assertThat(info.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    @DisplayName("projectCode getter/setter")
    void projectCode() {
        info.setProjectCode("PROJ1");
        assertThat(info.getProjectCode()).isEqualTo("PROJ1");
    }

    @Test
    @DisplayName("chartOfAccountsCode getter/setter")
    void chartOfAccountsCode() {
        info.setChartOfAccountsCode("UA");
        assertThat(info.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    @DisplayName("transactionAmount getter/setter")
    void transactionAmount() {
        KualiDecimal amount = new KualiDecimal(7500.50);
        info.setTransactionAmount(amount);
        assertThat(info.getTransactionAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("all fields null by default")
    void allFieldsNullByDefault() {
        AssetPaymentInfo fresh = new AssetPaymentInfo();
        assertThat(fresh.getCapitalAssetNumber()).isNull();
        assertThat(fresh.getPaymentSequenceNumber()).isNull();
        assertThat(fresh.getDepreciationDate()).isNull();
        assertThat(fresh.getDepreciableLifeLimit()).isNull();
        assertThat(fresh.getSalvageAmount()).isNull();
        assertThat(fresh.getTransactionAmount()).isNull();
    }
}
