package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.CheckBase;
import org.kuali.kfs.fp.dataaccess.CheckDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CheckDao checkDao;

    @InjectMocks
    private CheckServiceImpl checkService;

    @Test
    void getByDocumentHeaderIdDelegatesToCheckDao() {
        List<CheckBase> expected = new ArrayList<>();
        expected.add(new CheckBase());
        when(checkDao.findByDocumentHeaderId("DOC-001")).thenReturn(expected);

        Collection<CheckBase> result = checkService.getByDocumentHeaderId("DOC-001");

        assertThat(result).hasSize(1);
        verify(checkDao).findByDocumentHeaderId("DOC-001");
    }

    @Test
    void getByDocumentHeaderIdReturnsEmptyCollectionWhenNoneFound() {
        when(checkDao.findByDocumentHeaderId("DOC-NONE")).thenReturn(new ArrayList<>());

        Collection<CheckBase> result = checkService.getByDocumentHeaderId("DOC-NONE");

        assertThat(result).isEmpty();
    }

    @Test
    void setAndGetCheckDao() {
        CheckDao newDao = org.mockito.Mockito.mock(CheckDao.class);
        checkService.setCheckDao(newDao);
        assertThat(checkService.getCheckDao()).isSameAs(newDao);
    }
}
