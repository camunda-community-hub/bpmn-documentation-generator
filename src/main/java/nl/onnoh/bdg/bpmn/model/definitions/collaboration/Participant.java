package nl.onnoh.bdg.bpmn.model.definitions.collaboration;

import lombok.Data;

import java.util.Map;


@Data
public class Participant {
    String id;
    String name;
    String documentation;
    String processRef;
    String processName;
    Map<String, Object> extensions;
}
