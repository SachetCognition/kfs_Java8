package org.kuali.rice.kns.document.authorization;

import java.util.Set;

public interface BusinessObjectRestrictions {
    boolean hasAnyFieldRestrictions();
    boolean hasRestriction(String fieldName);
    FieldRestriction getFieldRestriction(String fieldName);
    Set<String> getRestrictedFieldNames();
    void addFullyMaskedField(String fieldName);
    void addPartiallyMaskedField(String fieldName);
}
