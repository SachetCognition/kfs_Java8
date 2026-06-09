package org.kuali.rice.kew.framework.document.attribute;

public interface SearchableAttribute {
    java.lang.String generateSearchContent(org.kuali.rice.kew.api.extension.ExtensionDefinition p0, java.lang.String p1, org.kuali.rice.kew.api.document.attribute.WorkflowAttributeDefinition p2);
    java.util.List<org.kuali.rice.kew.api.document.attribute.DocumentAttribute> extractDocumentAttributes(org.kuali.rice.kew.api.extension.ExtensionDefinition p0, org.kuali.rice.kew.api.document.DocumentWithContent p1);
    java.util.List<org.kuali.rice.core.api.uif.RemotableAttributeField> getSearchFields(org.kuali.rice.kew.api.extension.ExtensionDefinition p0, java.lang.String p1);
    java.util.List<org.kuali.rice.core.api.uif.RemotableAttributeError> validateDocumentAttributeCriteria(org.kuali.rice.kew.api.extension.ExtensionDefinition p0, org.kuali.rice.kew.api.document.search.DocumentSearchCriteria p1);
}
