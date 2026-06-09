package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.EntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class EntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private EntryDao entryDao;

    @InjectMocks
    private EntryServiceImpl entryService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        entryService.purgeYearByChart("BL", 2024);
        verify(entryDao).purgeYearByChart("BL", 2024);
    }
}
