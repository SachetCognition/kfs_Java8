package org.kuali.rice.kim.api.group;

public class Group extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements GroupContract {
    private String id;
    private String name;
    private String namespaceCode;
    private String description;
    private boolean active;
    private String kimTypeId;
    
    public Group() {}
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNamespaceCode() { return namespaceCode; }
    public String getDescription() { return description; }
    public java.util.Map<String, String> getAttributes() { return new java.util.HashMap<>(); }
    public Long getVersionNumber() { return null; }
    public String getObjectId() { return null; }
    public boolean isActive() { return active; }
    public String getKimTypeId() { return kimTypeId; }
}
