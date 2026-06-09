package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.dataaccess.AccountDelegateDao;
import org.kuali.kfs.coa.dataaccess.AccountDelegateGlobalDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class AccountDelegateServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AccountDelegateDao accountDelegateDao;
    @Mock
    private AccountDelegateGlobalDao accountDelegateGlobalDao;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private AccountDelegateServiceImpl accountDelegateService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(accountDelegateService).isNotNull();
    }
}
