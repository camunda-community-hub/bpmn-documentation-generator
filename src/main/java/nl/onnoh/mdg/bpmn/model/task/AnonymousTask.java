package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;

public class AnonymousTask extends Task {
    public AnonymousTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
