package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PaymentAccountDetail;
import org.kuali.kfs.pdp.businessobject.PaymentFileLoad;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ResearchParticipantPaymentValidationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private ResearchParticipantPaymentValidationServiceImpl service;

    @Test
    void getPaymentAccountDetail_nullPaymentGroups_returnsNull() {
        PaymentFileLoad paymentFile = new PaymentFileLoad();
        PaymentAccountDetail result = service.getPaymentAccountDetail(paymentFile);
        assertThat(result).isNull();
    }

    @Test
    void getPaymentAccountDetail_emptyPaymentGroups_returnsNull() {
        PaymentFileLoad paymentFile = new PaymentFileLoad();
        PaymentAccountDetail result = service.getPaymentAccountDetail(paymentFile);
        assertThat(result).isNull();
    }
}
