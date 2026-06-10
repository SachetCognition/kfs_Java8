package org.kuali.rice.kim.impl.permission;
public class NamespaceWildcardAllowedAndOrStringExactMatchPermissionTypeServiceImpl extends PermissionTypeServiceBase {
    protected String exactMatchStringAttributeName;
    public String getExactMatchStringAttributeName() { return exactMatchStringAttributeName; }
    public void setExactMatchStringAttributeName(String name) { this.exactMatchStringAttributeName = name; }
}
