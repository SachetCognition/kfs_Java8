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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.SubContractor;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SubcontractorServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private SubcontractorServiceImpl subcontractorService;

    @Test
    public void testGetByPrimaryId_found() {
        SubContractor expected = new SubContractor();
        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), any(Map.class))).thenReturn(expected);

        SubContractor result = subcontractorService.getByPrimaryId("SC001");

        assertNotNull(result);
        assertEquals(expected, result);
        verify(businessObjectService).findByPrimaryKey(eq(SubContractor.class), any(Map.class));
    }

    @Test
    public void testGetByPrimaryId_notFound() {
        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), any(Map.class))).thenReturn(null);

        SubContractor result = subcontractorService.getByPrimaryId("INVALID");

        assertNull(result);
    }

    @Test
    public void testGetByPrimaryId_trimInput() {
        SubContractor expected = new SubContractor();
        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), any(Map.class))).thenReturn(expected);

        SubContractor result = subcontractorService.getByPrimaryId("  SC001  ");

        assertNotNull(result);
        assertEquals(expected, result);
    }
}
