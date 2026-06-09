package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.DunningLetterTemplate;
import org.kuali.kfs.module.ar.businessobject.GenerateDunningLettersLookupResult;
import org.kuali.kfs.module.ar.document.ContractsGrantsInvoiceDocument;
import org.kuali.kfs.module.ar.document.dataaccess.ContractsGrantsInvoiceDocumentDao;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.service.ContractsGrantsBillingUtilityService;
import org.kuali.kfs.sys.businessobject.ChartOrgHolder;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DunningLetterServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ContractsGrantsInvoiceDocumentDao contractsGrantsInvoiceDocumentDao;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private ContractsGrantsBillingUtilityService contractsGrantsBillingUtilityService;
    @Mock private DateTimeService dateTimeService;
    @Mock private FinancialSystemUserService financialSystemUserService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private NoteService noteService;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private DunningLetterServiceImpl service;

    @Test
    void isValidOrganizationForTemplate_shouldReturnFalseWhenTemplateChartsBlank() {
        DunningLetterTemplate template = new DunningLetterTemplate();
        template.setBillByChartOfAccountCode("");
        template.setBilledByOrganizationCode("");
        Person user = mock(Person.class);

        assertThat(service.isValidOrganizationForTemplate(template, user)).isFalse();
    }

    @Test
    void isValidOrganizationForTemplate_shouldReturnTrueWhenOrgMatches() {
        DunningLetterTemplate template = new DunningLetterTemplate();
        template.setBillByChartOfAccountCode("UA");
        template.setBilledByOrganizationCode("VPIT");

        Person user = mock(Person.class);
        ChartOrgHolder chartOrg = mock(ChartOrgHolder.class);
        when(chartOrg.getChartOfAccountsCode()).thenReturn("UA");
        when(chartOrg.getOrganizationCode()).thenReturn("VPIT");
        when(financialSystemUserService.getPrimaryOrganization(any(Person.class), any(String.class)))
                .thenReturn(chartOrg);

        assertThat(service.isValidOrganizationForTemplate(template, user)).isTrue();
    }

    @Test
    void isValidOrganizationForTemplate_shouldReturnFalseWhenOrgMismatches() {
        DunningLetterTemplate template = new DunningLetterTemplate();
        template.setBillByChartOfAccountCode("UA");
        template.setBilledByOrganizationCode("VPIT");

        Person user = mock(Person.class);
        ChartOrgHolder chartOrg = mock(ChartOrgHolder.class);
        when(chartOrg.getChartOfAccountsCode()).thenReturn("BL");
        when(financialSystemUserService.getPrimaryOrganization(any(Person.class), any(String.class)))
                .thenReturn(chartOrg);

        assertThat(service.isValidOrganizationForTemplate(template, user)).isFalse();
    }

    @Test
    void getPopulatedGenerateDunningLettersLookupResults_shouldReturnEmptyForEmptyInvoices() {
        Collection<GenerateDunningLettersLookupResult> result =
                service.getPopulatedGenerateDunningLettersLookupResults(new ArrayList<ContractsGrantsInvoiceDocument>());
        assertThat(result).isEmpty();
    }

    @Test
    void getPopulatedGenerateDunningLettersLookupResults_shouldReturnEmptyForNullInvoices() {
        Collection<GenerateDunningLettersLookupResult> result =
                service.getPopulatedGenerateDunningLettersLookupResults(null);
        assertThat(result).isEmpty();
    }

    @Test
    void createZipOfPDFs_shouldCreateZipSuccessfully() throws IOException {
        byte[] pdfContent = new byte[]{1, 2, 3, 4, 5};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean result = service.createZipOfPDFs(pdfContent, baos);
        assertThat(result).isTrue();
        assertThat(baos.size()).isGreaterThan(0);
    }

    @Test
    void createZipOfPDFs_shouldReturnTrueForNullContent() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean result = service.createZipOfPDFs(null, baos);
        assertThat(result).isTrue();
    }
}
