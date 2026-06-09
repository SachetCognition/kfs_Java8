package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.businessobject.InvoicePaidApplied;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountsReceivablePendingEntryServiceImplTest extends KfsUnitTestBase {

    @Mock private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private AccountsReceivablePendingEntryServiceImpl service;

    @Test
    void getAccountsReceivableObjectCode_shouldReturnNullForBlankChart() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        detail.setChartOfAccountsCode("");

        String result = service.getAccountsReceivableObjectCode(detail);
        assertThat(result).isNull();
    }

    @Test
    void getAccountsReceivableObjectCode_shouldReturnNullForNullChart() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        detail.setChartOfAccountsCode(null);

        String result = service.getAccountsReceivableObjectCode(detail);
        assertThat(result).isNull();
    }
}
