package nl.onnoh.mdg.dmn.model.requirements;

import lombok.Data;

@Data
public class KnowledgeRequirement {
    String id;
    String description;
    String requiredKnowledgeHref;
}
