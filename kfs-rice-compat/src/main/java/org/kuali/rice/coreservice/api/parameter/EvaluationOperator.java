package org.kuali.rice.coreservice.api.parameter;

public enum EvaluationOperator {
    ALLOW("A"),
    DISALLOW("D");
    
    private final String code;
    EvaluationOperator(String code) { this.code = code; }
    public String getCode() { return code; }
    
    public static EvaluationOperator fromCode(String code) {
        if (code == null) return null;
        for (EvaluationOperator e : values()) {
            if (e.code.equals(code)) return e;
        }
        return null;
    }
}
