package nl.onnoh.mdg.bpmn.model.gateway;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class InclusiveGateway extends Gateway {
    public InclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
