package org.apache.ojb.broker;
public interface PersistenceBroker {
    void close();
    Object getObjectByQuery(org.apache.ojb.broker.query.Query query);
    java.util.Iterator getIteratorByQuery(org.apache.ojb.broker.query.Query query);
    int getCount(org.apache.ojb.broker.query.Query query);
    void store(Object obj);
    void delete(Object obj);
    void clearCache();
    void beginTransaction();
    void commitTransaction();
    void abortTransaction();
    boolean isInTransaction();
    org.apache.ojb.broker.metadata.ClassDescriptor getClassDescriptor(Class clazz);
    java.util.Collection getCollectionByQuery(org.apache.ojb.broker.query.Query q);
    Object getObjectByIdentity(Object id);
    void retrieveAllReferences(Object obj);
    void retrieveReference(Object obj, String refName);
}
