package org.kuali.rice.kew.exception;
public class WorkflowServiceErrorException extends RuntimeException {
    public WorkflowServiceErrorException() { super(); }
    public WorkflowServiceErrorException(String message) { super(message); }
    public WorkflowServiceErrorException(String message, java.util.List errors) { super(message); }
}
