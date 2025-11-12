package nl.onnoh.mdg.bpmn.model;

import lombok.Data;
import nl.onnoh.mdg.bpmn.model.definitions.Category;
import nl.onnoh.mdg.bpmn.model.definitions.Collaboration;
import nl.onnoh.mdg.bpmn.model.definitions.Error;
import nl.onnoh.mdg.bpmn.model.definitions.Escalation;
import nl.onnoh.mdg.bpmn.model.definitions.Message;
import nl.onnoh.mdg.bpmn.model.definitions.Process;
import nl.onnoh.mdg.bpmn.model.definitions.Signal;

import java.util.List;

@Data
public class BpmnDocumentation {
    String id;
    String fileName;
    String filePath;
    Boolean suppressEmptySections;
    Boolean openSections;
    String exporter;
    String exporterVersion;
    String executionPlatform;
    String executionPlatformVersion;
    String diagramRelationId;
    Collaboration collaboration;
    List<Process> processes;
    List<Message> messages;
    List<Signal> signals;
    List<Category> categories;
    List<Escalation> escalations;
    List<Error> errors;
}
