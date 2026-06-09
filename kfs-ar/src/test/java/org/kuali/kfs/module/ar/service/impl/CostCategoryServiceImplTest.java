package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CostCategory;
import org.kuali.kfs.module.ar.businessobject.CostCategoryDetail;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectCode;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectConsolidation;
import org.kuali.kfs.module.ar.businessobject.CostCategoryObjectLevel;
import org.kuali.kfs.module.ar.dataaccess.CostCategoryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CostCategoryServiceImplTest extends KfsUnitTestBase {

    @Mock private CostCategoryDao costCategoryDao;
    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private CostCategoryServiceImpl service;

    @Test
    void isCostCategoryObjectConsolidationUnique_shouldReturnNullWhenAllUnique() {
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(any(CostCategoryObjectConsolidation.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongLevels(any(CostCategoryObjectConsolidation.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongCodes(any(CostCategoryObjectConsolidation.class)))
                .thenReturn(null);

        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        CostCategoryDetail result = service.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isNull();
    }

    @Test
    void isCostCategoryObjectConsolidationUnique_shouldReturnDetailWhenConsolidationMatch() {
        CostCategoryDetail existingDetail = new CostCategoryObjectConsolidation();
        when(costCategoryDao.retrieveMatchingCostCategoryConsolidationAmongConsolidations(any(CostCategoryObjectConsolidation.class)))
                .thenReturn(existingDetail);

        CostCategoryObjectConsolidation consolidation = new CostCategoryObjectConsolidation();
        CostCategoryDetail result = service.isCostCategoryObjectConsolidationUnique(consolidation);
        assertThat(result).isSameAs(existingDetail);
    }

    @Test
    void isCostCategoryObjectLevelUnique_shouldReturnNullWhenAllUnique() {
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongLevels(any(CostCategoryObjectLevel.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongConsolidations(any(CostCategoryObjectLevel.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryLevelAmongCodes(any(CostCategoryObjectLevel.class)))
                .thenReturn(null);

        CostCategoryObjectLevel level = new CostCategoryObjectLevel();
        CostCategoryDetail result = service.isCostCategoryObjectLevelUnique(level);
        assertThat(result).isNull();
    }

    @Test
    void isCostCategoryObjectCodeUnique_shouldReturnNullWhenAllUnique() {
        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongCodes(any(CostCategoryObjectCode.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveMatchingCostCategoryObjectCodeAmongLevels(any(CostCategoryObjectCode.class)))
                .thenReturn(null);
        when(costCategoryDao.retrieveCostCategoryObjectCodeAmongConsolidations(any(CostCategoryObjectCode.class)))
                .thenReturn(null);

        CostCategoryObjectCode code = new CostCategoryObjectCode();
        CostCategoryDetail result = service.isCostCategoryObjectCodeUnique(code);
        assertThat(result).isNull();
    }
}
