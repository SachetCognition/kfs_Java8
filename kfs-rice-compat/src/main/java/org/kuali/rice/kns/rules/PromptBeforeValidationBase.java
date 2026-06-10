package org.kuali.rice.kns.rules;
import org.kuali.rice.krad.document.Document;
public class PromptBeforeValidationBase {
    protected org.kuali.rice.kns.rule.event.PromptBeforeValidationEvent event;
    public PromptBeforeValidationBase() {}
    public boolean doPrompts(Document document) { return true; }
    protected boolean askOrAnalyzeYesNoQuestion(String questionId, String questionText) { return true; }
    public static class IsAskingException extends RuntimeException {
        public IsAskingException() { super(); }
    }
}
