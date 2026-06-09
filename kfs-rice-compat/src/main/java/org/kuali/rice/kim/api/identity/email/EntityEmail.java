package org.kuali.rice.kim.api.identity.email;

public class EntityEmail extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String emailAddress;
    private String emailType;
    private boolean active;
    private boolean defaultValue;
    
    public EntityEmail() {}
    public String getEmailAddress() { return emailAddress; }
    public String getEmailType() { return emailType; }
    public boolean isActive() { return active; }
    public boolean isDefaultValue() { return defaultValue; }
}
