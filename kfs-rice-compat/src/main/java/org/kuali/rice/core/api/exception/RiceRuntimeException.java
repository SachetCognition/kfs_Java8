package org.kuali.rice.core.api.exception;

public class RiceRuntimeException extends java.lang.RuntimeException {
    public RiceRuntimeException() {}
    public RiceRuntimeException(String message) { super(message); }
    public RiceRuntimeException(String message, Throwable cause) { super(message, cause); }
    public RiceRuntimeException(Throwable cause) { super(cause); }
}
