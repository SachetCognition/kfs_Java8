package org.kuali.kfs.module.external.kc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.integration.cg.dto.BudgetAdjustmentCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.BudgetAdjustmentParametersDTO;
import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.service.AccountCreationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.kns.service.TransactionalDocumentDictionaryService;
import org.mockito.Mock;

class BudgetAdjustmentServiceImplUnitTest extends KfsUnitTestBase {

    @Mock private DocumentService documentService;
    @Mock private ParameterService parameterService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private AccountCreationService accountCreationService;
    @Mock private ObjectCodeService objectCodeService;
    @Mock private TransactionalDocumentDictionaryService transactionalDocumentDictionaryService;
    @Mock private KualiRuleService kualiRuleService;
    @Mock private PersonService personService;
    @Mock private MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;

    private BudgetAdjustmentServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BudgetAdjustmentServiceImpl();
        service.setDocumentService(documentService);
        service.setParameterService(parameterService);
        service.setDataDictionaryService(dataDictionaryService);
        service.setBusinessObjectService(businessObjectService);
        service.setAccountCreationService(accountCreationService);
        service.setObjectCodeService(objectCodeService);
        service.setTransactionalDocumentDictionaryService(transactionalDocumentDictionaryService);
        service.setKualiRuleService(kualiRuleService);
        service.setPersonService(personService);
        service.setMaintenanceDocumentDictionaryService(maintenanceDocumentDictionaryService);
    }



    @Test
    void testSetFailStatus() {
        BudgetAdjustmentCreationStatusDTO status = new BudgetAdjustmentCreationStatusDTO();
        status.setErrorMessages(new ArrayList<String>());
        status.setStatus(KcConstants.KcWebService.STATUS_KC_SUCCESS);

        service.setFailStatus(status, "Test Error");
        assertEquals(KcConstants.KcWebService.STATUS_KC_FAILURE, status.getStatus());
        assertTrue(status.getErrorMessages().contains("Test Error"));
    }

    @Test
    void testCheckforEmptyField_nullValue() {
        BudgetAdjustmentCreationStatusDTO status = new BudgetAdjustmentCreationStatusDTO();
        status.setErrorMessages(new ArrayList<String>());

        boolean result = service.checkforEmptyField(status, "Account", null, 1);
        assertFalse(result);
        assertEquals(KcConstants.KcWebService.STATUS_KC_FAILURE, status.getStatus());
    }

    @Test
    void testCheckforEmptyField_emptyValue() {
        BudgetAdjustmentCreationStatusDTO status = new BudgetAdjustmentCreationStatusDTO();
        status.setErrorMessages(new ArrayList<String>());

        boolean result = service.checkforEmptyField(status, "Account", "", 1);
        assertFalse(result);
    }

    @Test
    void testCheckforEmptyField_validValue() {
        BudgetAdjustmentCreationStatusDTO status = new BudgetAdjustmentCreationStatusDTO();
        status.setErrorMessages(new ArrayList<String>());

        boolean result = service.checkforEmptyField(status, "Account", "1234567", 0);
        assertTrue(result);
    }

    @Test
    void testCheckforEmptyField_zeroLineNumber() {
        BudgetAdjustmentCreationStatusDTO status = new BudgetAdjustmentCreationStatusDTO();
        status.setErrorMessages(new ArrayList<String>());

        boolean result = service.checkforEmptyField(status, "Description", null, 0);
        assertFalse(result);
    }
}
