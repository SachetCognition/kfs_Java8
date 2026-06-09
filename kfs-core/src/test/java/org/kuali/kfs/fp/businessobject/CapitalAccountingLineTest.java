package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CapitalAccountingLineTest extends KfsUnitTestBase {

    private CapitalAccountingLine line;

    @BeforeEach
    void setUp() {
        line = new CapitalAccountingLine();
    }

    @Test
    void defaultConstructorSetsCanCreateAssetToTrue() {
        assertThat(line.isCanCreateAsset()).isTrue();
    }

    @Test
    void setAndGetDistributionCode() {
        line.setDistributionCode("1");
        assertThat(line.getDistributionCode()).isEqualTo("1");
    }

    @Test
    void setAndGetCanCreateAsset() {
        line.setCanCreateAsset(false);
        assertThat(line.isCanCreateAsset()).isFalse();
    }

    @Test
    void toStringMapperContainsExpectedKeys() {
        line.setDistributionCode("2");
        line.setCanCreateAsset(true);
        assertThat(line.toStringMapper_RICE20_REFACTORME())
                .containsKey("distributionCode")
                .containsKey("canCreateAsset");
    }
}
