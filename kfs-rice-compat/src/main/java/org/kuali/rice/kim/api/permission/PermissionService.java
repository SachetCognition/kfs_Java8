package org.kuali.rice.kim.api.permission;
public interface PermissionService {
    boolean isAuthorized(String principalId, String namespaceCode, String permissionName, java.util.Map<String, String> qualification);
    boolean isAuthorizedByTemplate(String principalId, String namespaceCode, String permissionTemplateName, java.util.Map<String, String> qualification, java.util.Map<String, String> permissionDetails);
}
