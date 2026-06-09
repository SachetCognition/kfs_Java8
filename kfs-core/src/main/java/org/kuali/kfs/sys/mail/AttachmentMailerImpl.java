/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.mail;

import javax.mail.MessagingException;

import org.kuali.rice.core.mail.MailerImpl;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;

/**
 * This class extends the Rice MailerImpl to add attachment support.
 * Uses jakarta.mail internally (Spring 6.x) while keeping javax.mail
 * in the interface contract for Rice backward compatibility.
 */
public class AttachmentMailerImpl extends MailerImpl implements AttachmentMailer {

    protected final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AttachmentMailerImpl.class);

    protected JavaMailSenderImpl mailSender;

    @Override
    public void sendEmail(AttachmentMailMessage message) throws MessagingException {
        try {
            jakarta.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();

            jakarta.mail.internet.MimeBodyPart body = new jakarta.mail.internet.MimeBodyPart();
            body.setText(message.getMessage());

            jakarta.mail.internet.MimeBodyPart attachment = new jakarta.mail.internet.MimeBodyPart();
            jakarta.mail.Multipart multipart = new jakarta.mail.internet.MimeMultipart();
            multipart.addBodyPart(body);
            jakarta.mail.util.ByteArrayDataSource ds = new jakarta.mail.util.ByteArrayDataSource(message.getContent(), message.getType());
            attachment.setDataHandler(new jakarta.activation.DataHandler(ds));
            attachment.setFileName(message.getFileName());
            multipart.addBodyPart(attachment);
            mimeMessage.setContent(multipart);

            MimeMailMessage mmm = new MimeMailMessage(mimeMessage);

            mmm.setTo( (String[])message.getToAddresses().toArray(new String[message.getToAddresses().size()]) );
            mmm.setBcc( (String[])message.getBccAddresses().toArray(new String[message.getBccAddresses().size()]) );
            mmm.setCc( (String[])message.getCcAddresses().toArray(new String[message.getCcAddresses().size()]) );
            mmm.setSubject(message.getSubject());
            mmm.setFrom(message.getFromAddress());

            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "sendEmail() - Sending message: " + mmm.toString() );
            }
            mailSender.send(mmm.getMimeMessage());
        }
        catch (jakarta.mail.MessagingException e) {
            LOG.error("sendEmail() - Error sending email.", e);
            throw new MessagingException(e.getMessage(), e);
        }
        catch (Exception e) {
            LOG.error("sendEmail() - Error sending email.", e);
            throw new RuntimeException(e);
        }
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    @Override
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

}
