package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.LedgerEntryHolder;
import org.kuali.kfs.gl.service.OriginEntryGroupService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class OriginEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OriginEntryGroupService originEntryGroupService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private OriginEntryServiceImpl originEntryService;

    @Test
    void getSummaryByGroupId_emptyGroupList_returnsEmptyHolder() {
        LedgerEntryHolder result = originEntryService.getSummaryByGroupId(Collections.emptyList());
        assertThat(result).isNotNull();
    }
}
