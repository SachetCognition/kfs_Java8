package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectCodeServiceImplTest extends KfsUnitTestBase {

    private ProjectCodeServiceImpl projectCodeService;

    @BeforeEach
    void setUp() {
        projectCodeService = new ProjectCodeServiceImpl();
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(projectCodeService).isNotNull();
    }
}
