package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class DevelopmentMailServiceImplTest extends KfsUnitTestBase {

    @Test
    void serviceInstantiatesCorrectly() {
        DevelopmentMailServiceImpl service = new DevelopmentMailServiceImpl();
        assertThat(service).isNotNull();
    }
}
