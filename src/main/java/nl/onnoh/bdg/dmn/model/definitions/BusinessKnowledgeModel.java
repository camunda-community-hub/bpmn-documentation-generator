package nl.onnoh.bdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.bdg.dmn.model.common.EncapsulatedLogic;
import nl.onnoh.bdg.dmn.model.common.Variable;
import nl.onnoh.bdg.dmn.model.requirements.AuthorityRequirement;

import java.util.List;

@Data
public class BusinessKnowledgeModel {
    String id;
    String name;
    String documentation;
    List<AuthorityRequirement> authorityRequirements;
    Variable variable;
    EncapsulatedLogic encapsulatedLogic;
}
