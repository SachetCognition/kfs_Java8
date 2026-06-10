package org.kuali.rice.kns.document.authorization;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
public interface DocumentAuthorizer {
    boolean isAuthorized(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionName, String principalId);
    boolean isAuthorizedByTemplate(org.kuali.rice.krad.bo.BusinessObject bo, String namespaceCode, String permissionTemplateName, String principalId);
    default Set<String> getDocumentActions(org.kuali.rice.krad.document.Document document, org.kuali.rice.kim.api.identity.Person user, Set<String> documentActions) { return documentActions != null ? documentActions : new HashSet<>(); }
    default Set<String> getEditModes(org.kuali.rice.krad.document.Document document, org.kuali.rice.kim.api.identity.Person user, Set<String> editModes) { return editModes != null ? editModes : new HashSet<>(); }

    default boolean canReceiveAdHoc(org.kuali.rice.krad.document.Document document, org.kuali.rice.kim.api.identity.Person user, String actionRequestCode) { return true; }
}
