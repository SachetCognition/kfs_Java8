/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.cg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.CGConstants;
import org.kuali.kfs.module.cg.CGKeyConstants;
import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.module.cg.businessobject.Proposal;
import org.kuali.kfs.module.cg.dataaccess.CloseDao;
import org.kuali.kfs.module.cg.document.ProposalAwardCloseDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CloseServiceImplTest extends KfsUnitTestBase {

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

    @InjectMocks
    private CloseServiceImpl closeService;

    private Date today;

    @BeforeEach
    public void setUp() {
        today = new Date(System.currentTimeMillis());
        lenient().when(dateTimeService.getCurrentSqlDateMidnight()).thenReturn(today);
    }

    @Test
    public void testClose_noMaxApprovedClose() {
        when(closeDao.getMaxApprovedClose(today)).thenReturn(null);

        boolean result = closeService.close();

        assertTrue(result);
    }

    @Test
    public void testGetMostRecentClose_found() throws WorkflowException {
        String docNumber = "DOC123";
        when(closeDao.getMostRecentClose(today)).thenReturn(docNumber);

        ProposalAwardCloseDocument expectedDoc = Mockito.mock(ProposalAwardCloseDocument.class);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(expectedDoc);

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertNotNull(result);
        assertEquals(expectedDoc, result);
    }

    @Test
    public void testGetMostRecentClose_notFound() {
        when(closeDao.getMostRecentClose(today)).thenReturn(null);

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertNull(result);
    }

    @Test
    public void testGetMostRecentClose_blankDocNumber() {
        when(closeDao.getMostRecentClose(today)).thenReturn("");

        ProposalAwardCloseDocument result = closeService.getMostRecentClose();

        assertNull(result);
    }

    @Test
    public void testGetMaxApprovedClose_found() throws WorkflowException {
        String docNumber = "DOC456";
        when(closeDao.getMaxApprovedClose(today)).thenReturn(docNumber);

        ProposalAwardCloseDocument expectedDoc = Mockito.mock(ProposalAwardCloseDocument.class);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(expectedDoc);

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertNotNull(result);
        assertEquals(expectedDoc, result);
    }

    @Test
    public void testGetMaxApprovedClose_notFound() {
        when(closeDao.getMaxApprovedClose(today)).thenReturn(null);

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertNull(result);
    }

    @Test
    public void testGetMaxApprovedClose_blankDocNumber() {
        when(closeDao.getMaxApprovedClose(today)).thenReturn("");

        ProposalAwardCloseDocument result = closeService.getMaxApprovedClose(today);

        assertNull(result);
    }

    @Test
    public void testClose_maxApprovedCloseNotOnUnprocessedNode() throws WorkflowException {
        String docNumber = "DOC789";
        when(closeDao.getMaxApprovedClose(today)).thenReturn(docNumber);

        ProposalAwardCloseDocument closeDoc = Mockito.mock(ProposalAwardCloseDocument.class);
        DocumentHeader docHeader = Mockito.mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = Mockito.mock(WorkflowDocument.class);

        when(workflowDoc.getCurrentNodeNames()).thenReturn(Collections.<String>emptySet());
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(closeDoc.getDocumentHeader()).thenReturn(docHeader);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(closeDoc);

        boolean result = closeService.close();

        assertTrue(result);
        verify(closeDao, never()).getProposalsToClose(any(ProposalAwardCloseDocument.class));
        verify(closeDao, never()).getAwardsToClose(any(ProposalAwardCloseDocument.class));
    }

    @Test
    public void testClose_closesProposalsAndAwards() throws WorkflowException {
        String docNumber = "DOC100";
        when(closeDao.getMaxApprovedClose(today)).thenReturn(docNumber);

        Set<String> nodeNames = new HashSet<>();
        nodeNames.add(CGConstants.CGKimApiConstants.UNPROCESSED_ROUTING_NODE_NAME);

        ProposalAwardCloseDocument closeDoc = Mockito.mock(ProposalAwardCloseDocument.class);
        DocumentHeader docHeader = Mockito.mock(DocumentHeader.class);
        WorkflowDocument workflowDoc = Mockito.mock(WorkflowDocument.class);

        when(workflowDoc.getCurrentNodeNames()).thenReturn(nodeNames);
        when(docHeader.getWorkflowDocument()).thenReturn(workflowDoc);
        when(closeDoc.getDocumentHeader()).thenReturn(docHeader);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(closeDoc);

        Collection<Proposal> proposals = new ArrayList<>();
        proposals.add(new Proposal());
        proposals.add(new Proposal());
        when(closeDao.getProposalsToClose(closeDoc)).thenReturn(proposals);

        Collection<Award> awards = new ArrayList<>();
        awards.add(new Award());
        when(closeDao.getAwardsToClose(closeDoc)).thenReturn(awards);

        when(configService.getPropertyValueAsString(CGKeyConstants.MESSAGE_CLOSE_JOB_SUCCEEDED))
                .thenReturn("Close job succeeded.");

        closeService.close();

        verify(closeDoc).setAwardClosedCount(1L);
        verify(closeDoc).setProposalClosedCount(2L);
    }
}
