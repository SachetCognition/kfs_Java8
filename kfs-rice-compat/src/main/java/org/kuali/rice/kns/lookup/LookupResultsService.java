package org.kuali.rice.kns.lookup;
public interface LookupResultsService {
    java.util.Set<String> retrieveSetOfSelectedObjectIds(String lookupResultsSequenceNumber, String personId);
}
