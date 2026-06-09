package org.kuali.rice.kns.document.authorization;

public class FieldRestriction {
    private String fieldName;
    private boolean masked;
    private boolean partiallyMasked;
    private boolean readOnly;
    private boolean hidden;
    
    public FieldRestriction() {}
    public FieldRestriction(String fieldName) { this.fieldName = fieldName; }
    
    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public boolean isMasked() { return masked; }
    public void setMasked(boolean masked) { this.masked = masked; }
    public boolean isPartiallyMasked() { return partiallyMasked; }
    public void setPartiallyMasked(boolean partiallyMasked) { this.partiallyMasked = partiallyMasked; }
    public boolean isReadOnly() { return readOnly; }
    public void setReadOnly(boolean readOnly) { this.readOnly = readOnly; }
    public boolean isHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    
    public boolean shouldBeEncrypted() { return masked || partiallyMasked; }
    public String getMaskFormatter() { return ""; }
}
