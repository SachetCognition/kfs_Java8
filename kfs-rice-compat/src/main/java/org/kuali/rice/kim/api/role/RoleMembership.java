package org.kuali.rice.kim.api.role;
import java.util.HashMap;
import java.util.Map;
public class RoleMembership extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String roleId;
    private String memberId;
    private String roleMemberId;
    private Map<String, String> qualifier;
    public RoleMembership() {}
    public String getRoleId() { return roleId; }
    public String getMemberId() { return memberId; }
    public String getRoleMemberId() { return roleMemberId; }
    public Map<String, String> getQualifier() { return qualifier; }

    public String getRoleSortingCode() { return null; }

    public static class Builder {
        private String roleId;
        private String memberId;
        private String roleMemberId;
        private Map<String, String> qualifier = new HashMap<>();
        
        private Builder(String roleId, String memberId, String roleMemberId) {
            this.roleId = roleId;
            this.memberId = memberId;
            this.roleMemberId = roleMemberId;
        }
        public void setRoleSortingCode(String code) {}
        public static Builder create(RoleMembership rm) { return new Builder(null, null, null); }
        public static Builder create(String roleId, String memberId, String roleMemberId, org.kuali.rice.core.api.membership.MemberType memberType, Map<String, String> qualifier) { return new Builder(roleId, memberId, roleMemberId); }
        public static Builder create(String roleId, String memberId, String roleMemberId, String memberTypeCode, Map<String, String> qualifier) {
            Builder b = new Builder(roleId, memberId, roleMemberId);
            if (qualifier != null) b.qualifier = qualifier;
            return b;
        }
        public static Builder create(String roleId, String memberId, String memberTypeCode, Map<String, String> qualifier) {
            return create(roleId, memberId, null, memberTypeCode, qualifier);
        }
        public Builder qualifier(Map<String, String> qualifier) { this.qualifier = qualifier; return this; }
        public String getRoleId() { return roleId; }
        public String getMemberId() { return memberId; }
        public Map<String, String> getQualifier() { return qualifier; }
        public RoleMembership build() {
            RoleMembership rm = new RoleMembership();
            rm.roleId = this.roleId;
            rm.memberId = this.memberId;
            rm.roleMemberId = this.roleMemberId;
            rm.qualifier = this.qualifier;
            return rm;
        }
    }
}
