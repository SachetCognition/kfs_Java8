package org.kuali.kfs.sys.util;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class KfsWebUtilsTest extends KfsUnitTestBase {

    @Mock
    private HttpServletResponse response;

    @Test
    void saveMimeOutputStreamAsFile_attachment_setsContentType() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("hello".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "application/pdf", baos, "report.pdf", false);

        verify(response).setContentType("application/pdf");
    }

    @Test
    void saveMimeOutputStreamAsFile_attachment_setsContentLength() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("test content".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "file.txt", false);

        verify(response).setContentLength("test content".length());
    }

    @Test
    void saveMimeOutputStreamAsFile_attachment_writesContent() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = "hello world".getBytes();
        baos.write(data);

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "out.txt", false);

        assertThat(testOut.toByteArray()).isEqualTo(data);
    }

    @Test
    void saveMimeOutputStreamAsFile_attachment_setsDispositionHeader() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("x".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "report.txt", false);

        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).setHeader(eq(KFSConstants.HttpHeaderResponse.CONTENT_DIPOSITION), headerCaptor.capture());
        assertThat(headerCaptor.getValue()).contains("attachment");
        assertThat(headerCaptor.getValue()).contains("report.txt");
    }

    @Test
    void saveMimeOutputStreamAsFile_inline_setsDisposition() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("x".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "report.txt", true);

        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).setHeader(eq(KFSConstants.HttpHeaderResponse.CONTENT_DIPOSITION), headerCaptor.capture());
        assertThat(headerCaptor.getValue()).contains("inline");
    }

    @Test
    void saveMimeOutputStreamAsFile_stripsQuotesFromFileName() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("x".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "\"quoted.txt\"", false);

        ArgumentCaptor<String> headerCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).setHeader(eq(KFSConstants.HttpHeaderResponse.CONTENT_DIPOSITION), headerCaptor.capture());
        assertThat(headerCaptor.getValue()).contains("quoted.txt");
        assertThat(headerCaptor.getValue()).doesNotContain("\"\"");
    }

    @Test
    void saveMimeOutputStreamAsFile_setsExpiresHeader() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("x".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "f.txt", false);

        verify(response).setHeader(KFSConstants.HttpHeaderResponse.EXPIRES, KFSConstants.ZERO);
    }

    @Test
    void saveMimeOutputStreamAsFile_setCacheControlHeader() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("x".getBytes());

        TestServletOutputStream testOut = new TestServletOutputStream();
        when(response.getOutputStream()).thenReturn(testOut);

        KfsWebUtils.saveMimeOutputStreamAsFile(response, "text/plain", baos, "f.txt", false);

        verify(response).setHeader(KFSConstants.HttpHeaderResponse.CACHE_CONTROL,
                KFSConstants.HttpHeaderResponse.CACHE_CONTROL_REVALIDATE_PRE_POST_CHECK_ZERO);
    }

    private static class TestServletOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream delegate = new ByteArrayOutputStream();

        @Override
        public void write(int b) {
            delegate.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) {
            delegate.write(b, off, len);
        }

        public byte[] toByteArray() {
            return delegate.toByteArray();
        }
    }
}
