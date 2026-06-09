package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.FiscalYearFunctionControl;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class FiscalYearFunctionControlServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private FiscalYearFunctionControlServiceImpl fiscalYearFunctionControlService;

    @Test
    void isBaseAmountChangeAllowed_controlNotFound_returnsFalse() {
        HashMap<String, Object> keys = new HashMap<String, Object>();
        keys.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, Integer.valueOf(2024));
        keys.put(KFSPropertyConstants.FINANCIAL_SYSTEM_FUNCTION_CONTROL_CODE, "BASEAD");
        when(businessObjectService.findByPrimaryKey(FiscalYearFunctionControl.class, keys))
                .thenReturn(null);

        boolean result = fiscalYearFunctionControlService.isBaseAmountChangeAllowed(Integer.valueOf(2024));
        assertThat(result).isFalse();
    }

    @Test
    void isBaseAmountChangeAllowed_controlActiveTrue_returnsTrue() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(true);
        HashMap<String, Object> keys = new HashMap<String, Object>();
        keys.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, Integer.valueOf(2024));
        keys.put(KFSPropertyConstants.FINANCIAL_SYSTEM_FUNCTION_CONTROL_CODE, "BASEAD");
        when(businessObjectService.findByPrimaryKey(FiscalYearFunctionControl.class, keys))
                .thenReturn(control);

        boolean result = fiscalYearFunctionControlService.isBaseAmountChangeAllowed(Integer.valueOf(2024));
        assertThat(result).isTrue();
    }

    @Test
    void isBaseAmountChangeAllowed_controlActiveFalse_returnsFalse() {
        FiscalYearFunctionControl control = new FiscalYearFunctionControl();
        control.setFinancialSystemFunctionActiveIndicator(false);
        HashMap<String, Object> keys = new HashMap<String, Object>();
        keys.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, Integer.valueOf(2024));
        keys.put(KFSPropertyConstants.FINANCIAL_SYSTEM_FUNCTION_CONTROL_CODE, "BASEAD");
        when(businessObjectService.findByPrimaryKey(FiscalYearFunctionControl.class, keys))
                .thenReturn(control);

        boolean result = fiscalYearFunctionControlService.isBaseAmountChangeAllowed(Integer.valueOf(2024));
        assertThat(result).isFalse();
    }
}
