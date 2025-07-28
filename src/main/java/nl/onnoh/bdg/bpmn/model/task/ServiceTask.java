package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class ServiceTask extends Task {
    public ServiceTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
