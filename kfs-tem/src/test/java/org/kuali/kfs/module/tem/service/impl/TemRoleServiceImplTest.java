package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.module.tem.TemConstants.TravelDocTypes;
import org.kuali.kfs.module.tem.TemPropertyConstants.TemProfileProperties;
import org.kuali.kfs.module.tem.businessobject.TemProfileArranger;
import org.kuali.kfs.module.tem.document.service.TravelArrangerDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class TemRoleServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TemRoleServiceImpl temRoleService;

    @Mock
    private RoleService roleService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private TravelArrangerDocumentService arrangerDocumentService;

    @Test
    void testIsArrangerForProfile_true() {
        TemProfileArranger arranger = new TemProfileArranger();
        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(arranger);

        assertTrue(temRoleService.isArrangerForProfile("user1", 100));
    }

    @Test
    void testIsArrangerForProfile_false() {
        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(null);

        assertFalse(temRoleService.isArrangerForProfile("user1", 100));
    }

    @Test
    void testIsArrangerForProfile_nullPrincipalId() {
        assertFalse(temRoleService.isArrangerForProfile(null, 100));
    }

    @Test
    void testIsTravelDocumentArrangerForProfile_taDocType() {
        TemProfileArranger arranger = new TemProfileArranger();
        arranger.setTaInd(true);
        arranger.setTrInd(false);

        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(arranger);

        assertTrue(temRoleService.isTravelDocumentArrangerForProfile(TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT, "user1", 100));
    }

    @Test
    void testIsTravelDocumentArrangerForProfile_trDocType() {
        TemProfileArranger arranger = new TemProfileArranger();
        arranger.setTaInd(false);
        arranger.setTrInd(true);

        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(arranger);

        assertTrue(temRoleService.isTravelDocumentArrangerForProfile(TravelDocTypes.TRAVEL_REIMBURSEMENT_DOCUMENT, "user1", 100));
    }

    @Test
    void testIsTravelDocumentArrangerForProfile_noArrangerRecord() {
        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(null);

        assertFalse(temRoleService.isTravelDocumentArrangerForProfile(TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT, "user1", 100));
    }

    @Test
    void testIsTravelDocumentArrangerForProfile_arrangerButNotForDocType() {
        TemProfileArranger arranger = new TemProfileArranger();
        arranger.setTaInd(false);
        arranger.setTrInd(true);

        when(arrangerDocumentService.findTemProfileArranger("user1", 100)).thenReturn(arranger);

        assertFalse(temRoleService.isTravelDocumentArrangerForProfile(TravelDocTypes.TRAVEL_AUTHORIZATION_DOCUMENT, "user1", 100));
    }

    @Test
    void testIsProfileArranger_true() {
        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(TemProfileProperties.PRINCIPAL_ID, "user1");

        ArrayList<TemProfileArranger> arrangers = new ArrayList();
        arrangers.add(new TemProfileArranger());

        when(businessObjectService.findMatching(TemProfileArranger.class, fieldValues)).thenReturn(arrangers);

        assertTrue(temRoleService.isProfileArranger("user1"));
    }

    @Test
    void testIsProfileArranger_false() {
        Map<String, Object> fieldValues = new HashMap();
        fieldValues.put(TemProfileProperties.PRINCIPAL_ID, "user1");

        when(businessObjectService.findMatching(TemProfileArranger.class, fieldValues)).thenReturn(new java.util.ArrayList());

        assertFalse(temRoleService.isProfileArranger("user1"));
    }

    @Test
    void testIsProfileArranger_blankId() {
        assertFalse(temRoleService.isProfileArranger(""));
    }

    @Test
    void testIsProfileArranger_nullId() {
        assertFalse(temRoleService.isProfileArranger(null));
    }
}
