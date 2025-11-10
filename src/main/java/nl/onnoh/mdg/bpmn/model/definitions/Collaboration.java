package nl.onnoh.mdg.bpmn.model.definitions;

import lombok.Data;
import nl.onnoh.mdg.bpmn.model.definitions.collaboration.Artifact;
import nl.onnoh.mdg.bpmn.model.definitions.collaboration.Group;
import nl.onnoh.mdg.bpmn.model.definitions.collaboration.Participant;

import java.util.List;
import java.util.Map;

@Data
public class Collaboration {
    String id;
    String name;
    String documentation;
    String processRef;
    Map<String, Object> extensions;
    List<Artifact> artifacts;
    List<Participant> participants;
    List<Group> groups;
}
