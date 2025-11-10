package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;

public abstract class Task extends FlowElement {

    public Task(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
