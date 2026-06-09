package org.kuali.rice.kim.api.type;

public interface KimTypeInfoService {
    KimType getKimType(String id);
    KimType findKimTypeByNameAndNamespace(String namespaceCode, String name);
}
