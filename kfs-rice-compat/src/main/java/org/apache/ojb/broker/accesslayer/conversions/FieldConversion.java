package org.apache.ojb.broker.accesslayer.conversions;
public interface FieldConversion extends java.io.Serializable {
    Object javaToSql(Object source) throws ConversionException;
    Object sqlToJava(Object source) throws ConversionException;
}
