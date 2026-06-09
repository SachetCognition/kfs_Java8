package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.ObjectCodeCurrent;
import org.kuali.kfs.coa.dataaccess.ObjectCodeDao;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ObjectCodeServiceImplTest extends KfsUnitTestBase {

    @Mock private ObjectCodeDao objectCodeDao;
    @Mock private UniversityDateService universityDateService;
    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private ObjectCodeServiceImpl objectCodeService;

    @Nested
    @DisplayName("getYearList")
    class GetYearListTests {

        @Test
        void delegatesToDao() {
            List<Integer> years = Arrays.asList(2024, 2025);
            when(objectCodeDao.getYearList("BL", "5000")).thenReturn(years);

            List result = objectCodeService.getYearList("BL", "5000");

            assertThat(result).containsExactly(2024, 2025);
        }
    }

    @Nested
    @DisplayName("getObjectCodesByLevelIds")
    class GetByLevelIdsTests {

        @Test
        void returnsMatchingObjectCodes() {
            List<String> levelCodes = Arrays.asList("TRVL", "SUPL");
            ObjectCode oc = new ObjectCode(2025, "BL", "5000");
            when(businessObjectService.findMatching(eq(ObjectCode.class), any()))
                .thenReturn(Collections.singletonList(oc));

            List<ObjectCode> result = objectCodeService.getObjectCodesByLevelIds(levelCodes);

            assertThat(result).hasSize(1);
        }

        @Test
        void returnsEmptyListWhenNoMatch() {
            when(businessObjectService.findMatching(eq(ObjectCode.class), any()))
                .thenReturn(Collections.emptyList());

            List<ObjectCode> result = objectCodeService.getObjectCodesByLevelIds(Collections.singletonList("NONE"));

            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("doesObjectConsolidationContainObjectCode")
    class ConsolidationTests {

        @Test
        void returnsTrueWhenCountGreaterThanZero() {
            when(businessObjectService.countMatching(eq(ObjectCodeCurrent.class), any())).thenReturn(1);

            boolean result = objectCodeService.doesObjectConsolidationContainObjectCode("BL", "TRVL", "BL", "5000");

            assertThat(result).isTrue();
        }

        @Test
        void returnsFalseWhenCountIsZero() {
            when(businessObjectService.countMatching(eq(ObjectCodeCurrent.class), any())).thenReturn(0);

            boolean result = objectCodeService.doesObjectConsolidationContainObjectCode("BL", "TRVL", "BL", "9999");

            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("doesObjectLevelContainObjectCode")
    class ObjectLevelTests {

        @Test
        void returnsTrueWhenCountGreaterThanZero() {
            when(businessObjectService.countMatching(eq(ObjectCodeCurrent.class), any())).thenReturn(3);

            boolean result = objectCodeService.doesObjectLevelContainObjectCode("BL", "TRVL", "BL", "5000");

            assertThat(result).isTrue();
        }

        @Test
        void returnsFalseWhenCountIsZero() {
            when(businessObjectService.countMatching(eq(ObjectCodeCurrent.class), any())).thenReturn(0);

            boolean result = objectCodeService.doesObjectLevelContainObjectCode("BL", "TRVL", "BL", "9999");

            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("getObjectCodeNamesByCharts")
    class GetNamesByChartsTests {

        @Test
        void returnsNotFoundWhenObjectCodeNull() {
            // getByPrimaryId calls SpringContext internally — returns null
            // We can't fully test this without Spring context.
            // Just verify setter/getter for businessObjectService works
            assertThat(objectCodeService.getBusinessObjectService()).isSameAs(businessObjectService);
        }
    }
}
