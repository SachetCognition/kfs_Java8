package org.kuali.rice.krad.datadictionary;

public interface InactivationBlockingMetadata {
    java.lang.String getBlockedReferencePropertyName();
    java.lang.Class getBlockedBusinessObjectClass();
    java.lang.String getInactivationBlockingDetectionServiceBeanName();
    java.lang.Class getBlockingReferenceBusinessObjectClass();
    java.lang.String getRelationshipLabel();
}
