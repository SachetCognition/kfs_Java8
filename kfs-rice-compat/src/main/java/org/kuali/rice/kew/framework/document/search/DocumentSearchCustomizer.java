package org.kuali.rice.kew.framework.document.search;

public interface DocumentSearchCustomizer {
    String getDocumentSearchDocumentTypeName();
    boolean isCustomizeCriteriaEnabled(String documentTypeName);
    boolean isCustomizeResultsEnabled(String documentTypeName);
    boolean isCustomizeClearCriteriaEnabled(String documentTypeName);
}
