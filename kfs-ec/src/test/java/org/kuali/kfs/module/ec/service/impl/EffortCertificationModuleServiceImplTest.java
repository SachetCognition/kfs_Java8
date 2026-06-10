package org.kuali.kfs.module.ec.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.EffortConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationModuleServiceImplTest extends KfsUnitTestBase {

    private final EffortCertificationModuleServiceImpl service = new EffortCertificationModuleServiceImpl();

    @Test
    @DisplayName("getCostShareSubAccountTypeCodes should return expected codes")
    void shouldReturnCostShareSubAccountTypeCodes() {
        List<String> codes = service.getCostShareSubAccountTypeCodes();
        assertThat(codes).isEqualTo(EffortConstants.ELIGIBLE_COST_SHARE_SUB_ACCOUNT_TYPE_CODES);
        assertThat(codes).isNotEmpty();
    }
}
