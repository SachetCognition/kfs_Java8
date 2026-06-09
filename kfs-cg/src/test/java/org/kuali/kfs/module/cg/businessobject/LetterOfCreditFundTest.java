package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class LetterOfCreditFundTest extends KfsUnitTestBase {

    private LetterOfCreditFund fund;

    @BeforeEach
    void setUp() {
        fund = new LetterOfCreditFund();
    }

    @Test
    void testLetterOfCreditFundCode() {
        fund.setLetterOfCreditFundCode("LOC01");
        assertThat(fund.getLetterOfCreditFundCode()).isEqualTo("LOC01");
    }

    @Test
    void testLetterOfCreditFundDescription() {
        fund.setLetterOfCreditFundDescription("Federal LOC Fund");
        assertThat(fund.getLetterOfCreditFundDescription()).isEqualTo("Federal LOC Fund");
    }

    @Test
    void testLetterOfCreditFundGroupCode() {
        fund.setLetterOfCreditFundGroupCode("GRP01");
        assertThat(fund.getLetterOfCreditFundGroupCode()).isEqualTo("GRP01");
    }

    @Test
    void testLetterOfCreditFundAmount() {
        KualiDecimal amount = new KualiDecimal(500000);
        fund.setLetterOfCreditFundAmount(amount);
        assertThat(fund.getLetterOfCreditFundAmount()).isEqualTo(amount);
    }

    @Test
    void testLetterOfCreditFundStartDate() {
        Date date = Date.valueOf("2024-01-01");
        fund.setLetterOfCreditFundStartDate(date);
        assertThat(fund.getLetterOfCreditFundStartDate()).isEqualTo(date);
    }

    @Test
    void testLetterOfCreditFundExpirationDate() {
        Date date = Date.valueOf("2025-12-31");
        fund.setLetterOfCreditFundExpirationDate(date);
        assertThat(fund.getLetterOfCreditFundExpirationDate()).isEqualTo(date);
    }

    @Test
    void testActive() {
        fund.setActive(true);
        assertThat(fund.isActive()).isTrue();

        fund.setActive(false);
        assertThat(fund.isActive()).isFalse();
    }

    @Test
    void testLetterOfCreditFundGroup() {
        LetterOfCreditFundGroup group = new LetterOfCreditFundGroup();
        fund.setLetterOfCreditFundGroup(group);
        assertThat(fund.getLetterOfCreditFundGroup()).isSameAs(group);
    }
}
