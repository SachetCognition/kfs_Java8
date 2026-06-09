package org.apache.ojb.broker.query;

public class ReportQueryByCriteria extends QueryByCriteria {
    public ReportQueryByCriteria(Class targetClass, String[] columns, Criteria criteria) {
        super(targetClass, criteria);
    }
    public ReportQueryByCriteria(Class targetClass, String[] columns, Criteria criteria, boolean distinct) {
        super(targetClass, criteria, distinct);
    }
    public void setAttributes(String[] columns) {}
}
