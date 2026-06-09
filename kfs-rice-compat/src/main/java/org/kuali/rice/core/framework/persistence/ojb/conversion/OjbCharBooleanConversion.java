package org.kuali.rice.core.framework.persistence.ojb.conversion;

public class OjbCharBooleanConversion implements org.apache.ojb.broker.accesslayer.conversions.FieldConversion {
    public OjbCharBooleanConversion() {}

    public static final java.lang.String DATABASE_BOOLEAN_TRUE_STRING_REPRESENTATION = "Y";
    public static final java.lang.String DATABASE_BOOLEAN_FALSE_STRING_REPRESENTATION = "N";

    public java.lang.Object javaToSql(java.lang.Object p0) {
        if (p0 instanceof Boolean) {
            return ((Boolean) p0).booleanValue() ? DATABASE_BOOLEAN_TRUE_STRING_REPRESENTATION : DATABASE_BOOLEAN_FALSE_STRING_REPRESENTATION;
        }
        return p0;
    }

    public java.lang.Object sqlToJava(java.lang.Object p0) {
        if (p0 instanceof String) {
            return DATABASE_BOOLEAN_TRUE_STRING_REPRESENTATION.equals(p0) ? Boolean.TRUE : Boolean.FALSE;
        }
        return p0;
    }
}
