package org.kuali.rice.krad.service;

public interface SessionDocumentService {
    org.kuali.rice.kew.api.WorkflowDocument getDocumentFromSession(org.kuali.rice.krad.UserSession userSession, String docId);
    void addDocumentToUserSession(org.kuali.rice.krad.UserSession userSession, org.kuali.rice.kew.api.WorkflowDocument document);
    void purgeDocumentFromSession(org.kuali.rice.krad.UserSession userSession, String docId);
}
