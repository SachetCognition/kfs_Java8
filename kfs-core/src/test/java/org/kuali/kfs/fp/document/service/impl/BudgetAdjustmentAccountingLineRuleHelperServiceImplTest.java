package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetAdjustmentAccountingLineRuleHelperServiceImplTest extends KfsUnitTestBase {

    @Test
    void serviceInstantiatesCorrectly() {
        BudgetAdjustmentAccountingLineRuleHelperServiceImpl service = new BudgetAdjustmentAccountingLineRuleHelperServiceImpl();
        assertThat(service).isNotNull();
    }
}
