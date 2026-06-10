package org.kuali.rice.krad.document;
public class TransactionalDocumentBase extends DocumentBase implements TransactionalDocument {
    public TransactionalDocumentBase() {}
    public void refreshNonUpdateableReferences() {}
    public java.util.List getAdHocRoutePersons() { return new java.util.ArrayList(); }
    public java.util.List getAdHocRouteWorkgroups() { return new java.util.ArrayList(); }
}
