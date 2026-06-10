package org.kuali.rice.kim.api.permission;
public interface PermissionService {
    boolean isAuthorized(String principalId, String namespaceCode, String permissionName, java.util.Map<String, String> qualification);
    boolean isAuthorizedByTemplate(String principalId, String namespaceCode, String permissionTemplateName, java.util.Map<String, String> qualification, java.util.Map<String, String> permissionDetails);
    public boolean hasPermission(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2);
    public boolean hasPermissionByTemplate(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.util.Map<java.lang.String,java.lang.String> arg3);
    org.kuali.rice.kim.api.permission.Template findPermTemplateByNamespaceCodeAndName(String namespaceCode, String name);
    org.kuali.rice.kim.api.permission.Template getPermissionTemplate(String templateId);
    Permission findPermByNamespaceCodeAndName(String namespaceCode, String permissionName);
    void assignPermissionToRole(String permissionId, String roleId);
    void revokePermissionFromRole(String permissionId, String roleId);
}