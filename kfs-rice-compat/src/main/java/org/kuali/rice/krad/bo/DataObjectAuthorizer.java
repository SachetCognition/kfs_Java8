package org.kuali.rice.krad.bo;

public interface DataObjectAuthorizer {
    boolean isAuthorized(Object dataObject, String namespaceCode, String permissionName, String principalId);
    boolean isAuthorizedByTemplate(Object dataObject, String namespaceCode, String permissionTemplateName, String principalId);
}
