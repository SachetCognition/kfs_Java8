package org.kuali.rice.kim.api.role;

public class RoleResponsibilityAction extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private String roleResponsibilityId;
    private String roleMemberId;
    private String actionTypeCode;
    private String actionPolicyCode;
    private boolean forceAction;
    private Integer priorityNumber;
    
    public RoleResponsibilityAction() {}
    
    public String getId() { return id; }
    public String getRoleResponsibilityId() { return roleResponsibilityId; }
    public String getRoleMemberId() { return roleMemberId; }
    public String getActionTypeCode() { return actionTypeCode; }
    public String getActionPolicyCode() { return actionPolicyCode; }
    public boolean isForceAction() { return forceAction; }
    public Integer getPriorityNumber() { return priorityNumber; }
    
    public static class Builder implements org.kuali.rice.core.api.mo.ModelBuilder, java.io.Serializable {
        private String id;
        private String roleResponsibilityId;
        private String roleMemberId;
        private String actionTypeCode;
        private String actionPolicyCode;
        private boolean forceAction;
        private Integer priorityNumber;
        
        private Builder() {}
        public static Builder create() { return new Builder(); }
        
        public RoleResponsibilityAction build() {
            RoleResponsibilityAction rra = new RoleResponsibilityAction();
            rra.id = this.id; rra.roleResponsibilityId = this.roleResponsibilityId;
            rra.roleMemberId = this.roleMemberId; rra.actionTypeCode = this.actionTypeCode;
            rra.actionPolicyCode = this.actionPolicyCode; rra.forceAction = this.forceAction;
            rra.priorityNumber = this.priorityNumber;
            return rra;
        }
        
        public void setId(String id) { this.id = id; }
        public void setRoleMemberId(String roleMemberId) { this.roleMemberId = roleMemberId; }
        public void setActionTypeCode(String code) { this.actionTypeCode = code; }
        public void setActionPolicyCode(String code) { this.actionPolicyCode = code; }
        public void setForceAction(boolean forceAction) { this.forceAction = forceAction; }
        public void setPriorityNumber(Integer priorityNumber) { this.priorityNumber = priorityNumber; }
        public void setRoleResponsibilityId(String roleResponsibilityId) { this.roleResponsibilityId = roleResponsibilityId; }
    }
}
