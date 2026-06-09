package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.BalanceTypeService;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.coa.service.ObjectTypeService;
import org.kuali.kfs.coa.service.OffsetDefinitionService;
import org.kuali.kfs.gl.service.SufficientFundsService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.dataaccess.GeneralLedgerPendingEntryDao;
import org.kuali.kfs.sys.service.FlexibleOffsetAccountService;
import org.kuali.kfs.sys.service.HomeOriginationService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralLedgerPendingEntryServiceImplTest extends KfsUnitTestBase {

    @Mock
    private GeneralLedgerPendingEntryDao generalLedgerPendingEntryDao;
    @Mock
    private ChartService chartService;
    @Mock
    private ObjectCodeService objectCodeService;
    @Mock
    private ObjectTypeService objectTypeService;
    @Mock
    private AccountService accountService;
    @Mock
    private BalanceTypeService balanceTypeService;
    @Mock
    private OffsetDefinitionService offsetDefinitionService;
    @Mock
    private OptionsService optionsService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private UniversityDateService universityDateService;
    @Mock
    private FlexibleOffsetAccountService flexibleOffsetAccountService;
    @Mock
    private HomeOriginationService homeOriginationService;
    @Mock
    private SufficientFundsService sufficientFundsService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private PersistenceStructureService persistenceStructureService;
    @Mock
    private KualiRuleService kualiRuleService;

    @InjectMocks
    private GeneralLedgerPendingEntryServiceImpl generalLedgerPendingEntryService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(generalLedgerPendingEntryService).isNotNull();
    }
}
