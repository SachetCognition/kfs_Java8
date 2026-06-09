package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.document.dataaccess.CashManagementDao;
import org.kuali.kfs.fp.document.service.CashReceiptService;
import org.kuali.kfs.fp.service.CashDrawerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CashManagementServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DocumentService documentService;
    @Mock
    private CashManagementDao cashManagementDao;
    @Mock
    private CashReceiptService cashReceiptService;
    @Mock
    private CashDrawerService cashDrawerService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private CashManagementServiceImpl cashManagementService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(cashManagementService).isNotNull();
    }
}
