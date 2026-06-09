package org.kuali.kfs.module.ld.document.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ld.LaborLedgerExpenseTransferAccountingLine;
import org.kuali.kfs.sys.businessobject.UniversityDate;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalaryExpenseTransferTransactionAgeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private UniversityDateService universityDateService;

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private SalaryExpenseTransferTransactionAgeServiceImpl service;

    @BeforeEach
    void setUpStaticFields() throws Exception {
        // Set static fields via reflection since they are static in the impl
        Field udsField = SalaryExpenseTransferTransactionAgeServiceImpl.class.getDeclaredField("universityDateService");
        udsField.setAccessible(true);
        udsField.set(null, universityDateService);

        Field psField = SalaryExpenseTransferTransactionAgeServiceImpl.class.getDeclaredField("parameterService");
        psField.setAccessible(true);
        psField.set(null, parameterService);
    }

    @Test
    void testDefaultNumberOfFiscalPeriodsCheck_emptyList() {
        UniversityDate currDate = new UniversityDate();
        currDate.setUniversityFiscalYear(2014);
        currDate.setUniversityFiscalAccountingPeriod("06");
        when(universityDateService.getCurrentUniversityDate()).thenReturn(currDate);

        List<LaborLedgerExpenseTransferAccountingLine> lines = new ArrayList<>();
        boolean result = service.defaultNumberOfFiscalPeriodsCheck(lines, 2);
        assertTrue(result);
    }

    @Test
    void testDefaultNumberOfFiscalPeriodsCheck_nullPeriodsParam() {
        UniversityDate currDate = new UniversityDate();
        currDate.setUniversityFiscalYear(2014);
        currDate.setUniversityFiscalAccountingPeriod("06");
        when(universityDateService.getCurrentUniversityDate()).thenReturn(currDate);

        List<LaborLedgerExpenseTransferAccountingLine> lines = new ArrayList<>();
        boolean result = service.defaultNumberOfFiscalPeriodsCheck(lines, null);
        assertTrue(result);
    }
}
