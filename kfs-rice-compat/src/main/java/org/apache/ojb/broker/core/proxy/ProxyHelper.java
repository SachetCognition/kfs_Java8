package org.apache.ojb.broker.core.proxy;
public class ProxyHelper {
    public ProxyHelper() {}
    public static Class getRealClass(Object obj) { return obj != null ? obj.getClass() : null; }
    public static Object getRealObject(Object obj) { return obj; }
    public static boolean isProxy(Object obj) { return false; }
}
