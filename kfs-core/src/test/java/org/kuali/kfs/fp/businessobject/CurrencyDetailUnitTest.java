package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyDetailUnitTest extends KfsUnitTestBase {

    private CurrencyDetail currencyDetail;

    @BeforeEach
    void setUp() {
        currencyDetail = new CurrencyDetail();
    }

    @Test
    void defaultConstructorInitializesAmountsToZero() {
        assertThat(currencyDetail.getFinancialDocumentHundredDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentFiftyDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentTwentyDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentTenDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentFiveDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentTwoDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentOneDollarAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(currencyDetail.getFinancialDocumentOtherDollarAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void threeArgConstructorSetsKeys() {
        CurrencyDetail detail = new CurrencyDetail("DOC-001", "CR", "C");
        assertThat(detail.getDocumentNumber()).isEqualTo("DOC-001");
        assertThat(detail.getFinancialDocumentTypeCode()).isEqualTo("CR");
        assertThat(detail.getCashieringStatus()).isEqualTo("C");
    }

    @Test
    void copyConstructorCopiesAllFields() {
        CurrencyDetail source = new CurrencyDetail("DOC-002", "CM", "O");
        source.setFinancialDocumentHundredDollarAmount(new KualiDecimal(500.00));
        source.setFinancialDocumentFiftyDollarAmount(new KualiDecimal(250.00));
        source.setFinancialDocumentTwentyDollarAmount(new KualiDecimal(100.00));
        source.setFinancialDocumentTenDollarAmount(new KualiDecimal(50.00));
        source.setFinancialDocumentFiveDollarAmount(new KualiDecimal(25.00));
        source.setFinancialDocumentTwoDollarAmount(new KualiDecimal(10.00));
        source.setFinancialDocumentOneDollarAmount(new KualiDecimal(5.00));
        source.setFinancialDocumentOtherDollarAmount(new KualiDecimal(3.00));

        CurrencyDetail copy = new CurrencyDetail(source);

        assertThat(copy.getDocumentNumber()).isEqualTo("DOC-002");
        assertThat(copy.getFinancialDocumentHundredDollarAmount()).isEqualTo(new KualiDecimal(500.00));
        assertThat(copy.getFinancialDocumentFiftyDollarAmount()).isEqualTo(new KualiDecimal(250.00));
        assertThat(copy.getFinancialDocumentTwentyDollarAmount()).isEqualTo(new KualiDecimal(100.00));
        assertThat(copy.getFinancialDocumentTenDollarAmount()).isEqualTo(new KualiDecimal(50.00));
        assertThat(copy.getFinancialDocumentFiveDollarAmount()).isEqualTo(new KualiDecimal(25.00));
        assertThat(copy.getFinancialDocumentTwoDollarAmount()).isEqualTo(new KualiDecimal(10.00));
        assertThat(copy.getFinancialDocumentOneDollarAmount()).isEqualTo(new KualiDecimal(5.00));
        assertThat(copy.getFinancialDocumentOtherDollarAmount()).isEqualTo(new KualiDecimal(3.00));
    }

    @Test
    void setKeysSetsAllKeyFields() {
        currencyDetail.setKeys("DOC-100", "IB", "R");
        assertThat(currencyDetail.getDocumentNumber()).isEqualTo("DOC-100");
        assertThat(currencyDetail.getFinancialDocumentTypeCode()).isEqualTo("IB");
        assertThat(currencyDetail.getCashieringStatus()).isEqualTo("R");
    }

    @Test
    void hundredDollarCountComputesCorrectly() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(500.00));
        assertThat(currencyDetail.getHundredDollarCount()).isEqualTo(5);
    }

    @Test
    void hundredDollarCountReturnsZeroWhenNull() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(null);
        assertThat(currencyDetail.getHundredDollarCount()).isEqualTo(0);
    }

    @Test
    void getTotalAmountSumsAllDenominations() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(100.00));
        currencyDetail.setFinancialDocumentFiftyDollarAmount(new KualiDecimal(50.00));
        currencyDetail.setFinancialDocumentTwentyDollarAmount(new KualiDecimal(20.00));
        currencyDetail.setFinancialDocumentTenDollarAmount(new KualiDecimal(10.00));
        currencyDetail.setFinancialDocumentFiveDollarAmount(new KualiDecimal(5.00));
        currencyDetail.setFinancialDocumentTwoDollarAmount(new KualiDecimal(2.00));
        currencyDetail.setFinancialDocumentOneDollarAmount(new KualiDecimal(1.00));
        currencyDetail.setFinancialDocumentOtherDollarAmount(new KualiDecimal(0.50));

        assertThat(currencyDetail.getTotalAmount()).isEqualTo(new KualiDecimal(188.50));
    }

    @Test
    void getTotalAmountHandlesNullAmounts() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(null);
        currencyDetail.setFinancialDocumentFiftyDollarAmount(null);
        currencyDetail.setFinancialDocumentTwentyDollarAmount(null);
        currencyDetail.setFinancialDocumentTenDollarAmount(null);
        currencyDetail.setFinancialDocumentFiveDollarAmount(null);
        currencyDetail.setFinancialDocumentTwoDollarAmount(null);
        currencyDetail.setFinancialDocumentOneDollarAmount(null);
        currencyDetail.setFinancialDocumentOtherDollarAmount(null);

        assertThat(currencyDetail.getTotalAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void zeroOutSetsAllAmountsToZero() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(500.00));
        currencyDetail.zeroOutAmounts();
        assertThat(currencyDetail.getFinancialDocumentHundredDollarAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void isEmptyReturnsTrueWhenAllZero() {
        assertThat(currencyDetail.isEmpty()).isTrue();
    }

    @Test
    void isEmptyReturnsFalseWhenAnyNonZero() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(100.00));
        assertThat(currencyDetail.isEmpty()).isFalse();
    }

    @Test
    void addAmountsToThisCurrencyDetail() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(100.00));

        CurrencyDetail other = new CurrencyDetail();
        other.setFinancialDocumentHundredDollarAmount(new KualiDecimal(200.00));

        currencyDetail.add(other);

        assertThat(currencyDetail.getFinancialDocumentHundredDollarAmount()).isEqualTo(new KualiDecimal(300.00));
    }

    @Test
    void subtractAmountsFromThisCurrencyDetail() {
        currencyDetail.setFinancialDocumentHundredDollarAmount(new KualiDecimal(500.00));

        CurrencyDetail other = new CurrencyDetail();
        other.setFinancialDocumentHundredDollarAmount(new KualiDecimal(200.00));

        currencyDetail.subtract(other);

        assertThat(currencyDetail.getFinancialDocumentHundredDollarAmount()).isEqualTo(new KualiDecimal(300.00));
    }
}
