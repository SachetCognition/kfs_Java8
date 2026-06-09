package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectConsServiceImplTest extends KfsUnitTestBase {

    private ObjectConsServiceImpl objectConsService;

    @BeforeEach
    void setUp() {
        objectConsService = new ObjectConsServiceImpl();
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(objectConsService).isNotNull();
    }
}
