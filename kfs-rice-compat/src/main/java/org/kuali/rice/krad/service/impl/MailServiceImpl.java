package org.kuali.rice.krad.service.impl;

public class MailServiceImpl implements org.kuali.rice.krad.service.MailService {
    public MailServiceImpl() {}


    public void setMailer(org.kuali.rice.core.api.mail.Mailer p0) {  }
    public void setBatchMailingList(java.lang.String p0) {  }
    public java.lang.String getBatchMailingList() { return null; }
    public void sendMessage(org.kuali.rice.core.api.mail.MailMessage p0) throws org.kuali.rice.krad.exception.InvalidAddressException, javax.mail.MessagingException {  }
    public java.lang.String getNonProductionNotificationMailingList() { return null; }
    public void setNonProductionNotificationMailingList(java.lang.String p0) {  }
    public boolean isRealNotificationsEnabled() { return false; }
    public void setRealNotificationsEnabled(boolean p0) {  }
    protected org.kuali.rice.core.api.mail.MailMessage composeMessage(org.kuali.rice.core.api.mail.MailMessage message) { return message; }
}
