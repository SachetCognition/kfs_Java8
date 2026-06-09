package org.kuali.rice.kns.document.authorization;

public interface BusinessObjectAuthorizer {
    boolean isAuthorized(org.kuali.rice.krad.bo.BusinessObject businessObject, String namespaceCode, String permissionName, String principalId);
    boolean isAuthorizedByTemplate(org.kuali.rice.krad.bo.BusinessObject businessObject, String namespaceCode, String permissionTemplateName, String principalId);
}
