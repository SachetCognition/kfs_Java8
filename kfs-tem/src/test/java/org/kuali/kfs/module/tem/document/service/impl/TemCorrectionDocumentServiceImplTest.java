package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.web.ui.Column;
import org.mockito.InjectMocks;

class TemCorrectionDocumentServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private TemCorrectionDocumentServiceImpl correctionService;

    @Test
    void testGetTableRenderColumnMetadata_returnsColumns() {
        List<Column> columns = correctionService.getTableRenderColumnMetadata("DOC001");

        assertNotNull(columns);
        assertFalse(columns.isEmpty());
    }

    @Test
    void testGetTableRenderColumnMetadata_containsAgencyCode() {
        List<Column> columns = correctionService.getTableRenderColumnMetadata("DOC001");

        boolean hasAgencyCode = false;
        for (Column col : columns) {
            if ("Agency Code".equals(col.getColumnTitle())) {
                hasAgencyCode = true;
                break;
            }
        }

        assertTrue(hasAgencyCode);
    }

    @Test
    void testGetTableRenderColumnMetadata_containsExpenseAmount() {
        List<Column> columns = correctionService.getTableRenderColumnMetadata("DOC001");

        boolean hasExpenseAmount = false;
        for (Column col : columns) {
            if ("Expense Amount".equals(col.getColumnTitle())) {
                hasExpenseAmount = true;
                break;
            }
        }

        assertTrue(hasExpenseAmount);
    }

    @Test
    void testGetTableRenderColumnMetadata_sameInstanceOnSecondCall() {
        List<Column> first = correctionService.getTableRenderColumnMetadata("DOC001");
        List<Column> second = correctionService.getTableRenderColumnMetadata("DOC002");

        assertSame(first, second);
    }
}
