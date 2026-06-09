package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.module.purap.businessobject.NegativePaymentRequestApprovalLimit;
import org.kuali.kfs.module.purap.document.dataaccess.NegativePaymentRequestApprovalLimitDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

@MockitoSettings(strictness = Strictness.LENIENT)
public class NegativePaymentRequestApprovalLimitServiceImplTest extends KfsUnitTestBase {

    @Mock private NegativePaymentRequestApprovalLimitDao negativePaymentRequestApprovalLimitDao;

    @InjectMocks
    private NegativePaymentRequestApprovalLimitServiceImpl service;

    @Test
    public void testFindByChart_returnsCollection() {
        Collection<NegativePaymentRequestApprovalLimit> expected = new ArrayList<NegativePaymentRequestApprovalLimit>();
        expected.add(new NegativePaymentRequestApprovalLimit());
        when(negativePaymentRequestApprovalLimitDao.findByChart("BL")).thenReturn(expected);

        Collection<NegativePaymentRequestApprovalLimit> result = service.findByChart("BL");

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindByChart_emptyResult() {
        when(negativePaymentRequestApprovalLimitDao.findByChart("XX")).thenReturn(new ArrayList<NegativePaymentRequestApprovalLimit>());

        Collection<NegativePaymentRequestApprovalLimit> result = service.findByChart("XX");

        assertThat(result).isEmpty();
    }

    @Test
    public void testFindByChartAndAccount_returnsCollection() {
        Collection<NegativePaymentRequestApprovalLimit> expected = new ArrayList<NegativePaymentRequestApprovalLimit>();
        expected.add(new NegativePaymentRequestApprovalLimit());
        when(negativePaymentRequestApprovalLimitDao.findByChartAndAccount("BL", "1234567")).thenReturn(expected);

        Collection<NegativePaymentRequestApprovalLimit> result = service.findByChartAndAccount("BL", "1234567");

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindByChartAndOrganization_returnsCollection() {
        Collection<NegativePaymentRequestApprovalLimit> expected = new ArrayList<NegativePaymentRequestApprovalLimit>();
        when(negativePaymentRequestApprovalLimitDao.findByChartAndOrganization("BL", "ACCT")).thenReturn(expected);

        Collection<NegativePaymentRequestApprovalLimit> result = service.findByChartAndOrganization("BL", "ACCT");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindAboveLimit_returnsCollection() {
        KualiDecimal limit = new KualiDecimal(500);
        Collection<NegativePaymentRequestApprovalLimit> expected = new ArrayList<NegativePaymentRequestApprovalLimit>();
        when(negativePaymentRequestApprovalLimitDao.findAboveLimit(limit)).thenReturn(expected);

        Collection<NegativePaymentRequestApprovalLimit> result = service.findAboveLimit(limit);

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindBelowLimit_returnsCollection() {
        KualiDecimal limit = new KualiDecimal(100);
        Collection<NegativePaymentRequestApprovalLimit> expected = new ArrayList<NegativePaymentRequestApprovalLimit>();
        when(negativePaymentRequestApprovalLimitDao.findBelowLimit(limit)).thenReturn(expected);

        Collection<NegativePaymentRequestApprovalLimit> result = service.findBelowLimit(limit);

        assertThat(result).isNotNull();
    }
}
