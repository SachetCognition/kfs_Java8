package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.Bank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DepositTest extends KfsUnitTestBase {

    private Deposit deposit;

    @BeforeEach
    void setUp() {
        deposit = new Deposit();
    }

    @Test
    void defaultConstructorInitializesLists() {
        assertThat(deposit.getDepositCashReceiptControl()).isNotNull().isEmpty();
        assertThat(deposit.getBank()).isNotNull();
    }

    @Test
    void setAndGetDocumentNumber() {
        deposit.setDocumentNumber("DOC-001");
        assertThat(deposit.getDocumentNumber()).isEqualTo("DOC-001");
    }

    @Test
    void setAndGetFinancialDocumentDepositLineNumber() {
        deposit.setFinancialDocumentDepositLineNumber(3);
        assertThat(deposit.getFinancialDocumentDepositLineNumber()).isEqualTo(3);
    }

    @Test
    void setAndGetDepositTypeCode() {
        deposit.setDepositTypeCode("F");
        assertThat(deposit.getDepositTypeCode()).isEqualTo("F");
    }

    @Test
    void setAndGetDepositDate() {
        Date date = Date.valueOf("2024-06-15");
        deposit.setDepositDate(date);
        assertThat(deposit.getDepositDate()).isEqualTo(date);
    }

    @Test
    void setAndGetDepositAmount() {
        KualiDecimal amount = new KualiDecimal(5000.00);
        deposit.setDepositAmount(amount);
        assertThat(deposit.getDepositAmount()).isEqualTo(amount);
    }

    @Test
    void setAndGetDepositTicketNumber() {
        deposit.setDepositTicketNumber("TICKET-123");
        assertThat(deposit.getDepositTicketNumber()).isEqualTo("TICKET-123");
    }

    @Test
    void setAndGetDepositBankCode() {
        deposit.setDepositBankCode("BANK01");
        assertThat(deposit.getDepositBankCode()).isEqualTo("BANK01");
    }

    @Test
    void setAndGetBank() {
        Bank bank = new Bank();
        deposit.setBank(bank);
        assertThat(deposit.getBank()).isSameAs(bank);
    }

    @Test
    void setAndGetDepositedCurrency() {
        CurrencyDetail currency = new CurrencyDetail("DOC-001", "CM", "C");
        deposit.setDepositedCurrency(currency);
        assertThat(deposit.getDepositedCurrency()).isSameAs(currency);
    }

    @Test
    void setAndGetDepositedCoin() {
        CoinDetail coin = new CoinDetail("DOC-001", "CM", "C");
        deposit.setDepositedCoin(coin);
        assertThat(deposit.getDepositedCoin()).isSameAs(coin);
    }
}
