package nl.onnoh.bdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.bpmn.ObjectFactory;
import nl.onnoh.bdg.bpmn.TBoundaryEvent;
import nl.onnoh.bdg.bpmn.TEndEvent;
import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.TIntermediateCatchEvent;
import nl.onnoh.bdg.bpmn.TIntermediateThrowEvent;
import nl.onnoh.bdg.bpmn.TStartEvent;
import nl.onnoh.bdg.bpmn.model.event.BoundaryEvent;
import nl.onnoh.bdg.bpmn.model.event.EndEvent;
import nl.onnoh.bdg.bpmn.model.event.IntermediateCatchEvent;
import nl.onnoh.bdg.bpmn.model.event.IntermediateThrowEvent;
import nl.onnoh.bdg.bpmn.model.event.StartEvent;

@Slf4j
public class EventParser {

    public static StartEvent parseStartEvent(String processId, String flowType, TFlowElement flowElement) {
        StartEvent parsedStartEvent = new StartEvent(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TStartEvent> startEvent = objectFactory.createStartEvent((TStartEvent) flowElement);
        log.debug("Parsing StartEvent: {}", startEvent.getValue().getName());
        startEvent.getValue().getEventDefinitions().forEach(eventDefinition -> {
            parsedStartEvent.setEventType(eventDefinition.getName().getLocalPart());
        });

        return parsedStartEvent;
    }

    public static EndEvent parseEndEvent(String processId, String flowType, TFlowElement flowElement) {
        EndEvent parsedEndEvent = new EndEvent(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TEndEvent> endEvent = objectFactory.createEndEvent((TEndEvent) flowElement);
        log.debug("Parsing EndEvent: {}", endEvent.getValue().getName());
        endEvent.getValue().getEventDefinitions().forEach(eventDefinition -> {
            parsedEndEvent.setEventType(eventDefinition.getName().getLocalPart());
        });
        return parsedEndEvent;
    }

    static BoundaryEvent parseBoundaryEvent(String processId, String flowType, TFlowElement flowElement) {
        BoundaryEvent parsedBoundaryEvent = new BoundaryEvent(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TBoundaryEvent> boundaryEvent = objectFactory.createBoundaryEvent((TBoundaryEvent) flowElement);
        log.debug("Parsing Boundary Event: {}", boundaryEvent.getValue().getName());
        boundaryEvent.getValue().getEventDefinitions().forEach(eventDefinition -> {
            parsedBoundaryEvent.setEventType(eventDefinition.getName().getLocalPart());
        });
        parsedBoundaryEvent.setAttachedTo(boundaryEvent.getValue().getAttachedToRef().getLocalPart());
        parsedBoundaryEvent.setNonInterrupting(!boundaryEvent.getValue().isCancelActivity());
        parsedBoundaryEvent.setParallelMultiple(boundaryEvent.getValue().isParallelMultiple());

        return parsedBoundaryEvent;
    }

    static IntermediateThrowEvent parseIntermediateThrowEvent(String processId, String flowType, TFlowElement flowElement) {
        IntermediateThrowEvent parsedIntermediateThrowEvent = new IntermediateThrowEvent(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TIntermediateThrowEvent> intermediateThrowEvent = objectFactory.createIntermediateThrowEvent((TIntermediateThrowEvent) flowElement);
        log.debug("Intermediate Throw Event: {}", intermediateThrowEvent.getValue().getName());
        intermediateThrowEvent.getValue().getEventDefinitions().forEach(eventDefinition -> {
            parsedIntermediateThrowEvent.setEventType(eventDefinition.getName().getLocalPart());
        });

        return parsedIntermediateThrowEvent;
    }

    static IntermediateCatchEvent parseIntermediateCatchEvent(String processId, String flowType, TFlowElement flowElement) {
        IntermediateCatchEvent parsedIntermediateCatchEvent = new IntermediateCatchEvent(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TIntermediateCatchEvent> intermediateCatchEvent = objectFactory.createIntermediateCatchEvent((TIntermediateCatchEvent) flowElement);
        log.debug("Parsing Intermediate Catch Event: {}", intermediateCatchEvent.getValue().getName());
        intermediateCatchEvent.getValue().getEventDefinitions().forEach(eventDefinition -> {
            parsedIntermediateCatchEvent.setEventType(eventDefinition.getName().getLocalPart());
        });
        return parsedIntermediateCatchEvent;
    }

}
