package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.ObjectLevel;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.gl.batch.dataaccess.SufficientFundsDao;
import org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SufficientFundsServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private SufficientFundsServiceImpl sufficientFundsService;

    @Mock
    private SufficientFundsDao sufficientFundsDao;

    @Mock
    private SufficientFundBalancesDao sufficientFundBalancesDao;

    @Test
    void getSufficientFundsObjectCode_noChecking_returnsNA() {
        ObjectCode objectCode = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(objectCode, KFSConstants.SF_TYPE_NO_CHECKING);
        assertThat(result).isEqualTo(KFSConstants.NOT_AVAILABLE_STRING);
    }

    @Test
    void getSufficientFundsObjectCode_accountType_returnsFourSpaces() {
        ObjectCode objectCode = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(objectCode, KFSConstants.SF_TYPE_ACCOUNT);
        assertThat(result).isEqualTo("    ");
    }

    @Test
    void getSufficientFundsObjectCode_cashAtAccountType_returnsFourSpaces() {
        ObjectCode objectCode = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(objectCode, KFSConstants.SF_TYPE_CASH_AT_ACCOUNT);
        assertThat(result).isEqualTo("    ");
    }

    @Test
    void getSufficientFundsObjectCode_objectType_returnsObjectCode() {
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectCode("5100");
        String result = sufficientFundsService.getSufficientFundsObjectCode(objectCode, KFSConstants.SF_TYPE_OBJECT);
        assertThat(result).isEqualTo("5100");
    }

    @Test
    void getSufficientFundsObjectCode_levelType_returnsLevelCode() {
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectLevelCode("TRAV");
        String result = sufficientFundsService.getSufficientFundsObjectCode(objectCode, KFSConstants.SF_TYPE_LEVEL);
        assertThat(result).isEqualTo("TRAV");
    }

    @Test
    void getSufficientFundsObjectCode_invalidType_throwsException() {
        ObjectCode objectCode = new ObjectCode();
        assertThatThrownBy(() ->
            sufficientFundsService.getSufficientFundsObjectCode(objectCode, "INVALID")
        ).isInstanceOf(IllegalArgumentException.class)
         .hasMessageContaining("Invalid Sufficient Funds Code");
    }
}
