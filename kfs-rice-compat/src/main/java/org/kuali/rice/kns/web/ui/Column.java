package org.kuali.rice.kns.web.ui;
public class Column {
    private String columnTitle;
    private String propertyName;
    private String propertyValue;
    public Column() {}
    public Column(String columnTitle, String propertyName) { this.columnTitle = columnTitle; this.propertyName = propertyName; }
    public String getColumnTitle() { return columnTitle; }
    public void setColumnTitle(String columnTitle) { this.columnTitle = columnTitle; }
    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public String getPropertyValue() { return propertyValue; }
    public void setPropertyValue(String propertyValue) { this.propertyValue = propertyValue; }
    public void setColumnAnchor(org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData arg0) {  }
    public void setMaxLength(int maxLength) {}
    public void setFormatter(org.kuali.rice.core.web.format.Formatter formatter) {}
    public String getAlternateDisplayPropertyName() { return null; }
    public void setAlternateDisplayPropertyName(String s) {}
    public String getAdditionalDisplayPropertyName() { return null; }
    public void setAdditionalDisplayPropertyName(String s) {}
}