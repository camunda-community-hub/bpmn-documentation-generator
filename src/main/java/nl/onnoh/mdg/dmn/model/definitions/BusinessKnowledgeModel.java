package nl.onnoh.mdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.mdg.dmn.model.common.EncapsulatedLogic;
import nl.onnoh.mdg.dmn.model.common.Variable;
import nl.onnoh.mdg.dmn.model.requirements.AuthorityRequirement;
import nl.onnoh.mdg.dmn.model.requirements.KnowledgeRequirement;

import java.util.List;

@Data
public class BusinessKnowledgeModel {
    String id;
    String name;
    String description;
    List<AuthorityRequirement> authorityRequirements;
    List<KnowledgeRequirement> knowledgeRequirements;
    EncapsulatedLogic encapsulatedLogic;
    Variable variable;
}
