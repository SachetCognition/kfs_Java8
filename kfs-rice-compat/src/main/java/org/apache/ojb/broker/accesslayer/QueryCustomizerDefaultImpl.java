package org.apache.ojb.broker.accesslayer;
public class QueryCustomizerDefaultImpl implements QueryCustomizer {
    public QueryCustomizerDefaultImpl() {}
    public String getAttribute(String key) { return null; }
    public String getAttribute(String key, String defaultValue) { return defaultValue; }
    public void addAttribute(String key, String value) {}
}
