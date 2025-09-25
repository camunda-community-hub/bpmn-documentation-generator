package nl.onnoh.bdg.dmn.model.definitions;

import lombok.Data;

@Data
public class InformationRequirement {
    String id;
    String name;
    String requiredInput;
    String requiredDecision;
}
