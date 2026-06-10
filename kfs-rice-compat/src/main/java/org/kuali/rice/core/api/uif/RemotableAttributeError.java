package org.kuali.rice.core.api.uif;
import java.util.List;
import java.util.ArrayList;
public class RemotableAttributeError {
    private String attributeName;
    private List<String> errors;
    
    public RemotableAttributeError() { this.errors = new ArrayList<>(); }
    public String getAttributeName() { return attributeName; }
    public List<String> getErrors() { return errors; }
    
    public static class Builder {
        private String attributeName;
        private List<String> errors = new ArrayList<>();
        
        public static Builder create(String attributeName) {
            Builder b = new Builder();
            b.attributeName = attributeName;
            return b;
        }
        public List<String> getErrors() { return errors; }
        public RemotableAttributeError build() { return new RemotableAttributeError(); }
    }
}
