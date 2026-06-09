package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleBillingService;
import org.kuali.kfs.module.ar.businessobject.ContractsGrantsInvoiceDetail;
import org.kuali.kfs.module.ar.document.ContractsGrantsInvoiceDocument;
import org.kuali.kfs.module.ar.document.dataaccess.ContractsGrantsInvoiceDocumentDao;
import org.kuali.kfs.module.ar.document.service.AccountsReceivablePendingEntryService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.service.ContractsGrantsBillingUtilityService;
import org.kuali.kfs.module.ar.service.CostCategoryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.service.AttachmentService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractsGrantsInvoiceDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsReceivablePendingEntryService accountsReceivablePendingEntryService;
    @Mock private AccountService accountService;
    @Mock private AttachmentService attachmentService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ConfigurationService configurationService;
    @Mock private ContractsGrantsBillingUtilityService contractsGrantsBillingUtilityService;
    @Mock private ContractsGrantsInvoiceDocumentDao contractsGrantsInvoiceDocumentDao;
    @Mock private ContractsAndGrantsModuleBillingService contractsAndGrantsModuleBillingService;
    @Mock private CostCategoryService costCategoryService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private DateTimeService dateTimeService;
    @Mock private DocumentService documentService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;
    @Mock private IdentityService identityService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private KualiRuleService kualiRuleService;
    @Mock private NoteService noteService;
    @Mock private ObjectCodeService objectCodeService;
    @Mock private ParameterService parameterService;
    @Mock private PermissionService permissionService;
    @Mock private PersonService personService;
    @Mock private UniversityDateService universityDateService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private ContractsGrantsInvoiceDocumentServiceImpl service;

    @Test
    void getInvoicesByAward_shouldReturnEmptyMapForEmptyInvoices() {
        Map<Long, List<ContractsGrantsInvoiceDocument>> result = service.getInvoicesByAward(new ArrayList<ContractsGrantsInvoiceDocument>());
        assertThat(result).isEmpty();
    }

    @Test
    void retrieveAllCGInvoicesByCriteria_shouldReturnResults() {
        ContractsGrantsInvoiceDocument doc = mock(ContractsGrantsInvoiceDocument.class);
        ArrayList<ContractsGrantsInvoiceDocument> list = new ArrayList<ContractsGrantsInvoiceDocument>();
        list.add(doc);
        when(contractsGrantsInvoiceDocumentDao.getMatchingInvoicesByCollection(any(Map.class)))
                .thenReturn(list);

        Collection<ContractsGrantsInvoiceDocument> result = service.retrieveAllCGInvoicesByCriteria(new java.util.HashMap<String, String>());
        assertThat(result).hasSize(1);
    }

    @Test
    void retrieveAllCGInvoicesByCriteria_shouldReturnEmptyWhenNone() {
        when(contractsGrantsInvoiceDocumentDao.getMatchingInvoicesByCollection(any(Map.class)))
                .thenReturn(new ArrayList<ContractsGrantsInvoiceDocument>());

        Collection<ContractsGrantsInvoiceDocument> result = service.retrieveAllCGInvoicesByCriteria(new java.util.HashMap<String, String>());
        assertThat(result).isEmpty();
    }

    @Test
    void getInvoiceDetailExpenditureSum_shouldReturnZeroForEmptyList() {
        KualiDecimal result = service.getInvoiceDetailExpenditureSum(new ArrayList<ContractsGrantsInvoiceDetail>());
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getInvoiceDetailExpenditureSum_shouldSumExpenditures() {
        ContractsGrantsInvoiceDetail detail1 = new ContractsGrantsInvoiceDetail();
        detail1.setInvoiceAmount(new KualiDecimal(100));
        ContractsGrantsInvoiceDetail detail2 = new ContractsGrantsInvoiceDetail();
        detail2.setInvoiceAmount(new KualiDecimal(200));

        KualiDecimal result = service.getInvoiceDetailExpenditureSum(Arrays.asList(detail1, detail2));
        assertThat(result).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void getAccountService_shouldReturnInjectedService() {
        assertThat(service.getAccountService()).isSameAs(accountService);
    }
}
