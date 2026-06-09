package org.kuali.kfs.sys.workflow;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that implements document routing via Flowable BPM,
 * replacing Rice KEW workflow routing. Provides the bridge between
 * KFS document lifecycle operations and BPMN process instances.
 *
 * <p>KEW concepts are mapped as follows:</p>
 * <ul>
 *   <li>KEW Document Type → BPMN Process Definition key</li>
 *   <li>KEW Route Node → BPMN User Task</li>
 *   <li>KEW Action Request → Flowable Task assignment</li>
 *   <li>KEW Document Status → Process instance/variable state</li>
 * </ul>
 */
public class KfsDocumentRoutingService {

    private RuntimeService runtimeService;
    private TaskService taskService;
    private HistoryService historyService;

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public String routeDocument(String documentTypeName, String documentId,
                                 String initiatorPrincipalId, String annotation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("documentId", documentId);
        variables.put("initiator", initiatorPrincipalId);
        variables.put("annotation", annotation);
        variables.put("documentStatus", "ENROUTE");

        String processDefinitionKey = mapDocumentTypeToProcessKey(documentTypeName);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                processDefinitionKey, documentId, variables);
        return instance.getId();
    }

    public void approveDocument(String processInstanceId, String principalId, String annotation) {
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskCandidateUser(principalId)
                .list();

        for (Task task : tasks) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("action", "APPROVE");
            vars.put("actionUser", principalId);
            vars.put("annotation", annotation);
            taskService.complete(task.getId(), vars);
        }
    }

    public void disapproveDocument(String processInstanceId, String principalId, String annotation) {
        runtimeService.setVariable(processInstanceId, "documentStatus", "DISAPPROVED");
        runtimeService.deleteProcessInstance(processInstanceId, "Disapproved by " + principalId + ": " + annotation);
    }

    public void cancelDocument(String processInstanceId, String principalId, String annotation) {
        runtimeService.setVariable(processInstanceId, "documentStatus", "CANCELLED");
        runtimeService.deleteProcessInstance(processInstanceId, "Cancelled by " + principalId + ": " + annotation);
    }

    public String getDocumentStatus(String processInstanceId) {
        Object status = runtimeService.getVariable(processInstanceId, "documentStatus");
        return status != null ? status.toString() : "INITIATED";
    }

    private String mapDocumentTypeToProcessKey(String documentTypeName) {
        return "kfs-" + documentTypeName.toLowerCase().replace("_", "-");
    }
}
