package org.kuali.rice.kim.api.permission;

import java.util.Map;
import java.util.HashMap;

public class Permission extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private String name;
    private String namespaceCode;
    private String description;
    private String templateId;
    private Map<String, String> attributes;
    private boolean active;
    
    public Permission() { this.attributes = new HashMap<String, String>(); }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNamespaceCode() { return namespaceCode; }
    public String getDescription() { return description; }
    public String getTemplateId() { return templateId; }
    public Map<String, String> getAttributes() { return attributes; }
    public boolean isActive() { return active; }
}
