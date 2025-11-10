package nl.onnoh.mdg.dmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.dmn.TAuthorityRequirement;
import nl.onnoh.mdg.dmn.TDMNElement.ExtensionElements;
import nl.onnoh.mdg.dmn.TFunctionDefinition;
import nl.onnoh.mdg.dmn.TInformationItem;
import nl.onnoh.mdg.dmn.TInformationRequirement;
import nl.onnoh.mdg.dmn.TKnowledgeRequirement;
import nl.onnoh.mdg.dmn.TLiteralExpression;
import nl.onnoh.mdg.dmn.model.common.EncapsulatedLogic;
import nl.onnoh.mdg.dmn.model.common.FormalParameter;
import nl.onnoh.mdg.dmn.model.common.Variable;
import nl.onnoh.mdg.dmn.model.requirements.AuthorityRequirement;
import nl.onnoh.mdg.dmn.model.requirements.InformationRequirement;
import nl.onnoh.mdg.dmn.model.requirements.KnowledgeRequirement;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.mdg.dmn.DmnModelZeebeConstants.ZEEBE_VERSION_TAG;
import static nl.onnoh.mdg.dmn.parser.DecisionParser.parseLiteralExpression;

@Slf4j
public class CommonParser {

    public static Map<String, Object> parseExtensionElements(ExtensionElements extensionElements) {

        Map<String, Object> parsedExtensionElements = new HashMap<>();

        if (extensionElements != null) {
            List<Element> elements = extensionElements.getAnies();
            log.debug("Parsing {} anies", elements.size());
            for (Element element : elements) {
                log.debug("Parsing ani {} {}:{}", element.getLocalName(), element.getNamespaceURI(), element.getNodeName());
                switch (element.getLocalName()) {
                    case ZEEBE_VERSION_TAG:
                        parsedExtensionElements.put(element.getLocalName(), parseZeebeElementAttributes(element));
                        break;
                    default:
                        log.warn("Unknown extension element: {}:{}", element.getNamespaceURI(), element.getNodeName());
                        break;
                }
            }
        }

        return parsedExtensionElements;
    }

    public static List<AuthorityRequirement> parsedAuthorityRequirements(List<TAuthorityRequirement> authorityRequirements) {
        List<AuthorityRequirement> parsedAuthorityRequirements = new ArrayList<>();
        if (authorityRequirements != null) {
            authorityRequirements.forEach(authorityRequirement -> {
                AuthorityRequirement parsedAuthorityRequirement = new AuthorityRequirement();
                parsedAuthorityRequirement.setId(authorityRequirement.getId());
                parsedAuthorityRequirement.setDescription(authorityRequirement.getDescription());
                if (authorityRequirement.getRequiredAuthority() != null) parsedAuthorityRequirement.setRequiredAuthorityHref(authorityRequirement.getRequiredAuthority().getHref());
                if (authorityRequirement.getRequiredInput() != null) parsedAuthorityRequirement.setRequiredInputHref(authorityRequirement.getRequiredInput().getHref());
                if (authorityRequirement.getRequiredDecision() != null) parsedAuthorityRequirement.setRequiredDecisionHref(authorityRequirement.getRequiredDecision().getHref());
                parsedAuthorityRequirements.add(parsedAuthorityRequirement);
            });
        }
        return parsedAuthorityRequirements;
    }

    public static List<KnowledgeRequirement> parsedKnowledgeRequirements(List<TKnowledgeRequirement> knowledgeRequirements) {
        List<KnowledgeRequirement> parsedKnowledgeRequirements = new ArrayList<>();
        if (knowledgeRequirements != null) {
            knowledgeRequirements.forEach(knowledgeRequirement -> {
                KnowledgeRequirement parsedKnowledgeRequirement = new KnowledgeRequirement();
                parsedKnowledgeRequirement.setId(knowledgeRequirement.getId());
                parsedKnowledgeRequirement.setDescription(knowledgeRequirement.getDescription());
                if (knowledgeRequirement.getRequiredKnowledge() != null) parsedKnowledgeRequirement.setRequiredKnowledgeHref(knowledgeRequirement.getRequiredKnowledge().getHref());
                parsedKnowledgeRequirements.add(parsedKnowledgeRequirement);
            });
        }
        return parsedKnowledgeRequirements;
    }

    public static List<InformationRequirement> parsedInformationRequirements(List<TInformationRequirement> informationRequirements) {
        List<InformationRequirement> parsedInformationRequirements = new ArrayList<>();
        if (informationRequirements != null) {
            informationRequirements.forEach(informationRequirement -> {
                InformationRequirement parsedInformationRequirement = new InformationRequirement();
                parsedInformationRequirement.setId(informationRequirement.getId());
                parsedInformationRequirement.setDescription(informationRequirement.getDescription());
                if (informationRequirement.getRequiredInput() != null) parsedInformationRequirement.setRequiredInputHref(informationRequirement.getRequiredInput().getHref());
                if (informationRequirement.getRequiredDecision() != null) parsedInformationRequirement.setRequiredDecisionHref(informationRequirement.getRequiredDecision().getHref());
                parsedInformationRequirements.add(parsedInformationRequirement);
            });
        }
        return parsedInformationRequirements;
    }

    private static Map<String, String> parseZeebeElementAttributes(Element element) {
        Map<String, String> parsedAttributes = new HashMap<>();
        NamedNodeMap attributes = element.getAttributes();
        log.debug("Parsing {} attributes", attributes.getLength());
        for (int attribute = 0; attribute < attributes.getLength(); attribute++) {
            if (attributes.item(attribute).getNamespaceURI() == null) {
                log.debug("Parsing attribute {} - {}", attributes.item(attribute).getLocalName(), attributes.item(attribute).getNodeValue());
                parsedAttributes.put(attributes.item(attribute).getLocalName(), attributes.item(attribute).getNodeValue());
            }
        }
        return parsedAttributes;
    }

    public static Variable parseVariable(TInformationItem variable) {
        Variable parsedVariable = new Variable();
        parsedVariable.setId(variable.getId());
        parsedVariable.setName(variable.getName());
        parsedVariable.setTypeRef(variable.getTypeRef());

        return parsedVariable;
    }

    public static EncapsulatedLogic parseEncapsulatedLogic(TFunctionDefinition encapsulatedLogic) {
        EncapsulatedLogic parsedEncapsulatedLogic = new EncapsulatedLogic();
        List<FormalParameter> parsedFormalParameters = new ArrayList<>();
        parsedEncapsulatedLogic.setId(encapsulatedLogic.getId());
        encapsulatedLogic.getFormalParameters().forEach( formalParameter -> {
            FormalParameter parsedFormalParameter = new FormalParameter();
            parsedFormalParameter.setName(formalParameter.getName());
            parsedFormalParameter.setTypeRef(formalParameter.getTypeRef());
            parsedFormalParameters.add(parsedFormalParameter);
        });
        parsedEncapsulatedLogic.setFormalParameters(parsedFormalParameters);
        if (encapsulatedLogic.getExpression().getDeclaredType().equals(TLiteralExpression.class))
            parsedEncapsulatedLogic.setLiteralExpression(parseLiteralExpression((TLiteralExpression) encapsulatedLogic.getExpression().getValue()));

        return parsedEncapsulatedLogic;
    }
}
