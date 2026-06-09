package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class OffsetDefinitionServiceImplTest extends KfsUnitTestBase {

    private OffsetDefinitionServiceImpl offsetDefinitionService;

    @BeforeEach
    void setUp() {
        offsetDefinitionService = new OffsetDefinitionServiceImpl();
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(offsetDefinitionService).isNotNull();
    }
}
