package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class SubAccountServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private SubAccountServiceImpl subAccountService;

    @Test
    void getByPrimaryId_returnsSubAccount() {
        SubAccount expected = new SubAccount();
        when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any(Map.class))).thenReturn(expected);

        SubAccount result = subAccountService.getByPrimaryId("BL", "1234567", "SUB1");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any(Map.class))).thenReturn(null);

        SubAccount result = subAccountService.getByPrimaryId("XX", "9999999", "ZZZ");
        assertThat(result).isNull();
    }

    @Test
    void getByPrimaryIdWithCaching_delegatesToGetByPrimaryId() {
        SubAccount expected = new SubAccount();
        when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any(Map.class))).thenReturn(expected);

        SubAccount result = subAccountService.getByPrimaryIdWithCaching("BL", "1234567", "SUB1");
        assertThat(result).isSameAs(expected);
    }
}
