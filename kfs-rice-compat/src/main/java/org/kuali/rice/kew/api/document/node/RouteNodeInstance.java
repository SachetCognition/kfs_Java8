package org.kuali.rice.kew.api.document.node;

public class RouteNodeInstance extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private String name;
    private boolean active;
    private boolean complete;
    private boolean initial;
    
    public RouteNodeInstance() {}
    
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public boolean isComplete() { return complete; }
    public boolean isInitial() { return initial; }
    
    public static class Builder implements org.kuali.rice.core.api.mo.ModelBuilder, java.io.Serializable {
        private String id;
        private String name;
        
        private Builder() {}
        public static Builder create() { return new Builder(); }
        public RouteNodeInstance build() { RouteNodeInstance r = new RouteNodeInstance(); r.id = this.id; r.name = this.name; return r; }
    }
}
