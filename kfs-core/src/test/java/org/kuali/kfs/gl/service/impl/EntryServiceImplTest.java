package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.EntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class EntryServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EntryServiceImpl entryService;

    @Mock
    private EntryDao entryDao;

    @Test
    void purgeYearByChart_delegatesToDao() {
        entryService.purgeYearByChart("BL", 2020);

        verify(entryDao).purgeYearByChart("BL", 2020);
    }

    @Test
    void purgeYearByChart_withDifferentChart_delegatesToDao() {
        entryService.purgeYearByChart("UA", 2023);

        verify(entryDao).purgeYearByChart("UA", 2023);
    }
}
