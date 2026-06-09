package org.kuali.kfs.module.purap.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.kuali.kfs.module.purap.service.PurapAccountingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class PurapGeneralLedgerServiceImplTest extends KfsUnitTestBase {

    @Mock private DateTimeService dateTimeService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;
    @Mock private KualiRuleService kualiRuleService;
    @Mock private PurapAccountingService purapAccountingService;
    @Mock private UniversityDateService universityDateService;

    @InjectMocks
    private PurapGeneralLedgerServiceImpl purapGeneralLedgerService;

    @Test
    public void testServiceInstantiation() {
        assertThat(purapGeneralLedgerService).isNotNull();
    }

    @Test
    public void testSetDateTimeService() {
        purapGeneralLedgerService.setDateTimeService(dateTimeService);
        assertThat(purapGeneralLedgerService).isNotNull();
    }

    @Test
    public void testSetBusinessObjectService() {
        purapGeneralLedgerService.setBusinessObjectService(businessObjectService);
        assertThat(purapGeneralLedgerService).isNotNull();
    }
}
