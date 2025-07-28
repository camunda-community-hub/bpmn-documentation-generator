package nl.onnoh.bdg.bpmn.model.gateway;

import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;

public abstract class Gateway extends FlowElement {

    public Gateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
