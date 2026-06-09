package org.kuali.rice.core.api.mail;
public class EmailBody {
    private String body;
    private boolean html;
    public EmailBody(String body) { this.body = body; }
    public String getBody() { return body; }
    public boolean isHtml() { return html; }
    public void setHtml(boolean html) { this.html = html; }
}
