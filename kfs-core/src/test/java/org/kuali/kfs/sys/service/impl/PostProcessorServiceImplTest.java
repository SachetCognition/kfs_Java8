package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class PostProcessorServiceImplTest extends KfsUnitTestBase {

    @Test
    void serviceInstantiatesCorrectly() {
        PostProcessorServiceImpl service = new PostProcessorServiceImpl();
        assertThat(service).isNotNull();
    }
}
