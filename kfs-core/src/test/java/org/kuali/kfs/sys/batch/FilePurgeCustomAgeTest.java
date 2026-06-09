package org.kuali.kfs.sys.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FilePurgeCustomAgeTest extends KfsUnitTestBase {

    @Test
    void setAndGetDirectory() {
        FilePurgeCustomAge age = new FilePurgeCustomAge();
        age.setDirectory("/tmp/batch/staging");
        assertThat(age.getDirectory()).isEqualTo("/tmp/batch/staging");
    }

    @Test
    void setAndGetParameterPrefix() {
        FilePurgeCustomAge age = new FilePurgeCustomAge();
        age.setParameterPrefix("STAGING_FILES");
        assertThat(age.getParameterPrefix()).isEqualTo("STAGING_FILES");
    }

    @Test
    void defaultValues_areNull() {
        FilePurgeCustomAge age = new FilePurgeCustomAge();
        assertThat(age.getDirectory()).isNull();
        assertThat(age.getParameterPrefix()).isNull();
    }
}
