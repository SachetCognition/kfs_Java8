package org.apache.ojb.broker;
public interface PersistenceBrokerAware {
    void beforeInsert(PersistenceBroker broker) throws PersistenceBrokerException;
    void afterInsert(PersistenceBroker broker) throws PersistenceBrokerException;
    void beforeUpdate(PersistenceBroker broker) throws PersistenceBrokerException;
    void afterUpdate(PersistenceBroker broker) throws PersistenceBrokerException;
    void beforeDelete(PersistenceBroker broker) throws PersistenceBrokerException;
    void afterDelete(PersistenceBroker broker) throws PersistenceBrokerException;
    void afterLookup(PersistenceBroker broker) throws PersistenceBrokerException;
}
