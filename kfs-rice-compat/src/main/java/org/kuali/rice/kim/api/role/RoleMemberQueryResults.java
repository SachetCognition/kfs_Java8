package org.kuali.rice.kim.api.role;

import java.util.List;
import java.util.ArrayList;

public class RoleMemberQueryResults extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private List<RoleMember> results;
    private int totalRowCount;
    private boolean moreResultsAvailable;
    
    public RoleMemberQueryResults() { this.results = new ArrayList<RoleMember>(); }
    
    public List<RoleMember> getResults() { return results; }
    public int getTotalRowCount() { return totalRowCount; }
    public boolean isMoreResultsAvailable() { return moreResultsAvailable; }
}
