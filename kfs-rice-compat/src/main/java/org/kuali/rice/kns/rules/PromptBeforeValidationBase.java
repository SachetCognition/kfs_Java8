package org.kuali.rice.kns.rules;
public class PromptBeforeValidationBase {
    public PromptBeforeValidationBase() {}
    public static class IsAskingException extends RuntimeException {
        public IsAskingException() { super(); }
    }
}
