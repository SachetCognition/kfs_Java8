package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.PriorYearAccount;
import org.kuali.kfs.coa.dataaccess.PriorYearAccountDao;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.ReportWriterService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.PersistenceService;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PriorYearAccountServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PriorYearAccountDao priorYearAccountDao;
    @Mock
    private AccountService accountService;
    @Mock
    private ReportWriterService reportWriterService;
    @Mock
    private PersistenceStructureService persistenceStructureService;
    @Mock
    private PersistenceService persistenceServiceOjb;
    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private PriorYearAccountServiceImpl priorYearAccountService;

    @Test
    void getByPrimaryKey_returnsAccount() {
        PriorYearAccount expected = new PriorYearAccount();
        when(businessObjectService.findByPrimaryKey(eq(PriorYearAccount.class), any(Map.class))).thenReturn(expected);

        PriorYearAccount result = priorYearAccountService.getByPrimaryKey("BL", "1234567");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryKey_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(PriorYearAccount.class), any(Map.class))).thenReturn(null);

        PriorYearAccount result = priorYearAccountService.getByPrimaryKey("XX", "9999999");
        assertThat(result).isNull();
    }
}
