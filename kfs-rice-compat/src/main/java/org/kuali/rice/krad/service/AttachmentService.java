package org.kuali.rice.krad.service;

public interface AttachmentService {
    org.kuali.rice.krad.bo.Attachment createAttachment(org.kuali.rice.krad.bo.PersistableBusinessObject p0, java.lang.String p1, java.lang.String p2, int p3, java.io.InputStream p4, java.lang.String p5) throws java.io.IOException;
    java.io.InputStream retrieveAttachmentContents(org.kuali.rice.krad.bo.Attachment p0) throws java.io.IOException;
    void deleteAttachmentContents(org.kuali.rice.krad.bo.Attachment p0);
    void moveAttachmentWherePending(org.kuali.rice.krad.bo.Note p0);
    void deletePendingAttachmentsModifiedBefore(long p0);
    org.kuali.rice.krad.bo.Attachment getAttachmentByNoteId(java.lang.Long p0);
}
