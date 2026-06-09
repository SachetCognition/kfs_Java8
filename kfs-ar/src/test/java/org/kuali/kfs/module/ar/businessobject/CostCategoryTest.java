package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CostCategoryTest extends KfsUnitTestBase {

    private CostCategory costCategory;

    @BeforeEach
    void setUp() {
        costCategory = new CostCategory();
    }

    @Test
    void testCategoryCode() {
        costCategory.setCategoryCode("PERS");
        assertThat(costCategory.getCategoryCode()).isEqualTo("PERS");
    }

    @Test
    void testCategoryName() {
        costCategory.setCategoryName("Personnel");
        assertThat(costCategory.getCategoryName()).isEqualTo("Personnel");
    }

    @Test
    void testActive() {
        costCategory.setActive(true);
        assertThat(costCategory.isActive()).isTrue();

        costCategory.setActive(false);
        assertThat(costCategory.isActive()).isFalse();
    }

    @Test
    void testIndirectCostIndicator() {
        costCategory.setIndirectCostIndicator(true);
        assertThat(costCategory.isIndirectCostIndicator()).isTrue();

        costCategory.setIndirectCostIndicator(false);
        assertThat(costCategory.isIndirectCostIndicator()).isFalse();
    }

    @Test
    void testObjectCodes() {
        List<CostCategoryObjectCode> objectCodes = new ArrayList<>();
        CostCategoryObjectCode code = new CostCategoryObjectCode();
        code.setCategoryCode("PERS");
        objectCodes.add(code);

        costCategory.setObjectCodes(objectCodes);
        assertThat(costCategory.getObjectCodes()).hasSize(1);
    }

    @Test
    void testObjectLevels() {
        List<CostCategoryObjectLevel> levels = new ArrayList<>();
        CostCategoryObjectLevel level = new CostCategoryObjectLevel();
        level.setCategoryCode("PERS");
        levels.add(level);

        costCategory.setObjectLevels(levels);
        assertThat(costCategory.getObjectLevels()).hasSize(1);
    }

    @Test
    void testObjectConsolidations() {
        List<CostCategoryObjectConsolidation> consolidations = new ArrayList<>();
        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        consolidation.setCategoryCode("PERS");
        consolidations.add(consolidation);

        costCategory.setObjectConsolidations(consolidations);
        assertThat(costCategory.getObjectConsolidations()).hasSize(1);
    }
}
