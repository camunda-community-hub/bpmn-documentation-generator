package nl.onnoh.bdg.bpmn.model.gateway;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class ExclusiveGateway extends Gateway {
    public ExclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
