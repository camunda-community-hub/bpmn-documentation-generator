package nl.onnoh.bdg.bpmn.model.gateway;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class InclusiveGateway extends Gateway {
    public InclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
