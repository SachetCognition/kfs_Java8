package org.kuali.rice.core.framework.persistence.platform;
public class DatabasePlatform {
    public DatabasePlatform() {}
    public String getCurTimeFunction() { return "CURRENT_TIMESTAMP"; }
    public String getIsNullFunction(String column, String defaultValue) { return "COALESCE(" + column + "," + defaultValue + ")"; }
    public String getStrToDateFunction() { return ""; }
    public String getDateFormatString(String format) { return ""; }
}
