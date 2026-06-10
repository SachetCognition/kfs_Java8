package org.kuali.rice.kew.api;
public interface WorkflowDocument {
    String getDocumentId();
    String getDocumentTypeName();
    String getTitle();
    org.kuali.rice.kew.api.document.DocumentStatus getStatus();
    String getInitiatorPrincipalId();
    org.joda.time.DateTime getDateCreated();
    org.joda.time.DateTime getDateLastModified();
    org.joda.time.DateTime getDateFinalized();
    org.joda.time.DateTime getDateApproved();
    boolean isInitiated();
    boolean isSaved();
    boolean isEnroute();
    boolean isProcessed();
    boolean isFinal();
    boolean isCanceled();
    boolean isDisapproved();
    boolean isApproved();
    boolean isRecalled();
    boolean isCompletionRequested();
    boolean isApprovalRequested();
    boolean isAcknowledgeRequested();
    boolean isFYIRequested();
    void approve(String annotation);
    void complete(String annotation);
    void route(String annotation);
    void save(String annotation);
    void cancel(String annotation);
    void disapprove(String annotation);
    void acknowledge(String annotation);
    void fyi();
    void blanketApprove(String annotation);
    void superUserBlanketApprove(String annotation);
    void superUserApprove(String annotation);
    java.util.Set<String> getNodeNames();
    java.util.Set<String> getCurrentNodeNames();
    org.kuali.rice.kew.api.document.DocumentStatus getDocumentStatus();
    String getApplicationDocumentStatus();
    void setApplicationDocumentStatus(String status);
    String getApplicationDocumentId();
    void setApplicationDocumentId(String id);
    boolean hasDocumentId();
    java.util.List<org.kuali.rice.kew.api.document.node.RouteNodeInstance> getCurrentRouteNodeInstances();
    java.util.List<org.kuali.rice.kew.api.action.ActionTaken> getActionsTaken();
    default boolean isException() { return false; }
    default void route(String annotation, Object unused) {}
    default void superUserApproveDocument(org.kuali.rice.krad.document.Document doc, String annotation) {}
    default void prepareWorkflowDocument(org.kuali.rice.krad.document.Document doc) {}
}
