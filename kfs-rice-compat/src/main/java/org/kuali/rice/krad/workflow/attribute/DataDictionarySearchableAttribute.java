package org.kuali.rice.krad.workflow.attribute;

import java.util.List;
import java.util.ArrayList;

public class DataDictionarySearchableAttribute implements org.kuali.rice.kew.framework.document.attribute.SearchableAttribute {
    public DataDictionarySearchableAttribute() {}

    public static final String DATA_TYPE_BOOLEAN = "";

    public String generateSearchContent(org.kuali.rice.kew.api.extension.ExtensionDefinition extensionDefinition, String documentTypeName, org.kuali.rice.kew.api.document.attribute.WorkflowAttributeDefinition attributeDefinition) { return null; }
    public List<org.kuali.rice.kew.api.document.attribute.DocumentAttribute> extractDocumentAttributes(org.kuali.rice.kew.api.extension.ExtensionDefinition extensionDefinition, org.kuali.rice.kew.api.document.DocumentWithContent documentWithContent) { return new ArrayList<org.kuali.rice.kew.api.document.attribute.DocumentAttribute>(); }
    public List<org.kuali.rice.core.api.uif.RemotableAttributeField> getSearchFields(org.kuali.rice.kew.api.extension.ExtensionDefinition extensionDefinition, String documentTypeName) { return new ArrayList<org.kuali.rice.core.api.uif.RemotableAttributeField>(); }
    public List<org.kuali.rice.core.api.uif.RemotableAttributeError> validateDocumentAttributeCriteria(org.kuali.rice.kew.api.extension.ExtensionDefinition extensionDefinition, org.kuali.rice.kew.api.document.search.DocumentSearchCriteria criteria) { return new ArrayList<org.kuali.rice.core.api.uif.RemotableAttributeError>(); }
}
