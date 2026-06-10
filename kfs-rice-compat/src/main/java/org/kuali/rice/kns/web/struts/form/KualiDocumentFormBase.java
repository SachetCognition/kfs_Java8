package org.kuali.rice.kns.web.struts.form;
import java.util.List;
import java.util.ArrayList;
import org.kuali.rice.kns.web.ui.HeaderField;
public class KualiDocumentFormBase extends KualiForm {
    private org.kuali.rice.krad.document.Document document;
    private List<HeaderField> docInfo = new ArrayList<>();
    private int numColumns = 2;

    public KualiDocumentFormBase() {}
    public org.kuali.rice.krad.document.Document getDocument() { return document; }
    public void setDocument(org.kuali.rice.krad.document.Document doc) { this.document = doc; }
    public void populateHeaderFields(org.kuali.rice.kew.api.WorkflowDocument wd) {}
    protected void customInitMaxUploadSizes() {}
    public boolean isFieldLevelHelpEnabled() { return false; }
    public List<HeaderField> getDocInfo() { return docInfo; }
    public void setDocInfo(List<HeaderField> docInfo) { this.docInfo = docInfo; }
    public int getNumColumns() { return numColumns; }
    public void setNumColumns(int numColumns) { this.numColumns = numColumns; }
    public String getDocumentHandlerUrl(String documentNumber) { return null; }
    protected String buildHtmlLink(String url, String text) { return "<a href=\"" + url + "\">" + text + "</a>"; }
    protected String getDefaultDocumentTypeName() { return null; }
    public boolean hasDocumentId() { return document != null && document.getDocumentNumber() != null && !document.getDocumentNumber().isEmpty(); }
    public String getFormKey() { return null; }
    public void setFormKey(String formKey) {}
    public String getDocFormKey() { return null; }
    public void setDocFormKey(String docFormKey) {}
    public int getCurrentTabIndex() { return 0; }
    public int getNextArbitrarilyHighIndex() { return 0; }
}
