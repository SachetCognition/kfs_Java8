package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.businessobject.CommodityCode;
import org.kuali.kfs.vnd.dataaccess.CommodityCodeDao;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommodityCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private CommodityCodeDao commodityCodeDao;

    @InjectMocks
    private CommodityCodeServiceImpl commodityCodeService;

    @Test
    void getByPrimaryId_returnsRetrievedCommodityCode() {
        CommodityCode expected = new CommodityCode();
        when(businessObjectService.retrieve(any(CommodityCode.class))).thenReturn(expected);

        CommodityCode result = commodityCodeService.getByPrimaryId("1234");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.retrieve(any(CommodityCode.class))).thenReturn(null);

        CommodityCode result = commodityCodeService.getByPrimaryId("ZZZZ");
        assertThat(result).isNull();
    }

    @Test
    void wildCardCommodityCodeExists_delegatesToDao() {
        when(commodityCodeDao.wildCardCommodityCodeExists("12*")).thenReturn(true);

        assertThat(commodityCodeService.wildCardCommodityCodeExists("12*")).isTrue();
        verify(commodityCodeDao).wildCardCommodityCodeExists("12*");
    }

    @Test
    void wildCardCommodityCodeExists_noMatch_returnsFalse() {
        when(commodityCodeDao.wildCardCommodityCodeExists("ZZ*")).thenReturn(false);

        assertThat(commodityCodeService.wildCardCommodityCodeExists("ZZ*")).isFalse();
    }
}
