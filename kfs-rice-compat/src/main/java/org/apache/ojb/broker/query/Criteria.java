package org.apache.ojb.broker.query;
public class Criteria implements java.io.Serializable {
    public static final String PARENT_QUERY_PREFIX = "parentQuery.";
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
    public void addNotExists(QueryByCriteria subQuery) {}
    public void addExists(QueryByCriteria subQuery) {}
    public void addNotIn(String attribute, java.util.Collection values) {}
    public void addColumnEqualToField(String column, String field) {}
    public void addEqualToField(String attribute, String fieldPath) {}
    public void setEmbraced(boolean embrace) {}

    public void addColumnIsNull(String column) {}
    public void addColumnNotNull(String column) {}
    public void addNotLike(String attribute, String value) {}
}
