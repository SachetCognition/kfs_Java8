package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.dataaccess.PriorYearOrganizationDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriorYearOrganizationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PriorYearOrganizationDao priorYearOrganizationDao;

    @InjectMocks
    private PriorYearOrganizationServiceImpl priorYearOrganizationService;

    @Test
    void populatePriorYearOrganizationsFromCurrent_purgesAndCopies() {
        when(priorYearOrganizationDao.purgePriorYearOrganizations()).thenReturn(10);
        when(priorYearOrganizationDao.copyCurrentOrganizationsToPriorYearTable()).thenReturn(15);

        priorYearOrganizationService.populatePriorYearOrganizationsFromCurrent();

        verify(priorYearOrganizationDao).purgePriorYearOrganizations();
        verify(priorYearOrganizationDao).copyCurrentOrganizationsToPriorYearTable();
    }
}
