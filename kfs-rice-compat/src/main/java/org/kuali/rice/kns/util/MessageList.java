package org.kuali.rice.kns.util;
import java.util.ArrayList;
public class MessageList extends ArrayList<String> {
    public MessageList() { super(); }
    public void add(String pattern, String[] parameters) { add(pattern); }
}
