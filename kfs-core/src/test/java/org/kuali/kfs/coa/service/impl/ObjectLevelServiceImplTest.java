package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectLevel;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ObjectLevelServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ObjectLevelServiceImpl objectLevelService;

    @Test
    void getByPrimaryId_returnsObjectLevel() {
        ObjectLevel expected = new ObjectLevel();
        when(businessObjectService.findByPrimaryKey(eq(ObjectLevel.class), any(Map.class))).thenReturn(expected);

        ObjectLevel result = objectLevelService.getByPrimaryId("BL", "COGS");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(ObjectLevel.class), any(Map.class))).thenReturn(null);

        ObjectLevel result = objectLevelService.getByPrimaryId("XX", "ZZ");
        assertThat(result).isNull();
    }

    @Test
    void getObjectLevelsByConsolidationsIds_returnsMatchingLevels() {
        ObjectLevel level = new ObjectLevel();
        when(businessObjectService.findMatching(eq(ObjectLevel.class), any(Map.class)))
                .thenReturn(Arrays.asList(level));

        List<ObjectLevel> result = objectLevelService.getObjectLevelsByConsolidationsIds(Arrays.asList("COGS"));
        assertThat(result).hasSize(1);
    }

    @Test
    void getObjectLevelsByConsolidationsIds_noResults_returnsEmptyList() {
        when(businessObjectService.findMatching(eq(ObjectLevel.class), any(Map.class)))
                .thenReturn(Collections.<ObjectLevel>emptyList());

        List<ObjectLevel> result = objectLevelService.getObjectLevelsByConsolidationsIds(Arrays.asList("XX"));
        assertThat(result).isEmpty();
    }

    @Test
    void getObjectLevelsByLevelIds_returnsMatchingLevels() {
        ObjectLevel level = new ObjectLevel();
        when(businessObjectService.findMatching(eq(ObjectLevel.class), any(Map.class)))
                .thenReturn(Arrays.asList(level));

        List<ObjectLevel> result = objectLevelService.getObjectLevelsByLevelIds(Arrays.asList("COGS"));
        assertThat(result).hasSize(1);
    }
}
