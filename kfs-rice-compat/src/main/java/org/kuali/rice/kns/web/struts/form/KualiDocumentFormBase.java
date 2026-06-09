package org.kuali.rice.kns.web.struts.form;
import java.util.List;
import java.util.ArrayList;
public class KualiDocumentFormBase extends KualiForm {
    private org.kuali.rice.krad.document.Document document;
    private List docInfo = new ArrayList();
    private int numColumns = 2;

    public KualiDocumentFormBase() {}
    public org.kuali.rice.krad.document.Document getDocument() { return document; }
    public void setDocument(org.kuali.rice.krad.document.Document doc) { this.document = doc; }
    public void populateHeaderFields(org.kuali.rice.kew.api.WorkflowDocument wd) {}
    protected void customInitMaxUploadSizes() {}
    public boolean isFieldLevelHelpEnabled() { return false; }
    public List getDocInfo() { return docInfo; }
    public void setDocInfo(List docInfo) { this.docInfo = docInfo; }
    public int getNumColumns() { return numColumns; }
    public void setNumColumns(int numColumns) { this.numColumns = numColumns; }
}
