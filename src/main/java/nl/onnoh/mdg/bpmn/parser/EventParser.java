package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TBoundaryEvent;
import nl.onnoh.mdg.bpmn.TCancelEventDefinition;
import nl.onnoh.mdg.bpmn.TCompensateEventDefinition;
import nl.onnoh.mdg.bpmn.TConditionalEventDefinition;
import nl.onnoh.mdg.bpmn.TEndEvent;
import nl.onnoh.mdg.bpmn.TErrorEventDefinition;
import nl.onnoh.mdg.bpmn.TEscalationEventDefinition;
import nl.onnoh.mdg.bpmn.TEventDefinition;
import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.TIntermediateCatchEvent;
import nl.onnoh.mdg.bpmn.TIntermediateThrowEvent;
import nl.onnoh.mdg.bpmn.TLinkEventDefinition;
import nl.onnoh.mdg.bpmn.TMessageEventDefinition;
import nl.onnoh.mdg.bpmn.TSignalEventDefinition;
import nl.onnoh.mdg.bpmn.TStartEvent;
import nl.onnoh.mdg.bpmn.TTerminateEventDefinition;
import nl.onnoh.mdg.bpmn.TTimerEventDefinition;
import nl.onnoh.mdg.bpmn.model.event.BoundaryEvent;
import nl.onnoh.mdg.bpmn.model.event.EndEvent;
import nl.onnoh.mdg.bpmn.model.event.IntermediateCatchEvent;
import nl.onnoh.mdg.bpmn.model.event.IntermediateThrowEvent;
import nl.onnoh.mdg.bpmn.model.event.StartEvent;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_CANCEL_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_COMPENSATE_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_CONDITIONAL_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ERROR_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ESCALATION_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_LINK_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_MESSAGE_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SIGNAL_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_TERMINATE_EVENT_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_TIMER_EVENT_DEFINITION;

@Slf4j
public class EventParser {

    public static final String EVENT_TYPE = "eventType";

    public static StartEvent parseStartEvent(String processId, String flowType, TFlowElement flowElement) {
        StartEvent parsedStartEvent = new StartEvent(processId, flowType, flowElement);
        List<Map<String, Object>> eventDefinitions = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TStartEvent> startEvent = objectFactory.createStartEvent((TStartEvent) flowElement);
        log.debug("Parsing StartEvent: {}", startEvent.getValue().getName());
        parsedStartEvent.setEventType(startEvent.getName().getLocalPart());
        startEvent.getValue().getEventDefinitions().forEach(eventDefinition -> eventDefinitions.add(parseEventDefinition(eventDefinition)));
        parsedStartEvent.setEventDefinitions(eventDefinitions);

        return parsedStartEvent;
    }

    public static EndEvent parseEndEvent(String processId, String flowType, TFlowElement flowElement) {
        EndEvent parsedEndEvent = new EndEvent(processId, flowType, flowElement);
        List<Map<String, Object>> eventDefinitions = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TEndEvent> endEvent = objectFactory.createEndEvent((TEndEvent) flowElement);
        log.debug("Parsing EndEvent: {}", endEvent.getValue().getName());
        parsedEndEvent.setEventType(endEvent.getName().getLocalPart());
        endEvent.getValue().getEventDefinitions().forEach(eventDefinition -> eventDefinitions.add(parseEventDefinition(eventDefinition)));
        parsedEndEvent.setEventDefinitions(eventDefinitions);
        return parsedEndEvent;
    }

    static BoundaryEvent parseBoundaryEvent(String processId, String flowType, TFlowElement flowElement) {
        BoundaryEvent parsedBoundaryEvent = new BoundaryEvent(processId, flowType, flowElement);
        List<Map<String, Object>> eventDefinitions = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TBoundaryEvent> boundaryEvent = objectFactory.createBoundaryEvent((TBoundaryEvent) flowElement);
        log.debug("Parsing Boundary Event: {}", boundaryEvent.getValue().getName());
        parsedBoundaryEvent.setEventType(boundaryEvent.getName().getLocalPart());
        parsedBoundaryEvent.setAttachedTo(boundaryEvent.getValue().getAttachedToRef().getLocalPart());
        parsedBoundaryEvent.setNonInterrupting(!boundaryEvent.getValue().isCancelActivity());
        parsedBoundaryEvent.setParallelMultiple(boundaryEvent.getValue().isParallelMultiple());
        boundaryEvent.getValue().getEventDefinitions().forEach(eventDefinition -> eventDefinitions.add(parseEventDefinition(eventDefinition)));
        parsedBoundaryEvent.setEventDefinitions(eventDefinitions);

        return parsedBoundaryEvent;
    }

