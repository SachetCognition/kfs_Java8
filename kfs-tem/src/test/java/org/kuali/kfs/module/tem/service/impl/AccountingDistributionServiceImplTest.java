package org.kuali.kfs.module.tem.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.module.tem.businessobject.AccountingDistribution;
import org.kuali.kfs.module.tem.businessobject.TemDistributionAccountingLine;
import org.kuali.kfs.module.tem.businessobject.TemSourceAccountingLine;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AccountingDistributionServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountingDistributionServiceImpl accountingDistributionService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ObjectCodeService objectCodeService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private ParameterService parameterService;

    @Test
    void testDistributionToDistributionAccountingLine_allSelected() {
        List<AccountingDistribution> distributions = new ArrayList();
        AccountingDistribution dist1 = new AccountingDistribution();
        dist1.setSelected(true);
        dist1.setRemainingAmount(new KualiDecimal(100));
        AccountingDistribution dist2 = new AccountingDistribution();
        dist2.setSelected(true);
        dist2.setRemainingAmount(new KualiDecimal(200));
        distributions.add(dist1);
        distributions.add(dist2);

        TemDistributionAccountingLine result = accountingDistributionService.distributionToDistributionAccountingLine(distributions);

        assertEquals(new KualiDecimal(300), result.getAmount());
    }

    @Test
    void testDistributionToDistributionAccountingLine_noneSelected() {
        List<AccountingDistribution> distributions = new ArrayList();
        AccountingDistribution dist1 = new AccountingDistribution();
        dist1.setSelected(false);
        dist1.setRemainingAmount(new KualiDecimal(100));
        distributions.add(dist1);

        TemDistributionAccountingLine result = accountingDistributionService.distributionToDistributionAccountingLine(distributions);

        assertEquals(KualiDecimal.ZERO, result.getAmount());
    }

    @Test
    void testDistributionToDistributionAccountingLine_partialSelected() {
        List<AccountingDistribution> distributions = new ArrayList();
        AccountingDistribution dist1 = new AccountingDistribution();
        dist1.setSelected(true);
        dist1.setRemainingAmount(new KualiDecimal(150));
        AccountingDistribution dist2 = new AccountingDistribution();
        dist2.setSelected(false);
        dist2.setRemainingAmount(new KualiDecimal(200));
        distributions.add(dist1);
        distributions.add(dist2);

        TemDistributionAccountingLine result = accountingDistributionService.distributionToDistributionAccountingLine(distributions);

        assertEquals(new KualiDecimal(150), result.getAmount());
    }

    @Test
    void testDistributionToDistributionAccountingLine_emptyList() {
        List<AccountingDistribution> distributions = new ArrayList();

        TemDistributionAccountingLine result = accountingDistributionService.distributionToDistributionAccountingLine(distributions);

        assertEquals(KualiDecimal.ZERO, result.getAmount());
    }

    @Test
    void testDistributionToSourceAccountingLines_noSelectedDistributions() {
        List<TemDistributionAccountingLine> accountingLines = new ArrayList();
        List<AccountingDistribution> distributions = new ArrayList();
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(false);
        dist.setRemainingAmount(new KualiDecimal(100));
        distributions.add(dist);

        List<TemSourceAccountingLine> result = accountingDistributionService.distributionToSouceAccountingLines(
                accountingLines, distributions, KualiDecimal.ZERO, null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDistributionToSourceAccountingLines_zeroTotal() {
        List<TemDistributionAccountingLine> accountingLines = new ArrayList();
        List<AccountingDistribution> distributions = new ArrayList();
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(true);
        dist.setRemainingAmount(KualiDecimal.ZERO);
        distributions.add(dist);

        List<TemSourceAccountingLine> result = accountingDistributionService.distributionToSouceAccountingLines(
                accountingLines, distributions, KualiDecimal.ZERO, null);

        assertTrue(result.isEmpty());
    }
}
