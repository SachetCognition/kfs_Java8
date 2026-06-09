package org.kuali.rice.kew.framework.postprocessor;
public class ProcessDocReport {
    private boolean success;
    private String message;
    public ProcessDocReport(boolean success) { this.success = success; }
    public ProcessDocReport(boolean success, String message) { this.success = success; this.message = message; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
