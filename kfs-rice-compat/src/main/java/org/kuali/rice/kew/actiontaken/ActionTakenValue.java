package org.kuali.rice.kew.actiontaken;

public class ActionTakenValue {
    public static final String ACTION_TAKEN_APPROVED_CD = "A";
    public static final String ACTION_TAKEN_COMPLETED_CD = "C";
    public static final String ACTION_TAKEN_DENIED_CD = "D";
    public static final String ACTION_TAKEN_CANCELED_CD = "X";
    public static final String ACTION_TAKEN_ACKNOWLEDGED_CD = "K";
    public static final String ACTION_TAKEN_FYI_CD = "F";
    public static final String ACTION_TAKEN_RETURNED_TO_PREVIOUS_CD = "Z";
    public static final String ACTION_TAKEN_BLANKET_APPROVE_CD = "B";
    
    private String actionTakenId;
    private String documentId;
    private String principalId;
    private String actionTaken;
    
    public ActionTakenValue() {}
    
    public String getActionTakenId() { return actionTakenId; }
    public String getDocumentId() { return documentId; }
    public String getPrincipalId() { return principalId; }
    public String getActionTaken() { return actionTaken; }
}
