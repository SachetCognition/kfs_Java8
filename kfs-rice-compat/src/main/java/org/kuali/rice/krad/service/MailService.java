package org.kuali.rice.krad.service;

public interface MailService {
    void sendMessage(org.kuali.rice.core.api.mail.MailMessage p0) throws org.kuali.rice.krad.exception.InvalidAddressException, javax.mail.MessagingException;
    java.lang.String getBatchMailingList();
}
