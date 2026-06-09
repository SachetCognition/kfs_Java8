package org.kuali.rice.core.api.search;

public enum SearchOperator {
    BETWEEN(".."),
    OR("|"),
    AND("&&"),
    NOT("!"),
    EQUAL("="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_EQUAL(">="),
    LESS_THAN_EQUAL("<="),
    LIKE("*"),
    NULL("NULL"),
    NOT_NULL("!NULL");
    
    private final String op;
    SearchOperator(String op) { this.op = op; }
    public String op() { return op; }
    public String toString() { return op; }
}
