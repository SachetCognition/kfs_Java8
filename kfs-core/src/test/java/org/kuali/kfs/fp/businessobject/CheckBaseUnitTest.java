package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class CheckBaseUnitTest extends KfsUnitTestBase {

    private CheckBase check;

    @BeforeEach
    void setUp() {
        check = new CheckBase();
    }

    @Test
    void defaultConstructorSetsSequenceIdToOne() {
        assertThat(check.getSequenceId()).isEqualTo(1);
    }

    @Test
    void defaultConstructorSetsAmountToZero() {
        assertThat(check.getAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void setAndGetCheckNumber() {
        check.setCheckNumber("12345");
        assertThat(check.getCheckNumber()).isEqualTo("12345");
    }

    @Test
    void setAndGetCheckDate() {
        Date date = Date.valueOf("2024-06-15");
        check.setCheckDate(date);
        assertThat(check.getCheckDate()).isEqualTo(date);
    }

    @Test
    void setAndGetDescription() {
        check.setDescription("Test check");
        assertThat(check.getDescription()).isEqualTo("Test check");
    }

    @Test
    void setAndGetSequenceId() {
        check.setSequenceId(42);
        assertThat(check.getSequenceId()).isEqualTo(42);
    }

    @Test
    void setAndGetAmount() {
        KualiDecimal amount = new KualiDecimal(100.50);
        check.setAmount(amount);
        assertThat(check.getAmount()).isEqualTo(amount);
    }

    @Test
    void setAndGetDocumentNumber() {
        check.setDocumentNumber("DOC-001");
        assertThat(check.getDocumentNumber()).isEqualTo("DOC-001");
    }

    @Test
    void setAndGetFinancialDocumentTypeCode() {
        check.setFinancialDocumentTypeCode("CR");
        assertThat(check.getFinancialDocumentTypeCode()).isEqualTo("CR");
    }

    @Test
    void setAndGetCashieringStatus() {
        check.setCashieringStatus("C");
        assertThat(check.getCashieringStatus()).isEqualTo("C");
    }

    @Test
    void setAndGetFinancialDocumentDepositLineNumber() {
        check.setFinancialDocumentDepositLineNumber(3);
        assertThat(check.getFinancialDocumentDepositLineNumber()).isEqualTo(3);
    }

    @Test
    void isLikeReturnsTrueForIdenticalChecks() {
        Date date = Date.valueOf("2024-01-15");

        check.setCheckNumber("100");
        check.setDescription("desc");
        check.setFinancialDocumentTypeCode("CR");
        check.setCashieringStatus("C");
        check.setDocumentNumber("DOC-1");
        check.setSequenceId(1);
        check.setFinancialDocumentDepositLineNumber(1);
        check.setCheckDate(date);
        check.setAmount(new KualiDecimal(50.00));

        CheckBase other = new CheckBase();
        other.setCheckNumber("100");
        other.setDescription("desc");
        other.setFinancialDocumentTypeCode("CR");
        other.setCashieringStatus("C");
        other.setDocumentNumber("DOC-1");
        other.setSequenceId(1);
        other.setFinancialDocumentDepositLineNumber(1);
        other.setCheckDate(date);
        other.setAmount(new KualiDecimal(50.00));

        assertThat(check.isLike(other)).isTrue();
    }

    @Test
    void isLikeReturnsFalseForDifferentCheckNumber() {
        Date date = Date.valueOf("2024-01-15");

        check.setCheckNumber("100");
        check.setCheckDate(date);
        check.setAmount(new KualiDecimal(50.00));

        CheckBase other = new CheckBase();
        other.setCheckNumber("200");
        other.setCheckDate(date);
        other.setAmount(new KualiDecimal(50.00));

        assertThat(check.isLike(other)).isFalse();
    }

    @Test
    void isLikeReturnsFalseForDifferentAmount() {
        Date date = Date.valueOf("2024-01-15");

        check.setCheckNumber("100");
        check.setCheckDate(date);
        check.setAmount(new KualiDecimal(50.00));

        CheckBase other = new CheckBase();
        other.setCheckNumber("100");
        other.setCheckDate(date);
        other.setAmount(new KualiDecimal(75.00));

        assertThat(check.isLike(other)).isFalse();
    }

    @Test
    void isLikeReturnsFalseForDifferentDescription() {
        Date date = Date.valueOf("2024-01-15");

        check.setCheckNumber("100");
        check.setDescription("desc1");
        check.setCheckDate(date);
        check.setAmount(new KualiDecimal(50.00));

        CheckBase other = new CheckBase();
        other.setCheckNumber("100");
        other.setDescription("desc2");
        other.setCheckDate(date);
        other.setAmount(new KualiDecimal(50.00));

        assertThat(check.isLike(other)).isFalse();
    }

    @Test
    void toStringMapperContainsExpectedKeys() {
        check.setCheckNumber("999");
        check.setAmount(new KualiDecimal(100.00));
        check.setSequenceId(5);

        LinkedHashMap map = check.toStringMapper_RICE20_REFACTORME();

        assertThat(map).containsKey("sequenceId");
        assertThat(map).containsKey("checkNumber");
        assertThat(map).containsKey("amount");
        assertThat(map).containsKey("checkDate");
        assertThat(map.get("checkNumber")).isEqualTo("999");
    }
}
