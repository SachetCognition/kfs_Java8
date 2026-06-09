package org.kuali.rice.kim.api.identity.phone;

public class EntityPhone extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String phoneNumber;
    private String phoneType;
    private boolean active;
    private boolean defaultValue;
    
    public EntityPhone() {}
    public String getPhoneNumber() { return phoneNumber; }
    public String getPhoneType() { return phoneType; }
    public boolean isActive() { return active; }
    public boolean isDefaultValue() { return defaultValue; }
}
