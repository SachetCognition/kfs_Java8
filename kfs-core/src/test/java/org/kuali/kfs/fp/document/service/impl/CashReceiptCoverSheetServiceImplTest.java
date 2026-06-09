package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CashReceiptCoverSheetServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private DocumentHelperService documentHelperService;

    @InjectMocks
    private CashReceiptCoverSheetServiceImpl cashReceiptCoverSheetService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(cashReceiptCoverSheetService).isNotNull();
    }
}
