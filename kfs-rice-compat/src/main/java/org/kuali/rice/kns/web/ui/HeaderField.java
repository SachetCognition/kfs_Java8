package org.kuali.rice.kns.web.ui;
public class HeaderField {
    public static final HeaderField EMPTY_FIELD = new HeaderField();
    private String id;
    private String ddAttributeEntryName;
    private String displayValue;
    private String nonLookupValue;
    
    public HeaderField() {}
    public HeaderField(String ddAttributeEntryName, String displayValue) {
        this.ddAttributeEntryName = ddAttributeEntryName;
        this.displayValue = displayValue;
    }
    public HeaderField(String ddAttributeEntryName, String displayValue, String nonLookupValue) {
        this.ddAttributeEntryName = ddAttributeEntryName;
        this.displayValue = displayValue;
        this.nonLookupValue = nonLookupValue;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDdAttributeEntryName() { return ddAttributeEntryName; }
    public void setDdAttributeEntryName(String s) { this.ddAttributeEntryName = s; }
    public String getDisplayValue() { return displayValue; }
    public void setDisplayValue(String s) { this.displayValue = s; }
    public String getNonLookupValue() { return nonLookupValue; }
    public void setNonLookupValue(String s) { this.nonLookupValue = s; }
}
