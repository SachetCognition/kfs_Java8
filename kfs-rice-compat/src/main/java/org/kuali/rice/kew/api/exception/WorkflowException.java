package org.kuali.rice.kew.api.exception;
public class WorkflowException extends Exception {
    public WorkflowException() { super(); }
    public WorkflowException(String message) { super(message); }
    public WorkflowException(String message, Throwable cause) { super(message, cause); }
}
