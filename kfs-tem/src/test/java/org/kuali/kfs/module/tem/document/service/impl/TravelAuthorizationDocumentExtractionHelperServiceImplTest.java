package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntry;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.PaymentSourceHelperService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelAuthorizationDocumentExtractionHelperServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelAuthorizationDocumentExtractionHelperServiceImpl extractionService;

    @Mock
    private DocumentService documentService;

    @Mock
    private PaymentSourceHelperService paymentSourceHelperService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private TravelPaymentsHelperServiceImpl travelPaymentsHelperService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testRetrieveAllApprovedAuthorizationDocuments_empty() {
        Collection<TravelAuthorizationDocument> authDocs = new ArrayList<TravelAuthorizationDocument>();
        doReturn(authDocs).when(travelDocumentDao).getAuthorizationsAndAmendmentsByHeaderStatus(KFSConstants.DocumentStatusCodes.APPROVED, false);

        List<? extends TravelAuthorizationDocument> result = extractionService.retrieveAllApprovedAuthorizationDocuments(false);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testShouldRollBackPendingEntry_travelAuthCheckAch() {
        GeneralLedgerPendingEntry entry = new GeneralLedgerPendingEntry();
        entry.setFinancialDocumentTypeCode(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_CHECK_ACH_DOCUMENT);

        assertTrue(extractionService.shouldRollBackPendingEntry(entry));
    }

    @Test
    void testShouldRollBackPendingEntry_travelAuthWireForeignDraft() {
        GeneralLedgerPendingEntry entry = new GeneralLedgerPendingEntry();
        entry.setFinancialDocumentTypeCode(TemConstants.TravelDocTypes.TRAVEL_AUTHORIZATION_WIRE_OR_FOREIGN_DRAFT_DOCUMENT);

        assertTrue(extractionService.shouldRollBackPendingEntry(entry));
    }

    @Test
    void testShouldRollBackPendingEntry_otherDocType() {
        GeneralLedgerPendingEntry entry = new GeneralLedgerPendingEntry();
        entry.setFinancialDocumentTypeCode("DV");

        assertFalse(extractionService.shouldRollBackPendingEntry(entry));
    }

    @Test
    void testRetrievePaymentSourcesByCampus_emptyDocuments() {
        Collection<TravelAuthorizationDocument> authDocs = new ArrayList<TravelAuthorizationDocument>();
        doReturn(authDocs).when(travelDocumentDao).getAuthorizationsAndAmendmentsByHeaderStatus(KFSConstants.DocumentStatusCodes.APPROVED, false);

        Map<String, List<TravelAuthorizationDocument>> result = extractionService.retrievePaymentSourcesByCampus(false);

        assertTrue(result.isEmpty());
    }
}
