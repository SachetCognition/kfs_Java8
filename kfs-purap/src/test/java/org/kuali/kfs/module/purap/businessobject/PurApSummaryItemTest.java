package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PurApSummaryItemTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorSetsZeroAmount() {
        PurApSummaryItem item = new PurApSummaryItem();
        assertThat(item.getEstimatedEncumberanceAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void parameterizedConstructor() {
        PurApSummaryItem item = new PurApSummaryItem(new KualiDecimal(500));
        assertThat(item.getEstimatedEncumberanceAmount()).isEqualTo(new KualiDecimal(500));
    }

    @Test
    void setAndGetEstimatedEncumberanceAmount() {
        PurApSummaryItem item = new PurApSummaryItem();
        item.setEstimatedEncumberanceAmount(new KualiDecimal(250));
        assertThat(item.getEstimatedEncumberanceAmount()).isEqualTo(new KualiDecimal(250));
    }

    @Test
    void accountingLineClassIsSourceAccountingLine() {
        PurApSummaryItem item = new PurApSummaryItem();
        assertThat(item.getAccountingLineClass()).isEqualTo(SourceAccountingLine.class);
    }

    @Test
    void useTaxClassIsSummaryItemUseTax() {
        PurApSummaryItem item = new PurApSummaryItem();
        assertThat(item.getUseTaxClass()).isEqualTo(SummaryItemUseTax.class);
    }

    @Test
    void isConsideredEnteredAlwaysFalse() {
        PurApSummaryItem item = new PurApSummaryItem();
        assertThat(item.isConsideredEntered()).isFalse();
    }
}
