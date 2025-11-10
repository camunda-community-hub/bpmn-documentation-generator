package nl.onnoh.mdg.dmn.model.requirements;

import lombok.Data;

@Data
public class InformationRequirement {
    String id;
    String description;
    String requiredInputHref;
    String requiredDecisionHref;
}
