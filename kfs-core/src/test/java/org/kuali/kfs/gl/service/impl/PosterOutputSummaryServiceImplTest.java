package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.batch.service.AccountingCycleCachingService;
import org.kuali.kfs.gl.businessobject.PosterOutputSummaryEntry;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class PosterOutputSummaryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AccountingCycleCachingService accountingCycleCachingService;

    @InjectMocks
    private PosterOutputSummaryServiceImpl posterOutputSummaryService;

    @Test
    void getEntryComparator_returnsNonNull() {
        Comparator<PosterOutputSummaryEntry> comparator = posterOutputSummaryService.getEntryComparator();
        assertThat(comparator).isNotNull();
    }
}
