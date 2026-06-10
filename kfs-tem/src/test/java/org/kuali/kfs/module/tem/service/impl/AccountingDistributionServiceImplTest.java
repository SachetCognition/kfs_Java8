package org.kuali.kfs.module.tem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AccountingDistributionServiceImpl")
class AccountingDistributionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ObjectCodeService objectCodeService;

    @Mock
    private TravelDocumentService travelDocumentService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private AccountingDistributionServiceImpl accountingDistributionService;

    private List<TemDistributionAccountingLine> distributionLines;
    private List<AccountingDistribution> distributionList;

    @BeforeEach
    void setUp() {
        distributionLines = new ArrayList<>();
        distributionList = new ArrayList<>();
    }

    @Test
    @DisplayName("should return empty list when no distributions selected")
    void testDistributionToSourceLinesNoSelection() {
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(Boolean.FALSE);
        dist.setRemainingAmount(new KualiDecimal(100));
        distributionList.add(dist);

        TemDistributionAccountingLine line = new TemDistributionAccountingLine();
        line.setAmount(new KualiDecimal(100));
        distributionLines.add(line);

        List<TemSourceAccountingLine> result = accountingDistributionService
                .distributionToSouceAccountingLines(distributionLines, distributionList,
                        KualiDecimal.ZERO, null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return empty list when total is zero")
    void testDistributionToSourceLinesZeroTotal() {
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(Boolean.TRUE);
        dist.setRemainingAmount(KualiDecimal.ZERO);
        distributionList.add(dist);

        TemDistributionAccountingLine line = new TemDistributionAccountingLine();
        line.setAmount(new KualiDecimal(100));
        distributionLines.add(line);

        List<TemSourceAccountingLine> result = accountingDistributionService
                .distributionToSouceAccountingLines(distributionLines, distributionList,
                        KualiDecimal.ZERO, null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return empty when all distributions have zero remaining")
    void testDistributionToSourceLinesZeroRemainingAmounts() {
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(Boolean.TRUE);
        dist.setRemainingAmount(KualiDecimal.ZERO);
        distributionList.add(dist);

        TemDistributionAccountingLine line = new TemDistributionAccountingLine();
        line.setAmount(new KualiDecimal(100));
        distributionLines.add(line);

        List<TemSourceAccountingLine> result = accountingDistributionService
                .distributionToSouceAccountingLines(distributionLines, distributionList,
                        KualiDecimal.ZERO, null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should handle empty distribution list")
    void testDistributionToSourceLinesEmptyDistributionList() {
        TemDistributionAccountingLine line = new TemDistributionAccountingLine();
        line.setAmount(new KualiDecimal(100));
        distributionLines.add(line);

        List<TemSourceAccountingLine> result = accountingDistributionService
                .distributionToSouceAccountingLines(distributionLines, distributionList,
                        KualiDecimal.ZERO, null);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should not distribute when no selected distributions")
    void testDistributionWithNoSelectedDistributions() {
        AccountingDistribution dist = new AccountingDistribution();
        dist.setSelected(Boolean.FALSE);
        dist.setRemainingAmount(new KualiDecimal(500));
        dist.setObjectCode("5000");
        dist.setCardType("PERSONAL");
        distributionList.add(dist);

        TemDistributionAccountingLine line = new TemDistributionAccountingLine();
        line.setAmount(new KualiDecimal(200));
        line.setChartOfAccountsCode("BL");
        line.setFinancialObjectCode("5000");
        distributionLines.add(line);

        List<TemSourceAccountingLine> result = accountingDistributionService
                .distributionToSouceAccountingLines(distributionLines, distributionList,
                        KualiDecimal.ZERO, new KualiDecimal(300));

        assertThat(result).isEmpty();
    }
}
