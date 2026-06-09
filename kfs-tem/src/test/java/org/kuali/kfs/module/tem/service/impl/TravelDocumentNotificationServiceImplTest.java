package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemParameterConstants;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.KfsNotificationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TravelDocumentNotificationServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TravelDocumentNotificationServiceImpl notificationService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private KfsNotificationService kfsNotificationService;

    @Test
    void testGetTravelProfile_nullProfileId() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getProfileId()).thenReturn(null);

        TemProfile result = notificationService.getTravelProfile(document);

        assertNull(result);
    }

    @Test
    void testGetTravelProfile_foundProfile() {
        TravelDocument document = mock(TravelDocument.class);
        when(document.getProfileId()).thenReturn(100);

        TemProfile profile = new TemProfile();
        when(businessObjectService.findBySinglePrimaryKey(TemProfile.class, 100)).thenReturn(profile);

        TemProfile result = notificationService.getTravelProfile(document);

        assertSame(profile, result);
    }

    @Test
    void testIsNotificationEnabled_whenEnabled() {
        when(parameterService.getParameterValueAsBoolean(
                TemParameterConstants.TEM_DOCUMENT.class,
                TemConstants.TravelParameters.SEND_NOTIFICATION_ON_WORKFLOW_STATUS_CHANGE_IND)).thenReturn(true);

        assertTrue(notificationService.isNotificationEnabled());
    }

    @Test
    void testIsNotificationEnabled_whenDisabled() {
        when(parameterService.getParameterValueAsBoolean(
                TemParameterConstants.TEM_DOCUMENT.class,
                TemConstants.TravelParameters.SEND_NOTIFICATION_ON_WORKFLOW_STATUS_CHANGE_IND)).thenReturn(false);

        assertFalse(notificationService.isNotificationEnabled());
    }

    @Test
    void testGetNoNotificationRouteStatusList_initializedOnFirstCall() {
        List<String> list = notificationService.getNoNotificationRouteStatusList();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}
