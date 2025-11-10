package nl.onnoh.mdg.bpmn.model.gateway;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;

public abstract class Gateway extends FlowElement {

    public Gateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
