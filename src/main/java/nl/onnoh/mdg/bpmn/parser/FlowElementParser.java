package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.TSequenceFlow;
import nl.onnoh.mdg.bpmn.model.other.SequenceFlow;

import java.util.ArrayList;
import java.util.List;

import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_AD_HOC_SUB_PROCESS;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_BOUNDARY_EVENT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_BUSINESS_RULE_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_CALL_ACTIVITY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_COMPLEX_GATEWAY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_DATA_OBJECT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_DATA_OBJECT_REFERENCE;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_DATA_STORE_REFERENCE;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_END_EVENT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_EVENT_BASED_GATEWAY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_EXCLUSIVE_GATEWAY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_INCLUSIVE_GATEWAY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_INTERMEDIATE_CATCH_EVENT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_INTERMEDIATE_THROW_EVENT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_MANUAL_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_PARALLEL_GATEWAY;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_RECEIVE_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SCRIPT_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SEND_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SEQUENCE_FLOW;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SERVICE_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_START_EVENT;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SUB_PROCESS;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_TRANSACTION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_USER_TASK;
import static nl.onnoh.mdg.bpmn.parser.EventParser.parseBoundaryEvent;
import static nl.onnoh.mdg.bpmn.parser.EventParser.parseEndEvent;
import static nl.onnoh.mdg.bpmn.parser.EventParser.parseIntermediateCatchEvent;
import static nl.onnoh.mdg.bpmn.parser.EventParser.parseIntermediateThrowEvent;
import static nl.onnoh.mdg.bpmn.parser.EventParser.parseStartEvent;
import static nl.onnoh.mdg.bpmn.parser.GatewayParser.parseComplexGateway;
import static nl.onnoh.mdg.bpmn.parser.GatewayParser.parseEventBasedGateway;
import static nl.onnoh.mdg.bpmn.parser.GatewayParser.parseExclusiveGateway;
import static nl.onnoh.mdg.bpmn.parser.GatewayParser.parseInclusiveGateway;
import static nl.onnoh.mdg.bpmn.parser.GatewayParser.parseParallelGateway;
import static nl.onnoh.mdg.bpmn.parser.OtherParser.parseDataObject;
import static nl.onnoh.mdg.bpmn.parser.OtherParser.parseDataObjectReference;
import static nl.onnoh.mdg.bpmn.parser.OtherParser.parseDataStoreReference;
import static nl.onnoh.mdg.bpmn.parser.OtherParser.parseTransaction;
import static nl.onnoh.mdg.bpmn.parser.SubprocessParser.parseAdHocSubProcess;
import static nl.onnoh.mdg.bpmn.parser.SubprocessParser.parseCallActivity;
import static nl.onnoh.mdg.bpmn.parser.SubprocessParser.parseSubProcess;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseAnonymousTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseBusinessRuleTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseManualTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseReceiveTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseScriptTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseSendTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseServiceTask;
import static nl.onnoh.mdg.bpmn.parser.TaskParser.parseUserTask;

@Slf4j
public class FlowElementParser {

    public static List<Object> parseFlowElements(String processId, List<JAXBElement<? extends TFlowElement>> flowElements) {
        List<Object> parsedFlowElements = new ArrayList<>();
        if (flowElements != null) {
            log.debug("Parsing {} flow elements.",  flowElements.size());
            flowElements.forEach(flowElement -> {
                log.debug("Parsing Flow Element: {}", flowElement.getValue().getName());
                String flowElementId = flowElement.getValue().getId();
                String flowType = flowElement.getName().getLocalPart();
                switch (flowType) {
                    case BPMN_ELEMENT_TASK:
                        parsedFlowElements.add(parseAnonymousTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_START_EVENT:
                        parsedFlowElements.add(parseStartEvent(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_END_EVENT:
                        parsedFlowElements.add(parseEndEvent(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_SERVICE_TASK:
                        parsedFlowElements.add(parseServiceTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_SEND_TASK:
                        parsedFlowElements.add(parseSendTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_RECEIVE_TASK:
                        parsedFlowElements.add(parseReceiveTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_USER_TASK:
                        parsedFlowElements.add(parseUserTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_MANUAL_TASK:
                        parsedFlowElements.add(parseManualTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_BUSINESS_RULE_TASK:
                        parsedFlowElements.add(parseBusinessRuleTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_SCRIPT_TASK:
                        parsedFlowElements.add(parseScriptTask(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_CALL_ACTIVITY:
                        parsedFlowElements.add(parseCallActivity(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_EXCLUSIVE_GATEWAY:
                        parsedFlowElements.add(parseExclusiveGateway(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_PARALLEL_GATEWAY:
                        parsedFlowElements.add(parseParallelGateway(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_COMPLEX_GATEWAY:
                        parsedFlowElements.add(parseComplexGateway(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_INCLUSIVE_GATEWAY:
                        parsedFlowElements.add(parseInclusiveGateway(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_EVENT_BASED_GATEWAY:
                        parsedFlowElements.add(parseEventBasedGateway(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_BOUNDARY_EVENT:
                        parsedFlowElements.add(parseBoundaryEvent(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_SUB_PROCESS:
                        parsedFlowElements.add(parseSubProcess(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_AD_HOC_SUB_PROCESS:
                        parsedFlowElements.add(parseAdHocSubProcess(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_SEQUENCE_FLOW:
                        parsedFlowElements.add(parseSequenceFlow(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_INTERMEDIATE_THROW_EVENT:
                        parsedFlowElements.add(parseIntermediateThrowEvent(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_INTERMEDIATE_CATCH_EVENT:
                        parsedFlowElements.add(parseIntermediateCatchEvent(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_TRANSACTION:
                        parsedFlowElements.add(parseTransaction(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_DATA_STORE_REFERENCE:
                        parsedFlowElements.add(parseDataStoreReference(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_DATA_OBJECT_REFERENCE:
                        parsedFlowElements.add(parseDataObjectReference(processId, flowType, flowElement.getValue()));
                        break;
                    case BPMN_ELEMENT_DATA_OBJECT:
                        parsedFlowElements.add(parseDataObject(processId, flowType, flowElement.getValue()));
                        break;
                    default:
                        log.warn("Unknown element name: {}", flowElement.getName());
                }
            });
        }

        return parsedFlowElements;
    }

    private static SequenceFlow parseSequenceFlow(String processId, String flowType, TFlowElement flowElement) {
        SequenceFlow parsedSequenceFlow = new SequenceFlow(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TSequenceFlow> sequenceFlow = objectFactory.createSequenceFlow((TSequenceFlow) flowElement);
        log.debug("SequenceFlow: {}", sequenceFlow.getValue().getName());
        return parsedSequenceFlow;
    }

}
