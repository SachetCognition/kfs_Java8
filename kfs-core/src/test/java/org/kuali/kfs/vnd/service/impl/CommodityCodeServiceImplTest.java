package org.kuali.kfs.vnd.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.businessobject.CommodityCode;
import org.kuali.kfs.vnd.dataaccess.CommodityCodeDao;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommodityCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private CommodityCodeDao commodityCodeDao;

    private CommodityCodeServiceImpl commodityCodeService;

    @BeforeEach
    void setUp() {
        commodityCodeService = new CommodityCodeServiceImpl();
        commodityCodeService.setBusinessObjectService(businessObjectService);
        commodityCodeService.setCommodityCodeDao(commodityCodeDao);
    }

    @Test
    void getByPrimaryIdUppercasesCode() {
        CommodityCode expected = new CommodityCode();
        expected.setPurchasingCommodityCode("ABC123");
        when(businessObjectService.retrieve(any(CommodityCode.class))).thenReturn(expected);

        CommodityCode result = commodityCodeService.getByPrimaryId("abc123");

        ArgumentCaptor<CommodityCode> captor = ArgumentCaptor.forClass(CommodityCode.class);
        verify(businessObjectService).retrieve(captor.capture());
        assertThat(captor.getValue().getPurchasingCommodityCode()).isEqualTo("ABC123");
    }

    @Test
    void getByPrimaryIdReturnsResult() {
        CommodityCode expected = new CommodityCode();
        expected.setPurchasingCommodityCode("TEST");
        when(businessObjectService.retrieve(any(CommodityCode.class))).thenReturn(expected);

        CommodityCode result = commodityCodeService.getByPrimaryId("test");
        assertThat(result).isNotNull();
        assertThat(result.getPurchasingCommodityCode()).isEqualTo("TEST");
    }

    @Test
    void getByPrimaryIdReturnsNullWhenNotFound() {
        when(businessObjectService.retrieve(any(CommodityCode.class))).thenReturn(null);

        CommodityCode result = commodityCodeService.getByPrimaryId("NONEXISTENT");
        assertThat(result).isNull();
    }

    @Test
    void wildCardCommodityCodeExistsDelegatesToDao() {
        when(commodityCodeDao.wildCardCommodityCodeExists("ABC*")).thenReturn(true);

        boolean result = commodityCodeService.wildCardCommodityCodeExists("ABC*");
        assertThat(result).isTrue();
        verify(commodityCodeDao).wildCardCommodityCodeExists("ABC*");
    }

    @Test
    void wildCardCommodityCodeExistsReturnsFalse() {
        when(commodityCodeDao.wildCardCommodityCodeExists("XYZ*")).thenReturn(false);

        boolean result = commodityCodeService.wildCardCommodityCodeExists("XYZ*");
        assertThat(result).isFalse();
    }
}
