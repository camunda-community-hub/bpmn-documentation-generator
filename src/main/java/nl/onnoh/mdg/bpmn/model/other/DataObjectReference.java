package nl.onnoh.mdg.bpmn.model.other;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;


public class DataObjectReference extends FlowElement {
    public DataObjectReference(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
