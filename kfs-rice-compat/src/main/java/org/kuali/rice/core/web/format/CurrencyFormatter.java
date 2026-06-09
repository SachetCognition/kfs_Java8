package org.kuali.rice.core.web.format;

public class CurrencyFormatter extends Formatter {
    public CurrencyFormatter() {}
    
    public Object format(Object value) {
        if (value == null) return "";
        return value.toString();
    }
    
    public Object convertFromPresentationFormat(Object value) { return value; }
}
