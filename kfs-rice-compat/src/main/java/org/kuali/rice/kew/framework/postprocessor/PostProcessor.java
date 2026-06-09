package org.kuali.rice.kew.framework.postprocessor;
public interface PostProcessor {
    ProcessDocReport doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) throws Exception;
    ProcessDocReport doRouteLevelChange(DocumentRouteLevelChange levelChangeEvent) throws Exception;
    ProcessDocReport doDeleteRouteHeader(DeleteEvent event) throws Exception;
    ProcessDocReport doActionTaken(ActionTakenEvent event) throws Exception;
    ProcessDocReport afterActionTaken(org.kuali.rice.kew.api.action.ActionType performed, ActionTakenEvent event) throws Exception;
    ProcessDocReport beforeProcess(BeforeProcessEvent event) throws Exception;
    ProcessDocReport afterProcess(AfterProcessEvent event) throws Exception;
    java.util.List<String> getDocumentIdsToLock(DocumentLockingEvent lockingEvent) throws Exception;
}
