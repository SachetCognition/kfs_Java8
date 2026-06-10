package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

class PurapAccountingLineComparatorTest extends KfsUnitTestBase {

    private final PurapAccountingLineComparator comparator = new PurapAccountingLineComparator();

    private AccountingLine createLine(String accountNumber, String objectCode) {
        AccountingLine line = mock(AccountingLine.class);
        lenient().when(line.getAccountNumber()).thenReturn(accountNumber);
        lenient().when(line.getFinancialObjectCode()).thenReturn(objectCode);
        return line;
    }

    @Test
    void compareEqualLines() {
        AccountingLine a = createLine("1234567", "5000");
        AccountingLine b = createLine("1234567", "5000");
        assertThat(comparator.compare(a, b)).isZero();
    }

    @Test
    void compareByAccountNumberAscending() {
        AccountingLine a = createLine("1111111", "5000");
        AccountingLine b = createLine("2222222", "5000");
        assertThat(comparator.compare(a, b)).isNegative();
        assertThat(comparator.compare(b, a)).isPositive();
    }

    @Test
    void compareByObjectCodeWhenAccountsEqual() {
        AccountingLine a = createLine("1234567", "4000");
        AccountingLine b = createLine("1234567", "5000");
        assertThat(comparator.compare(a, b)).isNegative();
        assertThat(comparator.compare(b, a)).isPositive();
    }

    @Test
    void compareBothNull() {
        assertThat(comparator.compare(null, null)).isZero();
    }

    @Test
    void compareFirstNull() {
        AccountingLine b = createLine("1234567", "5000");
        assertThat(comparator.compare(null, b)).isZero();
    }

    @Test
    void compareSecondNull() {
        AccountingLine a = createLine("1234567", "5000");
        assertThat(comparator.compare(a, null)).isZero();
    }

    @Test
    void compareNullAccountNumbers() {
        AccountingLine a = createLine(null, "5000");
        AccountingLine b = createLine(null, "4000");
        assertThat(comparator.compare(a, b)).isZero();
    }

    @Test
    void compareNullObjectCodesWithEqualAccounts() {
        AccountingLine a = createLine("1234567", null);
        AccountingLine b = createLine("1234567", null);
        assertThat(comparator.compare(a, b)).isZero();
    }
}
