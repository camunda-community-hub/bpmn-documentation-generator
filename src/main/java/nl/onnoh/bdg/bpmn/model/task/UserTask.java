package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class UserTask extends Task {
    public UserTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
