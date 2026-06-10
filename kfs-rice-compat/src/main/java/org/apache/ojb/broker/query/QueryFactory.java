package org.apache.ojb.broker.query;

public class QueryFactory {
    public static QueryByCriteria newQuery(Class targetClass, Criteria criteria) {
        return new QueryByCriteria(targetClass, criteria);
    }
    public static QueryByCriteria newQuery(Class targetClass, Criteria criteria, boolean distinct) {
        return new QueryByCriteria(targetClass, criteria, distinct);
    }
    public static ReportQueryByCriteria newReportQuery(Class targetClass, Criteria criteria) {
        return new ReportQueryByCriteria(targetClass, new String[]{}, criteria);
    }
    public static ReportQueryByCriteria newReportQuery(Class targetClass, String[] columns, Criteria criteria) {
        return new ReportQueryByCriteria(targetClass, columns, criteria);
    }
    public static ReportQueryByCriteria newReportQuery(Class targetClass, String[] columns, Criteria criteria, boolean distinct) {
        return new ReportQueryByCriteria(targetClass, columns, criteria, distinct);
    }
}
