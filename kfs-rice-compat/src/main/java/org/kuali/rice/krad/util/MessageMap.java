package org.kuali.rice.krad.util;
import java.util.*;
public class MessageMap {
    public MessageMap() {}
    public void putError(String propertyName, String errorKey, String... errorParameters) {}
    public void putWarning(String propertyName, String warningKey, String... warningParameters) {}
    public void putInfo(String propertyName, String infoKey, String... infoParameters) {}
    public boolean hasErrors() { return false; }
    public boolean hasWarnings() { return false; }
    public boolean hasInfo() { return false; }
    public boolean hasNoErrors() { return true; }
    public int getErrorCount() { return 0; }
    public Set<String> getAllPropertiesWithErrors() { return new HashSet<String>(); }
    public void addToErrorPath(String path) {}
    public void removeFromErrorPath(String path) {}
    public boolean containsMessageKey(String messageKey) { return false; }
    public void clearErrorPath() {}
    public List<String> getErrorPath() { return new ArrayList<String>(); }
    public void putFieldError(String propertyName, String errorKey) {}
    public void putFieldError(String propertyName, String errorKey, String... errorParameters) {}
    public boolean checkEmptyValue(String propertyName) { return false; }
    public boolean containsKeyMatchingPattern(String pattern) { return false; }
}
