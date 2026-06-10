package org.kuali.rice.kns.lookup;
public class LookupResultsServiceImpl implements LookupResultsService {
    public LookupResultsServiceImpl() {}
    public java.util.Set<String> retrieveSetOfSelectedObjectIds(String lookupResultsSequenceNumber, String personId) {
        return new java.util.HashSet<String>();
    }
    public java.util.List<org.kuali.rice.kns.web.ui.ResultRow> retrieveResultsTable(String lookupResultsSequenceNumber, String personId) throws Exception {
        return new java.util.ArrayList<>();
    }
    public void persistResultsTable(String lookupResultsSequenceNumber, java.util.List<org.kuali.rice.kns.web.ui.ResultRow> resultTable, String personId) throws Exception {}
    public java.util.Collection retrieveSelectedResultBOs(String lookupResultsSequenceNumber, Class boClass, String personId) throws Exception { return new java.util.ArrayList(); }
}
