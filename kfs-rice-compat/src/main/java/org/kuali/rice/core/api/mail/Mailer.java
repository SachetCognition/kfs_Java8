package org.kuali.rice.core.api.mail;

public interface Mailer {
    void sendEmail(org.kuali.rice.core.api.mail.MailMessage p0) throws javax.mail.MessagingException;
    void sendEmail(org.kuali.rice.core.api.mail.EmailFrom p0, org.kuali.rice.core.api.mail.EmailTo p1, org.kuali.rice.core.api.mail.EmailSubject p2, org.kuali.rice.core.api.mail.EmailBody p3, boolean p4);
    void sendEmail(org.kuali.rice.core.api.mail.EmailFrom p0, org.kuali.rice.core.api.mail.EmailToList p1, org.kuali.rice.core.api.mail.EmailSubject p2, org.kuali.rice.core.api.mail.EmailBody p3, org.kuali.rice.core.api.mail.EmailCcList p4, org.kuali.rice.core.api.mail.EmailBcList p5, boolean p6);
}
