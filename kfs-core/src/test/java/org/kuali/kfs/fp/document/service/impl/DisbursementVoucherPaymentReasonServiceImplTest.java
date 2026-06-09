package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.DisbursementPayee;
import org.kuali.kfs.fp.document.service.DisbursementVoucherPayeeService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class DisbursementVoucherPaymentReasonServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DisbursementVoucherPayeeService disbursementVoucherPayeeService;

    @InjectMocks
    private DisbursementVoucherPaymentReasonServiceImpl paymentReasonService;

    @Test
    void isPayeeQualifiedForPayment_nullPayeeTypeCodes_returnsFalse() {
        DisbursementPayee payee = new DisbursementPayee();
        boolean result = paymentReasonService.isPayeeQualifiedForPayment(payee, "A", (Collection<String>) null);
        assertThat(result).isFalse();
    }

    @Test
    void isPayeeQualifiedForPayment_emptyPayeeTypeCodes_returnsFalse() {
        DisbursementPayee payee = new DisbursementPayee();
        Collection<String> emptyTypes = Collections.emptyList();
        boolean result = paymentReasonService.isPayeeQualifiedForPayment(payee, "A", emptyTypes);
        assertThat(result).isFalse();
    }
}
