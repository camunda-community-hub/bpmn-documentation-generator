package nl.onnoh.bdg.bpmn.model.event;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class IntermediateCatchEvent extends Event {
    public IntermediateCatchEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
