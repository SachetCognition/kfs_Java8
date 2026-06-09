package org.kuali.rice.core.api.membership;
public enum MemberType {
    PRINCIPAL("P"), GROUP("G"), ROLE("R");
    private String code;
    MemberType(String code) { this.code = code; }
    public String getCode() { return code; }
    public String toString() { return code; }
    public static MemberType fromCode(String code) {
        for (MemberType mt : values()) {
            if (mt.code.equals(code)) return mt;
        }
        return null;
    }
}
