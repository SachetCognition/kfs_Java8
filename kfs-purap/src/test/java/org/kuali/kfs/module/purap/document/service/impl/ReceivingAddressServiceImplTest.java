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
import org.kuali.kfs.module.purap.businessobject.ReceivingAddress;
import org.kuali.kfs.module.purap.document.dataaccess.ReceivingAddressDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ReceivingAddressServiceImplTest extends KfsUnitTestBase {

    @Mock private ReceivingAddressDao dao;

    @InjectMocks
    private ReceivingAddressServiceImpl receivingAddressService;

    @Test
    public void testFindActiveByChartOrg_returnsCollection() {
        Collection<ReceivingAddress> expected = new ArrayList<ReceivingAddress>();
        expected.add(new ReceivingAddress());
        when(dao.findActiveByChartOrg("BL", "ACCT")).thenReturn(expected);

        Collection<ReceivingAddress> result = receivingAddressService.findActiveByChartOrg("BL", "ACCT");

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindActiveByChartOrg_emptyResult() {
        when(dao.findActiveByChartOrg("XX", "YY")).thenReturn(new ArrayList<ReceivingAddress>());

        Collection<ReceivingAddress> result = receivingAddressService.findActiveByChartOrg("XX", "YY");

        assertThat(result).isEmpty();
    }

    @Test
    public void testFindDefaultByChartOrg_returnsCollection() {
        Collection<ReceivingAddress> expected = new ArrayList<ReceivingAddress>();
        expected.add(new ReceivingAddress());
        when(dao.findDefaultByChartOrg("BL", "ACCT")).thenReturn(expected);

        Collection<ReceivingAddress> result = receivingAddressService.findDefaultByChartOrg("BL", "ACCT");

        assertThat(result).hasSize(1);
    }

    @Test
    public void testCountActiveByChartOrg_returnsCount() {
        when(dao.countActiveByChartOrg("BL", "ACCT")).thenReturn(2);

        int result = receivingAddressService.countActiveByChartOrg("BL", "ACCT");

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void testCountActiveByChartOrg_zeroResults() {
        when(dao.countActiveByChartOrg("XX", "YY")).thenReturn(0);

        int result = receivingAddressService.countActiveByChartOrg("XX", "YY");

        assertThat(result).isEqualTo(0);
    }
}
