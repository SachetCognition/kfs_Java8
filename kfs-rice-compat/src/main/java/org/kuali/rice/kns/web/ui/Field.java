package org.kuali.rice.kns.web.ui;
import java.util.List;
import java.util.ArrayList;

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
    public static final String CONTAINER = "container";
    public static final String QUICKFINDER = "quickFinder";
    public static final String IMAGE_SUBMIT = "imageSubmit";
    public static final String READONLY = "readOnly";
    public static final String EDITABLE = "editable";
    public static final String KUALI_USER = "kualiUser";
    public static final String WORKFLOW_WORKGROUP = "workflowWorkgroup";

    private String propertyName;
    private String fieldLabel;
    private String fieldType;
    private String propertyValue;
    private String quickFinderClassNameImpl;
    private org.kuali.rice.kns.lookup.HtmlData inquiryURL;
    private String webOnBlurHandler;
    private int maxLength;
    private List<String> fieldValidValues = new ArrayList<>();
    private String propertyPrefix;

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
    public String getQuickFinderClassNameImpl() { return quickFinderClassNameImpl; }
    public void setQuickFinderClassNameImpl(String s) { this.quickFinderClassNameImpl = s; }
    public org.kuali.rice.kns.lookup.HtmlData getInquiryURL() { return inquiryURL; }
    public void setInquiryURL(org.kuali.rice.kns.lookup.HtmlData inquiryURL) { this.inquiryURL = inquiryURL; }
    public String getWebOnBlurHandler() { return webOnBlurHandler; }
    public void setWebOnBlurHandler(String handler) { this.webOnBlurHandler = handler; }
    public int getMaxLength() { return maxLength; }
    public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
    public List<String> getFieldValidValues() { return fieldValidValues; }
    public void setFieldValidValues(List<String> fieldValidValues) { this.fieldValidValues = fieldValidValues; }
    public String getPropertyPrefix() { return propertyPrefix; }
    public void setPropertyPrefix(String propertyPrefix) { this.propertyPrefix = propertyPrefix; }
    public boolean isReadOnly() { return false; }
    public void setReadOnly(boolean readOnly) {}
    public void setFieldRequired(boolean required) {}
    public boolean isFieldRequired() { return false; }
    public int getSize() { return 0; }
    public void setSize(int size) {}
    public String getStyleClass() { return ""; }
    public void setStyleClass(String styleClass) {}
    public int getFormattedMaxLength() { return maxLength; }
    public void setFormattedMaxLength(int len) {}
    public String getName() { return propertyName; }
    public void setName(String name) { this.propertyName = name; }

    public String getLookupParameters() { return null; }
    public void setLookupParameters(java.util.Map<String, String> params) {}
    public String getFieldConversions() { return null; }
    public void setFieldConversions(java.util.Map<String, String> conversions) {}
    public boolean isSecure() { return false; }
    public org.kuali.rice.krad.datadictionary.mask.MaskFormatter getDisplayMask() { return new org.kuali.rice.krad.datadictionary.mask.MaskFormatter(); }
    public String getId() { return null; }
    public void setId(String id) {}
    public boolean isDatePicker() { return false; }
    public static final String TEXT_AREA = "TEXT_AREA";
    public String getDefaultValue() { return null; }
    public void setFieldDataType(String type) {}
    public void setColumnVisible(boolean visible) {}
}