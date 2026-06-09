package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectCodeCurrentInactivationBlockingDetectionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ObjectCodeCurrentInactivationBlockingDetectionServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
