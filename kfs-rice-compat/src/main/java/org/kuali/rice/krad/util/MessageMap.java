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
    public boolean hasMessages() { return false; }
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
    public void clearErrorMessages() {}
    public void merge(MessageMap other) {}
    public List getMessages(String property) { return new ArrayList(); }
    public Set<String> getPropertiesWithErrors() { return new HashSet<>(); }

    public void putErrorWithoutFullErrorPath(String propertyName, String errorKey, String... params) {}
    public int getNumberOfPropertiesWithErrors() { return 0; }
    public Object replaceError(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) { return null; }
    public void putErrorForSectionId(String sectionId, String errorConstant, String... params) {}
    public java.util.Map<String, java.util.List<Object>> getErrorMessages() { return new java.util.HashMap<>(); }
}
