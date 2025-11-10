package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TArtifact;
import nl.onnoh.mdg.bpmn.TAssociation;
import nl.onnoh.mdg.bpmn.TCategory;
import nl.onnoh.mdg.bpmn.TCollaboration;
import nl.onnoh.mdg.bpmn.TError;
import nl.onnoh.mdg.bpmn.TEscalation;
import nl.onnoh.mdg.bpmn.TGroup;
import nl.onnoh.mdg.bpmn.TMessage;
import nl.onnoh.mdg.bpmn.TProcess;
import nl.onnoh.mdg.bpmn.TRootElement;
import nl.onnoh.mdg.bpmn.TSignal;
import nl.onnoh.mdg.bpmn.TTextAnnotation;
import nl.onnoh.mdg.bpmn.model.definitions.Category;
import nl.onnoh.mdg.bpmn.model.definitions.Collaboration;
import nl.onnoh.mdg.bpmn.model.definitions.Error;
import nl.onnoh.mdg.bpmn.model.definitions.Escalation;
import nl.onnoh.mdg.bpmn.model.definitions.Message;
import nl.onnoh.mdg.bpmn.model.definitions.Process;
import nl.onnoh.mdg.bpmn.model.definitions.Signal;
import nl.onnoh.mdg.bpmn.model.definitions.collaboration.Artifact;
import nl.onnoh.mdg.bpmn.model.definitions.collaboration.Participant;
import nl.onnoh.mdg.bpmn.model.definitions.process.Lane;
import nl.onnoh.mdg.bpmn.model.definitions.process.LaneSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.mdg.CamundaConstants.CAMUNDA_ATTRIBUTE_ID;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ATTRIBUTE_CATEGORY_VALUE_REF;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ATTRIBUTE_DIRECTION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ATTRIBUTE_SOURCE_REF;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ATTRIBUTE_TARGET_REF;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ASSOCIATION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_DOCUMENTATION;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_GROUP;
import static nl.onnoh.mdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_TEXT_ANNOTATION;
import static nl.onnoh.mdg.bpmn.parser.CommonParser.parseDocumentation;
import static nl.onnoh.mdg.bpmn.parser.CommonParser.parseExtensionElements;
import static nl.onnoh.mdg.bpmn.parser.FlowElementParser.parseFlowElements;

@Slf4j
public class DefinitionsParser {

    public static Collaboration parseCollaboration(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TCollaboration> collaboration = objectFactory.createCollaboration((TCollaboration) value);
        log.debug("Collaboration: {}", collaboration.getValue().getId());

        Collaboration parsedCollaboration = new Collaboration();
        parsedCollaboration.setId(collaboration.getValue().getId());
        parsedCollaboration.setDocumentation(parseDocumentation(collaboration.getValue().getDocumentations()));
        parsedCollaboration.setParticipants(parseParticipants(collaboration.getValue().getParticipants()));
        parsedCollaboration.setExtensions(parseExtensionElements(collaboration.getValue().getExtensionElements()));
        parsedCollaboration.setArtifacts(parseArtifacts(collaboration.getValue().getArtifacts()));

        return parsedCollaboration;
    }

    private static List<Artifact> parseArtifacts(List<JAXBElement<? extends TArtifact>> artifacts) {
        List<Artifact> parsedArtifacts = new ArrayList<>();
        Map<String, Object> details = new HashMap<>();
        for (JAXBElement<? extends TArtifact> artifact : artifacts) {
            Artifact parsedArtifact = new Artifact();
            parsedArtifact.setId(artifact.getValue().getId());
            parsedArtifact.setArtifactType(artifact.getName().getLocalPart());
            parsedArtifact.setDocumentation(parseDocumentation(artifact.getValue().getDocumentations()));
            parsedArtifact.setExtensions(parseExtensionElements(artifact.getValue().getExtensionElements()));
            Map<String, String> artifactDetails = new HashMap<>();
            switch (artifact.getName().getLocalPart()) {
                case BPMN_ELEMENT_GROUP:
                    TGroup group = (TGroup) artifact.getValue();
                    artifactDetails.put(BPMN_ATTRIBUTE_CATEGORY_VALUE_REF, group.getCategoryValueRef().getLocalPart().toString());
                    details.put(BPMN_ELEMENT_GROUP, artifactDetails);
                    break;
                case BPMN_ELEMENT_TEXT_ANNOTATION:
                    TTextAnnotation textAnnotation = (TTextAnnotation) artifact.getValue();
                    artifactDetails.put(BPMN_ELEMENT_TEXT_ANNOTATION, textAnnotation.getText().getContent().toString());
                    artifactDetails.put(BPMN_ELEMENT_DOCUMENTATION, parseDocumentation(textAnnotation.getDocumentations()));
                    details.put(BPMN_ELEMENT_TEXT_ANNOTATION, artifactDetails);
                    break;
                case "association":
                    TAssociation association = (TAssociation) artifact.getValue();
                    artifactDetails.put(CAMUNDA_ATTRIBUTE_ID, association.getId());
                    artifactDetails.put(BPMN_ATTRIBUTE_DIRECTION, association.getAssociationDirection().toString());
                    artifactDetails.put(BPMN_ATTRIBUTE_SOURCE_REF, association.getSourceRef().getLocalPart());
                    artifactDetails.put(BPMN_ATTRIBUTE_TARGET_REF, association.getTargetRef().getLocalPart());
                    details.put(BPMN_ELEMENT_ASSOCIATION, artifactDetails);
                    break;
                default:
                    log.warn("Unknown artifact type: {}", artifact.getName());
            }
        }

        return parsedArtifacts;
    }

