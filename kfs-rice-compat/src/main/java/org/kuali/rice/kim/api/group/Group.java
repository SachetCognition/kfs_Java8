package org.kuali.rice.kim.api.group;

public class Group extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
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
    public boolean isActive() { return active; }
    public String getKimTypeId() { return kimTypeId; }
}
