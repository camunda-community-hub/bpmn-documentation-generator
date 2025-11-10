package nl.onnoh.mdg.bpmn.model.gateway;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class ParallelGateway extends Gateway {
    public ParallelGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
