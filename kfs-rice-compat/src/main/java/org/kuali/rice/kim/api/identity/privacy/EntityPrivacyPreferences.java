package org.kuali.rice.kim.api.identity.privacy;
public class EntityPrivacyPreferences extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public EntityPrivacyPreferences() {}
    public boolean isSuppressName() { return false; }
    public boolean isSuppressAddress() { return false; }
    public boolean isSuppressEmail() { return false; }
    public boolean isSuppressPhone() { return false; }
    public boolean isSuppressPersonal() { return false; }
}
