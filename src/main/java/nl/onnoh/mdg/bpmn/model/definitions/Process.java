package nl.onnoh.mdg.bpmn.model.definitions;

import lombok.Data;
import nl.onnoh.mdg.bpmn.model.definitions.process.LaneSet;

import java.util.List;
import java.util.Map;

@Data
public class Process {
    String id;
    String name;
    String documentation;
    Map<String, Object> extensions;
    List<LaneSet> laneSets;
    List<Object> elements;
}
