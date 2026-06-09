package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.BalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class BalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BalanceDao balanceDao;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private UniversityDateService universityDateService;
    @Mock
    private OptionsService optionsService;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        balanceService.purgeYearByChart("BL", 2024);
        verify(balanceDao).purgeYearByChart("BL", 2024);
    }
}
