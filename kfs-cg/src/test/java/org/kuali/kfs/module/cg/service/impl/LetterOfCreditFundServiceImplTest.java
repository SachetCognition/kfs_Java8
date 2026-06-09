package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.CGPropertyConstants;
import org.kuali.kfs.module.cg.businessobject.LetterOfCreditFund;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LetterOfCreditFundServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    private LetterOfCreditFundServiceImpl letterOfCreditFundService;

    @BeforeEach
    void setUp() {
        letterOfCreditFundService = new LetterOfCreditFundServiceImpl();
        letterOfCreditFundService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetByPrimaryIdReturnsLetterOfCreditFund() {
        String fundCode = "LOC01";
        LetterOfCreditFund expected = new LetterOfCreditFund();
        expected.setLetterOfCreditFundCode(fundCode);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(CGPropertyConstants.LETTER_OF_CREDIT_FUND_CODE, fundCode);

        when(businessObjectService.findByPrimaryKey(eq(LetterOfCreditFund.class), eq(primaryKeys)))
                .thenReturn(expected);

        LetterOfCreditFund result = letterOfCreditFundService.getByPrimaryId(fundCode);

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(LetterOfCreditFund.class), eq(primaryKeys));
    }

    @Test
    void testGetByPrimaryIdReturnsNullWhenNotFound() {
        String fundCode = "NOTFOUND";

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(CGPropertyConstants.LETTER_OF_CREDIT_FUND_CODE, fundCode);

        when(businessObjectService.findByPrimaryKey(eq(LetterOfCreditFund.class), eq(primaryKeys)))
                .thenReturn(null);

        LetterOfCreditFund result = letterOfCreditFundService.getByPrimaryId(fundCode);

        assertThat(result).isNull();
    }

    @Test
    void testGetBusinessObjectService() {
        assertThat(letterOfCreditFundService.getBusinessObjectService()).isSameAs(businessObjectService);
    }
}
