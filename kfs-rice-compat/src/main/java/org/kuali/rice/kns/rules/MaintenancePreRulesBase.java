package org.kuali.rice.kns.rules;
public class MaintenancePreRulesBase {
    protected static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MaintenancePreRulesBase.class);
    public MaintenancePreRulesBase() {}
    public boolean doCustomPreRules(org.kuali.rice.krad.document.Document document) { return true; }
    protected boolean askOrAnalyzeYesNoQuestion(String questionId, String questionText) { return true; }
    protected void resumeAfterQuestion() {}
    protected org.kuali.rice.krad.document.Document getDocument() { return null; }
    protected String getQuestion() { return null; }
    protected String getButtonClicked() { return null; }
}
