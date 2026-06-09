package org.kuali.rice.kim.api.identity;
public interface Person {
    String getPrincipalId();
    String getPrincipalName();
    String getEntityId();
    String getFirstName();
    String getMiddleName();
    String getLastName();
    String getName();
    String getEmailAddress();
    String getPhoneNumber();
    String getEmployeeId();
    boolean isActive();
    String getCampusCode();
    String getExternalIdentifier(String externalIdentifierTypeCode);
}
