package org.kuali.rice.kns.document.authorization;
public interface DocumentAuthorizer {
    boolean isAuthorized(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionName, String principalId);
    boolean isAuthorizedByTemplate(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionTemplateName, String principalId);
}
