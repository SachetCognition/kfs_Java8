package org.kuali.rice.krad.util;

import java.lang.reflect.InvocationTargetException;

public class ObjectUtils {
    private ObjectUtils() {}
    
    public static boolean isNull(Object obj) { return obj == null; }
    public static boolean isNotNull(Object obj) { return obj != null; }
    
    public static Object getPropertyValue(Object bo, String propertyName) { return null; }
    public static void setObjectProperty(Object bo, String propertyName, Object propertyValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {}
    public static void setObjectProperty(Object bo, String propertyName, Class propertyType, Object propertyValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {}
    
    public static Class getPropertyType(Object bo, String propertyName, org.kuali.rice.krad.service.PersistenceStructureService pss) { return null; }
    
    public static boolean equalByKeys(Object bo1, Object bo2) { return false; }
    
    public static String formatPropertyValue(Object propertyValue) { return propertyValue != null ? propertyValue.toString() : ""; }
    
    @SuppressWarnings("unchecked")
    public static <T> T createNewObjectFromClass(Class<T> clazz) {
        try { return clazz.newInstance(); } catch (Exception e) { return null; }
    }
    
    public static boolean isNestedAttribute(String attributeName) { return attributeName != null && attributeName.contains("."); }
    public static String getNestedAttributePrefix(String attributeName) {
        int idx = attributeName.lastIndexOf('.');
        return idx > 0 ? attributeName.substring(0, idx) : "";
    }
    public static String getNestedAttributePrimitive(String attributeName) {
        int idx = attributeName.lastIndexOf('.');
        return idx >= 0 ? attributeName.substring(idx + 1) : attributeName;
    }
    
    public static Object getNestedValue(Object bo, String fieldName) { return null; }
    
    public static String toString(Object obj) { return obj != null ? obj.toString() : "null"; }
    
    public static void materializeSubObjectsToDepth(org.kuali.rice.krad.bo.PersistableBusinessObject bo, int depth) {}
    public static void materializeAllSubObjects(org.kuali.rice.krad.bo.PersistableBusinessObject bo) {}
    public static void materializeClassForProxiedObject(org.kuali.rice.krad.bo.PersistableBusinessObject bo) {}
    
    public static Object getPropertyValue(Object bo, String propertyName, boolean forceIntrospection) { return null; }

    @SuppressWarnings("unchecked")
    public static <T extends java.io.Serializable> T deepCopy(T obj) {
        if (obj == null) return null;
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) { return obj; }
    }

    public static Class easyGetPropertyType(Object bo, String propertyName) { return String.class; }
}
