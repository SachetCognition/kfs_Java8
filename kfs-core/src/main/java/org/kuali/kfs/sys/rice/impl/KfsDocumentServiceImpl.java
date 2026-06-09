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
package org.kuali.kfs.sys.rice.impl;

import java.util.List;

import org.kuali.kfs.sys.rice.KfsDocumentService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;

/**
 * Delegates to Rice DocumentService.
 */
public class KfsDocumentServiceImpl implements KfsDocumentService {

    private DocumentService documentService;

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public Document getNewDocument(String documentTypeName) throws WorkflowException {
        return documentService.getNewDocument(documentTypeName);
    }

    @Override
    public Document getNewDocument(Class<? extends Document> documentClass) throws WorkflowException {
        return documentService.getNewDocument(documentClass);
    }

    @Override
    public Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException {
        return documentService.getByDocumentHeaderId(documentHeaderId);
    }

    @Override
    public Document saveDocument(Document document) throws WorkflowException {
        return documentService.saveDocument(document);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Document saveDocument(Document document, Class annotationType) throws WorkflowException {
        return documentService.saveDocument(document, annotationType);
    }

    @Override
    public Document routeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {
        return documentService.routeDocument(document, annotation, adHocRecipients);
    }

    @Override
    public Document approveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {
        return documentService.approveDocument(document, annotation, adHocRecipients);
    }

    @Override
    public Document blanketApproveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {
        return documentService.blanketApproveDocument(document, annotation, adHocRecipients);
    }

    @Override
    public Document cancelDocument(Document document, String annotation) throws WorkflowException {
        return documentService.cancelDocument(document, annotation);
    }

    @Override
    public Document acknowledgeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {
        return documentService.acknowledgeDocument(document, annotation, adHocRecipients);
    }

    @Override
    public boolean documentExists(String documentHeaderId) {
        return documentService.documentExists(documentHeaderId);
    }
}
