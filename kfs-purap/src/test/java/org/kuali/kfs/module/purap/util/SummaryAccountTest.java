package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.businessobject.PurApSummaryItem;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SummaryAccountTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorInitializesEmptyItems() {
        SummaryAccount sa = new SummaryAccount();
        assertThat(sa.getAccount()).isNull();
        assertThat(sa.getItems()).isNotNull().isEmpty();
    }

    @Test
    void constructorWithAccountingSetsAccount() {
        SourceAccountingLine line = mock(SourceAccountingLine.class);
        SummaryAccount sa = new SummaryAccount(line);
        assertThat(sa.getAccount()).isSameAs(line);
        assertThat(sa.getItems()).isEmpty();
    }

    @Test
    void setAndGetAccount() {
        SummaryAccount sa = new SummaryAccount();
        SourceAccountingLine line = mock(SourceAccountingLine.class);
        when(line.getAccountNumber()).thenReturn("9999999");
        sa.setAccount(line);
        assertThat(sa.getAccount().getAccountNumber()).isEqualTo("9999999");
    }

    @Test
    void setAndGetItems() {
        SummaryAccount sa = new SummaryAccount();
        List<PurApSummaryItem> items = new ArrayList<>();
        PurApSummaryItem item = new PurApSummaryItem();
        items.add(item);
        sa.setItems(items);
        assertThat(sa.getItems()).hasSize(1);
    }
}
