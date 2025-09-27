package nl.onnoh.bdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.bdg.dmn.model.requirements.AuthorityRequirement;

import java.util.List;

@Data
public class KnowledgeSource {
    String id;
    String name;
    String documentation;
    List<AuthorityRequirement> authorityRequirements;
}
