package org.kuali.rice.krad;
import java.util.Map;
import java.util.HashMap;
public class UserSession {
    private java.util.concurrent.atomic.AtomicInteger counter = new java.util.concurrent.atomic.AtomicInteger(0);
    private Map<String, Object> objectMap = new HashMap<>();
    public UserSession(String principalId) {}
    public String getPrincipalId() { return null; }
    public String getPrincipalName() { return null; }
    public org.kuali.rice.kim.api.identity.Person getPerson() { return null; }
    public String addObjectWithGeneratedKey(Object object) {
        String key = String.valueOf(counter.incrementAndGet());
        objectMap.put(key, object);
        return key;
    }
    public Object retrieveObject(String key) { return objectMap.get(key); }
    public void removeObject(String key) { objectMap.remove(key); }
    public void addObject(String key, Object object) { objectMap.put(key, object); }
}
