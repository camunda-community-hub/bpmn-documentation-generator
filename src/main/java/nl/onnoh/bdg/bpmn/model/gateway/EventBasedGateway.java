package nl.onnoh.bdg.bpmn.model.gateway;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class EventBasedGateway extends Gateway {
    public EventBasedGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
