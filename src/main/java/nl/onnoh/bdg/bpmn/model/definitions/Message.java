package nl.onnoh.bdg.bpmn.model.definitions;

import lombok.Data;

@Data
public class Message {
    String id;
    String name;
    String correlationKey;
}
