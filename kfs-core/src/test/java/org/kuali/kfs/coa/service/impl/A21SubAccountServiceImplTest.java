package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.A21SubAccount;
import org.kuali.kfs.coa.dataaccess.A21SubAccountDao;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class A21SubAccountServiceImplTest extends KfsUnitTestBase {

    @Mock
    private A21SubAccountDao a21SubAccountDao;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private A21SubAccountServiceImpl a21SubAccountService;

    @Test
    void buildCgIcrAccount_emptyChart_returnsNull() {
        A21SubAccount result = a21SubAccountService.buildCgIcrAccount("", "1234567", "SUB1", "EX");
        assertThat(result).isNull();
    }

    @Test
    void buildCgIcrAccount_emptyAccount_returnsNull() {
        A21SubAccount result = a21SubAccountService.buildCgIcrAccount("BL", "", "SUB1", "EX");
        assertThat(result).isNull();
    }

    @Test
    void buildCgIcrAccount_costShareType_returnsNull() {
        A21SubAccount result = a21SubAccountService.buildCgIcrAccount("BL", "1234567", "SUB1", KFSConstants.SubAccountType.COST_SHARE);
        assertThat(result).isNull();
    }

    @Test
    void buildCgIcrAccount_validParams_returnsPopulatedAccount() {
        A21SubAccount result = a21SubAccountService.buildCgIcrAccount("BL", "1234567", "SUB1", "EX");
        assertThat(result).isNotNull();
        assertThat(result.getSubAccountNumber()).isEqualTo("SUB1");
        assertThat(result.getSubAccountTypeCode()).isEqualTo("EX");
    }
}
