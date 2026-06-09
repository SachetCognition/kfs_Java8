package org.kuali.rice.location.framework.postalcode;
public interface PostalCodeEbo extends org.kuali.rice.krad.bo.ExternalizableBusinessObject {
    String getCode();
    String getCountryCode();
    String getStateCode();
    String getCityName();
}
