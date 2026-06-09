package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class EntryTest extends KfsUnitTestBase {

    private Entry entry;

    @BeforeEach
    void setUp() {
        entry = new Entry();
    }

    @Test
    void defaultConstructor_createsEmptyEntry() {
        assertThat(entry.getUniversityFiscalYear()).isNull();
        assertThat(entry.getChartOfAccountsCode()).isNull();
        assertThat(entry.getAccountNumber()).isNull();
        assertThat(entry.getTransactionLedgerEntryAmount()).isNull();
    }

    @Test
    void settersAndGetters_identifiers() {
        entry.setUniversityFiscalYear(2024);
        entry.setChartOfAccountsCode("BL");
        entry.setAccountNumber("1234567");
        entry.setSubAccountNumber("SUB1");
        entry.setFinancialObjectCode("5000");
        entry.setFinancialSubObjectCode("001");
        entry.setFinancialBalanceTypeCode("AC");
        entry.setFinancialObjectTypeCode("EX");

        assertThat(entry.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(entry.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(entry.getAccountNumber()).isEqualTo("1234567");
        assertThat(entry.getSubAccountNumber()).isEqualTo("SUB1");
        assertThat(entry.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(entry.getFinancialSubObjectCode()).isEqualTo("001");
        assertThat(entry.getFinancialBalanceTypeCode()).isEqualTo("AC");
        assertThat(entry.getFinancialObjectTypeCode()).isEqualTo("EX");
    }

    @Test
    void settersAndGetters_periodAndDocumentInfo() {
        entry.setUniversityFiscalPeriodCode("01");
        entry.setFinancialDocumentTypeCode("GLPE");
        entry.setFinancialSystemOriginationCode("01");
        entry.setDocumentNumber("DOC12345");
        entry.setTransactionLedgerEntrySequenceNumber(1);

        assertThat(entry.getUniversityFiscalPeriodCode()).isEqualTo("01");
        assertThat(entry.getFinancialDocumentTypeCode()).isEqualTo("GLPE");
        assertThat(entry.getFinancialSystemOriginationCode()).isEqualTo("01");
        assertThat(entry.getDocumentNumber()).isEqualTo("DOC12345");
        assertThat(entry.getTransactionLedgerEntrySequenceNumber()).isEqualTo(1);
    }

    @Test
    void settersAndGetters_amountAndDescription() {
        entry.setTransactionLedgerEntryAmount(new KualiDecimal(500.25));
        entry.setTransactionLedgerEntryDescription("Test transaction");
        entry.setTransactionDebitCreditCode("D");

        assertThat(entry.getTransactionLedgerEntryAmount()).isEqualTo(new KualiDecimal("500.25"));
        assertThat(entry.getTransactionLedgerEntryDescription()).isEqualTo("Test transaction");
        assertThat(entry.getTransactionDebitCreditCode()).isEqualTo("D");
    }

    @Test
    void settersAndGetters_dates() {
        Date txDate = Date.valueOf("2024-06-15");
        entry.setTransactionDate(txDate);

        assertThat(entry.getTransactionDate()).isEqualTo(txDate);
    }

    @Test
    void settersAndGetters_referenceFields() {
        entry.setOrganizationDocumentNumber("ORG001");
        entry.setProjectCode("PROJ01");
        entry.setOrganizationReferenceId("REF001");
        entry.setReferenceFinancialDocumentTypeCode("RFDT");
        entry.setReferenceFinancialSystemOriginationCode("02");
        entry.setReferenceFinancialDocumentNumber("REFDOC001");

        assertThat(entry.getOrganizationDocumentNumber()).isEqualTo("ORG001");
        assertThat(entry.getProjectCode()).isEqualTo("PROJ01");
        assertThat(entry.getOrganizationReferenceId()).isEqualTo("REF001");
        assertThat(entry.getReferenceFinancialDocumentTypeCode()).isEqualTo("RFDT");
        assertThat(entry.getReferenceFinancialSystemOriginationCode()).isEqualTo("02");
        assertThat(entry.getReferenceFinancialDocumentNumber()).isEqualTo("REFDOC001");
    }

    @Test
    void settersAndGetters_encumbranceUpdateCode() {
        entry.setTransactionEncumbranceUpdateCode("R");
        assertThat(entry.getTransactionEncumbranceUpdateCode()).isEqualTo("R");
    }
}
