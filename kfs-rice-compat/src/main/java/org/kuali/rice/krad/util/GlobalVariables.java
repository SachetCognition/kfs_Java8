package org.kuali.rice.krad.util;
public class GlobalVariables {
    private static final ThreadLocal<MessageMap> messageMap = new ThreadLocal<MessageMap>() {
        protected MessageMap initialValue() { return new MessageMap(); }
    };
    private static final ThreadLocal<org.kuali.rice.krad.UserSession> userSession = new ThreadLocal<org.kuali.rice.krad.UserSession>();

    public static MessageMap getMessageMap() { return messageMap.get(); }
    public static void setMessageMap(MessageMap map) { messageMap.set(map); }
    public static org.kuali.rice.krad.UserSession getUserSession() { return userSession.get(); }
    public static void setUserSession(org.kuali.rice.krad.UserSession session) { userSession.set(session); }
    public static void clear() { messageMap.remove(); userSession.remove(); }
    public static Object mergeErrorMap(org.kuali.rice.krad.util.MessageMap arg0) { return null; }
}
