package org.kuali.rice.kns.lookup;
public interface LookupResultsService {
    java.util.Set<String> retrieveSetOfSelectedObjectIds(String lookupResultsSequenceNumber, String personId);
    java.util.List<org.kuali.rice.kns.web.ui.ResultRow> retrieveResultsTable(String lookupResultsSequenceNumber, String personId) throws Exception;
    void persistResultsTable(String lookupResultsSequenceNumber, java.util.List<org.kuali.rice.kns.web.ui.ResultRow> resultTable, String personId) throws Exception;
    java.util.Collection retrieveSelectedResultBOs(String lookupResultsSequenceNumber, Class boClass, String personId) throws Exception;
}
