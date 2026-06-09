package org.kuali.rice.kns.util;

import java.util.ArrayList;
import java.util.List;

public class KNSGlobalVariables {
    private static ThreadLocal<org.kuali.rice.kns.web.struts.form.KualiForm> kualiForm = new ThreadLocal<org.kuali.rice.kns.web.struts.form.KualiForm>();
    private static ThreadLocal<MessageList> messageList = new ThreadLocal<MessageList>() {
        protected MessageList initialValue() { return new MessageList(); }
    };
    
    public static org.kuali.rice.kns.web.struts.form.KualiForm getKualiForm() { return kualiForm.get(); }
    public static void setKualiForm(org.kuali.rice.kns.web.struts.form.KualiForm form) { kualiForm.set(form); }
    
    public static MessageList getMessageList() { return messageList.get(); }
    public static void setMessageList(MessageList list) { messageList.set(list); }
    
    public static void clear() { kualiForm.remove(); messageList.remove(); }
    
    private KNSGlobalVariables() {}
}
