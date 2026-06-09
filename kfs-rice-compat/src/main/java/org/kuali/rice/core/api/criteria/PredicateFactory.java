package org.kuali.rice.core.api.criteria;

public class PredicateFactory {
    private PredicateFactory() {}
    
    public static Predicate equal(String propertyName, Object value) { return new SimplePredicate(propertyName, value); }
    public static Predicate like(String propertyName, Object value) { return new SimplePredicate(propertyName, value); }
    public static Predicate and(Predicate... predicates) { return new CompoundPredicate(predicates); }
    public static Predicate or(Predicate... predicates) { return new CompoundPredicate(predicates); }
    
    private static class SimplePredicate implements Predicate {
        SimplePredicate(String prop, Object val) {}
    }
    private static class CompoundPredicate implements Predicate {
        CompoundPredicate(Predicate... preds) {}
    }
}
