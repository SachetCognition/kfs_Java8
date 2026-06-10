package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.EffortPropertyConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationDetailBuildTest extends KfsUnitTestBase {

    private EffortCertificationDetailBuild detailBuild;

    @BeforeEach
    void setUp() {
        detailBuild = new EffortCertificationDetailBuild();
    }

    @Test
    @DisplayName("Default constructor initializes successfully")
    void defaultConstructor() {
        assertThat(detailBuild).isNotNull();
        assertThat(detailBuild.getEffortCertificationBuildNumber()).isNull();
    }

    @Test
    @DisplayName("Should set and get build number")
    void shouldSetAndGetBuildNumber() {
        detailBuild.setEffortCertificationBuildNumber(42L);
        assertThat(detailBuild.getEffortCertificationBuildNumber()).isEqualTo(42L);
    }

    @Test
    @DisplayName("Should inherit getter/setter from EffortCertificationDetail")
    void shouldInheritDetailFields() {
        detailBuild.setChartOfAccountsCode("BL");
        detailBuild.setPositionNumber("POS001");
        detailBuild.setFinancialObjectCode("2400");
        assertThat(detailBuild.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(detailBuild.getPositionNumber()).isEqualTo("POS001");
        assertThat(detailBuild.getFinancialObjectCode()).isEqualTo("2400");
    }

    @Test
    @DisplayName("getKeyList returns expected key fields")
    void getKeyListShouldReturnExpectedFields() {
        List<String> keys = EffortCertificationDetailBuild.getKeyList();
        assertThat(keys).containsExactly(
                EffortPropertyConstants.EFFORT_CERTIFICATION_BUILD_NUMBER,
                KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE,
                KFSPropertyConstants.ACCOUNT_NUMBER,
                KFSPropertyConstants.SUB_ACCOUNT_NUMBER,
                KFSPropertyConstants.FINANCIAL_OBJECT_CODE,
                KFSPropertyConstants.POSITION_NUMBER,
                EffortPropertyConstants.SOURCE_CHART_OF_ACCOUNTS_CODE,
                EffortPropertyConstants.SOURCE_ACCOUNT_NUMBER
        );
    }
}
