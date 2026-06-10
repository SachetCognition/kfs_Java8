package org.kuali.kfs.module.cg.batch.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.Agency;
import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class MaintenanceDocumentNotesMigrationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private IdentityService identityService;

    @Mock
    private NoteService noteService;

    private MaintenanceDocumentNotesMigrationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MaintenanceDocumentNotesMigrationServiceImpl();
        service.setBusinessObjectService(businessObjectService);
        service.setIdentityService(identityService);
        service.setNoteService(noteService);
    }

    @Test
    void testMoveAgencyMaintenanceDocumentNotesToBusinessObjects_EmptyCollection() {
        Collection<Agency> agencies = new ArrayList<>();
        Principal systemUser = Principal.Builder.create("kfs").build();

        when(businessObjectService.findAll(Agency.class)).thenReturn(agencies);
        when(identityService.getPrincipalByPrincipalName("kfs")).thenReturn(systemUser);

        service.moveAgencyMaintenanceDocumentNotesToBusinessObjects();

        verify(businessObjectService).findAll(Agency.class);
    }

    @Test
    void testMoveAwardMaintenanceDocumentNotesToBusinessObjects_EmptyCollection() {
        Collection<Award> awards = new ArrayList<>();
        Principal systemUser = Principal.Builder.create("kfs").build();

        when(businessObjectService.findAll(Award.class)).thenReturn(awards);
        when(identityService.getPrincipalByPrincipalName("kfs")).thenReturn(systemUser);

        service.moveAwardMaintenanceDocumentNotesToBusinessObjects();

        verify(businessObjectService).findAll(Award.class);
    }

    @Test
    void testGetBusinessObjectService() {
        assertThat(service.getBusinessObjectService()).isSameAs(businessObjectService);
    }

    @Test
    void testGetIdentityService() {
        assertThat(service.getIdentityService()).isSameAs(identityService);
    }

    @Test
    void testGetNoteService() {
        assertThat(service.getNoteService()).isSameAs(noteService);
    }
}
