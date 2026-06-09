package org.apache.ojb.broker;

public class OptimisticLockException extends PersistenceBrokerException {
    public OptimisticLockException() {}
    public OptimisticLockException(String msg) { super(msg); }
    public OptimisticLockException(String msg, Throwable cause) { super(msg, cause); }
}
