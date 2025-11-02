package nl.onnoh.bdg.dmn.model.requirements;

import lombok.Data;

@Data
public class KnowledgeRequirement {
    String id;
    String description;
    String requiredKnowledgeHref;
}
