package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ObjectLevelService;
import org.kuali.kfs.gl.batch.dataaccess.SufficientFundsDao;
import org.kuali.kfs.gl.dataaccess.SufficientFundBalancesDao;
import org.kuali.kfs.gl.service.SufficientFundsServiceConstants;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class SufficientFundsServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountService accountService;
    @Mock private ObjectLevelService objectLevelService;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private SufficientFundsDao sufficientFundsDao;
    @Mock private SufficientFundBalancesDao sufficientFundBalancesDao;
    @Mock private OptionsService optionsService;
    @Mock private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;
    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private SufficientFundsServiceImpl sufficientFundsService;

    @Test
    void getSufficientFundsObjectCode_noChecking_returnsNA() {
        ObjectCode oc = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(oc, KFSConstants.SF_TYPE_NO_CHECKING);
        assertThat(result).isEqualTo(KFSConstants.NOT_AVAILABLE_STRING);
    }

    @Test
    void getSufficientFundsObjectCode_account_returnsFourSpaces() {
        ObjectCode oc = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(oc, KFSConstants.SF_TYPE_ACCOUNT);
        assertThat(result).isEqualTo("    ");
    }

    @Test
    void getSufficientFundsObjectCode_cashAtAccount_returnsFourSpaces() {
        ObjectCode oc = new ObjectCode();
        String result = sufficientFundsService.getSufficientFundsObjectCode(oc, KFSConstants.SF_TYPE_CASH_AT_ACCOUNT);
        assertThat(result).isEqualTo("    ");
    }

    @Test
    void getSufficientFundsObjectCode_object_returnsObjectCode() {
        ObjectCode oc = new ObjectCode();
        oc.setFinancialObjectCode("5000");
        String result = sufficientFundsService.getSufficientFundsObjectCode(oc, KFSConstants.SF_TYPE_OBJECT);
        assertThat(result).isEqualTo("5000");
    }

    @Test
    void getSufficientFundsObjectCode_level_returnsLevelCode() {
        ObjectCode oc = new ObjectCode();
        oc.setFinancialObjectLevelCode("TRIN");
        String result = sufficientFundsService.getSufficientFundsObjectCode(oc, KFSConstants.SF_TYPE_LEVEL);
        assertThat(result).isEqualTo("TRIN");
    }

    @Test
    void getSufficientFundsObjectCode_invalidCode_throwsIllegalArgument() {
        ObjectCode oc = new ObjectCode();
        boolean thrown = false;
        try {
            sufficientFundsService.getSufficientFundsObjectCode(oc, "ZZ");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertThat(thrown).isTrue();
    }

    @Test
    void isYearEndDocument_withNonYearEndClass_returnsFalse() {
        assertThat(sufficientFundsService.isYearEndDocument(Object.class)).isFalse();
    }
}
