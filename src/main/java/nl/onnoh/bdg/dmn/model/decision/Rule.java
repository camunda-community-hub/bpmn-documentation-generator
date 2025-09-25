package nl.onnoh.bdg.dmn.model.decision;

import lombok.Data;

@Data
public class Rule {
    String id;
    String name;
    String description;
    String inputEntry;
    String outputEntry;
    String annotationEntry;
    String ruleType;
    String ruleOrder;
    String enabled;
    String hitPolicy;
}
