package org.kuali.kfs.coa.batch;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.PriorYearAccountService;
import org.kuali.kfs.coa.service.PriorYearOrganizationService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdatePriorYearDataStepTest extends KfsUnitTestBase {

    @Mock
    private PriorYearAccountService priorYearAccountService;

    @Mock
    private PriorYearOrganizationService priorYearOrganizationService;

    @InjectMocks
    private UpdatePriorYearDataStep step;

    @BeforeEach
    public void setUp() {
        step.setPriorYearAccountService(priorYearAccountService);
        step.setPriorYearOrganizationService(priorYearOrganizationService);
    }

    @Test
    public void testExecuteSuccess() throws Exception {
        boolean result = step.execute("testJob", new Date());
        assertTrue(result);
        verify(priorYearAccountService).populatePriorYearAccountsFromCurrent();
        verify(priorYearOrganizationService).populatePriorYearOrganizationsFromCurrent();
    }

    @Test
    public void testExecuteServiceInteraction() throws Exception {
        step.execute("testJob", new Date());
        verify(priorYearAccountService, atLeastOnce()).populatePriorYearAccountsFromCurrent();
    }

}
