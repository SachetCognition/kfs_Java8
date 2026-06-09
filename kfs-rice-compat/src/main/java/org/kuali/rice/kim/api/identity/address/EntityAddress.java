package org.kuali.rice.kim.api.identity.address;

public class EntityAddress extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private String entityTypeCode;
    private String entityId;
    private String addressTypeCode;
    private org.kuali.rice.kim.api.identity.CodedAttribute addressType;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String stateProvinceCode;
    private String postalCode;
    private String countryCode;
    private boolean defaultValue;
    private boolean active;
    
    public EntityAddress() {}
    
    public String getId() { return id; }
    public String getEntityTypeCode() { return entityTypeCode; }
    public String getEntityId() { return entityId; }
    public String getAddressTypeCode() { return addressTypeCode; }
    public org.kuali.rice.kim.api.identity.CodedAttribute getAddressType() { return addressType; }
    public String getLine1() { return line1; }
    public String getLine2() { return line2; }
    public String getLine3() { return line3; }
    public String getCity() { return city; }
    public String getStateProvinceCode() { return stateProvinceCode; }
    public String getPostalCode() { return postalCode; }
    public String getCountryCode() { return countryCode; }
    public boolean isDefaultValue() { return defaultValue; }
    public boolean isActive() { return active; }
    
    public String getLine1Unmasked() { return line1; }
    public String getLine2Unmasked() { return line2; }
    public String getLine3Unmasked() { return line3; }
    public String getCityUnmasked() { return city; }
    public String getStateProvinceCodeUnmasked() { return stateProvinceCode; }
    public String getPostalCodeUnmasked() { return postalCode; }
    public String getCountryCodeUnmasked() { return countryCode; }
}
