package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.document.TEMReimbursementDocument;
import org.kuali.kfs.module.tem.document.service.TravelReimbursementService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntry;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.PaymentSourceHelperService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ReimbursableDocumentExtractionHelperServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private ReimbursableDocumentExtractionHelperServiceImpl extractionService;

    @Mock
    private DocumentService documentService;

    @Mock
    private PaymentSourceHelperService paymentSourceHelperService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private TravelPaymentsHelperServiceImpl travelPaymentsHelperService;

    @Mock
    private TravelReimbursementService travelReimbursementService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testRetrieveAllApprovedReimbursableDocuments() {
        List<TEMReimbursementDocument> reimDocs = new ArrayList<TEMReimbursementDocument>();
        List<TEMReimbursementDocument> entDocs = new ArrayList<TEMReimbursementDocument>();
        List<TEMReimbursementDocument> reloDocs = new ArrayList<TEMReimbursementDocument>();

        doReturn(reimDocs).when(travelDocumentDao).getReimbursementDocumentsByHeaderStatus(KFSConstants.DocumentStatusCodes.APPROVED, false);
        doReturn(entDocs).when(travelDocumentDao).getRelocationDocumentsByHeaderStatus(KFSConstants.DocumentStatusCodes.APPROVED, false);
        doReturn(reloDocs).when(travelDocumentDao).getEntertainmentDocumentsByHeaderStatus(KFSConstants.DocumentStatusCodes.APPROVED, false);

        List<TEMReimbursementDocument> result = extractionService.retrieveAllApprovedReimbursableDocuments(false);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testShouldRollBackPendingEntry_travelReimbursementCheckAch() {
        GeneralLedgerPendingEntry entry = new GeneralLedgerPendingEntry();
        entry.setFinancialDocumentTypeCode(TemConstants.TravelDocTypes.TRAVEL_REIMBURSEMENT_CHECK_ACH_DOCUMENT);

        assertTrue(extractionService.shouldRollBackPendingEntry(entry));
    }

    @Test
    void testShouldRollBackPendingEntry_otherDocType() {
        GeneralLedgerPendingEntry entry = new GeneralLedgerPendingEntry();
        entry.setFinancialDocumentTypeCode("DV");

        assertFalse(extractionService.shouldRollBackPendingEntry(entry));
    }
}
