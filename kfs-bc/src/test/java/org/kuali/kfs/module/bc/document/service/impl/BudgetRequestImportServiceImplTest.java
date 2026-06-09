package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.ImportRequestDao;
import org.kuali.kfs.module.bc.document.service.BudgetDocumentService;
import org.kuali.kfs.module.bc.document.service.BudgetParameterService;
import org.kuali.kfs.module.bc.document.service.LockService;
import org.kuali.kfs.integration.ld.LaborModuleService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PersistenceService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BudgetRequestImportServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ImportRequestDao importRequestDao;

    @Mock
    private DictionaryValidationService dictionaryValidationService;

    @Mock
    private LockService lockService;

    @Mock
    private BudgetDocumentService budgetDocumentService;

    @Mock
    private LaborModuleService laborModuleService;

    @Mock
    private BudgetParameterService budgetParameterService;

    @Mock
    private OptionsService optionsService;

    @Mock
    private DocumentHelperService documentHelperService;

    @Mock
    private DocumentService documentService;

    @Mock
    private PersistenceService persistenceServiceOjb;

    @InjectMocks
    private BudgetRequestImportServiceImpl service;

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }

    @Test
    public void testGetImportRequestDao() {
        assertSame(importRequestDao, service.getImportRequestDao());
    }

    @Test
    public void testGetBudgetParameterService() {
        assertSame(budgetParameterService, service.getBudgetParameterService());
    }

    @Test
    public void testGetOptionsService() {
        assertSame(optionsService, service.getOptionsService());
    }

    @Test
    public void testGetDocumentHelperService() {
        assertSame(documentHelperService, service.getDocumentHelperService());
    }

    @Test
    public void testGetLaborModuleService() {
        assertSame(laborModuleService, service.getLaborModuleService());
    }
}
