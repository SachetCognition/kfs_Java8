package org.kuali.rice.krad.datadictionary;

import java.util.List;

public interface DataDictionaryEntry {
    String getJstlKey();
    String getFullClassName();
    String getObjectLabel();
    String getObjectDescription();
    List<AttributeDefinition> getAttributes();
    AttributeDefinition getAttributeDefinition(String attributeName);
    Class<?> getEntryClass();
    void completeValidation();
}
