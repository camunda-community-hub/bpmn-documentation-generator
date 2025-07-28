package nl.onnoh.bdg.bpmn.model;

import lombok.Data;
import nl.onnoh.bdg.bpmn.model.definitions.Category;
import nl.onnoh.bdg.bpmn.model.definitions.Collaboration;
import nl.onnoh.bdg.bpmn.model.definitions.Error;
import nl.onnoh.bdg.bpmn.model.definitions.Escalation;
import nl.onnoh.bdg.bpmn.model.definitions.Message;
import nl.onnoh.bdg.bpmn.model.definitions.Process;
import nl.onnoh.bdg.bpmn.model.definitions.Signal;

import java.util.List;

@Data
public class BpmnDocumentation {
    String id;
    String fileName;
    String exporter;
    String exporterVersion;
    String executionPlatform;
    String executionPlatformVersion;
    Collaboration collaboration;
    List<Process> processes;
    List<Message> messages;
    List<Signal> signals;
    List<Category> categories;
    List<Escalation> escalations;
    List<Error> errors;
}
