package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.gl.batch.dataaccess.SufficientFundsDao;
import org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class SufficientFundsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private SufficientFundsDao sufficientFundsDao;
    @Mock
    private SufficientFundBalancesDao sufficientFundBalancesDao;
    @Mock
    private AccountService accountService;
    @Mock
    private OptionsService optionsService;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ConfigurationService kualiConfigurationService;

    @InjectMocks
    private SufficientFundsServiceImpl sufficientFundsService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        sufficientFundsService.purgeYearByChart("BL", 2024);
        verify(sufficientFundsDao).purgeYearByChart("BL", 2024);
    }

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(sufficientFundsService).isNotNull();
    }
}
