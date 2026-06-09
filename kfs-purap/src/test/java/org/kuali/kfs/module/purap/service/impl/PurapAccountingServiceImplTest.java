package org.kuali.kfs.module.purap.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.kuali.kfs.module.purap.businessobject.PurApAccountingLine;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.dataaccess.PurApAccountingDao;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class PurapAccountingServiceImplTest extends KfsUnitTestBase {

    @Mock private PurApAccountingDao purApAccountingDao;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private PurapAccountingServiceImpl purapAccountingService;

    @Test
    public void testGetAccountsFromItem_returnsAccountingLines() {
        PurApItem item = mock(PurApItem.class);
        List<PurApAccountingLine> expected = new ArrayList<PurApAccountingLine>();
        when(purApAccountingDao.getAccountingLinesForItem(item)).thenReturn(expected);

        List<PurApAccountingLine> result = purapAccountingService.getAccountsFromItem(item);

        assertThat(result).isSameAs(expected);
    }

    @Test
    public void testGetAccountsFromItem_withAccounts() {
        PurApItem item = mock(PurApItem.class);
        List<PurApAccountingLine> accounts = new ArrayList<PurApAccountingLine>();
        PurApAccountingLine line = mock(PurApAccountingLine.class);
        accounts.add(line);
        when(purApAccountingDao.getAccountingLinesForItem(item)).thenReturn(accounts);

        List<PurApAccountingLine> result = purapAccountingService.getAccountsFromItem(item);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testServiceInstantiation() {
        assertThat(purapAccountingService).isNotNull();
    }

    @Test
    public void testGenerateAccountDistributionForProration_emptyAccounts() {
        List<SourceAccountingLine> accounts = new ArrayList<SourceAccountingLine>();

        List<PurApAccountingLine> result = purapAccountingService.generateAccountDistributionForProration(
            accounts, KualiDecimal.ZERO, 2);

        assertThat(result).isNull();
    }
}
