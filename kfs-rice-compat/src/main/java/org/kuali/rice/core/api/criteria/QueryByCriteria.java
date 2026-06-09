package org.kuali.rice.core.api.criteria;
public class QueryByCriteria {
    public QueryByCriteria() {}
    
    public static class Builder {
        public Builder() {}
        public static Builder create() { return new Builder(); }
        public Builder setPredicates(Predicate... predicates) { return this; }
        public QueryByCriteria build() { return new QueryByCriteria(); }
    }
}
