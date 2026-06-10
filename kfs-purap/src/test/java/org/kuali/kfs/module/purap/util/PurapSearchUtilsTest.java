package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.bo.DocumentHeader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PurapSearchUtilsTest extends KfsUnitTestBase {

    private DocumentHeader mockHeader(String status) {
        DocumentHeader header = mock(DocumentHeader.class);
        WorkflowDocument wd = mock(WorkflowDocument.class);
        when(header.getWorkflowDocument()).thenReturn(wd);
        lenient().when(wd.isInitiated()).thenReturn("INITIATED".equals(status));
        lenient().when(wd.isEnroute()).thenReturn("ENROUTE".equals(status));
        lenient().when(wd.isDisapproved()).thenReturn("DISAPPROVED".equals(status));
        lenient().when(wd.isCanceled()).thenReturn("CANCELLED".equals(status));
        lenient().when(wd.isApproved()).thenReturn("APPROVED".equals(status));
        return header;
    }

    @Test
    void initiatedStatus() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("INITIATED"))).isEqualTo("INITIATED");
    }

    @Test
    void enrouteStatus() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("ENROUTE"))).isEqualTo("ENROUTE");
    }

    @Test
    void disapprovedStatus() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("DISAPPROVED"))).isEqualTo("DISAPPROVED");
    }

    @Test
    void cancelledStatus() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("CANCELLED"))).isEqualTo("CANCELLED");
    }

    @Test
    void approvedStatus() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("APPROVED"))).isEqualTo("APPROVED");
    }

    @Test
    void unknownStatusReturnsEmpty() {
        assertThat(PurapSearchUtils.getWorkFlowStatusString(mockHeader("UNKNOWN"))).isEmpty();
    }
}
