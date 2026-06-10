package org.kuali.rice.kns.kim.type;
public class DataDictionaryTypeServiceBase implements org.kuali.rice.kim.framework.type.KimTypeService {
    public DataDictionaryTypeServiceBase() {}
    protected String getAttributeValue(java.util.Map<String, String> attributes, String attributeName) { return attributes != null ? attributes.get(attributeName) : null; }
    protected Object getTypeInfoService() { return null; }
    protected java.util.List<org.kuali.rice.core.api.uif.RemotableAttributeError> validateUnmodifiableAttributes(String kimTypeId, java.util.Map<String, String> originalAttrs, java.util.Map<String, String> newAttrs) { return new java.util.ArrayList<>(); }
    protected boolean performMatch(java.util.Map<String, String> inputAttributes, java.util.Map<String, String> storedAttributes) { return true; }
    public java.util.List<org.kuali.rice.kim.api.role.RoleMembership> sortRoleMembers(java.util.List<org.kuali.rice.kim.api.role.RoleMembership> roleMembers) { return roleMembers; }
    public java.util.List<org.kuali.rice.core.api.uif.RemotableAttributeError> validateAttributes(String kimTypeId, java.util.Map<String, String> attributes) { return new java.util.ArrayList<>(); }
}
