package org.kuali.rice.kns.lookup;

public class HtmlData implements java.io.Serializable {
    protected String displayText;
    protected String name;
    protected String title;
    
    public HtmlData() {}
    public HtmlData(String displayText) { this.displayText = displayText; }
    
    public String getDisplayText() { return displayText; }
    public void setDisplayText(String displayText) { this.displayText = displayText; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String constructCompleteHtmlTag() { return displayText != null ? displayText : ""; }
    
    public static String buildHtmlLink(String href, String displayText) {
        return "<a href=\"" + (href != null ? href : "") + "\">" + (displayText != null ? displayText : "") + "</a>";
    }
    
    public static class AnchorHtmlData extends HtmlData {
        private String href;
        private String target;
        private String onclick;
        
        public AnchorHtmlData() {}
        public AnchorHtmlData(String href, String methodToCall) { this.href = href; }
        public AnchorHtmlData(String href, String methodToCall, String displayText) { this.href = href; this.displayText = displayText; }
        
        public String getHref() { return href; }
        public void setHref(String href) { this.href = href; }
        public String getTarget() { return target; }
        public void setTarget(String target) { this.target = target; }
        public String getOnclick() { return onclick; }
        public void setOnclick(String onclick) { this.onclick = onclick; }
    }
    
    public static class InputHtmlData extends HtmlData {
        public InputHtmlData() {}
    }
}
