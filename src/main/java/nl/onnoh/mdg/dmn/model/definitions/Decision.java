package nl.onnoh.mdg.dmn.model.definitions;

import lombok.Data;
import nl.onnoh.mdg.dmn.model.decision.DecisionTable;
import nl.onnoh.mdg.dmn.model.common.LiteralExpression;
import nl.onnoh.mdg.dmn.model.common.Variable;
import nl.onnoh.mdg.dmn.model.requirements.AuthorityRequirement;
import nl.onnoh.mdg.dmn.model.requirements.InformationRequirement;
import nl.onnoh.mdg.dmn.model.requirements.KnowledgeRequirement;

import java.util.List;
import java.util.Map;

@Data
public class Decision {
    String id;
    String name;
    String description;
    String question;
    String allowedAnswers;
    List<InformationRequirement> informationRequirements;
    List<KnowledgeRequirement> knowledgeRequirements;
    List<AuthorityRequirement> authorityRequirements;
    Map<String, Object> extensions;
    DecisionTable decisionTable;
    LiteralExpression literalExpression;
    Variable variable;
}
