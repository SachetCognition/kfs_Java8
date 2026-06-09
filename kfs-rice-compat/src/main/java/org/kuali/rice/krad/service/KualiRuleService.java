package org.kuali.rice.krad.service;

public interface KualiRuleService {
    boolean applyRules(org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent event);
}
