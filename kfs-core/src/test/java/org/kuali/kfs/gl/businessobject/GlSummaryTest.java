package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GlSummaryTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_initializesAmountsToZero() {
        GlSummary summary = new GlSummary();

        assertThat(summary.getAccountLineAnnualBalanceAmount()).isEqualTo(KualiDecimal.ZERO);
        assertThat(summary.getMonth1Amount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void constructorWithDataArray_populatesAllFields() {
        Object[] data = new Object[17];
        data[0] = "FUND1";
        data[1] = new KualiDecimal(100);  // annual
        data[2] = new KualiDecimal(200);  // beginning
        data[3] = new KualiDecimal(300);  // CG beginning
        for (int i = 4; i <= 16; i++) {
            data[i] = new KualiDecimal(i * 10);
        }

        GlSummary summary = new GlSummary(data);

        assertThat(summary.getFundGroup()).isEqualTo("FUND1");
        assertThat(summary.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(100));
        assertThat(summary.getBeginningBalanceLineAmount()).isEqualTo(new KualiDecimal(200));
        assertThat(summary.getContractsGrantsBeginningBalanceAmount()).isEqualTo(new KualiDecimal(300));
        assertThat(summary.getMonth1Amount()).isEqualTo(new KualiDecimal(40));
        assertThat(summary.getMonth13Amount()).isEqualTo(new KualiDecimal(160));
    }

    @Test
    void add_combinesAllAmounts() {
        GlSummary s1 = new GlSummary();
        s1.setBeginningBalanceLineAmount(new KualiDecimal(10));
        s1.setContractsGrantsBeginningBalanceAmount(new KualiDecimal(20));
        s1.setAccountLineAnnualBalanceAmount(new KualiDecimal(30));
        s1.setMonth1Amount(new KualiDecimal(5));
        s1.setMonth2Amount(new KualiDecimal(6));

        GlSummary s2 = new GlSummary();
        s2.setBeginningBalanceLineAmount(new KualiDecimal(100));
        s2.setContractsGrantsBeginningBalanceAmount(new KualiDecimal(200));
        s2.setAccountLineAnnualBalanceAmount(new KualiDecimal(300));
        s2.setMonth1Amount(new KualiDecimal(50));
        s2.setMonth2Amount(new KualiDecimal(60));

        s1.add(s2);

        assertThat(s1.getBeginningBalanceLineAmount()).isEqualTo(new KualiDecimal(110));
        assertThat(s1.getContractsGrantsBeginningBalanceAmount()).isEqualTo(new KualiDecimal(220));
        assertThat(s1.getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(330));
        assertThat(s1.getMonth1Amount()).isEqualTo(new KualiDecimal(55));
        assertThat(s1.getMonth2Amount()).isEqualTo(new KualiDecimal(66));
    }

    @Test
    void getYearBalance_sumsAllThirteenMonths() {
        GlSummary summary = new GlSummary();
        summary.setMonth1Amount(new KualiDecimal(1));
        summary.setMonth2Amount(new KualiDecimal(2));
        summary.setMonth3Amount(new KualiDecimal(3));
        summary.setMonth4Amount(new KualiDecimal(4));
        summary.setMonth5Amount(new KualiDecimal(5));
        summary.setMonth6Amount(new KualiDecimal(6));
        summary.setMonth7Amount(new KualiDecimal(7));
        summary.setMonth8Amount(new KualiDecimal(8));
        summary.setMonth9Amount(new KualiDecimal(9));
        summary.setMonth10Amount(new KualiDecimal(10));
        summary.setMonth11Amount(new KualiDecimal(11));
        summary.setMonth12Amount(new KualiDecimal(12));
        summary.setMonth13Amount(new KualiDecimal(13));

        assertThat(summary.getYearBalance()).isEqualTo(new KualiDecimal(91));
    }

    @Test
    void getYearBalance_returnsZeroWhenAllMonthsZero() {
        GlSummary summary = new GlSummary();

        assertThat(summary.getYearBalance()).isEqualTo(KualiDecimal.ZERO);
    }
}
