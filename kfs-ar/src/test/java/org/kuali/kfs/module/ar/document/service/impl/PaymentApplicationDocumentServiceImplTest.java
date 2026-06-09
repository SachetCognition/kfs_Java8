package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.AccountsReceivableDocumentHeader;
import org.kuali.kfs.module.ar.businessobject.AppliedPayment;
import org.kuali.kfs.module.ar.businessobject.CashControlDetail;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.businessobject.InvoicePaidApplied;
import org.kuali.kfs.module.ar.businessobject.NonAppliedHolding;
import org.kuali.kfs.module.ar.document.CashControlDocument;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.module.ar.document.PaymentApplicationDocument;
import org.kuali.kfs.module.ar.document.dataaccess.CashControlDetailDao;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.module.ar.document.service.CustomerInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.InvoicePaidAppliedService;
import org.kuali.kfs.module.ar.document.service.NonAppliedHoldingService;
import org.kuali.kfs.module.ar.document.service.SystemInformationService;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class PaymentApplicationDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private DocumentService documentService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private NonAppliedHoldingService nonAppliedHoldingService;
    @Mock private InvoicePaidAppliedService<AppliedPayment> invoicePaidAppliedService;
    @Mock private UniversityDateService universityDateService;
    @Mock private CashControlDetailDao cashControlDetailDao;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private SystemInformationService systemInformationService;
    @Mock private CustomerAddressService customerAddressService;
    @Mock private ParameterService parameterService;
    @Mock private PersonService personService;
    @Mock private CustomerInvoiceDocumentService customerInvoiceDocumentService;
    @Mock private KualiRuleService kualiRuleService;

    @InjectMocks
    private PaymentApplicationDocumentServiceImpl service;

    @Test
    void getTotalAppliedAmountForPaymentApplicationDocument_shouldSumInvoicePaidApplieds() {
        PaymentApplicationDocument doc = mock(PaymentApplicationDocument.class);
        InvoicePaidApplied ipa1 = new InvoicePaidApplied();
        ipa1.setInvoiceItemAppliedAmount(new KualiDecimal(100));
        InvoicePaidApplied ipa2 = new InvoicePaidApplied();
        ipa2.setInvoiceItemAppliedAmount(new KualiDecimal(50));
        when(doc.getInvoicePaidApplieds()).thenReturn(Arrays.asList(ipa1, ipa2));
        when(doc.getSumOfNonInvoiceds()).thenReturn(new KualiDecimal(25));

        KualiDecimal result = service.getTotalAppliedAmountForPaymentApplicationDocument(doc);
        assertThat(result).isEqualTo(new KualiDecimal(175));
    }

    @Test
    void getTotalAppliedAmountForPaymentApplicationDocument_shouldReturnZeroWhenEmpty() {
        PaymentApplicationDocument doc = mock(PaymentApplicationDocument.class);
        when(doc.getInvoicePaidApplieds()).thenReturn(Collections.<InvoicePaidApplied>emptyList());
        when(doc.getSumOfNonInvoiceds()).thenReturn(KualiDecimal.ZERO);

        KualiDecimal result = service.getTotalAppliedAmountForPaymentApplicationDocument(doc);
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getTotalUnappliedFundsForPaymentApplicationDocument_shouldSumHoldings() {
        PaymentApplicationDocument doc = mock(PaymentApplicationDocument.class);
        AccountsReceivableDocumentHeader arHeader = new AccountsReceivableDocumentHeader();
        arHeader.setCustomerNumber("CUST001");
        when(doc.getAccountsReceivableDocumentHeader()).thenReturn(arHeader);

        NonAppliedHolding holding1 = new NonAppliedHolding();
        holding1.setFinancialDocumentLineAmount(new KualiDecimal(200));
        when(nonAppliedHoldingService.getNonAppliedHoldingsForCustomer("CUST001"))
                .thenReturn(Arrays.asList(holding1));

        NonAppliedHolding docHolding = new NonAppliedHolding();
        docHolding.setFinancialDocumentLineAmount(new KualiDecimal(50));
        when(doc.getNonAppliedHolding()).thenReturn(docHolding);

        KualiDecimal result = service.getTotalUnappliedFundsForPaymentApplicationDocument(doc);
        assertThat(result).isEqualTo(new KualiDecimal(250));
    }

    @Test
    void getTotalUnappliedFundsForPaymentApplicationDocument_shouldHandleNullDocHolding() {
        PaymentApplicationDocument doc = mock(PaymentApplicationDocument.class);
        AccountsReceivableDocumentHeader arHeader = new AccountsReceivableDocumentHeader();
        arHeader.setCustomerNumber("CUST001");
        when(doc.getAccountsReceivableDocumentHeader()).thenReturn(arHeader);
        when(nonAppliedHoldingService.getNonAppliedHoldingsForCustomer("CUST001"))
                .thenReturn(Collections.<NonAppliedHolding>emptyList());
        when(doc.getNonAppliedHolding()).thenReturn(null);

        KualiDecimal result = service.getTotalUnappliedFundsForPaymentApplicationDocument(doc);
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getCashControlDocumentForPaymentApplicationDocument_shouldThrowOnNull() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Exception {
                service.getCashControlDocumentForPaymentApplicationDocument(null);
            }
        });
    }

    @Test
    void getCashControlDocumentForPayAppDocNumber_shouldThrowOnBlank() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Exception {
                service.getCashControlDocumentForPayAppDocNumber("");
            }
        });
    }

    @Test
    void getCashControlDocumentForPayAppDocNumber_shouldReturnNullWhenNoDetail() {
        when(cashControlDetailDao.getCashControlDetailByRefDocNumber("PAY001"))
                .thenReturn(null);

        CashControlDocument result = service.getCashControlDocumentForPayAppDocNumber("PAY001");
        assertThat(result).isNull();
    }

    @Test
    void getCashControlDetailForPaymentApplicationDocument_shouldThrowOnNull() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                service.getCashControlDetailForPaymentApplicationDocument(null);
            }
        });
    }

    @Test
    void getCashControlDetailForPayAppDocNumber_shouldThrowOnBlank() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                service.getCashControlDetailForPayAppDocNumber("");
            }
        });
    }

    @Test
    void getCashControlDetailForPayAppDocNumber_shouldReturnDetail() {
        CashControlDetail detail = new CashControlDetail();
        when(cashControlDetailDao.getCashControlDetailByRefDocNumber("PAY001"))
                .thenReturn(detail);

        CashControlDetail result = service.getCashControlDetailForPayAppDocNumber("PAY001");
        assertThat(result).isSameAs(detail);
    }

    @Test
    void createInvoicePaidAppliedsForEntireInvoiceDocument_shouldClearAndRecreate() {
        CustomerInvoiceDocument invoice = mock(CustomerInvoiceDocument.class);
        PaymentApplicationDocument payDoc = mock(PaymentApplicationDocument.class);
        List<InvoicePaidApplied> paidApplieds = new ArrayList<>();
        when(payDoc.getInvoicePaidApplieds()).thenReturn(paidApplieds);
        when(invoice.getCustomerInvoiceDetailsWithoutDiscounts()).thenReturn(Collections.<CustomerInvoiceDetail>emptyList());

        PaymentApplicationDocument result = service.createInvoicePaidAppliedsForEntireInvoiceDocument(invoice, payDoc);
        assertThat(result).isSameAs(payDoc);
    }

    @Test
    void createInvoicePaidAppliedForInvoiceDetail_shouldCreateCorrectEntry() {
        CustomerInvoiceDetail detail = mock(CustomerInvoiceDetail.class);
        when(detail.getSequenceNumber()).thenReturn(new Integer(1));
        when(detail.getAmountOpen()).thenReturn(new KualiDecimal(500));
        when(detail.getDocumentNumber()).thenReturn("INV001");

        PaymentApplicationDocument payDoc = mock(PaymentApplicationDocument.class);
        when(payDoc.getDocumentNumber()).thenReturn("PAY001");

        org.kuali.kfs.coa.businessobject.AccountingPeriod period = mock(org.kuali.kfs.coa.businessobject.AccountingPeriod.class);
        when(period.getUniversityFiscalPeriodCode()).thenReturn("01");
        UniversityDate uDate = mock(UniversityDate.class);
        when(uDate.getAccountingPeriod()).thenReturn(period);
        when(universityDateService.getCurrentFiscalYear()).thenReturn(2024);
        when(universityDateService.getCurrentUniversityDate()).thenReturn(uDate);

        InvoicePaidApplied result = service.createInvoicePaidAppliedForInvoiceDetail(detail, payDoc, 1);
        assertThat(result).isNotNull();
        assertThat(result.getDocumentNumber()).isEqualTo("PAY001");
        assertThat(result.getUniversityFiscalYear()).isEqualTo(2024);
    }

    @Test
    void customerInvoiceDetailPairsWithInvoicePaidApplied_shouldReturnTrueForMatch() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        detail.setDocumentNumber("INV001");
        detail.setSequenceNumber(new Integer(1));

        InvoicePaidApplied ipa = new InvoicePaidApplied();
        ipa.setFinancialDocumentReferenceInvoiceNumber("INV001");
        ipa.setInvoiceItemNumber(1);

        boolean result = service.customerInvoiceDetailPairsWithInvoicePaidApplied(detail, ipa);
        assertThat(result).isTrue();
    }

    @Test
    void customerInvoiceDetailPairsWithInvoicePaidApplied_shouldReturnFalseForMismatch() {
        CustomerInvoiceDetail detail = new CustomerInvoiceDetail();
        detail.setDocumentNumber("INV001");
        detail.setSequenceNumber(new Integer(1));

        InvoicePaidApplied ipa = new InvoicePaidApplied();
        ipa.setFinancialDocumentReferenceInvoiceNumber("INV002");
        ipa.setInvoiceItemNumber(1);

        boolean result = service.customerInvoiceDetailPairsWithInvoicePaidApplied(detail, ipa);
        assertThat(result).isFalse();
    }
}
