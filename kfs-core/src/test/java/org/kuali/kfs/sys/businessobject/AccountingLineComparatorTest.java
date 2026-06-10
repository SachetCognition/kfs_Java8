package org.kuali.kfs.sys.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

class AccountingLineComparatorTest extends KfsUnitTestBase {

    private AccountingLineComparator comparator;

    @Mock
    private AccountingLine line1;

    @Mock
    private AccountingLine line2;

    @BeforeEach
    void setUp() {
        comparator = new AccountingLineComparator();
    }

    @Test
    void compare_firstSmaller_returnsNegative() {
        when(line1.getSequenceNumber()).thenReturn(1);
        when(line2.getSequenceNumber()).thenReturn(2);
        assertThat(comparator.compare(line1, line2)).isNegative();
    }

    @Test
    void compare_firstLarger_returnsPositive() {
        when(line1.getSequenceNumber()).thenReturn(3);
        when(line2.getSequenceNumber()).thenReturn(1);
        assertThat(comparator.compare(line1, line2)).isPositive();
    }

    @Test
    void compare_equal_returnsZero() {
        when(line1.getSequenceNumber()).thenReturn(5);
        when(line2.getSequenceNumber()).thenReturn(5);
        assertThat(comparator.compare(line1, line2)).isZero();
    }

    @Test
    void compare_firstNull_returnsZero() {
        assertThat(comparator.compare(null, line2)).isZero();
    }

    @Test
    void compare_secondNull_returnsZero() {
        assertThat(comparator.compare(line1, null)).isZero();
    }

    @Test
    void compare_bothNull_returnsZero() {
        assertThat(comparator.compare(null, null)).isZero();
    }

    @Test
    void compare_firstSequenceNull_returnsZero() {
        when(line1.getSequenceNumber()).thenReturn(null);
        lenient().when(line2.getSequenceNumber()).thenReturn(1);
        assertThat(comparator.compare(line1, line2)).isZero();
    }

    @Test
    void compare_secondSequenceNull_returnsZero() {
        when(line1.getSequenceNumber()).thenReturn(1);
        when(line2.getSequenceNumber()).thenReturn(null);
        assertThat(comparator.compare(line1, line2)).isZero();
    }

    @Test
    void compare_bothSequencesNull_returnsZero() {
        when(line1.getSequenceNumber()).thenReturn(null);
        lenient().when(line2.getSequenceNumber()).thenReturn(null);
        assertThat(comparator.compare(line1, line2)).isZero();
    }
}
