package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleBillingService;
import org.kuali.kfs.module.ar.batch.service.VerifyBillingFrequencyService;
import org.kuali.kfs.module.ar.dataaccess.AwardAccountObjectCodeTotalBilledDao;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsBillingAwardVerificationService;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.module.ar.service.ContractsGrantsBillingUtilityService;
import org.kuali.kfs.module.ar.service.CostCategoryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsGrantsInvoiceCreateDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountService accountService;
    @Mock private AccountingPeriodService accountingPeriodService;
    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;
    @Mock private AwardAccountObjectCodeTotalBilledDao awardAccountObjectCodeTotalBilledDao;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ConfigurationService configurationService;
    @Mock private ContractsGrantsBillingAwardVerificationService contractsGrantsBillingAwardVerificationService;
    @Mock private ContractsGrantsBillingUtilityService contractsGrantsBillingUtilityService;
    @Mock private ContractsAndGrantsModuleBillingService contractsAndGrantsModuleBillingService;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private CostCategoryService costCategoryService;
    @Mock private CustomerService customerService;
    @Mock private DateTimeService dateTimeService;
    @Mock private DocumentService documentService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ParameterService parameterService;
    @Mock private VerifyBillingFrequencyService verifyBillingFrequencyService;
    @Mock private WorkflowDocumentService workflowDocumentService;
    @Mock private UniversityDateService universityDateService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private ContractsGrantsInvoiceCreateDocumentServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