    static IntermediateThrowEvent parseIntermediateThrowEvent(String processId, String flowType, TFlowElement flowElement) {
        IntermediateThrowEvent parsedIntermediateThrowEvent = new IntermediateThrowEvent(processId, flowType, flowElement);
        List<Map<String, Object>> eventDefinitions = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TIntermediateThrowEvent> intermediateThrowEvent = objectFactory.createIntermediateThrowEvent((TIntermediateThrowEvent) flowElement);
        log.debug("Intermediate Throw Event: {}", intermediateThrowEvent.getValue().getName());
        parsedIntermediateThrowEvent.setEventType(intermediateThrowEvent.getName().getLocalPart());
        intermediateThrowEvent.getValue().getEventDefinitions().forEach(eventDefinition -> eventDefinitions.add(parseEventDefinition(eventDefinition)));
        parsedIntermediateThrowEvent.setEventDefinitions(eventDefinitions);

        return parsedIntermediateThrowEvent;
    }

    static IntermediateCatchEvent parseIntermediateCatchEvent(String processId, String flowType, TFlowElement flowElement) {
        IntermediateCatchEvent parsedIntermediateCatchEvent = new IntermediateCatchEvent(processId, flowType, flowElement);
        List<Map<String, Object>> eventDefinitions = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TIntermediateCatchEvent> intermediateCatchEvent = objectFactory.createIntermediateCatchEvent((TIntermediateCatchEvent) flowElement);
        log.debug("Parsing Intermediate Catch Event: {}", intermediateCatchEvent.getValue().getName());
        parsedIntermediateCatchEvent.setEventType(intermediateCatchEvent.getName().getLocalPart());
        intermediateCatchEvent.getValue().getEventDefinitions().forEach(eventDefinition -> eventDefinitions.add(parseEventDefinition(eventDefinition)));
        parsedIntermediateCatchEvent.setEventDefinitions(eventDefinitions);

        return parsedIntermediateCatchEvent;
    }

    private static Map<String, Object> parseEventDefinition(JAXBElement<? extends TEventDefinition> eventDefinition) {
        Map<String, Object> parsedEventDefinition = new HashMap<>();
        String eventType = eventDefinition.getName().getLocalPart();
        log.debug("Parsing event definition {}", eventType);
        parsedEventDefinition.put("type", eventType);
        parsedEventDefinition.put("id", eventDefinition.getValue().getId());
        ObjectFactory objectFactory = new ObjectFactory();
        switch (eventType) {
            case BPMN_ELEMENT_COMPENSATE_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "compensation");
                JAXBElement<TCompensateEventDefinition> compensateEventDefinition = objectFactory.createCompensateEventDefinition((TCompensateEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("ref", compensateEventDefinition.getValue().getActivityRef());
            }
            case BPMN_ELEMENT_ERROR_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "error");
                JAXBElement<TErrorEventDefinition> errorEventDefinition = objectFactory.createErrorEventDefinition((TErrorEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("ref",  errorEventDefinition.getValue().getErrorRef());
            }
            case BPMN_ELEMENT_MESSAGE_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "message");
                JAXBElement<TMessageEventDefinition> messageEventDefinition = objectFactory.createMessageEventDefinition((TMessageEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("ref", messageEventDefinition.getValue().getMessageRef());
            }
            case BPMN_ELEMENT_TIMER_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "timer");
                JAXBElement<TTimerEventDefinition> timerEventDefinition = objectFactory.createTimerEventDefinition((TTimerEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("timeCycle", timerEventDefinition.getValue().getTimeCycle());
            }
            case BPMN_ELEMENT_SIGNAL_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "signal");
                JAXBElement<TSignalEventDefinition> signalEventDefinition = objectFactory.createSignalEventDefinition((TSignalEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("ref", signalEventDefinition.getValue().getSignalRef());
            }
            case BPMN_ELEMENT_CANCEL_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "cancellation");
                JAXBElement<TCancelEventDefinition> cancelEventDefinition = objectFactory.createCancelEventDefinition((TCancelEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("cancelActivity", cancelEventDefinition.getValue());
            }
            case BPMN_ELEMENT_CONDITIONAL_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "conditional");
                JAXBElement<TConditionalEventDefinition> conditionalEventDefinition = objectFactory.createConditionalEventDefinition((TConditionalEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("condition", conditionalEventDefinition.getValue().getCondition());
            }
            case BPMN_ELEMENT_ESCALATION_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "escalation");
                JAXBElement<TEscalationEventDefinition> escalationEventDefinition = objectFactory.createEscalationEventDefinition((TEscalationEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("ref", escalationEventDefinition.getValue().getEscalationRef());
            }
            case BPMN_ELEMENT_TERMINATE_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "termination");
                JAXBElement<TTerminateEventDefinition> terminateEventDefinition = objectFactory.createTerminateEventDefinition((TTerminateEventDefinition) eventDefinition.getValue());
            }
            case BPMN_ELEMENT_LINK_EVENT_DEFINITION -> {
                parsedEventDefinition.put(EVENT_TYPE, "link");
                JAXBElement<TLinkEventDefinition> linkEventDefinition = objectFactory.createLinkEventDefinition((TLinkEventDefinition) eventDefinition.getValue());
                parsedEventDefinition.put("sources", linkEventDefinition.getValue().getSources().stream().map(QName::getLocalPart).collect(Collectors.joining(", ")));
                parsedEventDefinition.put("target", linkEventDefinition.getValue().getTarget());
            }
            default -> log.warn("Unknown Event Definition Type: {}", eventType);
        }

        return parsedEventDefinition;
    }
}
