package nl.onnoh.mdg.bpmn.model.gateway;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class ExclusiveGateway extends Gateway {
    public ExclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
