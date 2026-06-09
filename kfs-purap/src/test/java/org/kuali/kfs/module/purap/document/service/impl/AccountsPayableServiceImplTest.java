package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.PurapParameterConstants;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.document.AccountsPayableDocument;
import org.kuali.kfs.module.purap.document.PaymentRequestDocument;
import org.kuali.kfs.module.purap.document.PurchaseOrderDocument;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.module.purap.document.service.PurchaseOrderService;
import org.kuali.kfs.module.purap.service.PurapAccountingService;
import org.kuali.kfs.module.purap.service.PurapGeneralLedgerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountsPayableServiceImplTest extends KfsUnitTestBase {

    @Mock private ParameterService parameterService;
    @Mock private PurapService purapService;
    @Mock private PurapAccountingService purapAccountingService;
    @Mock private PurapGeneralLedgerService purapGeneralLedgerService;
    @Mock private DocumentService documentService;
    @Mock private DateTimeService dateTimeService;
    @Mock private PurchaseOrderService purchaseOrderService;
    @Mock private AccountService accountService;

    @InjectMocks
    private AccountsPayableServiceImpl accountsPayableService;

    @Test
    public void testPurchaseOrderItemEligibleForPayment_inactiveItem_returnsFalse() {
        PurchaseOrderItem poi = mock(PurchaseOrderItem.class);
        when(poi.isItemActiveIndicator()).thenReturn(false);

        boolean result = accountsPayableService.purchaseOrderItemEligibleForPayment(poi);

        assertThat(result).isFalse();
    }

    @Test
    public void testCanCopyAccountingLinesWithZeroAmount_yes() {
        when(parameterService.getParameterValueAsString(any(Class.class), eq(PurapParameterConstants.COPY_ACCOUNTING_LINES_WITH_ZERO_AMOUNT_FROM_PO_TO_PREQ_IND))).thenReturn("Y");

        boolean result = accountsPayableService.canCopyAccountingLinesWithZeroAmount();

        assertThat(result).isTrue();
    }

    @Test
    public void testCanCopyAccountingLinesWithZeroAmount_no() {
        when(parameterService.getParameterValueAsString(any(Class.class), eq(PurapParameterConstants.COPY_ACCOUNTING_LINES_WITH_ZERO_AMOUNT_FROM_PO_TO_PREQ_IND))).thenReturn("N");

        boolean result = accountsPayableService.canCopyAccountingLinesWithZeroAmount();

        assertThat(result).isFalse();
    }

    @Test
    public void testGetExpiredOrClosedAccountList_emptyDocument() {
        AccountsPayableDocument doc = mock(PaymentRequestDocument.class);
        PurchaseOrderDocument poDoc = mock(PurchaseOrderDocument.class);
        when(doc.getPurchaseOrderDocument()).thenReturn(poDoc);
        when(poDoc.getItems()).thenReturn(new ArrayList<PurApItem>());

        HashMap result = accountsPayableService.getExpiredOrClosedAccountList(doc);

        assertThat(result).isNotNull();
    }
}
