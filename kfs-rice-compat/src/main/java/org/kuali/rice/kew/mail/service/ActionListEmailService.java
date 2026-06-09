package org.kuali.rice.kew.mail.service;

public interface ActionListEmailService {
    void sendImmediateReminder(org.kuali.rice.kew.api.action.ActionItem p0, java.lang.Boolean p1);
    void sendDailyReminder();
    void sendWeeklyReminder();
    void scheduleBatchEmailReminders() throws java.lang.Exception;
}
