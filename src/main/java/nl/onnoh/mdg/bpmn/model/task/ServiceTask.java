package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class ServiceTask extends Task {
    public ServiceTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
