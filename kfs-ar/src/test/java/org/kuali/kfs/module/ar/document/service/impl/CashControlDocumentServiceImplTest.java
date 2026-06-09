package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.module.ar.document.service.SystemInformationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CashControlDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private SystemInformationService systemInformationService;
    @Mock private ChartService chartService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private GeneralLedgerPendingEntryService glpeService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private DocumentService documentService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private CashControlDocumentServiceImpl service;

    @Test
    void getSystemInformationService_shouldReturnInjectedService() {
        assertThat(service.getSystemInformationService()).isSameAs(systemInformationService);
    }

    @Test
    void getChartService_shouldReturnInjectedService() {
        assertThat(service.getChartService()).isSameAs(chartService);
    }

    @Test
    void getBusinessObjectService_shouldReturnInjectedService() {
        assertThat(service.getBusinessObjectService()).isSameAs(businessObjectService);
    }
}
