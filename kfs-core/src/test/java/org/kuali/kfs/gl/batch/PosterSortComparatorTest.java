package org.kuali.kfs.gl.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.OriginEntryFieldUtil;
import org.kuali.kfs.gl.businessobject.OriginEntryFull;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.MockedConstruction;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class PosterSortComparatorTest extends KfsUnitTestBase {

    @Test
    void posterSortComparator_isComparator() {
        // Verify the class implements Comparator<String>
        assertThat(java.util.Comparator.class).isAssignableFrom(PosterSortComparator.class);
    }

    @Test
    void posterSortComparator_usesOriginEntryFieldUtil() {
        // PosterSortComparator depends on OriginEntryFieldUtil for field positions
        // This is a class-level assertion
        assertThat(PosterSortComparator.class.getDeclaredFields())
            .extracting("name")
            .contains("oefu", "pMap", "compareRanges");
    }
}
