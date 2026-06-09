package org.kuali.kfs.module.purap.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.businessobject.PurApAccountingLineBase;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

class PurapAccountRevisionGroupTest extends KfsUnitTestBase {

    private PurApAccountingLineBase createEntry(String chart, String acct, String subAcct,
                                                 String objCode, String subObjCode,
                                                 Integer itemId, Integer postYear,
                                                 String postPeriod, KualiDecimal amount) {
        PurApAccountingLineBase entry = mock(PurApAccountingLineBase.class);
        lenient().when(entry.getChartOfAccountsCode()).thenReturn(chart);
        lenient().when(entry.getAccountNumber()).thenReturn(acct);
        lenient().when(entry.getSubAccountNumber()).thenReturn(subAcct);
        lenient().when(entry.getFinancialObjectCode()).thenReturn(objCode);
        lenient().when(entry.getFinancialSubObjectCode()).thenReturn(subObjCode);
        lenient().when(entry.getItemIdentifier()).thenReturn(itemId);
        lenient().when(entry.getPostingYear()).thenReturn(postYear);
        lenient().when(entry.getPostingPeriodCode()).thenReturn(postPeriod);
        lenient().when(entry.getProjectCode()).thenReturn("PROJ1");
        lenient().when(entry.getOrganizationReferenceId()).thenReturn("ORG1");
        lenient().when(entry.getAccountLinePercent()).thenReturn(new BigDecimal("100.00"));
        lenient().when(entry.getAmount()).thenReturn(amount);
        return entry;
    }

    @Test
    void constructorPopulatesFromEntry() {
        PurApAccountingLineBase entry = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup group = new PurapAccountRevisionGroup(entry);

        assertThat(group.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(group.getAccountNumber()).isEqualTo("1234567");
        assertThat(group.getSubAccountNumber()).isEqualTo("SUB1");
        assertThat(group.getFinancialObjectCode()).isEqualTo("5000");
        assertThat(group.getFinancialSubObjectCode()).isEqualTo("001");
        assertThat(group.getItemIdentifier()).isEqualTo(1);
        assertThat(group.getPostingYear()).isEqualTo(2024);
        assertThat(group.getPostingPeriodCode()).isEqualTo("01");
        assertThat(group.getAmount()).isEqualTo(new KualiDecimal(100));
    }

    @Test
    void equalGroupsMatch() {
        PurApAccountingLineBase e1 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));
        PurApAccountingLineBase e2 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(200));

        PurapAccountRevisionGroup g1 = new PurapAccountRevisionGroup(e1);
        PurapAccountRevisionGroup g2 = new PurapAccountRevisionGroup(e2);

        assertThat(g1).isEqualTo(g2);
        assertThat(g1.hashCode()).isEqualTo(g2.hashCode());
    }

    @Test
    void notEqualForDifferentAccount() {
        PurApAccountingLineBase e1 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));
        PurApAccountingLineBase e2 = createEntry("BL", "9999999", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup g1 = new PurapAccountRevisionGroup(e1);
        PurapAccountRevisionGroup g2 = new PurapAccountRevisionGroup(e2);

        assertThat(g1).isNotEqualTo(g2);
    }

    @Test
    void notEqualForDifferentChart() {
        PurApAccountingLineBase e1 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));
        PurApAccountingLineBase e2 = createEntry("UA", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup g1 = new PurapAccountRevisionGroup(e1);
        PurapAccountRevisionGroup g2 = new PurapAccountRevisionGroup(e2);

        assertThat(g1).isNotEqualTo(g2);
    }

    @Test
    void notEqualForDifferentObjectCode() {
        PurApAccountingLineBase e1 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));
        PurApAccountingLineBase e2 = createEntry("BL", "1234567", "SUB1",
                "6000", "001", 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup g1 = new PurapAccountRevisionGroup(e1);
        PurapAccountRevisionGroup g2 = new PurapAccountRevisionGroup(e2);

        assertThat(g1).isNotEqualTo(g2);
    }

    @Test
    void combineAddsAmount() {
        PurApAccountingLineBase e1 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));
        PurApAccountingLineBase e2 = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(200));

        PurapAccountRevisionGroup group = new PurapAccountRevisionGroup(e1);
        group.combineEntry(e2);

        assertThat(group.getAmount()).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void changeAmountSetterGetter() {
        PurApAccountingLineBase entry = createEntry("BL", "1234567", "SUB1",
                "5000", "001", 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup group = new PurapAccountRevisionGroup(entry);
        group.setChangeAmount(new KualiDecimal(50));
        assertThat(group.getChangeAmount()).isEqualTo(new KualiDecimal(50));
    }

    @Test
    void settersAndGetters() {
        PurApAccountingLineBase entry = createEntry("BL", "1234567", null,
                "5000", null, 1, 2024, "01", new KualiDecimal(100));

        PurapAccountRevisionGroup group = new PurapAccountRevisionGroup(entry);
        group.setProjectCode("P1");
        group.setOrganizationReferenceId("O1");
        group.setAccountLinePercent(new BigDecimal("50.00"));

        assertThat(group.getProjectCode()).isEqualTo("P1");
        assertThat(group.getOrganizationReferenceId()).isEqualTo("O1");
        assertThat(group.getAccountLinePercent()).isEqualByComparingTo(new BigDecimal("50.00"));
    }
}
