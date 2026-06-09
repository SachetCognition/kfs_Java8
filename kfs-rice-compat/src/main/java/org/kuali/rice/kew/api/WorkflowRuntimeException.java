package org.kuali.rice.kew.api;
public class WorkflowRuntimeException extends RuntimeException {
    public WorkflowRuntimeException() { super(); }
    public WorkflowRuntimeException(String message) { super(message); }
    public WorkflowRuntimeException(String message, Throwable cause) { super(message, cause); }
    public WorkflowRuntimeException(Throwable cause) { super(cause); }
}
