package org.kuali.rice.kns.document.authorization;
public class DocumentPresentationControllerBase implements DocumentPresentationController {
    public DocumentPresentationControllerBase() {}
    public boolean canRecall(org.kuali.rice.krad.document.Document document) { return false; }
    protected org.kuali.rice.coreservice.framework.parameter.ParameterService getParameterService() { return null; }
    public org.kuali.rice.krad.service.DataDictionaryService getDataDictionaryService() { return null; }
}
