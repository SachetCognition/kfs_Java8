/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.cg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.Agency;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AgencyServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private AgencyServiceImpl agencyService;

    @Test
    public void testGetByPrimaryId_found() {
        Agency expected = new Agency();
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(expected);

        Agency result = agencyService.getByPrimaryId("12345");

        assertNotNull(result);
        assertEquals(expected, result);
        verify(businessObjectService).findByPrimaryKey(eq(Agency.class), any(Map.class));
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(null);

        Agency result = agencyService.getByPrimaryId("99999");

        assertNull(result);
    }

    @Test
    public void testGetByPrimaryId_trimInput() {
        Agency expected = new Agency();
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(expected);

        agencyService.getByPrimaryId("  12345  ");

        verify(businessObjectService).findByPrimaryKey(eq(Agency.class), any(Map.class));
    }

    @Test
    public void testGetAgencyNotes_agencyFound() {
        Agency agency = Mockito.mock(Agency.class);
        when(agency.getObjectId()).thenReturn("obj-123");
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(agency);

        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(Mockito.mock(Note.class));
        when(noteService.getByRemoteObjectId("obj-123")).thenReturn(expectedNotes);

        List<Note> result = agencyService.getAgencyNotes("12345");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(noteService).getByRemoteObjectId("obj-123");
    }

    @Test
    public void testGetAgencyNotes_agencyNotFound() {
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(null);

        List<Note> result = agencyService.getAgencyNotes("99999");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAgencyNotes_noNotes() {
        Agency agency = Mockito.mock(Agency.class);
        when(agency.getObjectId()).thenReturn("obj-456");
        when(businessObjectService.findByPrimaryKey(eq(Agency.class), any(Map.class))).thenReturn(agency);
        when(noteService.getByRemoteObjectId("obj-456")).thenReturn(Collections.<Note>emptyList());

        List<Note> result = agencyService.getAgencyNotes("12345");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
