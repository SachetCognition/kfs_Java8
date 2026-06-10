package org.kuali.rice.kew.framework.postprocessor;
public class DocumentRouteStatusChange {
    private String documentId;
    private String oldRouteStatus;
    private String newRouteStatus;
    public DocumentRouteStatusChange() {}
    public DocumentRouteStatusChange(String documentId, String appDocId, String oldRouteStatus, String newRouteStatus) {
        this.documentId = documentId;
        this.oldRouteStatus = oldRouteStatus;
        this.newRouteStatus = newRouteStatus;
    }
    public String getDocumentId() { return documentId; }
    public String getOldRouteStatus() { return oldRouteStatus; }
    public String getNewRouteStatus() { return newRouteStatus; }
    public String getDocumentEventCode() { return newRouteStatus; }
}
