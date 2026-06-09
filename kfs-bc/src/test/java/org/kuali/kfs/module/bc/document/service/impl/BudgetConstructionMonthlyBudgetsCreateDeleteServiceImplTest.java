package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BudgetConstructionMonthlyBudgetsCreateDeleteDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionMonthlyBudgetsCreateDeleteServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BudgetConstructionMonthlyBudgetsCreateDeleteDao budgetConstructionMonthlyBudgetsCreateDeleteDao;

    @InjectMocks
    private BudgetConstructionMonthlyBudgetsCreateDeleteServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetBudgetConstructionMonthlyBudgetsCreateDeleteDao() {
        service.setBudgetConstructionMonthlyBudgetsCreateDeleteDao(budgetConstructionMonthlyBudgetsCreateDeleteDao);
        assertNotNull(service);
    }
}
