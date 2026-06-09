package org.kuali.rice.kns.web.ui;
public class Field {
    public static final String EMPTY_FIELD = "emptyField";
    public static final String TEXT = "text";
    public static final String HIDDEN = "hidden";
    public static final String DROPDOWN = "dropdown";
    public static final String RADIO = "radio";
    public static final String CHECKBOX = "checkbox";
    public static final String MULTISELECT = "multiselect";
    public static final String TEXTAREA = "textarea";
    public static final String CURRENCY = "currency";
    public static final String DATE = "date";
    public static final String LOOKUP_READONLY = "lookupreadonly";
    public static final String LOOKUP_HIDDEN = "lookuphidden";
    
    private String propertyName;
    private String fieldLabel;
    private String fieldType;
    private String propertyValue;
    
    public Field() {}
    public Field(String fieldLabel, String propertyName) { this.fieldLabel = fieldLabel; this.propertyName = propertyName; }
    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public String getFieldLabel() { return fieldLabel; }
    public void setFieldLabel(String fieldLabel) { this.fieldLabel = fieldLabel; }
    public String getFieldType() { return fieldType; }
    public void setFieldType(String fieldType) { this.fieldType = fieldType; }
    public String getPropertyValue() { return propertyValue; }
    public void setPropertyValue(String propertyValue) { this.propertyValue = propertyValue; }
}
