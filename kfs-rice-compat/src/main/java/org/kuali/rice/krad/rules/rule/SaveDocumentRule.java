package org.kuali.rice.krad.rules.rule;
public interface SaveDocumentRule extends BusinessRule {
    boolean processSaveDocument(org.kuali.rice.krad.rules.rule.event.SaveEvent event);
}
