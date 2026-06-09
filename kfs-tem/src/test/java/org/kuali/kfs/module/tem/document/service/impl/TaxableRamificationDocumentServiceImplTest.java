package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.TravelAdvance;
import org.kuali.kfs.module.tem.document.TaxableRamificationDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TaxableRamificationDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TaxableRamificationDocumentServiceImpl taxRamificationService;

    @Mock
    private DocumentService documentService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private AccountsReceivableModuleService accountsReceivableModuleService;

    @Test
    void testHasTaxableRamification_true() {
        TravelAdvance advance = new TravelAdvance();
        advance.setDocumentNumber("DOC001");

        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(TemPropertyConstants.TRAVEL_ADVANCE_DOCUMENT_NUMBER, "DOC001");

        when(businessObjectService.countMatching(TaxableRamificationDocument.class, fieldValues)).thenReturn(1);

        assertTrue(taxRamificationService.hasTaxableRamification(advance));
    }

    @Test
    void testHasTaxableRamification_false() {
        TravelAdvance advance = new TravelAdvance();
        advance.setDocumentNumber("DOC001");

        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(TemPropertyConstants.TRAVEL_ADVANCE_DOCUMENT_NUMBER, "DOC001");

        when(businessObjectService.countMatching(TaxableRamificationDocument.class, fieldValues)).thenReturn(0);

        assertFalse(taxRamificationService.hasTaxableRamification(advance));
    }

    @Test
    void testCreateAndBlanketApproveRamificationDocument_existingRamificationThrows() {
        TravelAdvance advance = new TravelAdvance();
        advance.setDocumentNumber("DOC001");

        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(TemPropertyConstants.TRAVEL_ADVANCE_DOCUMENT_NUMBER, "DOC001");

        when(businessObjectService.countMatching(TaxableRamificationDocument.class, fieldValues)).thenReturn(1);

        try {
            taxRamificationService.createAndBlanketApproveRamificationDocument(advance);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }
    }
}
