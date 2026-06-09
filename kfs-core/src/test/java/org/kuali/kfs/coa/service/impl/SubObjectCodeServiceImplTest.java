package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class SubObjectCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    private SubObjectCodeServiceImpl subObjectCodeService;

    @BeforeEach
    void setUp() {
        subObjectCodeService = new SubObjectCodeServiceImpl();
        subObjectCodeService.setUniversityDateService(universityDateService);
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(subObjectCodeService).isNotNull();
    }
}
