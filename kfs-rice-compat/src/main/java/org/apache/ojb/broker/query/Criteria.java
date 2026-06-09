package org.apache.ojb.broker.query;
public class Criteria implements java.io.Serializable {
    public Criteria() {}
    public void addEqualTo(String attribute, Object value) {}
    public void addNotEqualTo(String attribute, Object value) {}
    public void addLike(String attribute, Object value) {}
    public void addGreaterThan(String attribute, Object value) {}
    public void addLessThan(String attribute, Object value) {}
    public void addGreaterOrEqualThan(String attribute, Object value) {}
    public void addLessOrEqualThan(String attribute, Object value) {}
    public void addIsNull(String attribute) {}
    public void addNotNull(String attribute) {}
    public void addIn(String attribute, java.util.Collection values) {}
    public void addBetween(String attribute, Object lower, Object upper) {}
    public void addAndCriteria(Criteria criteria) {}
    public void addOrCriteria(Criteria criteria) {}
    public void addOrderByAscending(String attribute) {}
    public void addOrderByDescending(String attribute) {}
    public void addColumnEqualTo(String col1, String col2) {}
}
