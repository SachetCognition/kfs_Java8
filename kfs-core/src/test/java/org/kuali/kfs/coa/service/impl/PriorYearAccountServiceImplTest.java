package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.PriorYearAccount;
import org.kuali.kfs.coa.dataaccess.PriorYearAccountDao;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PriorYearAccountServiceImplTest extends KfsUnitTestBase {

    @Mock private PriorYearAccountDao priorYearAccountDao;
    @Mock private AccountService accountService;
    @Mock private PersistenceStructureService persistenceStructureService;
    @Mock private PersistenceService persistenceServiceOjb;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private PriorYearAccountServiceImpl priorYearAccountService;

    @Nested
    @DisplayName("getByPrimaryKey")
    class GetByPrimaryKeyTests {

        @Test
        void returnsAccountWhenFound() {
            PriorYearAccount expected = new PriorYearAccount();
            expected.setChartOfAccountsCode("BL");
            expected.setAccountNumber("1234567");
            when(businessObjectService.findByPrimaryKey(eq(PriorYearAccount.class), any()))
                .thenReturn(expected);

            PriorYearAccount result = priorYearAccountService.getByPrimaryKey("BL", "1234567");

            assertThat(result).isNotNull();
            assertThat(result.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(PriorYearAccount.class), any()))
                .thenReturn(null);

            assertThat(priorYearAccountService.getByPrimaryKey("XX", "0000000")).isNull();
        }
    }
}
