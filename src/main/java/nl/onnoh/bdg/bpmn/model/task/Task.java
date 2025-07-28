package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;

public abstract class Task extends FlowElement {

    public Task(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
