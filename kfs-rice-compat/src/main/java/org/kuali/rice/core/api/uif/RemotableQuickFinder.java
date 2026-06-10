package org.kuali.rice.core.api.uif;
public class RemotableQuickFinder extends RemotableAbstractWidget {
    public static class Builder extends RemotableAbstractWidget.Builder {
        public static Builder create(String baseLookupUrl, String dataObjectClass) { return new Builder(); }
        public void setLookupParameters(java.util.Map<String, String> params) {}
        public void setFieldConversions(java.util.Map<String, String> fieldConversions) {}
    }
}
