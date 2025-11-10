package nl.onnoh.mdg.bpmn.model.gateway;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class EventBasedGateway extends Gateway {
    public EventBasedGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
