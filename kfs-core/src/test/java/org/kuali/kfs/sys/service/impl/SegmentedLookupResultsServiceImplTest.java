package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class SegmentedLookupResultsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private SegmentedLookupResultsServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
