package org.kuali.kfs.module.cg.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doCallRealMethod;

class ProposalAwardCloseDocumentTest extends KfsUnitTestBase {

    private ProposalAwardCloseDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(ProposalAwardCloseDocument.class);
    }

    @Test
    void testCloseOnOrBeforeDate() {
        Date date = Date.valueOf("2024-06-30");
        doCallRealMethod().when(document).setCloseOnOrBeforeDate(date);
        doCallRealMethod().when(document).getCloseOnOrBeforeDate();
        document.setCloseOnOrBeforeDate(date);
        assertThat(document.getCloseOnOrBeforeDate()).isEqualTo(date);
    }

    @Test
    void testUserInitiatedCloseDate() {
        Date date = Date.valueOf("2024-06-01");
        doCallRealMethod().when(document).setUserInitiatedCloseDate(date);
        doCallRealMethod().when(document).getUserInitiatedCloseDate();
        document.setUserInitiatedCloseDate(date);
        assertThat(document.getUserInitiatedCloseDate()).isEqualTo(date);
    }

    @Test
    void testAwardClosedCount() {
        doCallRealMethod().when(document).setAwardClosedCount(25L);
        doCallRealMethod().when(document).getAwardClosedCount();
        document.setAwardClosedCount(25L);
        assertThat(document.getAwardClosedCount()).isEqualTo(25L);
    }

    @Test
    void testProposalClosedCount() {
        doCallRealMethod().when(document).setProposalClosedCount(10L);
        doCallRealMethod().when(document).getProposalClosedCount();
        document.setProposalClosedCount(10L);
        assertThat(document.getProposalClosedCount()).isEqualTo(10L);
    }

    @Test
    void testPrincipalName() {
        doCallRealMethod().when(document).setPrincipalName("admin");
        doCallRealMethod().when(document).getPrincipalName();
        document.setPrincipalName("admin");
        assertThat(document.getPrincipalName()).isEqualTo("admin");
    }

    @Test
    void testSettersAreCalled() {
        Date date = Date.valueOf("2024-06-30");
        document.setCloseOnOrBeforeDate(date);
        verify(document).setCloseOnOrBeforeDate(date);

        document.setAwardClosedCount(5L);
        verify(document).setAwardClosedCount(5L);

        document.setProposalClosedCount(3L);
        verify(document).setProposalClosedCount(3L);
    }
}
