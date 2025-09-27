package nl.onnoh.bdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.bdg.dmn.model.common.LiteralExpression;
import nl.onnoh.bdg.dmn.model.common.Variable;
import nl.onnoh.bdg.dmn.model.requirements.AuthorityRequirement;
import nl.onnoh.bdg.dmn.model.requirements.InformationRequirement;
import nl.onnoh.bdg.dmn.model.requirements.KnowledgeRequirement;

import java.util.List;
import java.util.Map;

@Data
public class Decision {
    String id;
    String name;
    String documentation;
    String question;
    String allowedAnswers;
    List<InformationRequirement> informationRequirements;
    List<KnowledgeRequirement> knowledgeRequirements;
    List<AuthorityRequirement> authorityRequirements;
    Map<String, Object> extensions;
    Variable variable;
    LiteralExpression literalExpression;
}
