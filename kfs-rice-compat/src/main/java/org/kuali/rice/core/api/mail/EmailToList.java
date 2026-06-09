package org.kuali.rice.core.api.mail;
import java.util.List;
import java.util.ArrayList;
public class EmailToList {
    private List<EmailTo> toAddresses = new ArrayList<EmailTo>();
    public EmailToList() {}
    public List<EmailTo> getToAddresses() { return toAddresses; }
}
