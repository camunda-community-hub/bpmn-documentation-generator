package nl.onnoh.mdg.bpmn.model.event;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class IntermediateCatchEvent extends Event {
    public IntermediateCatchEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
