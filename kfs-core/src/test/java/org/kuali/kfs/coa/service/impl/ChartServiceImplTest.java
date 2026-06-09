package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ChartServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private ChartServiceImpl chartService;

    @Nested
    @DisplayName("getByPrimaryId")
    class GetByPrimaryIdTests {

        @Test
        void returnsChartWhenFound() {
            Chart expected = new Chart();
            expected.setChartOfAccountsCode("BL");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "BL")).thenReturn(expected);

            Chart result = chartService.getByPrimaryId("BL");

            assertThat(result).isNotNull();
            assertThat(result.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "XX")).thenReturn(null);

            assertThat(chartService.getByPrimaryId("XX")).isNull();
        }
    }

    @Nested
    @DisplayName("getUniversityChart")
    class GetUniversityChartTests {

        @Test
        void returnsNullWhenNoOrgFound() {
            when(parameterService.getParameterValueAsString(eq(Organization.class), any())).thenReturn("U");
            when(businessObjectService.findMatching(eq(Organization.class), any()))
                .thenReturn(Collections.emptyList());

            assertThat(chartService.getUniversityChart()).isNull();
        }

        @Test
        void returnsChartWhenOrgFound() {
            when(parameterService.getParameterValueAsString(eq(Organization.class), any())).thenReturn("U");
            Organization org = new Organization();
            org.setChartOfAccountsCode("UA");
            when(businessObjectService.findMatching(eq(Organization.class), any()))
                .thenReturn(Collections.singletonList(org));
            Chart chart = new Chart();
            chart.setChartOfAccountsCode("UA");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "UA")).thenReturn(chart);

            Chart result = chartService.getUniversityChart();

            assertThat(result).isNotNull();
            assertThat(result.getChartOfAccountsCode()).isEqualTo("UA");
        }
    }

    @Nested
    @DisplayName("getAllChartCodes")
    class GetAllChartCodesTests {

        @Test
        void returnsListOfChartCodes() {
            Chart bl = new Chart();
            bl.setChartOfAccountsCode("BL");
            Chart ua = new Chart();
            ua.setChartOfAccountsCode("UA");
            when(businessObjectService.findAllOrderBy(eq(Chart.class), any(), eq(true)))
                .thenReturn(Arrays.asList(bl, ua));

            List<String> result = chartService.getAllChartCodes();

            assertThat(result).containsExactly("BL", "UA");
        }
    }

    @Nested
    @DisplayName("getAllActiveCharts")
    class GetAllActiveChartsTests {

        @Test
        void returnsActiveCharts() {
            Chart bl = new Chart();
            bl.setChartOfAccountsCode("BL");
            bl.setActive(true);
            when(businessObjectService.findMatchingOrderBy(eq(Chart.class), any(), any(), eq(true)))
                .thenReturn(Collections.singletonList(bl));

            Collection<Chart> result = chartService.getAllActiveCharts();

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("isParentChart")
    class IsParentChartTests {

        @Test
        void throwsOnNullChildChartCode() {
            assertThatThrownBy(() -> chartService.isParentChart(null, "UA"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void throwsOnNullParentChartCode() {
            assertThatThrownBy(() -> chartService.isParentChart("BL", null))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void returnsFalseWhenChartReportsToSelf() {
            Chart chart = new Chart();
            chart.setChartOfAccountsCode("UA");
            chart.setReportsToChartOfAccountsCode("UA");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "UA")).thenReturn(chart);

            assertThat(chartService.isParentChart("UA", "BL")).isFalse();
        }

        @Test
        void returnsTrueWhenDirectParent() {
            Chart child = new Chart();
            child.setChartOfAccountsCode("BL");
            child.setReportsToChartOfAccountsCode("UA");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "BL")).thenReturn(child);

            assertThat(chartService.isParentChart("BL", "UA")).isTrue();
        }

        @Test
        void returnsTrueForIndirectParent() {
            Chart child = new Chart();
            child.setChartOfAccountsCode("CS");
            child.setReportsToChartOfAccountsCode("BL");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "CS")).thenReturn(child);

            Chart mid = new Chart();
            mid.setChartOfAccountsCode("BL");
            mid.setReportsToChartOfAccountsCode("UA");
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "BL")).thenReturn(mid);

            assertThat(chartService.isParentChart("CS", "UA")).isTrue();
        }
    }

    @Nested
    @DisplayName("getReportsToHierarchy")
    class ReportsToHierarchyTests {

        @Test
        void buildsHierarchyMap() {
            Chart bl = new Chart();
            bl.setChartOfAccountsCode("BL");
            bl.setReportsToChartOfAccountsCode("UA");
            Chart ua = new Chart();
            ua.setChartOfAccountsCode("UA");
            ua.setReportsToChartOfAccountsCode("UA");

            when(businessObjectService.findAllOrderBy(eq(Chart.class), any(), eq(true)))
                .thenReturn(Arrays.asList(bl, ua));
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "BL")).thenReturn(bl);
            when(businessObjectService.findBySinglePrimaryKey(Chart.class, "UA")).thenReturn(ua);

            Map<String, String> result = chartService.getReportsToHierarchy();

            assertThat(result).containsEntry("BL", "UA");
            assertThat(result).containsEntry("UA", "UA");
        }
    }
}
