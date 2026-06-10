package org.kuali.kfs.gl.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class DemergerSortComparatorTest extends KfsUnitTestBase {

    @Test
    void demergerSortComparator_implementsComparator() {
        assertThat(Comparator.class).isAssignableFrom(DemergerSortComparator.class);
    }

    @Test
    void demergerSortComparator_hasCompareRangesField() {
        assertThat(DemergerSortComparator.class.getDeclaredFields())
            .extracting("name")
            .contains("compareRanges");
    }
}
