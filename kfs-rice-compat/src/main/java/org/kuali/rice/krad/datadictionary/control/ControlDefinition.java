package org.kuali.rice.krad.datadictionary.control;

public interface ControlDefinition {
    boolean isCheckbox();
    boolean isHidden();
    boolean isRadio();
    boolean isSelect();
    boolean isMultiselect();
    boolean isText();
    boolean isTextarea();
    boolean isCurrency();
    boolean isKualiUser();
    boolean isWorkflowWorkgroup();
    boolean isFile();
    boolean isLookupHidden();
    boolean isLookupReadonly();
    boolean isButton();
    boolean isLink();
    String getValuesFinderClass();
    String getBusinessObjectClass();
    String getKeyAttribute();
    String getLabelAttribute();
    boolean getIncludeKeyInLabel();
    boolean getIncludeBlankRow();
    int getSize();
    int getCols();
    int getRows();
    String getScript();
    String getDatePicker();
    String getExpandedTextArea();
    void setValuesFinderClass(String valuesFinderClass);
}
