package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CoinDetailUnitTest extends KfsUnitTestBase {

    private CoinDetail coinDetail;

    @BeforeEach
    void setUp() {
        coinDetail = new CoinDetail();
    }

    @Test
    void defaultConstructorInitializesAmountsToZero() {
        assertThat(coinDetail.getFinancialDocumentHundredCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentTwentyFiveCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentTenCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentFiveCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentOneCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentOtherCentAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void threeArgConstructorSetsKeysAndDefaultAmounts() {
        CoinDetail detail = new CoinDetail("DOC-001", "CR", "C");
        assertThat(detail.getDocumentNumber()).isEqualTo("DOC-001");
        assertThat(detail.getFinancialDocumentTypeCode()).isEqualTo("CR");
        assertThat(detail.getCashieringStatus()).isEqualTo("C");
        assertThat(detail.getFinancialDocumentFiftyCentAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void copyConstructorCopiesAllFields() {
        CoinDetail source = new CoinDetail("DOC-002", "CM", "O");
        source.setFinancialDocumentHundredCentAmount(new KualiDecimal(5.00));
        source.setFinancialDocumentFiftyCentAmount(new KualiDecimal(3.50));
        source.setFinancialDocumentTwentyFiveCentAmount(new KualiDecimal(2.25));
        source.setFinancialDocumentTenCentAmount(new KualiDecimal(1.10));
        source.setFinancialDocumentFiveCentAmount(new KualiDecimal(0.55));
        source.setFinancialDocumentOneCentAmount(new KualiDecimal(0.07));
        source.setFinancialDocumentOtherCentAmount(new KualiDecimal(0.99));

        CoinDetail copy = new CoinDetail(source);

        assertThat(copy.getDocumentNumber()).isEqualTo("DOC-002");
        assertThat(copy.getFinancialDocumentTypeCode()).isEqualTo("CM");
        assertThat(copy.getCashieringStatus()).isEqualTo("O");
        assertThat(copy.getFinancialDocumentHundredCentAmount()).isEqualTo(new KualiDecimal(5.00));
        assertThat(copy.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(3.50));
        assertThat(copy.getFinancialDocumentTwentyFiveCentAmount()).isEqualTo(new KualiDecimal(2.25));
        assertThat(copy.getFinancialDocumentTenCentAmount()).isEqualTo(new KualiDecimal(1.10));
        assertThat(copy.getFinancialDocumentFiveCentAmount()).isEqualTo(new KualiDecimal(0.55));
        assertThat(copy.getFinancialDocumentOneCentAmount()).isEqualTo(new KualiDecimal(0.07));
        assertThat(copy.getFinancialDocumentOtherCentAmount()).isEqualTo(new KualiDecimal(0.99));
    }

    @Test
    void setKeysSetsAllKeyFields() {
        coinDetail.setKeys("DOC-100", "IB", "R");
        assertThat(coinDetail.getDocumentNumber()).isEqualTo("DOC-100");
        assertThat(coinDetail.getFinancialDocumentTypeCode()).isEqualTo("IB");
        assertThat(coinDetail.getCashieringStatus()).isEqualTo("R");
    }

    @Test
    void copyAmountsCopiesOnlyAmountFields() {
        CoinDetail source = new CoinDetail("DOC-SRC", "CR", "O");
        source.setFinancialDocumentHundredCentAmount(new KualiDecimal(10.00));
        source.setFinancialDocumentFiftyCentAmount(new KualiDecimal(5.00));

        coinDetail.setDocumentNumber("DOC-TARGET");
        coinDetail.copyAmounts(source);

        assertThat(coinDetail.getDocumentNumber()).isEqualTo("DOC-TARGET");
        assertThat(coinDetail.getFinancialDocumentHundredCentAmount()).isEqualTo(new KualiDecimal(10.00));
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(5.00));
    }

    @Test
    void fiftyCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(2.50));
        assertThat(coinDetail.getFiftyCentCount()).isEqualTo(5);
    }

    @Test
    void fiftyCentCountReturnsZeroWhenNull() {
        coinDetail.setFinancialDocumentFiftyCentAmount(null);
        assertThat(coinDetail.getFiftyCentCount()).isEqualTo(0);
    }

    @Test
    void setFiftyCentCountComputesAmount() {
        coinDetail.setFiftyCentCount(4);
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(2.00));
    }

    @Test
    void setFiftyCentCountNullDoesNothing() {
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(1.00));
        coinDetail.setFiftyCentCount(null);
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(1.00));
    }

    @Test
    void twentyFiveCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentTwentyFiveCentAmount(new KualiDecimal(1.25));
        assertThat(coinDetail.getTwentyFiveCentCount()).isEqualTo(5);
    }

    @Test
    void tenCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentTenCentAmount(new KualiDecimal(0.50));
        assertThat(coinDetail.getTenCentCount()).isEqualTo(5);
    }

    @Test
    void fiveCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentFiveCentAmount(new KualiDecimal(0.25));
        assertThat(coinDetail.getFiveCentCount()).isEqualTo(5);
    }

    @Test
    void oneCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentOneCentAmount(new KualiDecimal(0.05));
        assertThat(coinDetail.getOneCentCount()).isEqualTo(5);
    }

    @Test
    void hundredCentCountComputesCorrectly() {
        coinDetail.setFinancialDocumentHundredCentAmount(new KualiDecimal(5.00));
        assertThat(coinDetail.getHundredCentCount()).isEqualTo(5);
    }

    @Test
    void getTotalAmountSumsAllDenominations() {
        coinDetail.setFinancialDocumentHundredCentAmount(new KualiDecimal(1.00));
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(0.50));
        coinDetail.setFinancialDocumentTwentyFiveCentAmount(new KualiDecimal(0.25));
        coinDetail.setFinancialDocumentTenCentAmount(new KualiDecimal(0.10));
        coinDetail.setFinancialDocumentFiveCentAmount(new KualiDecimal(0.05));
        coinDetail.setFinancialDocumentOneCentAmount(new KualiDecimal(0.01));
        coinDetail.setFinancialDocumentOtherCentAmount(new KualiDecimal(0.02));

        KualiDecimal expected = new KualiDecimal(1.93);
        assertThat(coinDetail.getTotalAmount()).isEqualTo(expected);
    }

    @Test
    void getTotalAmountHandlesNullAmounts() {
        coinDetail.setFinancialDocumentHundredCentAmount(null);
        coinDetail.setFinancialDocumentFiftyCentAmount(null);
        coinDetail.setFinancialDocumentTwentyFiveCentAmount(null);
        coinDetail.setFinancialDocumentTenCentAmount(null);
        coinDetail.setFinancialDocumentFiveCentAmount(null);
        coinDetail.setFinancialDocumentOneCentAmount(null);
        coinDetail.setFinancialDocumentOtherCentAmount(null);

        assertThat(coinDetail.getTotalAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void zeroOutSetsAllAmountsToZero() {
        coinDetail.setFinancialDocumentHundredCentAmount(new KualiDecimal(5.00));
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(3.00));

        coinDetail.zeroOutAmounts();

        assertThat(coinDetail.getFinancialDocumentHundredCentAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void isEmptyReturnsTrueWhenAllZero() {
        assertThat(coinDetail.isEmpty()).isTrue();
    }

    @Test
    void isEmptyReturnsFalseWhenAnyNonZero() {
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(0.50));
        assertThat(coinDetail.isEmpty()).isFalse();
    }

    @Test
    void addAmountsToThisCoinDetail() {
        coinDetail.setFinancialDocumentHundredCentAmount(new KualiDecimal(1.00));
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(0.50));

        CoinDetail other = new CoinDetail();
        other.setFinancialDocumentHundredCentAmount(new KualiDecimal(2.00));
        other.setFinancialDocumentFiftyCentAmount(new KualiDecimal(1.00));

        coinDetail.add(other);

        assertThat(coinDetail.getFinancialDocumentHundredCentAmount()).isEqualTo(new KualiDecimal(3.00));
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(1.50));
    }

    @Test
    void subtractAmountsFromThisCoinDetail() {
        coinDetail.setFinancialDocumentHundredCentAmount(new KualiDecimal(5.00));
        coinDetail.setFinancialDocumentFiftyCentAmount(new KualiDecimal(3.00));

        CoinDetail other = new CoinDetail();
        other.setFinancialDocumentHundredCentAmount(new KualiDecimal(2.00));
        other.setFinancialDocumentFiftyCentAmount(new KualiDecimal(1.00));

        coinDetail.subtract(other);

        assertThat(coinDetail.getFinancialDocumentHundredCentAmount()).isEqualTo(new KualiDecimal(3.00));
        assertThat(coinDetail.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(2.00));
    }
}
