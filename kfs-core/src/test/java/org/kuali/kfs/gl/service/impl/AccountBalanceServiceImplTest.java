package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.AccountBalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

class AccountBalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AccountBalanceDao accountBalanceDao;

    @InjectMocks
    private AccountBalanceServiceImpl accountBalanceService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        accountBalanceService.purgeYearByChart("BL", 2022);
        verify(accountBalanceDao).purgeYearByChart("BL", 2022);
    }
}
