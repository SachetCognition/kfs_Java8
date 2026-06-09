package org.kuali.rice.kew.api.action;

public enum ActionType implements org.kuali.rice.core.api.mo.common.Coded {
    ACKNOWLEDGE("k"),
    APPROVE("a"),
    BLANKET_APPROVE("B"),
    CANCEL("c"),
    COMPLETE("C"),
    DISAPPROVE("d"),
    FYI("f"),
    SAVE("s"),
    SU_ACKNOWLEDGE("K"),
    SU_FYI("F"),
    SU_COMPLETE("O"),
    SU_APPROVE("A"),
    SU_ROUTE_NODE_APPROVE("v"),
    SU_RETURN_TO_PREVIOUS("p"),
    SU_DISAPPROVE("D"),
    SU_CANCEL("X"),
    SU_BLANKET_APPROVE("b"),
    RECALL("L"),
    ROUTE("o"),
    ADHOC_REQUEST("r"),
    ADHOC_REQUEST_REVOKE("V"),
    LOG_MESSAGE("l"),
    MOVE("m"),
    TAKE_GROUP_AUTHORITY("t"),
    RELEASE_GROUP_AUTHORITY("T"),
    RETURN_TO_PREVIOUS("z");
    
    private final String code;
    ActionType(String code) { this.code = code; }
    public String getCode() { return code; }
    
    public static ActionType fromCode(String code) {
        if (code == null) return null;
        for (ActionType t : values()) {
            if (t.code.equals(code)) return t;
        }
        return null;
    }
}
