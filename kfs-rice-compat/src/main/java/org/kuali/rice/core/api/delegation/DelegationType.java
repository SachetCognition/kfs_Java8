package org.kuali.rice.core.api.delegation;

public enum DelegationType implements org.kuali.rice.core.api.mo.common.Coded {
    PRIMARY("P"),
    SECONDARY("S");
    
    private final String code;
    DelegationType(String code) { this.code = code; }
    public String getCode() { return code; }
    public String getLabel() { return name(); }
    
    public static DelegationType fromCode(String code) {
        if (code == null) return null;
        for (DelegationType t : values()) {
            if (t.code.equals(code)) return t;
        }
        return null;
    }
    
    public static DelegationType parseCode(String code) {
        return fromCode(code);
    }
}
