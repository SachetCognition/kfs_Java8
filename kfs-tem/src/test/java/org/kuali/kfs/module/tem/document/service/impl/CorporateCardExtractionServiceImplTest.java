package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemParameterConstants;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TEMReimbursementDocument;
import org.kuali.kfs.sys.KFSParameterKeyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.PaymentSourceHelperService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CorporateCardExtractionServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CorporateCardExtractionServiceImpl corporateCardService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private DocumentService documentService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private PaymentSourceHelperService paymentSourceHelperService;

    @Mock
    private TravelPaymentsHelperServiceImpl travelPaymentsHelperService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testRetrievePaymentSourcesByCampus_disbursementDisabled() {
        when(parameterService.getParameterValueAsBoolean(
                TemParameterConstants.TEM_DOCUMENT.class,
                TemConstants.TravelParameters.CORPORATE_CARD_PAYMENT_IND)).thenReturn(false);

        Map<String, List<TEMReimbursementDocument>> result = corporateCardService.retrievePaymentSourcesByCampus(false);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPreDisbursementCustomerProfileUnit() {
        when(parameterService.getParameterValueAsString(
                TemParameterConstants.TEM_DOCUMENT.class,
                KFSParameterKeyConstants.PdpExtractBatchParameters.PDP_ORG_CODE)).thenReturn("TEM");

        String result = corporateCardService.getPreDisbursementCustomerProfileUnit();

        assertEquals("TEM", result);
    }

    @Test
    void testGetPreDisbursementCustomerProfileSubUnit() {
        when(parameterService.getParameterValueAsString(
                TemParameterConstants.TEM_DOCUMENT.class,
                KFSParameterKeyConstants.PdpExtractBatchParameters.PDP_SBUNT_CODE)).thenReturn("CORP");

        String result = corporateCardService.getPreDisbursementCustomerProfileSubUnit();

        assertEquals("CORP", result);
    }
}
