package org.kuali.rice.krad.bo;
public class Note extends PersistableBusinessObjectBase {
    private Long noteIdentifier;
    private String noteText;
    private String noteTopicText;
    private String authorUniversalIdentifier;
    private String remoteObjectIdentifier;
    private Attachment attachment;
    public Note() {}
    public Long getNoteIdentifier() { return noteIdentifier; }
    public void setNoteIdentifier(Long id) { this.noteIdentifier = id; }
    public String getNoteText() { return noteText; }
    public void setNoteText(String noteText) { this.noteText = noteText; }
    public String getNoteTopicText() { return noteTopicText; }
    public void setNoteTopicText(String noteTopicText) { this.noteTopicText = noteTopicText; }
    public String getAuthorUniversalIdentifier() { return authorUniversalIdentifier; }
    public void setAuthorUniversalIdentifier(String id) { this.authorUniversalIdentifier = id; }
    public String getRemoteObjectIdentifier() { return remoteObjectIdentifier; }
    public void setRemoteObjectIdentifier(String id) { this.remoteObjectIdentifier = id; }
    public Attachment getAttachment() { return attachment; }
    public void setAttachment(Attachment attachment) { this.attachment = attachment; }
}
