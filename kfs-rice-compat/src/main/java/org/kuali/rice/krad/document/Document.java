package org.kuali.rice.krad.document;
public interface Document {
    String getDocumentNumber();
    void setDocumentNumber(String documentNumber);
    org.kuali.rice.krad.bo.DocumentHeader getDocumentHeader();
    void setDocumentHeader(org.kuali.rice.krad.bo.DocumentHeader documentHeader);
    String getDocumentTitle();
}
