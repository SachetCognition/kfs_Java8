package org.kuali.rice.kim.api.identity.type;

import java.util.List;
import java.util.ArrayList;

public class EntityTypeContactInfo extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String entityTypeCode;
    private String entityId;
    private List<org.kuali.rice.kim.api.identity.address.EntityAddress> addresses = new ArrayList<org.kuali.rice.kim.api.identity.address.EntityAddress>();
    private List<org.kuali.rice.kim.api.identity.phone.EntityPhone> phoneNumbers = new ArrayList<org.kuali.rice.kim.api.identity.phone.EntityPhone>();
    private List<org.kuali.rice.kim.api.identity.email.EntityEmail> emailAddresses = new ArrayList<org.kuali.rice.kim.api.identity.email.EntityEmail>();
    private boolean active;
    
    public EntityTypeContactInfo() {}
    
    public String getEntityTypeCode() { return entityTypeCode; }
    public String getEntityId() { return entityId; }
    public List<org.kuali.rice.kim.api.identity.address.EntityAddress> getAddresses() { return addresses; }
    public List<org.kuali.rice.kim.api.identity.phone.EntityPhone> getPhoneNumbers() { return phoneNumbers; }
    public List<org.kuali.rice.kim.api.identity.email.EntityEmail> getEmailAddresses() { return emailAddresses; }
    public boolean isActive() { return active; }
    
    public org.kuali.rice.kim.api.identity.address.EntityAddress getDefaultAddress() { return addresses.isEmpty() ? null : addresses.get(0); }
}
