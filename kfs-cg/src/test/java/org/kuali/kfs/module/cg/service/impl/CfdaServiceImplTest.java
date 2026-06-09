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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.CFDA;
import org.kuali.kfs.module.cg.businessobject.CfdaUpdateResults;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CfdaServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private CfdaServiceImpl cfdaService;

    @Test
    public void testGetByPrimaryId_found() {
        CFDA expected = new CFDA();
        expected.setCfdaNumber("10.001");
        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "10.001")).thenReturn(expected);

        CFDA result = cfdaService.getByPrimaryId("10.001");

        assertNotNull(result);
        assertEquals("10.001", result.getCfdaNumber());
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "99.999")).thenReturn(null);

        CFDA result = cfdaService.getByPrimaryId("99.999");

        assertNull(result);
    }

    @Test
    public void testGetByPrimaryId_blank() {
        CFDA result = cfdaService.getByPrimaryId("");

        assertNull(result);
        verify(businessObjectService, never()).findBySinglePrimaryKey(any(Class.class), any());
    }

    @Test
    public void testGetByPrimaryId_null() {
        CFDA result = cfdaService.getByPrimaryId(null);

        assertNull(result);
        verify(businessObjectService, never()).findBySinglePrimaryKey(any(Class.class), any());
    }

    @Test
    public void testGetByPrimaryId_trimInput() {
        CFDA expected = new CFDA();
        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "10.001")).thenReturn(expected);

        CFDA result = cfdaService.getByPrimaryId("  10.001  ");

        assertNotNull(result);
        verify(businessObjectService).findBySinglePrimaryKey(CFDA.class, "10.001");
    }

    @Test
    public void testGetKfsCodes_empty() throws IOException {
        Collection<CFDA> emptyList = new ArrayList<>();
        when(businessObjectService.findAll(CFDA.class)).thenReturn(emptyList);

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetKfsCodes_multipleEntries() throws IOException {
        Collection<CFDA> cfdaList = new ArrayList<>();
        CFDA cfda1 = new CFDA();
        cfda1.setCfdaNumber("10.001");
        cfda1.setCfdaProgramTitleName("Program A");
        cfdaList.add(cfda1);

        CFDA cfda2 = new CFDA();
        cfda2.setCfdaNumber("20.002");
        cfda2.setCfdaProgramTitleName("Program B");
        cfdaList.add(cfda2);

        when(businessObjectService.findAll(CFDA.class)).thenReturn(cfdaList);

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Program A", result.get("10.001").getCfdaProgramTitleName());
        assertEquals("Program B", result.get("20.002").getCfdaProgramTitleName());
    }

    @Test
    public void testGetKfsCodes_sorted() throws IOException {
        Collection<CFDA> cfdaList = new ArrayList<>();
        CFDA cfda1 = new CFDA();
        cfda1.setCfdaNumber("20.002");
        cfdaList.add(cfda1);

        CFDA cfda2 = new CFDA();
        cfda2.setCfdaNumber("10.001");
        cfdaList.add(cfda2);

        when(businessObjectService.findAll(CFDA.class)).thenReturn(cfdaList);

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertEquals("10.001", result.firstKey());
        assertEquals("20.002", result.lastKey());
    }

    @Test
    public void testGetKfsCodes_duplicateOverwritten() throws IOException {
        Collection<CFDA> cfdaList = new ArrayList<>();
        CFDA cfda1 = new CFDA();
        cfda1.setCfdaNumber("10.001");
        cfda1.setCfdaProgramTitleName("First");
        cfdaList.add(cfda1);

        CFDA cfda2 = new CFDA();
        cfda2.setCfdaNumber("10.001");
        cfda2.setCfdaProgramTitleName("Second");
        cfdaList.add(cfda2);

        when(businessObjectService.findAll(CFDA.class)).thenReturn(cfdaList);

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertEquals(1, result.size());
        assertEquals("Second", result.get("10.001").getCfdaProgramTitleName());
    }
}
