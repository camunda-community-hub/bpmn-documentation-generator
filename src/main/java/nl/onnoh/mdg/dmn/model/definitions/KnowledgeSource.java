package nl.onnoh.mdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.mdg.dmn.model.requirements.AuthorityRequirement;

import java.util.List;

@Data
public class KnowledgeSource {
    String id;
    String name;
    String description;
    List<AuthorityRequirement> authorityRequirements;
}
