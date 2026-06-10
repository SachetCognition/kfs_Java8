package org.kuali.rice.krad.service;

public interface InactivationBlockingDetectionService {
    boolean hasABlockingRecord(org.kuali.rice.krad.bo.BusinessObject p0, org.kuali.rice.krad.datadictionary.InactivationBlockingMetadata p1);
    java.util.Collection<org.kuali.rice.krad.bo.BusinessObject> listAllBlockerRecords(org.kuali.rice.krad.bo.BusinessObject p0, org.kuali.rice.krad.datadictionary.InactivationBlockingMetadata p1);

    java.util.Map<String, String> buildInactivationBlockerQueryMap(org.kuali.rice.krad.bo.BusinessObject blockedBo, org.kuali.rice.krad.datadictionary.InactivationBlockingMetadata blockingMetadata);
}
