package org.kuali.rice.kew.api.document;
public enum DocumentStatus {
    INITIATED("I"), SAVED("S"), ENROUTE("R"), PROCESSED("P"), FINAL("F"),
    CANCELED("X"), DISAPPROVED("D"), EXCEPTION("E"), RECALLED("L");
    private String code;
    DocumentStatus(String code) { this.code = code; }
    public String getCode() { return code; }
    public String getLabel() { return name(); }
    public String getCategory() { return null; }
    public static DocumentStatus fromCode(String code) {
        for (DocumentStatus ds : values()) {
            if (ds.code.equals(code)) return ds;
        }
        return null;
    }
}
