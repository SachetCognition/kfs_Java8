package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.dataaccess.AccountingLineDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AccountingLineServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AccountingLineDao accountingLineDao;

    @InjectMocks
    private AccountingLineServiceImpl accountingLineService;

    @Test
    void getByDocumentHeaderId_delegatesToDao() {
        ArrayList expected = new ArrayList();
        expected.add(new SourceAccountingLine());
        when(accountingLineDao.findByDocumentHeaderId(SourceAccountingLine.class, "DOC123"))
                .thenReturn(expected);

        List result = accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, "DOC123");
        assertThat(result).hasSize(1);
    }

    @Test
    void getByDocumentHeaderId_noResults_returnsEmptyList() {
        when(accountingLineDao.findByDocumentHeaderId(SourceAccountingLine.class, "NONE"))
                .thenReturn(new ArrayList());

        List result = accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, "NONE");
        assertThat(result).isEmpty();
    }

    @Test
    void getByDocumentHeaderIdAndLineType_delegatesToDao() {
        ArrayList expected = new ArrayList();
        expected.add(new SourceAccountingLine());
        when(accountingLineDao.findByDocumentHeaderIdAndLineType(SourceAccountingLine.class, "DOC123", "F"))
                .thenReturn(expected);

        List result = accountingLineService.getByDocumentHeaderIdAndLineType(SourceAccountingLine.class, "DOC123", "F");
        assertThat(result).hasSize(1);
    }
}
