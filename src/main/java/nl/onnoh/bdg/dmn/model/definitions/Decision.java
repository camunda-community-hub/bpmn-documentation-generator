package nl.onnoh.bdg.dmn.model.definitions;

import lombok.Data;

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
}
