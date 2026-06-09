package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class YearEndPendingEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private YearEndPendingEntryServiceImpl yearEndPendingEntryService;

    @Test
    void getPreviousFiscalYear_delegatesToUniversityDateService() {
        when(universityDateService.getCurrentFiscalYear()).thenReturn(Integer.valueOf(2024));

        Integer result = yearEndPendingEntryService.getPreviousFiscalYear();
        assertThat(result).isEqualTo(Integer.valueOf(2023));
    }
}
