package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class OptionsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    private OptionsServiceImpl optionsService;

    @BeforeEach
    void setUp() {
        optionsService = new OptionsServiceImpl();
        optionsService.setUniversityDateService(universityDateService);
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(optionsService).isNotNull();
    }
}
