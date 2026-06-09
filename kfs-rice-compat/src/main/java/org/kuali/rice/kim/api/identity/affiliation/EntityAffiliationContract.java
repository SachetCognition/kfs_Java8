package org.kuali.rice.kim.api.identity.affiliation;
public interface EntityAffiliationContract {
    String getAffiliationTypeCode();
    String getCampusCode();
    boolean isDefaultValue();
    boolean isActive();
}
