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
package org.kuali.kfs.module.cg.document.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.CGPropertyConstants;
import org.kuali.kfs.module.cg.businessobject.ResearchRiskType;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class RoutingFormResearchRiskServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private RoutingFormResearchRiskServiceImpl service;

    @Test
    public void testGetResearchRiskTypes_noExceptions() {
        List<ResearchRiskType> allTypes = createSampleRiskTypes();
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(allTypes);

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[0]);

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void testGetResearchRiskTypes_withExceptions() {
        List<ResearchRiskType> allTypes = createSampleRiskTypes();
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(allTypes);

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[]{"RT1"});

        assertNotNull(result);
        assertEquals(2, result.size());
        for (ResearchRiskType type : result) {
            assertTrue(!"RT1".equals(type.getResearchRiskTypeCode()));
        }
    }

    @Test
    public void testGetResearchRiskTypes_allExcepted() {
        List<ResearchRiskType> allTypes = createSampleRiskTypes();
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(allTypes);

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[]{"RT1", "RT2", "RT3"});

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetResearchRiskTypes_multipleExceptions() {
        List<ResearchRiskType> allTypes = createSampleRiskTypes();
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(allTypes);

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[]{"RT1", "RT3"});

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("RT2", result.get(0).getResearchRiskTypeCode());
    }

    @Test
    public void testGetResearchRiskTypes_emptyDatabase() {
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(Collections.<ResearchRiskType>emptyList());

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[0]);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetResearchRiskTypes_nonMatchingExceptionCode() {
        List<ResearchRiskType> allTypes = createSampleRiskTypes();
        when(businessObjectService.findMatchingOrderBy(
                eq(ResearchRiskType.class), any(Map.class),
                eq(CGPropertyConstants.RESEARCH_RISK_TYPE_SORT_NUMBER), eq(true)))
                .thenReturn(allTypes);

        List<ResearchRiskType> result = service.getResearchRiskTypes(new String[]{"NONEXISTENT"});

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    private List<ResearchRiskType> createSampleRiskTypes() {
        List<ResearchRiskType> types = new ArrayList<>();

        ResearchRiskType rt1 = new ResearchRiskType();
        rt1.setResearchRiskTypeCode("RT1");
        rt1.setActive(true);
        rt1.setResearchRiskTypeSortNumber(1);
        types.add(rt1);

        ResearchRiskType rt2 = new ResearchRiskType();
        rt2.setResearchRiskTypeCode("RT2");
        rt2.setActive(true);
        rt2.setResearchRiskTypeSortNumber(2);
        types.add(rt2);

        ResearchRiskType rt3 = new ResearchRiskType();
        rt3.setResearchRiskTypeCode("RT3");
        rt3.setActive(true);
        rt3.setResearchRiskTypeSortNumber(3);
        types.add(rt3);

        return types;
    }
}
