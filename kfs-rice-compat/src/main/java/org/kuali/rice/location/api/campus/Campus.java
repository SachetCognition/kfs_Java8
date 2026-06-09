package org.kuali.rice.location.api.campus;

public class Campus extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String code;
    private String name;
    private String shortName;
    private boolean active;
    
    public Campus() {}
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getShortName() { return shortName; }
    public boolean isActive() { return active; }
}
