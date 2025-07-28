package nl.onnoh.bdg.bpmn.model.gateway;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class ParallelGateway extends Gateway {
    public ParallelGateway(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
