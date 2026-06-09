package org.kuali.rice.krad.service;

public interface SequenceAccessorService {
    Long getNextAvailableSequenceNumber(String sequenceName);
    Long getNextAvailableSequenceNumber(String sequenceName, Class clazz);
}
