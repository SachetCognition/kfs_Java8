package org.kuali.rice.location.api.county;

public class County {
    private String code;
    private String name;
    private String countryCode;
    private String stateCode;
    private boolean active;
    
    public County() {}
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    public String getStateCode() { return stateCode; }
    public void setStateCode(String stateCode) { this.stateCode = stateCode; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
