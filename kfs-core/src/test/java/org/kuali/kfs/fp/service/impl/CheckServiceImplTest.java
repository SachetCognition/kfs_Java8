package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.CheckBase;
import org.kuali.kfs.fp.dataaccess.CheckDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CheckDao checkDao;

    @InjectMocks
    private CheckServiceImpl checkService;

    @Test
    void getByDocumentHeaderId_delegatesToDao() {
        ArrayList<CheckBase> expected = new ArrayList<CheckBase>();
        when(checkDao.findByDocumentHeaderId("DOC123")).thenReturn(expected);

        Collection<CheckBase> result = checkService.getByDocumentHeaderId("DOC123");
        assertThat(result).isSameAs(expected);
        verify(checkDao).findByDocumentHeaderId("DOC123");
    }
}
