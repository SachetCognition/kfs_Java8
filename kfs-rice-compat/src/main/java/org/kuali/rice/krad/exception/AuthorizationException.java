package org.kuali.rice.krad.exception;
public class AuthorizationException extends Exception {
    public AuthorizationException() { super(); }
    public AuthorizationException(String message) { super(message); }
    public AuthorizationException(String user, String action, String target) { super(user + " " + action + " " + target); }
    public AuthorizationException(String message, Throwable cause) { super(message, cause); }
}
