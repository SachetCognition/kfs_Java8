package org.kuali.kfs.module.cab.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.gl.businessobject.Entry;
import org.kuali.kfs.module.cab.CabConstants;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralLedgerEntryTest extends KfsUnitTestBase {

    private GeneralLedgerEntry gle;

    @BeforeEach
    void setUp() {
        gle = new GeneralLedgerEntry();
    }

    @Test
    @DisplayName("default constructor initializes lists")
    void defaultConstructor() {
        assertThat(gle.getGeneralLedgerEntryAssets()).isNotNull().isEmpty();
        assertThat(gle.getPurApLineAssetAccounts()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("constructor from Entry copies all fields")
    void constructorFromEntry() {
        Entry entry = new Entry();
        entry.setUniversityFiscalYear(2024);
        entry.setChartOfAccountsCode("UA");
        entry.setAccountNumber("1234567");
        entry.setSubAccountNumber("12345");
        entry.setFinancialObjectCode("7000");
        entry.setFinancialSubObjectCode("001");
        entry.setFinancialBalanceTypeCode("AC");
        entry.setFinancialObjectTypeCode("EE");
        entry.setUniversityFiscalPeriodCode("01");
        entry.setFinancialDocumentTypeCode("MPAY");
        entry.setFinancialSystemOriginationCode("01");
        entry.setDocumentNumber("DOC001");
        entry.setTransactionLedgerEntrySequenceNumber(1);
        entry.setTransactionLedgerEntryDescription("Test Entry");
        entry.setTransactionLedgerEntryAmount(new KualiDecimal(1000));
        entry.setTransactionDebitCreditCode(KFSConstants.GL_DEBIT_CODE);
        entry.setOrganizationReferenceId("ORG-REF");
        entry.setReferenceFinancialDocumentNumber("REF-DOC");
        entry.setProjectCode("PROJ");
        entry.setOrganizationDocumentNumber("ORG-DOC");

        GeneralLedgerEntry fromEntry = new GeneralLedgerEntry(entry);

        assertThat(fromEntry.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(fromEntry.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(fromEntry.getAccountNumber()).isEqualTo("1234567");
        assertThat(fromEntry.getSubAccountNumber()).isEqualTo("12345");
        assertThat(fromEntry.getFinancialObjectCode()).isEqualTo("7000");
        assertThat(fromEntry.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(fromEntry.getTransactionLedgerEntryAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(fromEntry.getTransactionDebitCreditCode()).isEqualTo(KFSConstants.GL_DEBIT_CODE);
    }

    @Test
    @DisplayName("getter/setter: generalLedgerAccountIdentifier")
    void generalLedgerAccountIdentifier() {
        gle.setGeneralLedgerAccountIdentifier(100L);
        assertThat(gle.getGeneralLedgerAccountIdentifier()).isEqualTo(100L);
    }

    @Test
    @DisplayName("getter/setter: universityFiscalYear")
    void universityFiscalYear() {
        gle.setUniversityFiscalYear(2024);
        assertThat(gle.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    @DisplayName("getter/setter: chartOfAccountsCode")
    void chartOfAccountsCode() {
        gle.setChartOfAccountsCode("BL");
        assertThat(gle.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    @DisplayName("getter/setter: accountNumber")
    void accountNumber() {
        gle.setAccountNumber("1234567");
        assertThat(gle.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    @DisplayName("getter/setter: transactionLedgerEntryAmount")
    void transactionLedgerEntryAmount() {
        KualiDecimal amount = new KualiDecimal(5000);
        gle.setTransactionLedgerEntryAmount(amount);
        assertThat(gle.getTransactionLedgerEntryAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("getter/setter: transactionLedgerSubmitAmount")
    void transactionLedgerSubmitAmount() {
        KualiDecimal amount = new KualiDecimal(3000);
        gle.setTransactionLedgerSubmitAmount(amount);
        assertThat(gle.getTransactionLedgerSubmitAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("getter/setter: transactionDebitCreditCode")
    void transactionDebitCreditCode() {
        gle.setTransactionDebitCreditCode(KFSConstants.GL_CREDIT_CODE);
        assertThat(gle.getTransactionDebitCreditCode()).isEqualTo(KFSConstants.GL_CREDIT_CODE);
    }

    @Test
    @DisplayName("getter/setter: activityStatusCode")
    void activityStatusCode() {
        gle.setActivityStatusCode(CabConstants.ActivityStatusCode.NEW);
        assertThat(gle.getActivityStatusCode()).isEqualTo(CabConstants.ActivityStatusCode.NEW);
    }

    @Test
    @DisplayName("getter/setter: selected flag")
    void selectedFlag() {
        gle.setSelected(true);
        assertThat(gle.isSelected()).isTrue();
    }

    @Test
    @DisplayName("getAmount: returns ledger amount for debit")
    void getAmountDebit() {
        KualiDecimal amount = new KualiDecimal(1500);
        gle.setTransactionLedgerEntryAmount(amount);
        gle.setTransactionDebitCreditCode("D");
        assertThat(gle.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("getAmount: returns negated amount for credit")
    void getAmountCredit() {
        KualiDecimal amount = new KualiDecimal(1500);
        gle.setTransactionLedgerEntryAmount(amount);
        gle.setTransactionDebitCreditCode("C");
        assertThat(gle.getAmount()).isEqualTo(amount.negated());
    }

    @Test
    @DisplayName("active flag defaults to false")
    void activeFlag() {
        assertThat(gle.isActive()).isFalse();
    }

    @Test
    @DisplayName("getter/setter: documentNumber")
    void documentNumber() {
        gle.setDocumentNumber("DOC12345");
        assertThat(gle.getDocumentNumber()).isEqualTo("DOC12345");
    }

    @Test
    @DisplayName("getter/setter: financialDocumentTypeCode")
    void financialDocumentTypeCode() {
        gle.setFinancialDocumentTypeCode("MPAY");
        assertThat(gle.getFinancialDocumentTypeCode()).isEqualTo("MPAY");
    }

    @Test
    @DisplayName("getter/setter: financialSystemOriginationCode")
    void financialSystemOriginationCode() {
        gle.setFinancialSystemOriginationCode("01");
        assertThat(gle.getFinancialSystemOriginationCode()).isEqualTo("01");
    }

    @Test
    @DisplayName("getter/setter: transactionLedgerEntrySequenceNumber")
    void transactionLedgerEntrySequenceNumber() {
        gle.setTransactionLedgerEntrySequenceNumber(5);
        assertThat(gle.getTransactionLedgerEntrySequenceNumber()).isEqualTo(5);
    }

    @Test
    @DisplayName("getter/setter: transactionLedgerEntryDescription")
    void transactionLedgerEntryDescription() {
        gle.setTransactionLedgerEntryDescription("Depreciation entry");
        assertThat(gle.getTransactionLedgerEntryDescription()).isEqualTo("Depreciation entry");
    }
}
