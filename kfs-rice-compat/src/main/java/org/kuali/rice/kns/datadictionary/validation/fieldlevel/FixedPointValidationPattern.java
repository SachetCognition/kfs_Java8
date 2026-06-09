package org.kuali.rice.kns.datadictionary.validation.fieldlevel;

public class FixedPointValidationPattern extends org.kuali.rice.krad.datadictionary.validation.ValidationPattern {
    private boolean allowNegative;
    private int precision;
    private int scale;
    
    public FixedPointValidationPattern() {}
    
    public boolean getAllowNegative() { return allowNegative; }
    public void setAllowNegative(boolean allowNegative) { this.allowNegative = allowNegative; }
    public int getPrecision() { return precision; }
    public void setPrecision(int precision) { this.precision = precision; }
    public int getScale() { return scale; }
    public void setScale(int scale) { this.scale = scale; }
}
