package org.apache.ojb.broker;
public class PersistenceBrokerException extends RuntimeException {
    public PersistenceBrokerException() {}
    public PersistenceBrokerException(String msg) { super(msg); }
    public PersistenceBrokerException(String msg, Throwable cause) { super(msg, cause); }
    public PersistenceBrokerException(Throwable cause) { super(cause); }
}
