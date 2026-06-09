package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.SubFundGroup;
import org.kuali.kfs.coa.dataaccess.SubFundGroupDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubFundGroupServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private SubFundGroupDao subFundGroupDao;

    @InjectMocks
    private SubFundGroupServiceImpl subFundGroupService;

    @Test
    void isForContractsAndGrants_nullSubFundGroup_returnsFalse() {
        assertThat(subFundGroupService.isForContractsAndGrants(null)).isFalse();
    }

    @Test
    void getByChartAndAccount_delegatesToDao() {
        SubFundGroup expected = new SubFundGroup();
        when(subFundGroupDao.getByChartAndAccount("BL", "1234567")).thenReturn(expected);

        SubFundGroup result = subFundGroupService.getByChartAndAccount("BL", "1234567");
        assertThat(result).isSameAs(expected);
        verify(subFundGroupDao).getByChartAndAccount("BL", "1234567");
    }

    @Test
    void getContractsAndGrantsDenotingValue_withSubFundGroup_returnsSubFundGroupCode() {
        SubFundGroup sfg = new SubFundGroup();
        sfg.setSubFundGroupCode("HIEDUA");
        sfg.setFundGroupCode("GF");

        when(parameterService.getParameterValueAsBoolean(
                org.kuali.kfs.coa.businessobject.Account.class,
                org.kuali.kfs.sys.KFSConstants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG))
                .thenReturn(false);

        String result = subFundGroupService.getContractsAndGrantsDenotingValue(sfg);
        assertThat(result).isEqualTo("HIEDUA");
    }
}
