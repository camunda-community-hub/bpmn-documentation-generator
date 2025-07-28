package nl.onnoh.bdg.bpmn.model.definitions.collaboration;

import lombok.Data;

import java.util.Map;


@Data
public class Artifact {
    String id;
    String documentation;
    String artifactType;
    Map<String, Object> details;
    Map<String, Object> extensions;
}
