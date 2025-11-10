package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TComplexGateway;
import nl.onnoh.mdg.bpmn.TEventBasedGateway;
import nl.onnoh.mdg.bpmn.TExclusiveGateway;
import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.TInclusiveGateway;
import nl.onnoh.mdg.bpmn.TParallelGateway;
import nl.onnoh.mdg.bpmn.model.gateway.ComplexGateway;
import nl.onnoh.mdg.bpmn.model.gateway.EventBasedGateway;
import nl.onnoh.mdg.bpmn.model.gateway.ExclusiveGateway;
import nl.onnoh.mdg.bpmn.model.gateway.InclusiveGateway;
import nl.onnoh.mdg.bpmn.model.gateway.ParallelGateway;

@Slf4j
public class GatewayParser {

    static InclusiveGateway parseInclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        InclusiveGateway parsedInclusiveGateway = new InclusiveGateway(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TInclusiveGateway> inclusiveGateway = objectFactory.createInclusiveGateway((TInclusiveGateway) flowElement);
        log.debug("Parsing Inclusive Gateway: {}", inclusiveGateway.getValue().getName());
        return parsedInclusiveGateway;
    }

    static ExclusiveGateway parseExclusiveGateway(String processId, String flowType, TFlowElement flowElement) {
        ExclusiveGateway parsedExclusiveGateway = new ExclusiveGateway(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TExclusiveGateway> exclusiveGateway = objectFactory.createExclusiveGateway((TExclusiveGateway) flowElement);
        log.debug("Exclusive Gateway: {}", exclusiveGateway.getValue().getName());
        return parsedExclusiveGateway;
    }

    static ParallelGateway parseParallelGateway(String processId, String flowType, TFlowElement flowElement) {
        ParallelGateway parsedParallelGateway = new ParallelGateway(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TParallelGateway> parallelGateway = objectFactory.createParallelGateway((TParallelGateway) flowElement);
        log.debug("Parallel Gateway: {}", parallelGateway.getValue().getName());
        return parsedParallelGateway;
    }

    static EventBasedGateway parseEventBasedGateway(String processId, String flowType, TFlowElement flowElement) {
        EventBasedGateway parsedEventBasedGateway = new EventBasedGateway(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TEventBasedGateway> eventBasedGateway = objectFactory.createEventBasedGateway((TEventBasedGateway) flowElement);
        log.debug("Parsing Event Based Gateway: {}", eventBasedGateway.getValue().getName());
        return parsedEventBasedGateway;
    }

    static ComplexGateway parseComplexGateway(String processId, String flowType, TFlowElement flowElement) {
        ComplexGateway parsedComplexGateway = new ComplexGateway(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TComplexGateway> complexGateway = objectFactory.createComplexGateway((TComplexGateway) flowElement);
        log.debug("Complex Gateway: {}", complexGateway.getValue().getName());
        return parsedComplexGateway;
    }

}
