package org.kuali.rice.kns.service;

public interface DocumentHelperService {
    org.kuali.rice.kns.document.authorization.DocumentAuthorizer getDocumentAuthorizer(String documentType);
    org.kuali.rice.kns.document.authorization.DocumentAuthorizer getDocumentAuthorizer(org.kuali.rice.krad.document.Document document);
    org.kuali.rice.kns.document.authorization.DocumentPresentationController getDocumentPresentationController(String documentType);
    org.kuali.rice.kns.document.authorization.DocumentPresentationController getDocumentPresentationController(org.kuali.rice.krad.document.Document document);
}
