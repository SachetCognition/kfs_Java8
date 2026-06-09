package org.kuali.rice.core.mail;

public class MailerImpl implements org.kuali.rice.core.api.mail.Mailer {
    public MailerImpl() {}


    public void setMailSender(org.springframework.mail.javamail.JavaMailSenderImpl p0) {  }
    public void sendEmail(org.kuali.rice.core.api.mail.MailMessage p0) throws javax.mail.MessagingException {  }
    public void sendEmail(org.kuali.rice.core.api.mail.EmailFrom p0, org.kuali.rice.core.api.mail.EmailTo p1, org.kuali.rice.core.api.mail.EmailSubject p2, org.kuali.rice.core.api.mail.EmailBody p3, boolean p4) {  }
    public void sendEmail(org.kuali.rice.core.api.mail.EmailFrom p0, org.kuali.rice.core.api.mail.EmailToList p1, org.kuali.rice.core.api.mail.EmailSubject p2, org.kuali.rice.core.api.mail.EmailBody p3, org.kuali.rice.core.api.mail.EmailCcList p4, org.kuali.rice.core.api.mail.EmailBcList p5, boolean p6) {  }
}
