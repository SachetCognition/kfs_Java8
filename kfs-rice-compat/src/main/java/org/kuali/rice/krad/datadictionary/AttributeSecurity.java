package org.kuali.rice.krad.datadictionary;

public class AttributeSecurity {
    private boolean readOnly;
    private boolean hide;
    private boolean mask;
    private boolean partialMask;
    
    public AttributeSecurity() {}
    
    public boolean isReadOnly() { return readOnly; }
    public void setReadOnly(boolean readOnly) { this.readOnly = readOnly; }
    public boolean isHide() { return hide; }
    public void setHide(boolean hide) { this.hide = hide; }
    public boolean isMask() { return mask; }
    public void setMask(boolean mask) { this.mask = mask; }
    public boolean isPartialMask() { return partialMask; }
    public void setPartialMask(boolean partialMask) { this.partialMask = partialMask; }
}
