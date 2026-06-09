package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SensitiveDataTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        SensitiveData sd = new SensitiveData();
        assertThat(sd.getSensitiveDataCode()).isNull();
        assertThat(sd.getSensitiveDataDescription()).isNull();
        assertThat(sd.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        SensitiveData sd = new SensitiveData();
        sd.setSensitiveDataCode("HAZMAT");
        sd.setSensitiveDataDescription("Hazardous Materials");
        sd.setActive(true);

        assertThat(sd.getSensitiveDataCode()).isEqualTo("HAZMAT");
        assertThat(sd.getSensitiveDataDescription()).isEqualTo("Hazardous Materials");
        assertThat(sd.isActive()).isTrue();
    }

    @Test
    void toggleActive() {
        SensitiveData sd = new SensitiveData();
        sd.setActive(true);
        assertThat(sd.isActive()).isTrue();
        sd.setActive(false);
        assertThat(sd.isActive()).isFalse();
    }
}
