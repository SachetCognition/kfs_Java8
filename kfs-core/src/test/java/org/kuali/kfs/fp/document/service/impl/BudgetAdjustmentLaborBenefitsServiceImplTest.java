package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class BudgetAdjustmentLaborBenefitsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetAdjustmentLaborBenefitsServiceImpl budgetAdjustmentLaborBenefitsService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(budgetAdjustmentLaborBenefitsService).isNotNull();
    }
}
