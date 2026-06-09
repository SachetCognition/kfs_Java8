package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.service.BudgetDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.kfs.integration.ld.LaborModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetConstructionRuleHelperServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DictionaryValidationService dictionaryValidationService;

    @Mock
    private BudgetDocumentService budgetDocumentService;

    @Mock
    private LaborModuleService laborModuleService;

    @InjectMocks
    private BudgetConstructionRuleHelperServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testSetDictionaryValidationService() {
        service.setDictionaryValidationService(dictionaryValidationService);
        assertNotNull(service);
    }

    @Test
    public void testSetBudgetDocumentService() {
        service.setBudgetDocumentService(budgetDocumentService);
        assertNotNull(service);
    }

    @Test
    public void testSetLaborModuleService() {
        service.setLaborModuleService(laborModuleService);
        assertNotNull(service);
    }
}
