package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.businessobject.ReceivingThreshold;
import org.kuali.kfs.module.purap.document.dataaccess.ThresholdDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ThresholdServiceImplTest extends KfsUnitTestBase {

    @Mock private ThresholdDao dao;

    @InjectMocks
    private ThresholdServiceImpl thresholdService;

    @Test
    public void testFindByChart_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        expected.add(new ReceivingThreshold());
        when(dao.findByChart("BL")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChart("BL");

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindByChartAndFund_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndFund("BL", "FUND1")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndFund("BL", "FUND1");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindByChartAndSubFund_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndSubFund("BL", "SUBFUND")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndSubFund("BL", "SUBFUND");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindByChartAndCommodity_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndCommodity("BL", "COMM1")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndCommodity("BL", "COMM1");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindByChartAndObjectCode_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndObjectCode("BL", "5000")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndObjectCode("BL", "5000");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindByChartAndOrg_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndOrg("BL", "ACCT")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndOrg("BL", "ACCT");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindByChartAndVendor_returnsThresholds() {
        Collection<ReceivingThreshold> expected = new ArrayList<ReceivingThreshold>();
        when(dao.findByChartAndVendor("BL", "1000", "0")).thenReturn(expected);

        Collection<ReceivingThreshold> result = thresholdService.findByChartAndVendor("BL", "1000", "0");

        assertThat(result).isNotNull();
    }
}
