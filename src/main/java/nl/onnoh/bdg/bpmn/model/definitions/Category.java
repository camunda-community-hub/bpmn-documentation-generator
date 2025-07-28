package nl.onnoh.bdg.bpmn.model.definitions;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    String id;
    String name;
    List<String> values;
}
