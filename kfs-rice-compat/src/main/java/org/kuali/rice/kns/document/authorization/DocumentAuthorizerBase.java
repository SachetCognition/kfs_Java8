package org.kuali.rice.kns.document.authorization;
public class DocumentAuthorizerBase implements DocumentAuthorizer {
    public DocumentAuthorizerBase() {}
    public boolean isAuthorized(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionName, String principalId) { return true; }
    public boolean isAuthorizedByTemplate(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionTemplateName, String principalId) { return true; }
}
