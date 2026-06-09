package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.dataaccess.PopulateProcurementCardDefaultIdsDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class PopulateProcurementCardDefaultIdsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PopulateProcurementCardDefaultIdsDao populateProcurementCardDefaultIdsDao;

    @InjectMocks
    private PopulateProcurementCardDefaultIdsServiceImpl service;

    @Test
    void populateIdsOnProcurementCardDefaults_delegatesToDao() {
        service.populateIdsOnProcurementCardDefaults();
        verify(populateProcurementCardDefaultIdsDao).populateIdsOnProcurementCardDefaults();
    }
}
