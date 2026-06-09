package org.kuali.rice.kew.api.action;

public class ActionRequest extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements org.kuali.rice.kew.api.action.ActionRequestContract {
    public ActionRequest() {}


    public java.lang.String getAnnotation() { return null; }
    public int getPriority() { return 0; }
    public int getRouteLevel() { return 0; }
    public java.lang.String getId() { return null; }
    public org.kuali.rice.kew.api.action.ActionRequestType getActionRequested() { return null; }
    public org.kuali.rice.kew.api.action.ActionRequestStatus getStatus() { return null; }
    public boolean isCurrent() { return false; }
    public org.joda.time.DateTime getDateCreated() { return null; }
    public java.lang.String getResponsibilityId() { return null; }
    public java.lang.String getDocumentId() { return null; }
    public org.kuali.rice.kew.api.action.RecipientType getRecipientType() { return null; }
    public java.lang.String getPrincipalId() { return null; }
    public java.lang.String getGroupId() { return null; }
    public org.kuali.rice.kew.api.action.ActionRequestPolicy getRequestPolicy() { return null; }
    public java.lang.String getResponsibilityDescription() { return null; }
    public boolean isForceAction() { return false; }
    public org.kuali.rice.core.api.delegation.DelegationType getDelegationType() { return null; }
    public java.lang.String getRoleName() { return null; }
    public java.lang.String getQualifiedRoleName() { return null; }
    public java.lang.String getQualifiedRoleNameLabel() { return null; }
    public java.lang.String getRouteNodeInstanceId() { return null; }
    public java.lang.String getNodeName() { return null; }
    public java.lang.String getRequestLabel() { return null; }
    public java.lang.String getParentActionRequestId() { return null; }
    public org.kuali.rice.kew.api.action.ActionTaken getActionTaken() { return null; }
    public java.util.List<org.kuali.rice.kew.api.action.ActionRequest> getChildRequests() { return new java.util.ArrayList(); }
    public boolean isAdHocRequest() { return false; }
    public boolean isGeneratedRequest() { return false; }
    public boolean isExceptionRequest() { return false; }
    public boolean isRouteModuleRequest() { return false; }
    public boolean isNotificationRequest() { return false; }
    public boolean isApprovalRequest() { return false; }
    public boolean isAcknowledgeRequest() { return false; }
    public boolean isFyiRequest() { return false; }
    public boolean isPending() { return false; }
    public boolean isCompleteRequest() { return false; }
    public boolean isInitialized() { return false; }
    public boolean isActivated() { return false; }
    public boolean isDone() { return false; }
    public boolean isUserRequest() { return false; }
    public boolean isGroupRequest() { return false; }
    public boolean isRoleRequest() { return false; }
    public java.util.List<org.kuali.rice.kew.api.action.ActionRequest> flatten() { return new java.util.ArrayList(); }
}
