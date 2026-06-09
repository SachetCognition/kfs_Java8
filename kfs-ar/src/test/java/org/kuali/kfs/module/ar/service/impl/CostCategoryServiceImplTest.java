package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CostCategory;
import org.kuali.kfs.module.ar.businessobject.CostCategoryDetail;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectCode;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectConsolidation;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectLevel;
import org.kuali.kfs.module.ar.dataaccess.CostCategoryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CostCategoryServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CostCategoryServiceImpl costCategoryService;

    @Mock
    private CostCategoryDao costCategoryDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @Test
    void testIsCostCategoryObjectConsolidationUnique_foundAmongConsolidations() {
        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        CostCategoryDetail expectedDetail = new CostCategoryObjectConsolidation();

        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(consolidation))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectConsolidationUnique_foundAmongLevels() {
        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        CostCategoryDetail expectedDetail = new CostCategoryObjectLevel();

        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(consolidation))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongLevels(consolidation))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectConsolidationUnique_foundAmongCodes() {
        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        CostCategoryDetail expectedDetail = new CostCategoryObjectCode();

        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(consolidation))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongLevels(consolidation))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongCodes(consolidation))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectConsolidationUnique_noneFound() {
        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();

        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(consolidation))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongLevels(consolidation))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongCodes(consolidation))
                .thenReturn(null);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isNull();
    }

    @Test
    void testIsCostCategoryObjectLevelUnique_foundAmongLevels() {
        CostCategoryObjectLevel level = new CostCategoryObjectLevel();
        CostCategoryDetail expectedDetail = new CostCategoryObjectLevel();

        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongLevels(level))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectLevelUnique(level);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectLevelUnique_foundAmongConsolidations() {
        CostCategoryObjectLevel level = new CostCategoryObjectLevel();
        CostCategoryDetail expectedDetail = new CostCategoryObjectConsolidation();

        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongLevels(level))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongConsolidations(level))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectLevelUnique(level);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectLevelUnique_foundAmongCodes() {
        CostCategoryObjectLevel level = new CostCategoryObjectLevel();
        CostCategoryDetail expectedDetail = new CostCategoryObjectCode();

        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongLevels(level))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongConsolidations(level))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongCodes(level))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectLevelUnique(level);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectCodeUnique_foundAmongCodes() {
        CostCategoryObjectCode objectCode = new CostCategoryObjectCode();
        CostCategoryDetail expectedDetail = new CostCategoryObjectCode();

        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongCodes(objectCode))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectCodeUnique(objectCode);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectCodeUnique_foundAmongLevels() {
        CostCategoryObjectCode objectCode = new CostCategoryObjectCode();
        CostCategoryDetail expectedDetail = new CostCategoryObjectLevel();

        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongCodes(objectCode))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongLevels(objectCode))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectCodeUnique(objectCode);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectCodeUnique_foundAmongConsolidations() {
        CostCategoryObjectCode objectCode = new CostCategoryObjectCode();
        CostCategoryDetail expectedDetail = new CostCategoryObjectConsolidation();

        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongCodes(objectCode))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongLevels(objectCode))
                .thenReturn(null);
        when(costCategoryDao.retrieveCostCategoryObjectCodeAmongConsolidations(objectCode))
                .thenReturn(expectedDetail);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectCodeUnique(objectCode);
        assertThat(result).isNotNull().isEqualTo(expectedDetail);
    }

    @Test
    void testIsCostCategoryObjectCodeUnique_noneFound() {
        CostCategoryObjectCode objectCode = new CostCategoryObjectCode();

        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongCodes(objectCode))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongLevels(objectCode))
                .thenReturn(null);
        when(costCategoryDao.retrieveCostCategoryObjectCodeAmongConsolidations(objectCode))
                .thenReturn(null);

        CostCategoryDetail result = costCategoryService.isCostCategoryObjectCodeUnique(objectCode);
        assertThat(result).isNull();
    }
}
