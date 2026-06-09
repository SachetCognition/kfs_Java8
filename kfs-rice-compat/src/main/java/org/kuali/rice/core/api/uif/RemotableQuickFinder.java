package org.kuali.rice.core.api.uif;

import java.util.Map;
import java.util.HashMap;

public class RemotableQuickFinder extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public RemotableQuickFinder() {}
    
    public static class Builder implements org.kuali.rice.core.api.mo.ModelBuilder, java.io.Serializable {
        private String baseLookupUrl;
        private String dataObjectClass;
        private Map<String, String> lookupParameters = new HashMap<String, String>();
        private Map<String, String> fieldConversions = new HashMap<String, String>();
        
        private Builder() {}
        
        public static Builder create(String baseLookupUrl, String dataObjectClass) {
            Builder b = new Builder();
            b.baseLookupUrl = baseLookupUrl;
            b.dataObjectClass = dataObjectClass;
            return b;
        }
        
        public RemotableQuickFinder build() { return new RemotableQuickFinder(); }
        
        public Map<String, String> getLookupParameters() { return lookupParameters; }
        public void setLookupParameters(Map<String, String> lp) { this.lookupParameters = lp; }
        public Map<String, String> getFieldConversions() { return fieldConversions; }
        public void setFieldConversions(Map<String, String> fc) { this.fieldConversions = fc; }
    }
}
