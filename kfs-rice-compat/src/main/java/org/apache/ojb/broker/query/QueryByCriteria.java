package org.apache.ojb.broker.query;
public class QueryByCriteria implements Query {
    public QueryByCriteria(Class targetClass, Criteria criteria) {}
    public QueryByCriteria(Class targetClass, Criteria criteria, boolean distinct) {}
    public Class getSearchClass() { return null; }
    public Criteria getCriteria() { return null; }
    public void setCriteria(Criteria criteria) {}
    public void addOrderByAscending(String name) {}
    public void addOrderByDescending(String name) {}
    public void setStartAtIndex(int start) {}
    public void setEndAtIndex(int end) {}
}
