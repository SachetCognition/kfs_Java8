package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.dataaccess.CloseDao;
import org.kuali.kfs.module.cg.document.ProposalAwardCloseDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.mockito.Mock;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CloseServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CloseDao closeDao;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DocumentService documentService;

    @Mock
    private ConfigurationService configService;

    private CloseServiceImpl closeService;

    @BeforeEach
    void setUp() {
        closeService = new CloseServiceImpl();
        closeService.setCloseDao(closeDao);
        closeService.setDateTimeService(dateTimeService);
        closeService.setBusinessObjectService(businessObjectService);
        closeService.setDocumentService(documentService);
        closeService.setConfigService(configService);
    }

    @Test
    void testCloseReturnsTrue_WhenNoMaxApprovedClose() {
        Date today = Date.valueOf("2024-06-01");
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMaxApprovedClose(today)).thenReturn(null);

        boolean result = closeService.close();

        assertThat(result).isTrue();
    }

    @Test
    void testCloseReturnsTrue_WhenMaxApprovedCloseEmpty() {
        Date today = Date.valueOf("2024-06-01");
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMaxApprovedClose(today)).thenReturn("");

        boolean result = closeService.close();

        assertThat(result).isTrue();
    }

    @Test
    void testGetMostRecentCloseReturnsNull_WhenNoDocumentNumber() {
        Date today = Date.valueOf("2024-06-01");
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMostRecentClose(today)).thenReturn(null);

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertThat(result).isNull();
    }

    @Test
    void testGetMostRecentCloseReturnsNull_WhenEmptyDocumentNumber() {
        Date today = Date.valueOf("2024-06-01");
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMostRecentClose(today)).thenReturn("");

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertThat(result).isNull();
    }

    @Test
    void testGetMostRecentCloseReturnsDocument_WhenDocumentExists() throws Exception {
        Date today = Date.valueOf("2024-06-01");
        ProposalAwardCloseDocument expected = mock(ProposalAwardCloseDocument.class);
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMostRecentClose(today)).thenReturn("DOC001");
        when(documentService.getByDocumentHeaderId("DOC001")).thenReturn(expected);

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetMostRecentCloseThrowsRuntimeException_WhenWorkflowException() throws Exception {
        Date today = Date.valueOf("2024-06-01");
        when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
        when(closeDao.getMostRecentClose(today)).thenReturn("DOC001");
        when(documentService.getByDocumentHeaderId("DOC001")).thenThrow(new WorkflowException("test") {});

        assertThatThrownBy(() -> closeService.getMostRecentClose())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testGetMaxApprovedCloseReturnsNull_WhenNoDocumentNumber() {
        Date today = Date.valueOf("2024-06-01");
        when(closeDao.getMaxApprovedClose(today)).thenReturn(null);

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertThat(result).isNull();
    }

    @Test
    void testGetMaxApprovedCloseReturnsNull_WhenEmptyDocumentNumber() {
        Date today = Date.valueOf("2024-06-01");
        when(closeDao.getMaxApprovedClose(today)).thenReturn("");

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertThat(result).isNull();
    }

    @Test
    void testGetMaxApprovedCloseReturnsDocument_WhenExists() throws Exception {
        Date today = Date.valueOf("2024-06-01");
        ProposalAwardCloseDocument expected = mock(ProposalAwardCloseDocument.class);
        when(closeDao.getMaxApprovedClose(today)).thenReturn("DOC002");
        when(documentService.getByDocumentHeaderId("DOC002")).thenReturn(expected);

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetMaxApprovedCloseThrowsRuntimeException_WhenWorkflowException() throws Exception {
        Date today = Date.valueOf("2024-06-01");
        when(closeDao.getMaxApprovedClose(today)).thenReturn("DOC002");
        when(documentService.getByDocumentHeaderId("DOC002")).thenThrow(new WorkflowException("test") {});

        assertThatThrownBy(() -> closeService.getMaxApprovedClose(today))
                .isInstanceOf(RuntimeException.class);
    }
}
