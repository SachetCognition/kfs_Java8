package org.kuali.rice.location.framework.county;

public interface CountyEbo extends org.kuali.rice.krad.bo.ExternalizableBusinessObject {
    String getCode();
    String getCountyName();
    String getStateCode();
    String getCountryCode();
    boolean isActive();
}
