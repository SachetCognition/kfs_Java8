package org.apache.ojb.broker.accesslayer;
public class QueryCustomizerDefaultImpl implements QueryCustomizer {
    public QueryCustomizerDefaultImpl() {}
    public String getAttribute(String key) { return null; }
    public String getAttribute(String key, String defaultValue) { return defaultValue; }
    public void addAttribute(String key, String value) {}
    public org.apache.ojb.broker.query.Query customizeQuery(Object anObject, org.apache.ojb.broker.PersistenceBroker broker, org.apache.ojb.broker.metadata.CollectionDescriptor cod, org.apache.ojb.broker.query.QueryByCriteria query) { return query; }
    public org.apache.ojb.broker.metadata.ClassDescriptor getClassDescriptor() { return null; }
}