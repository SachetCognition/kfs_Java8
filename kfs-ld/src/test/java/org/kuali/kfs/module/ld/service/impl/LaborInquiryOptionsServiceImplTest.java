package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.Constant;
import org.kuali.kfs.module.ld.service.LaborLedgerBalanceService;
import org.kuali.kfs.module.ld.service.LaborLedgerPendingEntryService;
import org.kuali.kfs.sys.KFSConstants.ParameterValues;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LaborInquiryOptionsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborLedgerPendingEntryService laborLedgerPendingEntryService;

    @Mock
    private LaborLedgerBalanceService laborLedgerBalanceService;

    @InjectMocks
    private LaborInquiryOptionsServiceImpl service;

    @Test
    void testGetConsolidationFieldName() {
        assertEquals(Constant.CONSOLIDATION_OPTION, service.getConsolidationFieldName());
    }

    @Test
    void testGetConsolidationField_found() {
        Field field = new Field();
        field.setPropertyName(Constant.CONSOLIDATION_OPTION);
        Row row = new Row();
        row.setFields(Collections.singletonList(field));
        Collection<Row> rows = Collections.singletonList(row);

        Field result = service.getConsolidationField(rows);
        assertSame(field, result);
    }

    @Test
    void testGetConsolidationField_notFound() {
        Field field = new Field();
        field.setPropertyName("otherField");
        Row row = new Row();
        row.setFields(Collections.singletonList(field));
        Collection<Row> rows = Collections.singletonList(row);

        Field result = service.getConsolidationField(rows);
        assertNull(result);
    }

    @Test
    void testGetSelectedPendingEntryOption() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.PENDING_ENTRY_OPTION, "All");

        String result = service.getSelectedPendingEntryOption(fieldValues);
        assertEquals("All", result);
        assertFalse(fieldValues.containsKey(Constant.PENDING_ENTRY_OPTION));
    }

    @Test
    void testGetSelectedPendingEntryOption_null() {
        Map<String, String> fieldValues = new HashMap<>();

        String result = service.getSelectedPendingEntryOption(fieldValues);
        assertNull(result);
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcluded_yes() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION, ParameterValues.YES);

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);
        assertTrue(result);
        assertFalse(fieldValues.containsKey(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION));
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcluded_no() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.EXCLUDE_CG_BEGINNING_BALANCE_ONLY_OPTION, "N");

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);
        assertFalse(result);
    }

    @Test
    void testIsCgBeginningBalanceOnlyExcluded_null() {
        Map<String, String> fieldValues = new HashMap<>();

        boolean result = service.isCgBeginningBalanceOnlyExcluded(fieldValues);
        assertFalse(result);
    }

    @Test
    void testGetConsolidationOption() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.CONSOLIDATION);

        String result = service.getConsolidationOption(fieldValues);
        assertEquals(Constant.CONSOLIDATION, result);
        assertFalse(fieldValues.containsKey(Constant.CONSOLIDATION_OPTION));
    }

    @Test
    void testIsConsolidationSelected_detail() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.DETAIL);

        boolean result = service.isConsolidationSelected(fieldValues);
        assertFalse(result);
    }

    @Test
    void testIsConsolidationSelected_consolidation() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.CONSOLIDATION);

        boolean result = service.isConsolidationSelected(fieldValues);
        assertTrue(result);
    }

    @Test
    void testIsConsolidationSelected_withSubAccountNumber() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.CONSOLIDATION);
        fieldValues.put(KFSPropertyConstants.SUB_ACCOUNT_NUMBER, "12345");

        boolean result = service.isConsolidationSelected(fieldValues);
        assertFalse(result);
    }

    @Test
    void testIsConsolidationSelected_withSubObjectCode() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.CONSOLIDATION);
        fieldValues.put(KFSPropertyConstants.SUB_OBJECT_CODE, "A01");

        boolean result = service.isConsolidationSelected(fieldValues);
        assertFalse(result);
    }

    @Test
    void testIsConsolidationSelected_withObjectTypeCode() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(Constant.CONSOLIDATION_OPTION, Constant.CONSOLIDATION);
        fieldValues.put(KFSPropertyConstants.OBJECT_TYPE_CODE, "EX");

        boolean result = service.isConsolidationSelected(fieldValues);
        assertFalse(result);
    }
}
