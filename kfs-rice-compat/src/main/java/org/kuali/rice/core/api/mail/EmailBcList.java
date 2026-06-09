package org.kuali.rice.core.api.mail;
import java.util.List;
import java.util.ArrayList;
public class EmailBcList {
    private List<String> bcAddresses = new ArrayList<String>();
    public EmailBcList() {}
    public List<String> getBcAddresses() { return bcAddresses; }
}
