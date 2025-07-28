package nl.onnoh.bdg.bpmn.model.event;

import nl.onnoh.bdg.bpmn.TFlowElement;

public class IntermediateThrowEvent extends Event {
    public IntermediateThrowEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
