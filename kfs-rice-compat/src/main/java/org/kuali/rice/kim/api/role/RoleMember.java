package org.kuali.rice.kim.api.role;
import java.util.Map;
public class RoleMember extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements RoleMemberContract {
    private String id;
    private String roleId;
    private String memberId;
    private String type;
    private Map<String, String> attributes;
    private org.joda.time.DateTime activeFromDate;
    private org.joda.time.DateTime activeToDate;
    
    public RoleMember() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRoleId() { return roleId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public void setType(org.kuali.rice.core.api.membership.MemberType type) { this.type = type != null ? type.getCode() : null; }
    public Map<String, String> getAttributes() { return attributes; }
    public void setAttributes(Map<String, String> attributes) { this.attributes = attributes; }
    public org.joda.time.DateTime getActiveFromDate() { return activeFromDate; }
    public void setActiveFromDate(org.joda.time.DateTime d) { this.activeFromDate = d; }
    public org.joda.time.DateTime getActiveToDate() { return activeToDate; }
    public void setActiveToDate(org.joda.time.DateTime d) { this.activeToDate = d; }
    
    public static class Builder {
        private String roleId;
        private String id;
        private String memberId;
        private String type;
        private Map<String, String> attributes;
        private org.joda.time.DateTime activeFromDate;
        private org.joda.time.DateTime activeToDate;
        
        public static Builder create(String roleId, String id, String memberId, String memberTypeCode, Map<String, String> qualifier) {
            Builder b = new Builder();
            b.roleId = roleId;
            b.id = id;
            b.memberId = memberId;
            b.type = memberTypeCode;
            b.attributes = qualifier;
            return b;
        }
        public static Builder create(RoleMember roleMember) {
            Builder b = new Builder();
            if (roleMember != null) {
                b.roleId = roleMember.getRoleId();
                b.id = roleMember.getId();
                b.memberId = roleMember.getMemberId();
                b.type = roleMember.getType();
                b.attributes = roleMember.getAttributes();
            }
            return b;
        }
        public Builder setActiveFromDate(org.joda.time.DateTime d) { this.activeFromDate = d; return this; }
        public Builder setActiveToDate(org.joda.time.DateTime d) { this.activeToDate = d; return this; }
        public Builder setMemberId(String id) { this.memberId = id; return this; }
        public Builder setType(org.kuali.rice.core.api.membership.MemberType t) { this.type = t != null ? t.getCode() : null; return this; }
        public Builder setAttributes(Map<String, String> attrs) { this.attributes = attrs; return this; }
        public RoleMember build() { return new RoleMember(); }
    }

    public boolean isActive(org.joda.time.DateTime asOfDate) { return true; }
}
