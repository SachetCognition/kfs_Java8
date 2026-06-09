package org.kuali.rice.krad.service;

public interface DocumentHeaderService {
    Class<? extends org.kuali.rice.krad.bo.DocumentHeader> getDocumentHeaderBaseClass();
    org.kuali.rice.krad.bo.DocumentHeader getDocumentHeaderById(String documentId);
    void saveDocumentHeader(org.kuali.rice.krad.bo.DocumentHeader documentHeader);
    void deleteDocumentHeader(org.kuali.rice.krad.bo.DocumentHeader documentHeader);
}
