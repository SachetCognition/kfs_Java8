package org.kuali.rice.kim.api.role;
public class Role extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements RoleContract {
    public Role() {}
    public String getId() { return null; }
    public String getName() { return null; }
    public String getNamespaceCode() { return null; }
    public String getKimTypeId() { return null; }
    public Long getVersionNumber() { return null; }
    public String getObjectId() { return null; }
    public boolean isActive() { return true; }
    public String getDescription() { return null; }

    public static class Builder {
        private String id;
        private String name;
        private String namespaceCode;
        private String kimTypeId;
        private String description;
        private boolean active = true;

        public static Builder create() { return new Builder(); }
        public static Builder create(Role role) {
            Builder b = new Builder();
            if (role != null) {
                b.id = role.getId();
                b.name = role.getName();
                b.namespaceCode = role.getNamespaceCode();
                b.kimTypeId = role.getKimTypeId();
                b.description = role.getDescription();
                b.active = role.isActive();
            }
            return b;
        }
        public Builder setId(String id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setNamespaceCode(String ns) { this.namespaceCode = ns; return this; }
        public Builder setKimTypeId(String kimTypeId) { this.kimTypeId = kimTypeId; return this; }
        public Builder setDescription(String desc) { this.description = desc; return this; }
        public Builder setActive(boolean active) { this.active = active; return this; }
        public String getId() { return id; }
        public String getName() { return name; }
        public String getNamespaceCode() { return namespaceCode; }
        public Role build() { return new Role(); }
    }
}
