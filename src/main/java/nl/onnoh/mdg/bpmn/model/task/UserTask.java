package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class UserTask extends Task {
    public UserTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
