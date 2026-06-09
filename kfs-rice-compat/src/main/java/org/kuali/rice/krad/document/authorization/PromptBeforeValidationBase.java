package org.kuali.rice.krad.document.authorization;
public class PromptBeforeValidationBase implements PromptBeforeValidation {
    public static class IsAskingException extends RuntimeException {
        public IsAskingException() { super(); }
    }
}
