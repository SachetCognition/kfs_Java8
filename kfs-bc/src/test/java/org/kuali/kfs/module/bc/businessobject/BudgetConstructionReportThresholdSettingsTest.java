package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetConstructionReportThresholdSettingsTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_defaultValues() {
        BudgetConstructionReportThresholdSettings settings = new BudgetConstructionReportThresholdSettings();
        assertThat(settings.isLockThreshold()).isFalse();
        assertThat(settings.isUseThreshold()).isFalse();
        assertThat(settings.getThresholdPercent()).isNull();
        assertThat(settings.isUseGreaterThanOperator()).isTrue();
    }

    @Test
    void setAndGetLockThreshold() {
        BudgetConstructionReportThresholdSettings settings = new BudgetConstructionReportThresholdSettings();
        settings.setLockThreshold(true);
        assertThat(settings.isLockThreshold()).isTrue();
    }

    @Test
    void setAndGetUseThreshold() {
        BudgetConstructionReportThresholdSettings settings = new BudgetConstructionReportThresholdSettings();
        settings.setUseThreshold(true);
        assertThat(settings.isUseThreshold()).isTrue();
    }

    @Test
    void setAndGetThresholdPercent() {
        BudgetConstructionReportThresholdSettings settings = new BudgetConstructionReportThresholdSettings();
        KualiDecimal percent = new KualiDecimal(10.5);
        settings.setThresholdPercent(percent);
        assertThat(settings.getThresholdPercent()).isEqualTo(percent);
    }

    @Test
    void setAndGetUseGreaterThanOperator() {
        BudgetConstructionReportThresholdSettings settings = new BudgetConstructionReportThresholdSettings();
        settings.setUseGreaterThanOperator(false);
        assertThat(settings.isUseGreaterThanOperator()).isFalse();
    }
}
