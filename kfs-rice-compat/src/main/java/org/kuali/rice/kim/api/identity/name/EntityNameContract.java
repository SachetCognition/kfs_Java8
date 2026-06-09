package org.kuali.rice.kim.api.identity.name;
public interface EntityNameContract {
    String getFirstName();
    String getMiddleName();
    String getLastName();
    String getCompositeName();
    boolean isDefaultValue();
}
