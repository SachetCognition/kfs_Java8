package org.kuali.rice.krad.datadictionary;
import java.util.*;
public class DataDictionaryIndexMapper implements DataDictionaryMapper {
    public DataDictionaryIndexMapper() {}
    public BusinessObjectEntry getBusinessObjectEntry(DataDictionaryIndex index, String className) { return null; }
    public DataObjectEntry getDataObjectEntry(DataDictionaryIndex index, String className) { return null; }
    public BusinessObjectEntry getBusinessObjectEntryForConcreteClass(DataDictionaryIndex index, String className) { return null; }
    public List<String> getBusinessObjectClassNames(DataDictionaryIndex index) { return new ArrayList<>(); }
    public Map<String, BusinessObjectEntry> getBusinessObjectEntries(DataDictionaryIndex index) { return new HashMap<>(); }
    public DataDictionaryEntry getDictionaryObjectEntry(DataDictionaryIndex index, String className) { return null; }
    public Map<String, DocumentEntry> getDocumentEntries(DataDictionaryIndex index) { return new HashMap<>(); }
    public DocumentEntry getDocumentEntry(DataDictionaryIndex index, String documentTypeName) { return null; }
    public MaintenanceDocumentEntry getMaintenanceDocumentEntryForBusinessObjectClass(DataDictionaryIndex index, Class<?> boClass) { return null; }
    public Set getAllInactivationBlockingMetadatas(DataDictionaryIndex index, Class<?> boClass) { return new HashSet(); }
    public org.kuali.rice.krad.uif.view.View getViewById(UifDictionaryIndex uifIndex, String viewId) { return null; }
    public org.kuali.rice.krad.uif.view.View getViewByTypeIndex(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey) { return null; }
    public org.springframework.beans.PropertyValues getViewPropertiesById(UifDictionaryIndex uifIndex, String viewId) { return null; }
    public org.springframework.beans.PropertyValues getViewPropertiesByType(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey) { return null; }
    public java.util.List getViewsForType(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType) { return new java.util.ArrayList(); }
    public boolean viewByTypeExist(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey) { return false; }
}
