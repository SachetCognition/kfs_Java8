package org.kuali.rice.krad.datadictionary.validation;

public abstract class ValidationPattern {
    public ValidationPattern() {}
    public String getRegexString() { return ".*"; }
    public String getValidationErrorMessageKey() { return "error.format.validation"; }
    public String[] getValidationErrorMessageParameters() { return new String[0]; }
    public String getRegexPattern() { return null; }

}
