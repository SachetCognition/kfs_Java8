package org.kuali.rice.krad.document;
public interface Document extends org.kuali.rice.krad.bo.BusinessObject {
    String getDocumentNumber();
    void setDocumentNumber(String documentNumber);
    org.kuali.rice.krad.bo.DocumentHeader getDocumentHeader();
    void setDocumentHeader(org.kuali.rice.krad.bo.DocumentHeader documentHeader);
    String getDocumentTitle();
    void refreshNonUpdateableReferences();
    java.util.List getAdHocRoutePersons();
    java.util.List getAdHocRouteWorkgroups();
}
