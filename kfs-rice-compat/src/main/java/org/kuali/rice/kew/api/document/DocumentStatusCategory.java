package org.kuali.rice.kew.api.document;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum DocumentStatusCategory {
    PENDING(DocumentStatus.INITIATED, DocumentStatus.SAVED, DocumentStatus.ENROUTE),
    SUCCESSFUL(DocumentStatus.PROCESSED, DocumentStatus.FINAL),
    UNSUCCESSFUL(DocumentStatus.CANCELED, DocumentStatus.DISAPPROVED, DocumentStatus.RECALLED);
    
    private final List<DocumentStatus> statuses;
    
    DocumentStatusCategory(DocumentStatus... statuses) {
        this.statuses = Collections.unmodifiableList(Arrays.asList(statuses));
    }
    
    public List<DocumentStatus> getStatuses() { return statuses; }
}
