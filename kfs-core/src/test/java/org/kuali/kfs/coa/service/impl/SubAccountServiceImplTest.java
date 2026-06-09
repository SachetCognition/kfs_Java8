package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class SubAccountServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private SubAccountServiceImpl subAccountService;

    @Nested
    @DisplayName("getByPrimaryId")
    class GetByPrimaryIdTests {

        @Test
        void returnsSubAccountWhenFound() {
            SubAccount expected = new SubAccount();
            expected.setChartOfAccountsCode("BL");
            expected.setAccountNumber("1234567");
            expected.setSubAccountNumber("001");
            when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any())).thenReturn(expected);

            SubAccount result = subAccountService.getByPrimaryId("BL", "1234567", "001");

            assertThat(result).isNotNull();
            assertThat(result.getSubAccountNumber()).isEqualTo("001");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any())).thenReturn(null);

            SubAccount result = subAccountService.getByPrimaryId("XX", "0000000", "999");

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("getByPrimaryIdWithCaching")
    class GetByPrimaryIdWithCachingTests {

        @Test
        void delegatesToGetByPrimaryId() {
            SubAccount expected = new SubAccount();
            expected.setSubAccountNumber("002");
            when(businessObjectService.findByPrimaryKey(eq(SubAccount.class), any())).thenReturn(expected);

            SubAccount result = subAccountService.getByPrimaryIdWithCaching("BL", "1234567", "002");

            assertThat(result).isNotNull();
            assertThat(result.getSubAccountNumber()).isEqualTo("002");
        }
    }
}
