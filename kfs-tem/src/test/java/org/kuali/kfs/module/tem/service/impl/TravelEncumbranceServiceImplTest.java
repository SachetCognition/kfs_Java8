package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.TripType;
import org.kuali.kfs.module.tem.document.TravelAuthorizationDocument;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelEncumbranceServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelEncumbranceServiceImpl travelEncumbranceService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private DocumentService documentService;

    @Mock
    private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ConfigurationService configurationService;

    @Test
    void testUpdateEncumbranceObjectCode_withTripType() {
        TravelAuthorizationDocument taDoc = mock(TravelAuthorizationDocument.class);
        TripType tripType = new TripType();
        tripType.setEncumbranceObjCode("9070");
        when(taDoc.getTripType()).thenReturn(tripType);

        SourceAccountingLine line = new SourceAccountingLine();
        travelEncumbranceService.updateEncumbranceObjectCode(taDoc, line);

        assertEquals("9070", line.getFinancialObjectCode());
    }

    @Test
    void testUpdateEncumbranceObjectCode_nullTripType() {
        TravelAuthorizationDocument taDoc = mock(TravelAuthorizationDocument.class);
        when(taDoc.getTripType()).thenReturn(null);

        SourceAccountingLine line = new SourceAccountingLine();
        travelEncumbranceService.updateEncumbranceObjectCode(taDoc, line);

        assertEquals("", line.getFinancialObjectCode());
    }

    @Test
    void testGetEncumbranceBalanceTypeByTripType_withTripType() {
        TravelDocument document = mock(TravelDocument.class);
        TripType tripType = new TripType();
        tripType.setEncumbranceBalanceType("EX");
        when(document.getTripType()).thenReturn(tripType);

        String result = travelEncumbranceService.getEncumbranceBalanceTypeByTripType(document);

        assertEquals("EX", result);
    }

    @Test
    void testGetEncumbranceBalanceTypeByTripType_nullTripType() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getTripType()).thenReturn(null);

        String result = travelEncumbranceService.getEncumbranceBalanceTypeByTripType(document);

        assertEquals("", result);
    }

    @Test
    void testGetEncumbranceBalanceTypeByTripType_nullBalanceType() {
        TravelDocument document = mock(TravelDocument.class);
        TripType tripType = new TripType();
        tripType.setEncumbranceBalanceType(null);
        when(document.getTripType()).thenReturn(tripType);

        String result = travelEncumbranceService.getEncumbranceBalanceTypeByTripType(document);

        assertEquals("", result);
    }
}
