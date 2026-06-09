package org.kuali.rice.coreservice.api.namespace;
public interface NamespaceService {
    Namespace getNamespace(String code);
    java.util.List<Namespace> findAllNamespaces();
}
