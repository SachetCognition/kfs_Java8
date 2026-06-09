package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectLevel;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ObjectLevelServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private ObjectLevelServiceImpl objectLevelService;

    @Nested
    @DisplayName("getByPrimaryId")
    class GetByPrimaryIdTests {

        @Test
        void returnsLevelWhenFound() {
            ObjectLevel expected = new ObjectLevel();
            expected.setChartOfAccountsCode("BL");
            expected.setFinancialObjectLevelCode("TRVL");
            when(businessObjectService.findByPrimaryKey(eq(ObjectLevel.class), any()))
                .thenReturn(expected);

            ObjectLevel result = objectLevelService.getByPrimaryId("BL", "TRVL");

            assertThat(result).isNotNull();
            assertThat(result.getFinancialObjectLevelCode()).isEqualTo("TRVL");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(ObjectLevel.class), any())).thenReturn(null);

            assertThat(objectLevelService.getByPrimaryId("XX", "XXXX")).isNull();
        }
    }

    @Nested
    @DisplayName("getObjectLevelsByConsolidationsIds")
    class ConsolidationTests {

        @Test
        void returnsMatchingLevels() {
            ObjectLevel ol = new ObjectLevel();
            ol.setFinancialConsolidationObjectCode("OPEX");
            when(businessObjectService.findMatching(eq(ObjectLevel.class), any()))
                .thenReturn(Collections.singletonList(ol));

            List<ObjectLevel> result = objectLevelService.getObjectLevelsByConsolidationsIds(
                Arrays.asList("OPEX"));

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getObjectLevelsByLevelIds")
    class LevelIdsTests {

        @Test
        void returnsMatchingLevels() {
            ObjectLevel ol = new ObjectLevel();
            ol.setFinancialObjectLevelCode("TRVL");
            when(businessObjectService.findMatching(eq(ObjectLevel.class), any()))
                .thenReturn(Collections.singletonList(ol));

            List<ObjectLevel> result = objectLevelService.getObjectLevelsByLevelIds(
                Arrays.asList("TRVL"));

            assertThat(result).hasSize(1);
        }

        @Test
        void returnsEmptyListWhenNoMatch() {
            when(businessObjectService.findMatching(eq(ObjectLevel.class), any()))
                .thenReturn(Collections.emptyList());

            List<ObjectLevel> result = objectLevelService.getObjectLevelsByLevelIds(
                Arrays.asList("NONE"));

            assertThat(result).isEmpty();
        }
    }
}
