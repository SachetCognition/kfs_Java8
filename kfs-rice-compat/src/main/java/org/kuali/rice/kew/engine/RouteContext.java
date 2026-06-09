package org.kuali.rice.kew.engine;

public class RouteContext implements java.io.Serializable {
    public RouteContext() {}


    public static org.kuali.rice.kew.engine.RouteContext getCurrentRouteContext() { return null; }
    public static void clearCurrentRouteContext() {  }
    public static org.kuali.rice.kew.engine.RouteContext createNewRouteContext() { return null; }
    public static org.kuali.rice.kew.engine.RouteContext releaseCurrentRouteContext() { return null; }
    public org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue getRouteHeader() { return null; }
    public void setRouteHeader(org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue p0) {  }
    public org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue getDocument() { return null; }
    public void setDocument(org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue p0) {  }
    public org.kuali.rice.kew.routeheader.DocumentContent getDocumentContent() { return null; }
    public void setDocumentContent(org.kuali.rice.kew.routeheader.DocumentContent p0) {  }
    public org.kuali.rice.kew.engine.node.RouteNodeInstance getNodeInstance() { return null; }
    public void setNodeInstance(org.kuali.rice.kew.engine.node.RouteNodeInstance p0) {  }
    public org.kuali.rice.kew.engine.EngineState getEngineState() { return null; }
    public void setEngineState(org.kuali.rice.kew.engine.EngineState p0) {  }
    public org.kuali.rice.kew.actionrequest.ActionRequestValue getActionRequest() { return null; }
    public void setActionRequest(org.kuali.rice.kew.actionrequest.ActionRequestValue p0) {  }
    public boolean isSimulation() { return false; }
    public org.kuali.rice.kew.engine.ActivationContext getActivationContext() { return null; }
    public void setActivationContext(org.kuali.rice.kew.engine.ActivationContext p0) {  }
    public boolean isDoNotSendApproveNotificationEmails() { return false; }
    public void setDoNotSendApproveNotificationEmails(boolean p0) {  }
    public java.util.Map getParameters() { return new java.util.HashMap(); }
    public void setParameters(java.util.Map p0) {  }
    public boolean isSearchIndexingRequestedForContext() { return false; }
    public void requestSearchIndexingForContext() {  }
}
