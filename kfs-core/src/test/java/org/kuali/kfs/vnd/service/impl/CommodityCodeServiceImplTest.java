package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.vnd.dataaccess.CommodityCodeDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CommodityCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private CommodityCodeDao commodityCodeDao;

    @InjectMocks
    private CommodityCodeServiceImpl commodityCodeService;

    @Test
    void wildCardCommodityCodeExists_delegatesToDao() {
        when(commodityCodeDao.wildCardCommodityCodeExists("AB*")).thenReturn(true);
        boolean result = commodityCodeService.wildCardCommodityCodeExists("AB*");
        assertThat(result).isTrue();
    }

    @Test
    void wildCardCommodityCodeExists_notFound_returnsFalse() {
        when(commodityCodeDao.wildCardCommodityCodeExists("ZZ*")).thenReturn(false);
        boolean result = commodityCodeService.wildCardCommodityCodeExists("ZZ*");
        assertThat(result).isFalse();
    }
}
