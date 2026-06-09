package org.kuali.rice.kew.framework.document.security;

public interface DocumentSecurityAttribute {
    boolean isAuthorizedForDocument(String principalId, org.kuali.rice.kew.doctype.bo.DocumentType docType);
}
