package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class KfsKualiModuleServiceImplTest extends KfsUnitTestBase {

    @Test
    void serviceInstantiatesCorrectly() {
        KfsKualiModuleServiceImpl service = new KfsKualiModuleServiceImpl();
        assertThat(service).isNotNull();
    }
}
