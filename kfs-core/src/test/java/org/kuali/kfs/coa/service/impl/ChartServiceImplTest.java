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
package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ChartServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private RoleService roleService;
    @Mock
    private PersonService personService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private ChartServiceImpl chartService;

    @Test
    void getByPrimaryId_returnsChart() {
        Chart expected = new Chart();
        expected.setChartOfAccountsCode("BL");
        when(businessObjectService.findBySinglePrimaryKey(Chart.class, "BL")).thenReturn(expected);

        Chart result = chartService.getByPrimaryId("BL");

        assertThat(result).isSameAs(expected);
        assertThat(result.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findBySinglePrimaryKey(Chart.class, "XX")).thenReturn(null);

        assertThat(chartService.getByPrimaryId("XX")).isNull();
    }

    @Test
    void getAllChartCodes_returnsCodesFromAllCharts() {
        Chart chart1 = new Chart();
        chart1.setChartOfAccountsCode("BL");
        Chart chart2 = new Chart();
        chart2.setChartOfAccountsCode("IN");

        when(businessObjectService.findAllOrderBy(eq(Chart.class), anyString(), eq(true)))
                .thenReturn(Arrays.asList(chart1, chart2));

        List<String> result = chartService.getAllChartCodes();

        assertThat(result).containsExactly("BL", "IN");
    }

    @Test
    void getAllChartCodes_whenNoCharts_returnsEmptyList() {
        when(businessObjectService.findAllOrderBy(eq(Chart.class), anyString(), eq(true)))
                .thenReturn(Collections.<Chart>emptyList());

        List<String> result = chartService.getAllChartCodes();

        assertThat(result).isEmpty();
    }

    @Test
    void getUniversityChart_whenNoOrgsFound_returnsNull() {
        when(parameterService.getParameterValueAsString(eq(Organization.class), anyString()))
                .thenReturn("U");
        when(businessObjectService.findMatching(eq(Organization.class), any(Map.class)))
                .thenReturn(Collections.<Organization>emptyList());

        Chart result = chartService.getUniversityChart();

        assertThat(result).isNull();
    }

    @Test
    void getUniversityChart_whenOrgFound_returnsChartForOrg() {
        when(parameterService.getParameterValueAsString(eq(Organization.class), anyString()))
                .thenReturn("U");

        Organization org = new Organization();
        org.setChartOfAccountsCode("UA");
        Collection<Organization> orgs = Collections.singletonList(org);
        when(businessObjectService.findMatching(eq(Organization.class), any(Map.class)))
                .thenReturn(orgs);

        Chart expected = new Chart();
        expected.setChartOfAccountsCode("UA");
        when(businessObjectService.findBySinglePrimaryKey(Chart.class, "UA")).thenReturn(expected);

        Chart result = chartService.getUniversityChart();

        assertThat(result).isSameAs(expected);
    }
}
