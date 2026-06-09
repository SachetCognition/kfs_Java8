package org.kuali.kfs.module.ec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortKeyConstantsTest extends KfsUnitTestBase {

    @Test
    @DisplayName("Error message keys should have effort prefix")
    void errorKeyShouldHaveExpectedPrefix() {
        assertThat(EffortKeyConstants.ERROR_TOTAL_EFFORT_PERCENTAGE_NOT_100)
                .isNotBlank();
    }

    @Test
    @DisplayName("Multiple key constants should not be null")
    void keysNotNull() {
        assertThat(EffortKeyConstants.ERROR_A21_SUB_ACCOUNT_NOT_FOUND).isNotBlank();
        assertThat(EffortKeyConstants.ERROR_ACCOUNT_CLOSED).isNotBlank();
        assertThat(EffortKeyConstants.ERROR_ACCOUNT_NUMBER_NOT_FOUND).isNotBlank();
    }

    @Test
    @DisplayName("Error key constants should start with error.effort prefix")
    void errorKeysShouldStartWithPrefix() {
        assertThat(EffortKeyConstants.ERROR_TOTAL_EFFORT_PERCENTAGE_NOT_100)
                .startsWith("error.effort");
        assertThat(EffortKeyConstants.ERROR_BATCH_JOB_NOT_SCHEDULED)
                .startsWith("error.effort");
    }
}
