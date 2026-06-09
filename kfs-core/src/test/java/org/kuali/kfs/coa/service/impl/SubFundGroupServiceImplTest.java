package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.FundGroup;
import org.kuali.kfs.coa.businessobject.SubFundGroup;
import org.kuali.kfs.coa.dataaccess.SubFundGroupDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class SubFundGroupServiceImplTest extends KfsUnitTestBase {

    @Mock private ParameterService parameterService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private SubFundGroupDao subFundGroupDao;

    @InjectMocks
    private SubFundGroupServiceImpl subFundGroupService;

    @Nested
    @DisplayName("isForContractsAndGrants")
    class IsForCGTests {

        @Test
        void returnsFalseWhenSubFundGroupIsNull() {
            assertThat(subFundGroupService.isForContractsAndGrants(null)).isFalse();
        }
    }

    @Nested
    @DisplayName("getContractsAndGrantsDenotingAttributeLabel")
    class DenotingLabelTests {

        @Test
        void returnsFundGroupLabelWhenFundGroupDenotesCG() {
            when(parameterService.getParameterValueAsBoolean(
                Account.class, KFSConstants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG))
                .thenReturn(true);
            when(dataDictionaryService.getAttributeLabel(
                eq(FundGroup.class), eq(KFSConstants.FUND_GROUP_CODE_PROPERTY_NAME)))
                .thenReturn("Fund Group Code");

            String label = subFundGroupService.getContractsAndGrantsDenotingAttributeLabel();

            assertThat(label).isEqualTo("Fund Group Code");
        }

        @Test
        void returnsSubFundGroupLabelWhenSubFundGroupDenotesCG() {
            when(parameterService.getParameterValueAsBoolean(
                Account.class, KFSConstants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG))
                .thenReturn(false);
            when(dataDictionaryService.getAttributeLabel(
                eq(SubFundGroup.class), eq(KFSConstants.SUB_FUND_GROUP_CODE_PROPERTY_NAME)))
                .thenReturn("Sub-Fund Group Code");

            String label = subFundGroupService.getContractsAndGrantsDenotingAttributeLabel();

            assertThat(label).isEqualTo("Sub-Fund Group Code");
        }
    }

    @Nested
    @DisplayName("getContractsAndGrantsDenotingValue")
    class DenotingValueTests {

        @Test
        void returnsFundGroupCodeWhenFundGroupDenotesCG() {
            when(parameterService.getParameterValueAsBoolean(
                Account.class, KFSConstants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG))
                .thenReturn(true);
            SubFundGroup sfg = new SubFundGroup();
            sfg.setFundGroupCode("CG");
            sfg.setSubFundGroupCode("HIEDUA");

            String value = subFundGroupService.getContractsAndGrantsDenotingValue(sfg);

            assertThat(value).isEqualTo("CG");
        }

        @Test
        void returnsSubFundGroupCodeWhenSubFundGroupDenotesCG() {
            when(parameterService.getParameterValueAsBoolean(
                Account.class, KFSConstants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG))
                .thenReturn(false);
            SubFundGroup sfg = new SubFundGroup();
            sfg.setFundGroupCode("CG");
            sfg.setSubFundGroupCode("HIEDUA");

            String value = subFundGroupService.getContractsAndGrantsDenotingValue(sfg);

            assertThat(value).isEqualTo("HIEDUA");
        }
    }

    @Nested
    @DisplayName("getContractsAndGrantsDenotingValues")
    class DenotingValuesTests {

        @Test
        void returnsParameterValues() {
            when(parameterService.getParameterValuesAsString(
                Account.class, KFSConstants.ChartApcParms.ACCOUNT_CG_DENOTING_VALUE))
                .thenReturn(Arrays.asList("CG", "GF"));

            Collection<String> values = subFundGroupService.getContractsAndGrantsDenotingValues();

            assertThat(values).containsExactly("CG", "GF");
        }
    }

    @Nested
    @DisplayName("getByChartAndAccount")
    class GetByChartAndAccountTests {

        @Test
        void delegatesToDao() {
            SubFundGroup expected = new SubFundGroup();
            expected.setSubFundGroupCode("HIEDUA");
            when(subFundGroupDao.getByChartAndAccount("BL", "1234567")).thenReturn(expected);

            SubFundGroup result = subFundGroupService.getByChartAndAccount("BL", "1234567");

            assertThat(result.getSubFundGroupCode()).isEqualTo("HIEDUA");
        }
    }
}
