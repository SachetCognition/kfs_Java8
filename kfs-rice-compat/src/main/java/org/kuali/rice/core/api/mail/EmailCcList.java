package org.kuali.rice.core.api.mail;
import java.util.List;
import java.util.ArrayList;
public class EmailCcList {
    private List<String> ccAddresses = new ArrayList<String>();
    public EmailCcList() {}
    public List<String> getCcAddresses() { return ccAddresses; }
}
