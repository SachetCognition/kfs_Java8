package org.kuali.rice.kns.kim.permission;

public class PermissionTypeServiceBase extends org.kuali.rice.kns.kim.type.DataDictionaryTypeServiceBase implements org.kuali.rice.kim.framework.permission.PermissionTypeService {
    public PermissionTypeServiceBase() {}


    public java.util.List<org.kuali.rice.kim.api.permission.Permission> getMatchingPermissions(java.util.Map<java.lang.String, java.lang.String> p0, java.util.List<org.kuali.rice.kim.api.permission.Permission> p1) { return new java.util.ArrayList(); }
    protected java.util.List<org.kuali.rice.kim.api.permission.Permission> performPermissionMatches(java.util.Map<String, String> requestedDetails, java.util.List<org.kuali.rice.kim.api.permission.Permission> permissionsList) { return permissionsList; }
}
