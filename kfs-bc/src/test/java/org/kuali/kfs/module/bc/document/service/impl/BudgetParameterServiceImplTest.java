package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class BudgetParameterServiceImplTest extends KfsUnitTestBase {

    private final BudgetParameterServiceImpl service = new BudgetParameterServiceImpl();

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }
}
