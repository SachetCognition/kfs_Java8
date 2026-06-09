package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.TaxDetail;
import org.kuali.kfs.sys.businessobject.TaxRegion;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.TaxRegionService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TaxServiceImplTest extends KfsUnitTestBase {

    @Mock
    private TaxRegionService taxRegionService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private TaxServiceImpl taxService;

    @Test
    void getSalesTaxDetails_emptyPostalCode_returnsEmptyList() {
        Date date = new Date(System.currentTimeMillis());
        List<TaxDetail> result = taxService.getSalesTaxDetails(date, "", new KualiDecimal(100));
        assertThat(result).isEmpty();
    }

    @Test
    void getSalesTaxDetails_nullPostalCode_returnsEmptyList() {
        Date date = new Date(System.currentTimeMillis());
        List<TaxDetail> result = taxService.getSalesTaxDetails(date, null, new KualiDecimal(100));
        assertThat(result).isEmpty();
    }

    @Test
    void getSalesTaxDetails_noTaxRegions_returnsEmptyList() {
        Date date = new Date(System.currentTimeMillis());
        when(taxRegionService.getSalesTaxRegions("12345")).thenReturn(new ArrayList<TaxRegion>());

        List<TaxDetail> result = taxService.getSalesTaxDetails(date, "12345", new KualiDecimal(100));
        assertThat(result).isEmpty();
    }

    @Test
    void getUseTaxDetails_emptyPostalCode_returnsEmptyList() {
        Date date = new Date(System.currentTimeMillis());
        List<TaxDetail> result = taxService.getUseTaxDetails(date, "", new KualiDecimal(100));
        assertThat(result).isEmpty();
    }

    @Test
    void getTotalSalesTaxAmount_emptyPostalCode_returnsZero() {
        Date date = new Date(System.currentTimeMillis());
        KualiDecimal result = taxService.getTotalSalesTaxAmount(date, "", new KualiDecimal(100));
        assertThat(result).isEqualTo(KualiDecimal.ZERO);
    }
}
