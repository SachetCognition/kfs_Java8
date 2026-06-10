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
package org.kuali.kfs.sys.rice;

import java.util.List;

import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.document.Document;

/**
 * KFS-owned document service interface. Mirrors Rice DocumentService
 * to decouple KFS from direct Rice API dependency.
 */
public interface KfsDocumentService {

    Document getNewDocument(String documentTypeName) throws WorkflowException;

    Document getNewDocument(Class<? extends Document> documentClass) throws WorkflowException;

    Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException;

    Document saveDocument(Document document) throws WorkflowException;

    Document saveDocument(Document document, Class annotationType) throws WorkflowException;

    Document routeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException;

    Document approveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException;

    Document blanketApproveDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException;

    Document cancelDocument(Document document, String annotation) throws WorkflowException;

    Document acknowledgeDocument(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException;

    boolean documentExists(String documentHeaderId);
}
