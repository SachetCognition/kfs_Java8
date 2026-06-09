package org.kuali.rice.krad.exception;
public class InfrastructureException extends RuntimeException {
    public InfrastructureException() { super(); }
    public InfrastructureException(String message) { super(message); }
    public InfrastructureException(String message, Throwable cause) { super(message, cause); }
    public InfrastructureException(Throwable cause) { super(cause); }
}
