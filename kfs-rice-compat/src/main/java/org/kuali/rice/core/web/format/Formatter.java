package org.kuali.rice.core.web.format;
public class Formatter {
    public Formatter() {}
    public static void setFormatterType(String propertyType, Class formatterType) {}
    public Object format(Object value) { return value; }
    public Object convertFromPresentationFormat(Object value) { return value; }

    public static Formatter findFormatter(Class<? extends Object> clazz) { return null; }
    public static Formatter getFormatter(Class<? extends Object> clazz) { return new Formatter(); }
    protected Object convertToObject(String value) { return value; }
    public String formatForPresentation(Object value) { return value != null ? value.toString() : ""; }
}
