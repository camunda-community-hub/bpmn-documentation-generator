package nl.onnoh.bdg.bpmn.model.other;

import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;


public class SequenceFlow extends FlowElement {
    public SequenceFlow(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
