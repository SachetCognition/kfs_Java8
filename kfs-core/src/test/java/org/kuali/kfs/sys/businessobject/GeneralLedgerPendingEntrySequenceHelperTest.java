package org.kuali.kfs.sys.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralLedgerPendingEntrySequenceHelperTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_startsAt1() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper();
        assertThat(helper.getSequenceCounter()).isEqualTo(1);
    }

    @Test
    void parameterizedConstructor_startsAtGivenValue() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper(10);
        assertThat(helper.getSequenceCounter()).isEqualTo(10);
    }

    @Test
    void increment_increasesBy1() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper();
        helper.increment();
        assertThat(helper.getSequenceCounter()).isEqualTo(2);
    }

    @Test
    void decrement_decreasesBy1() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper(5);
        helper.decrement();
        assertThat(helper.getSequenceCounter()).isEqualTo(4);
    }

    @Test
    void multipleIncrements() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper();
        helper.increment();
        helper.increment();
        helper.increment();
        assertThat(helper.getSequenceCounter()).isEqualTo(4);
    }

    @Test
    void incrementThenDecrement_returnsToOriginal() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper(3);
        helper.increment();
        helper.decrement();
        assertThat(helper.getSequenceCounter()).isEqualTo(3);
    }

    @Test
    void negativeStart() {
        GeneralLedgerPendingEntrySequenceHelper helper = new GeneralLedgerPendingEntrySequenceHelper(-1);
        assertThat(helper.getSequenceCounter()).isEqualTo(-1);
        helper.increment();
        assertThat(helper.getSequenceCounter()).isEqualTo(0);
    }
}
