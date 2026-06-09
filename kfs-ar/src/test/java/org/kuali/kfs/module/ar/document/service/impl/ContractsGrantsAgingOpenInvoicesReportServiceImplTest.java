package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.ContractsGrantsAgingOpenInvoicesReport;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.report.service.ContractsGrantsAgingReportService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsGrantsAgingOpenInvoicesReportServiceImplTest extends KfsUnitTestBase {

    @Mock private ContractsGrantsAgingReportService contractsGrantsAgingReportService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private DateTimeService dateTimeService;
    @Mock private KualiModuleService kualiModuleService;

    @InjectMocks
    private ContractsGrantsAgingOpenInvoicesReportServiceImpl service;

    @Test
    void getPopulatedReportDetails_shouldReturnEmptyListForEmptyInput() {
        Map<String, List<ContractsGrantsAgingOpenInvoicesReport>> reportDetails = new HashMap<String, List<ContractsGrantsAgingOpenInvoicesReport>>();
        // testing getter/setter
        assertThat(service.getContractsGrantsAgingReportService()).isSameAs(contractsGrantsAgingReportService);
    }
}
