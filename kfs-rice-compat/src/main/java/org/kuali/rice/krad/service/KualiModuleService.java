package org.kuali.rice.krad.service;

import java.util.List;

public interface KualiModuleService {
    List<ModuleService> getInstalledModuleServices();
    ModuleService getResponsibleModuleService(Class boClass);
    ModuleService getResponsibleModuleServiceForJob(String jobName);
    void setInstalledModuleServices(List<ModuleService> moduleServices);
    boolean isModuleServiceInstalled(String namespaceCode);
}
