package nl.onnoh.mdg.dmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.dmn.ObjectFactory;
import nl.onnoh.mdg.dmn.TBusinessKnowledgeModel;
import nl.onnoh.mdg.dmn.TDRGElement;
import nl.onnoh.mdg.dmn.TDecision;
import nl.onnoh.mdg.dmn.TDecisionTable;
import nl.onnoh.mdg.dmn.TInputData;
import nl.onnoh.mdg.dmn.TKnowledgeSource;
import nl.onnoh.mdg.dmn.TLiteralExpression;
import nl.onnoh.mdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.mdg.dmn.model.definitions.Decision;
import nl.onnoh.mdg.dmn.model.definitions.InputData;
import nl.onnoh.mdg.dmn.model.definitions.KnowledgeSource;

import static nl.onnoh.mdg.dmn.parser.CommonParser.parseEncapsulatedLogic;
import static nl.onnoh.mdg.dmn.parser.CommonParser.parseExtensionElements;
import static nl.onnoh.mdg.dmn.parser.CommonParser.parseVariable;
import static nl.onnoh.mdg.dmn.parser.CommonParser.parsedAuthorityRequirements;
import static nl.onnoh.mdg.dmn.parser.CommonParser.parsedInformationRequirements;
import static nl.onnoh.mdg.dmn.parser.CommonParser.parsedKnowledgeRequirements;
import static nl.onnoh.mdg.dmn.parser.DecisionParser.parseDecisionTable;
import static nl.onnoh.mdg.dmn.parser.DecisionParser.parseLiteralExpression;

@Slf4j
public class DefinitionsParser {

    public static Decision parseDecision(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDecision> decision = objectFactory.createDecision((TDecision) value);
        log.debug("Decision: {}", decision.getValue().getId());
        Decision parsedDecision = new Decision();
        parsedDecision.setId(decision.getValue().getId());
        parsedDecision.setName(decision.getValue().getName());
        parsedDecision.setDescription(decision.getValue().getDescription());
        parsedDecision.setQuestion(decision.getValue().getQuestion());
        parsedDecision.setAllowedAnswers(decision.getValue().getAllowedAnswers());
        if (decision.getValue().getExpression().getDeclaredType().equals(TDecisionTable.class))
            parsedDecision.setDecisionTable(parseDecisionTable((TDecisionTable) decision.getValue().getExpression().getValue()));
        if (decision.getValue().getExpression().getDeclaredType().equals(TLiteralExpression.class))
            parsedDecision.setLiteralExpression(parseLiteralExpression((TLiteralExpression) decision.getValue().getExpression().getValue()));
        if (decision.getValue().getVariable() != null) parsedDecision.setVariable(parseVariable(decision.getValue().getVariable()));
        parsedDecision.setExtensions(parseExtensionElements(decision.getValue().getExtensionElements()));
        parsedDecision.setAuthorityRequirements(parsedAuthorityRequirements(decision.getValue().getAuthorityRequirements()));
        parsedDecision.setKnowledgeRequirements(parsedKnowledgeRequirements(decision.getValue().getKnowledgeRequirements()));
        parsedDecision.setInformationRequirements(parsedInformationRequirements(decision.getValue().getInformationRequirements()));
        return parsedDecision;
    }

    public static InputData parseInputData(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TInputData> inputData = objectFactory.createInputData((TInputData) value);
        log.debug("Input Data: {}", inputData.getValue().getId());
        
        InputData parsedInputData = new InputData();
        parsedInputData.setId(inputData.getValue().getId());
        parsedInputData.setName(inputData.getValue().getName());
        parsedInputData.setDescription(inputData.getValue().getDescription());
        return parsedInputData;
    }

    public static KnowledgeSource parseKnowledgeSource(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TKnowledgeSource> knowledgeSource = objectFactory.createKnowledgeSource((TKnowledgeSource) value);
        log.debug("Knowledge Source: {}", knowledgeSource.getValue().getId());

        KnowledgeSource parsedKnowledgeSource = new KnowledgeSource();
        parsedKnowledgeSource.setId(knowledgeSource.getValue().getId());
        parsedKnowledgeSource.setName(knowledgeSource.getValue().getName());
        parsedKnowledgeSource.setDescription(knowledgeSource.getValue().getDescription());
        parsedKnowledgeSource.setAuthorityRequirements(parsedAuthorityRequirements(knowledgeSource.getValue().getAuthorityRequirements()));
        return parsedKnowledgeSource;
    }

    public static BusinessKnowledgeModel parseBusinessKnowledgeModel(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TBusinessKnowledgeModel> businessKnowledgeModel = objectFactory.createBusinessKnowledgeModel((TBusinessKnowledgeModel) value);
        log.debug("Business Knowledge Model: {}", businessKnowledgeModel.getValue().getId());

        BusinessKnowledgeModel parsedBusinessKnowledgeModel = new BusinessKnowledgeModel();
        parsedBusinessKnowledgeModel.setId(businessKnowledgeModel.getValue().getId());
        parsedBusinessKnowledgeModel.setName(businessKnowledgeModel.getValue().getName());
        parsedBusinessKnowledgeModel.setDescription(businessKnowledgeModel.getValue().getDescription());
        parsedBusinessKnowledgeModel.setAuthorityRequirements(parsedAuthorityRequirements(businessKnowledgeModel.getValue().getAuthorityRequirements()));
        parsedBusinessKnowledgeModel.setKnowledgeRequirements(parsedKnowledgeRequirements(businessKnowledgeModel.getValue().getKnowledgeRequirements()));
        if (businessKnowledgeModel.getValue().getVariable() != null) parsedBusinessKnowledgeModel.setVariable(parseVariable(businessKnowledgeModel.getValue().getVariable()));
        if (businessKnowledgeModel.getValue().getEncapsulatedLogic() != null) parsedBusinessKnowledgeModel.setEncapsulatedLogic(parseEncapsulatedLogic(businessKnowledgeModel.getValue().getEncapsulatedLogic()));
        return parsedBusinessKnowledgeModel;
    }

}
