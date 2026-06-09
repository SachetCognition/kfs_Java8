package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.Agency;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AgencyServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private NoteService noteService;

    private AgencyServiceImpl agencyService;

    @BeforeEach
    void setUp() {
        agencyService = new AgencyServiceImpl();
        agencyService.setBusinessObjectService(businessObjectService);
        agencyService.setNoteService(noteService);
    }

    @Test
    void testGetByPrimaryIdReturnsAgency() {
        String agencyNumber = "AG001";
        Agency expected = new Agency();
        expected.setAgencyNumber(agencyNumber);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.AGENCY_NUMBER, agencyNumber);

        when(businessObjectService.findByPrimaryKey(eq(Agency.class), eq(primaryKeys)))
                .thenReturn(expected);

        Agency result = agencyService.getByPrimaryId(agencyNumber);

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(Agency.class), eq(primaryKeys));
    }

    @Test
    void testGetByPrimaryIdTrimsInput() {
        String agencyNumber = "  AG001  ";
        Agency expected = new Agency();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.AGENCY_NUMBER, "AG001");

        when(businessObjectService.findByPrimaryKey(eq(Agency.class), eq(primaryKeys)))
                .thenReturn(expected);

        Agency result = agencyService.getByPrimaryId(agencyNumber);

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetByPrimaryIdReturnsNullWhenNotFound() {
        String agencyNumber = "NOTFOUND";

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.AGENCY_NUMBER, agencyNumber);

        when(businessObjectService.findByPrimaryKey(eq(Agency.class), eq(primaryKeys)))
                .thenReturn(null);

        Agency result = agencyService.getByPrimaryId(agencyNumber);

        assertThat(result).isNull();
    }

    @Test
    void testGetAgencyNotesReturnsNotesWhenAgencyExists() {
        String agencyNumber = "AG001";
        Agency agency = new Agency();
        agency.setAgencyNumber(agencyNumber);
        agency.setObjectId("obj-id-123");

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.AGENCY_NUMBER, agencyNumber);

        when(businessObjectService.findByPrimaryKey(eq(Agency.class), eq(primaryKeys)))
                .thenReturn(agency);

        List<Note> expectedNotes = new ArrayList<>();
        Note note = org.mockito.Mockito.mock(Note.class);
        expectedNotes.add(note);

        when(noteService.getByRemoteObjectId("obj-id-123")).thenReturn(expectedNotes);

        List<Note> result = agencyService.getAgencyNotes(agencyNumber);

        assertThat(result).hasSize(1);
        assertThat(result).isSameAs(expectedNotes);
    }

    @Test
    void testGetAgencyNotesReturnsEmptyWhenAgencyNotFound() {
        String agencyNumber = "NOTFOUND";

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.AGENCY_NUMBER, agencyNumber);

        when(businessObjectService.findByPrimaryKey(eq(Agency.class), eq(primaryKeys)))
                .thenReturn(null);

        List<Note> result = agencyService.getAgencyNotes(agencyNumber);

        assertThat(result).isEmpty();
    }

    @Test
    void testGetNoteService() {
        assertThat(agencyService.getNoteService()).isSameAs(noteService);
    }
}
