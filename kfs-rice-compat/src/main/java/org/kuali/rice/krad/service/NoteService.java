package org.kuali.rice.krad.service;

public interface NoteService {
    java.util.List<org.kuali.rice.krad.bo.Note> getByRemoteObjectId(java.lang.String p0);
    org.kuali.rice.krad.bo.Note getNoteByNoteId(java.lang.Long p0);
    void saveNoteList(java.util.List<org.kuali.rice.krad.bo.Note> p0);
    org.kuali.rice.krad.bo.Note save(org.kuali.rice.krad.bo.Note p0);
    void deleteNote(org.kuali.rice.krad.bo.Note p0);
    org.kuali.rice.krad.bo.Note createNote(org.kuali.rice.krad.bo.Note p0, org.kuali.rice.krad.bo.PersistableBusinessObject p1, java.lang.String p2);
}
