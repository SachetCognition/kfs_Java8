package org.kuali.rice.krad.datadictionary.parse;
public class StringMapConverter implements org.springframework.core.convert.converter.Converter<String, java.util.Map<String, String>> {
    public java.util.Map<String, String> convert(String source) { return new java.util.HashMap<>(); }
}
