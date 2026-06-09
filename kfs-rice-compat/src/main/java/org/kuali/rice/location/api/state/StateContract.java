package org.kuali.rice.location.api.state;
public interface StateContract {
    String getCode();
    String getName();
    String getCountryCode();
    boolean isActive();
}
