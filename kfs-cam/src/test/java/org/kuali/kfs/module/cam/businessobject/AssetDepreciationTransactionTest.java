package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AssetDepreciationTransactionTest extends KfsUnitTestBase {

    private AssetDepreciationTransaction txn;

    @BeforeEach
    void setUp() {
        txn = new AssetDepreciationTransaction();
    }

    @Test
    @DisplayName("default constructor: all fields null")
    void defaultConstructor() {
        assertThat(txn.getCapitalAssetNumber()).isNull();
        assertThat(txn.getDocumentNumber()).isNull();
        assertThat(txn.getChartOfAccountsCode()).isNull();
        assertThat(txn.getAccountNumber()).isNull();
        assertThat(txn.getTransactionAmount()).isNull();
    }

    @Test
    @DisplayName("capitalAssetNumber getter/setter")
    void capitalAssetNumber() {
        txn.setCapitalAssetNumber(5555L);
        assertThat(txn.getCapitalAssetNumber()).isEqualTo(5555L);
    }

    @Test
    @DisplayName("documentNumber getter/setter")
    void documentNumber() {
        txn.setDocumentNumber("DEPR-001");
        assertThat(txn.getDocumentNumber()).isEqualTo("DEPR-001");
    }

    @Test
    @DisplayName("chartOfAccountsCode getter/setter")
    void chartOfAccountsCode() {
        txn.setChartOfAccountsCode("UA");
        assertThat(txn.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    @DisplayName("accountNumber getter/setter")
    void accountNumber() {
        txn.setAccountNumber("1234567");
        assertThat(txn.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    @DisplayName("subAccountNumber getter/setter")
    void subAccountNumber() {
        txn.setSubAccountNumber("12345");
        assertThat(txn.getSubAccountNumber()).isEqualTo("12345");
    }

    @Test
    @DisplayName("financialObjectCode getter/setter")
    void financialObjectCode() {
        txn.setFinancialObjectCode("8610");
        assertThat(txn.getFinancialObjectCode()).isEqualTo("8610");
    }

    @Test
    @DisplayName("financialSubObjectCode getter/setter")
    void financialSubObjectCode() {
        txn.setFinancialSubObjectCode("001");
        assertThat(txn.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    @DisplayName("financialObjectTypeCode getter/setter")
    void financialObjectTypeCode() {
        txn.setFinancialObjectTypeCode("EX");
        assertThat(txn.getFinancialObjectTypeCode()).isEqualTo("EX");
    }

    @Test
    @DisplayName("transactionType getter/setter")
    void transactionType() {
        txn.setTransactionType("D");
        assertThat(txn.getTransactionType()).isEqualTo("D");
    }

    @Test
    @DisplayName("projectCode getter/setter")
    void projectCode() {
        txn.setProjectCode("PROJ1");
        assertThat(txn.getProjectCode()).isEqualTo("PROJ1");
    }

    @Test
    @DisplayName("transactionAmount getter/setter")
    void transactionAmount() {
        KualiDecimal amount = new KualiDecimal(250.50);
        txn.setTransactionAmount(amount);
        assertThat(txn.getTransactionAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("transactionLedgerEntryDescription getter/setter")
    void transactionLedgerEntryDescription() {
        txn.setTransactionLedgerEntryDescription("Monthly depreciation");
        assertThat(txn.getTransactionLedgerEntryDescription()).isEqualTo("Monthly depreciation");
    }
}
