package nl.onnoh.mdg.bpmn.model.definitions.process;

import lombok.Data;

import java.util.Map;


@Data
public class Lane {
    String id;
    String name;
    String documentation;
    Map<String, Object> details;
    Map<String, Object> extensions;

}
