package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class KfsModuleServiceImplTest extends KfsUnitTestBase {

    @Test
    void isExternalJob_returnsFalse() {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        assertThat(service.isExternalJob("anyJob")).isFalse();
    }

    @Test
    void getExternalJobStatus_nonExternalJob_returnsNull() {
        KfsModuleServiceImpl service = new KfsModuleServiceImpl();
        assertThat(service.getExternalJobStatus("someJob")).isNull();
    }
}
