package org.kuali.rice.krad.document;
import java.util.List;
import java.util.ArrayList;
public class DocumentBase extends org.kuali.rice.krad.bo.PersistableBusinessObjectBase implements Document {
    protected String documentNumber;
    protected org.kuali.rice.krad.bo.DocumentHeader documentHeader;
    protected List<org.kuali.rice.krad.bo.Note> notes = new ArrayList<org.kuali.rice.krad.bo.Note>();
    
    public DocumentBase() {}
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public org.kuali.rice.krad.bo.DocumentHeader getDocumentHeader() { return documentHeader; }
    public void setDocumentHeader(org.kuali.rice.krad.bo.DocumentHeader documentHeader) { this.documentHeader = documentHeader; }
    public String getDocumentTitle() { return null; }
    public void doRouteStatusChange(org.kuali.rice.krad.bo.DocumentHeader documentHeader) {}
    public void doRouteStatusChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange statusChangeEvent) {}
    public void doRouteLevelChange(org.kuali.rice.kew.framework.postprocessor.DocumentRouteLevelChange levelChangeEvent) {}
    public void doActionTaken(org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent event) {}
    public void afterActionTaken(org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent event, org.kuali.rice.kew.framework.postprocessor.AfterProcessEvent afterEvent) {}
    public void prepareForSave() {}
    public void prepareForSave(org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent event) {}
    public void processAfterRetrieve() {}
    public void populateDocumentForRouting() {}
    public void setNewDocumentHeader() {}
    public List<String> getWorkflowEngineDocumentIdsToLock() { return new ArrayList<String>(); }
    public List buildListOfDeletionAwareLists() { return new ArrayList(); }
    public void toCopy() throws org.kuali.rice.kew.api.exception.WorkflowException, IllegalStateException {}
    public List<org.kuali.rice.krad.bo.Note> getNotes() { return notes; }
    public void setNotes(List<org.kuali.rice.krad.bo.Note> notes) { this.notes = notes; }
    public void addNote(org.kuali.rice.krad.bo.Note note) { notes.add(note); }
    public org.kuali.rice.krad.bo.Note getNote(int index) { return notes.get(index); }
    public boolean removeNote(org.kuali.rice.krad.bo.Note note) { return notes.remove(note); }
    public void logErrors() {}
    public String getApplicationDocumentStatus() { return null; }
    public void setApplicationDocumentStatus(String status) {}
    public void performForceUppercase(Object bo) {}
    public void addCopyErrorDocumentNote(String errorText) {}
    public void postProcessSave(org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent event) {}
    public List generateSaveEvents() { return new ArrayList(); }
    public String discoverDocumentTypeName() { return null; }
    public void refreshNonUpdateableReferences() {}
    public java.util.List getAdHocRoutePersons() { return new java.util.ArrayList(); }
    public java.util.List getAdHocRouteWorkgroups() { return new java.util.ArrayList(); }
}