    public static List<Participant> parseParticipants(List<nl.onnoh.mdg.bpmn.Participant> participants) {
        log.debug("Parsing {} participants.", participants.size());
        List<Participant> parsedParticipants = new ArrayList<>();
        for (nl.onnoh.mdg.bpmn.Participant participant : participants) {
            log.debug("Participant: {} ({})", participant.getName(), participant.getId());
            Participant collaborationParticipant = new Participant();
            collaborationParticipant.setId(participant.getId());
            collaborationParticipant.setName(participant.getName());
            collaborationParticipant.setProcessRef(participant.getProcessRef().getLocalPart());
            collaborationParticipant.setDocumentation(parseDocumentation(participant.getDocumentations()));
            collaborationParticipant.setExtensions(parseExtensionElements(participant.getExtensionElements()));
            parsedParticipants.add(collaborationParticipant);
        }
        return parsedParticipants;
    }

    public static Object parseElement(TRootElement value) {

        return value;
    }

    public static Escalation parseEscalation(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TEscalation> escalation = objectFactory.createEscalation((TEscalation) value);
        log.debug("Escalation: {}", escalation.getValue().getName());

        Escalation parsedEscalation = new Escalation();
        parsedEscalation.setId(escalation.getValue().getId());
        parsedEscalation.setName(escalation.getValue().getName());
        parsedEscalation.setEscalationCode(escalation.getValue().getEscalationCode());

        return parsedEscalation;
    }

    public static Message parseMessage(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TMessage> message = objectFactory.createMessage((TMessage) value);
        log.debug("Message: {}", message.getValue().getName());

        Message parsedMessage = new Message();
        parsedMessage.setId(message.getValue().getId());
        parsedMessage.setName(message.getValue().getName());
        parsedMessage.setExtensions(parseExtensionElements(message.getValue().getExtensionElements()));

        return parsedMessage;
    }

    public static Process parseProcess(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TProcess> process = objectFactory.createProcess((TProcess) value);
        TProcess processValue = process.getValue();
        Process parsedProcess = new Process();
        parsedProcess.setId(processValue.getId());
        parsedProcess.setName(processValue.getName());
        parsedProcess.setDocumentation(parseDocumentation(processValue.getDocumentations()));
        parsedProcess.setLaneSets(parseLaneSets(processValue.getLaneSets()));
        parsedProcess.setElements(parseFlowElements(processValue.getId(), processValue.getFlowElements()));
        parsedProcess.setExtensions(parseExtensionElements(processValue.getExtensionElements()));

        return parsedProcess;
    }

    private static List<LaneSet> parseLaneSets(List<nl.onnoh.mdg.bpmn.LaneSet> laneSets) {
        List<LaneSet> parsedLaneSets = new ArrayList<>();
        if (laneSets != null) {
            log.debug("Processing {} lane sets.", laneSets.size());
            laneSets.forEach(laneSet -> {
                log.debug("Lane set: {}", laneSet.getName());
                LaneSet parsedLaneSet = new LaneSet();
                parsedLaneSet.setId(laneSet.getId());
                parsedLaneSet.setName(laneSet.getName());
                parsedLaneSet.setLanes(parseLanes(laneSet.getLanes()));
                parsedLaneSets.add(parsedLaneSet);
            });
        }
        return parsedLaneSets;
    }

    private static List<Lane> parseLanes(List<nl.onnoh.mdg.bpmn.Lane> lanes) {
        List<Lane> parsedLanes = new ArrayList<>();
        if (lanes != null) {
            log.debug("Processing {} lanes.", lanes.size());
            lanes.forEach(lane -> {
                log.debug("Lane: {}", lane.getName());
                Lane parsedLane = new Lane();
                parsedLane.setId(lane.getId());
                parsedLane.setName(lane.getName());
                parsedLane.setDocumentation(parseDocumentation(lane.getDocumentations()));
                parsedLane.setExtensions(parseExtensionElements(lane.getExtensionElements()));
                parsedLanes.add(parsedLane);
            });
        }
        return parsedLanes;
    }

    public static Signal parseSignal(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TSignal> signal = objectFactory.createSignal((TSignal) value);
        log.debug("Signal: {}", signal.getValue().getName());

        Signal parsedSignal = new Signal();
        parsedSignal.setId(signal.getValue().getId());
        parsedSignal.setName(signal.getValue().getName());

        return parsedSignal;
    }

    public static Error parseError(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TError> error = objectFactory.createError((TError)  value);
        log.debug("Error: {}", error.getValue().getName());

        Error parsedError = new Error();
        parsedError.setId(error.getValue().getId());
        parsedError.setName(error.getValue().getName());
        parsedError.setErrorCode(error.getValue().getErrorCode());

        return parsedError;
    }

    public static Category parseCategory(TRootElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TCategory> category = objectFactory.createCategory((TCategory) value);
        log.debug("Category: {}", category.getValue().getId());

        Category parsedCategory = new Category();
        parsedCategory.setId(category.getValue().getId());
        parsedCategory.setName(category.getValue().getName());
        List<String> values = new ArrayList<>();
        category.getValue().getCategoryValues().forEach(element -> {
            log.debug("Category value: {}", element.getValue());
            values.add(element.getValue());
        });

        return parsedCategory;
    }

}
