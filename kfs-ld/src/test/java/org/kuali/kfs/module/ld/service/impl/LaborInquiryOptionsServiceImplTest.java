package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.Constant;
import org.kuali.kfs.module.ld.service.LaborLedgerBalanceService;
import org.kuali.kfs.module.ld.service.LaborLedgerPendingEntryService;
import org.kuali.kfs.sys.KFSConstants.ParameterValues;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LaborInquiryOptionsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerPendingEntryService laborLedgerPendingEntryService;

    @Mock
    private LaborLedgerBalanceService laborLedgerBalanceService;

    @InjectMocks
    private LaborInquiryOptionsServiceImpl service;

    @Test
    void testGetConsolidationFieldNameReturnsConsolidationOption() {
        assertThat(service.getConsolidationFieldName()).isEqualTo(Constant.CONSOLIDATION_OPTION);
    }

    @Test
    void testGetSelectedPendingEntryOptionRemovesAndReturnsValue() {
        Map fieldValues = new HashMap();
        fieldValues.put(Constant.PENDING_ENTRY_OPTION, "Approved");
        fieldValues.put("otherField", "value");

        String result = service.getSelectedPendingEntryOption(fieldValues);

        assertThat(result).isEqualTo("Approved");
        assertThat(fieldValues).doesNotContainKey(Constant.PENDING_ENTRY_OPTION);
        assertThat(fieldValues).containsKey("otherField");
    }

    @Test
    void testGetSelectedPendingEntryOptionReturnsNullIfNotPresent() {
        Map fieldValues = new HashMap();

        String result = service.getSelectedPendingEntryOption(fieldValues);

        assertThat(result).isNull();
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcludedReturnsTrueForYes() {
        Map fieldValues = new HashMap();
        fieldValues.put(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION, ParameterValues.YES);

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);

        assertThat(result).isTrue();
        assertThat(fieldValues).doesNotContainKey(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION);
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcludedReturnsFalseForNo() {
        Map fieldValues = new HashMap();
        fieldValues.put(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION, ParameterValues.NO);

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);

        assertThat(result).isFalse();
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcludedReturnsFalseWhenMissing() {
        Map fieldValues = new HashMap();

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);

        assertThat(result).isFalse();
    }

    @Test
    void testGetConsolidationOptionRemovesAndReturnsValue() {
        Map fieldValues = new HashMap();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, "Consolidation");

        String result = service.getConsolidationOption(fieldValues);

        assertThat(result).isEqualTo("Consolidation");
        assertThat(fieldValues).doesNotContainKey(Constant.CONSOLIDATION_OPTION);
    }
}
