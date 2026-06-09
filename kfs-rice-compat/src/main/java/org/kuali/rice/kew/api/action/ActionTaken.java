package org.kuali.rice.kew.api.action;

public class ActionTaken extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String actionTakenId;
    private String documentId;
    private String principalId;
    private ActionType actionTaken;
    
    public ActionTaken() {}
    
    public String getActionTakenId() { return actionTakenId; }
    public String getDocumentId() { return documentId; }
    public String getPrincipalId() { return principalId; }
    public ActionType getActionTaken() { return actionTaken; }
    public String getCode() { return actionTaken != null ? actionTaken.getCode() : null; }
}
