package org.kuali.rice.kim.api.type;

public class KimType extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements KimTypeContract {
    private String id;
    private String serviceName;
    private String namespaceCode;
    private String name;
    private boolean active;
    
    public KimType() {}
    
    public String getId() { return id; }
    public String getServiceName() { return serviceName; }
    public String getNamespaceCode() { return namespaceCode; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
}
