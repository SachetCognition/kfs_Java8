package org.kuali.rice.kns.web.struts.form;
import java.util.Map;
import java.util.HashMap;
public class KualiTransactionalDocumentFormBase extends org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase {
    protected Map forcedLookupOptionalFields = new HashMap();
    public KualiTransactionalDocumentFormBase() {}

    public void populate(javax.servlet.http.HttpServletRequest p0) { super.populate(p0); }
    public org.kuali.rice.krad.document.TransactionalDocument getTransactionalDocument() { return null; }
    public java.util.Map getForcedReadOnlyFields() { return new java.util.HashMap(); }
    public void setForcedReadOnlyFields(java.util.Map p0) {  }
    public Map getForcedLookupOptionalFields() { return forcedLookupOptionalFields; }
    public void setForcedLookupOptionalFields(Map fields) { this.forcedLookupOptionalFields = fields; }
    public boolean shouldMethodToCallParameterBeUsed(java.lang.String p0, java.lang.String p1, javax.servlet.http.HttpServletRequest p2) { return false; }
}
