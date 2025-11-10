package nl.onnoh.mdg.bpmn.model.event;

import nl.onnoh.mdg.bpmn.TFlowElement;

public class IntermediateThrowEvent extends Event {
    public IntermediateThrowEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
