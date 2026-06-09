package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.GlSummary;
import org.kuali.kfs.gl.dataaccess.BalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BalanceServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Mock
    private BalanceDao balanceDao;

    @Test
    void getGlSummary_withResults_returnsListOfGlSummary() {
        List<String> balanceTypeCodes = Arrays.asList("AC", "CB");
        Object[] row1 = {"FUND1", new KualiDecimal(100), new KualiDecimal(200), new KualiDecimal(300),
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO,
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO,
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO};
        Object[] row2 = {"FUND2", new KualiDecimal(400), new KualiDecimal(500), new KualiDecimal(600),
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO,
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO,
            KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO};

        Iterator<Object[]> mockIterator = Arrays.asList(row1, row2).iterator();
        when(balanceDao.getGlSummary(2024, balanceTypeCodes)).thenReturn(mockIterator);

        List<GlSummary> results = balanceService.getGlSummary(2024, balanceTypeCodes);

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getFundGroup()).isEqualTo("FUND1");
        assertThat(results.get(0).getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(100));
        assertThat(results.get(1).getFundGroup()).isEqualTo("FUND2");
        assertThat(results.get(1).getAccountLineAnnualBalanceAmount()).isEqualTo(new KualiDecimal(400));
        verify(balanceDao).getGlSummary(2024, balanceTypeCodes);
    }

    @Test
    void getGlSummary_withNoResults_returnsEmptyList() {
        List<String> balanceTypeCodes = Collections.singletonList("AC");
        Iterator<Object[]> emptyIterator = Collections.<Object[]>emptyList().iterator();
        when(balanceDao.getGlSummary(2024, balanceTypeCodes)).thenReturn(emptyIterator);

        List<GlSummary> results = balanceService.getGlSummary(2024, balanceTypeCodes);

        assertThat(results).isEmpty();
    }
}
