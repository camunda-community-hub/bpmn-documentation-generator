package nl.onnoh.bdg.dmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.dmn.ObjectFactory;
import nl.onnoh.bdg.dmn.TBusinessKnowledgeModel;
import nl.onnoh.bdg.dmn.TDRGElement;
import nl.onnoh.bdg.dmn.TDecision;
import nl.onnoh.bdg.dmn.TInputData;
import nl.onnoh.bdg.dmn.TKnowledgeSource;
import nl.onnoh.bdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.bdg.dmn.model.definitions.Decision;
import nl.onnoh.bdg.dmn.model.definitions.InputData;
import nl.onnoh.bdg.dmn.model.definitions.KnowledgeSource;

@Slf4j
public class DefinitionsParser {

    public static Decision parseDecision(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDecision> decision = objectFactory.createDecision((TDecision) value);
        log.debug("Decision: {}", decision.getValue().getId());

        Decision parsedDecision = new Decision();
        parsedDecision.setId(decision.getValue().getId());
        parsedDecision.setName(decision.getValue().getName());
        parsedDecision.setDocumentation(decision.getValue().getDescription());
        parsedDecision.setQuestion(decision.getValue().getQuestion());
        parsedDecision.setAllowedAnswers(decision.getValue().getAllowedAnswers());

        return parsedDecision;
    }

    public static InputData parseInputData(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TInputData> inputData = objectFactory.createInputData((TInputData) value);
        log.debug("Input Data: {}", inputData.getValue().getId());
        
        InputData parsedInputData = new InputData();
        parsedInputData.setId(inputData.getValue().getId());
        parsedInputData.setName(inputData.getValue().getName());
        parsedInputData.setDocumentation(inputData.getValue().getDescription());
        return parsedInputData;
    }

    public static KnowledgeSource parseKnowledgeSource(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TKnowledgeSource> knowledgeSource = objectFactory.createKnowledgeSource((TKnowledgeSource) value);
        log.debug("Knowledge Source: {}", knowledgeSource.getValue().getId());

        KnowledgeSource parsedKnowledgeSource = new KnowledgeSource();
        parsedKnowledgeSource.setId(knowledgeSource.getValue().getId());
        parsedKnowledgeSource.setName(knowledgeSource.getValue().getName());
        parsedKnowledgeSource.setDocumentation(knowledgeSource.getValue().getDescription());

        return parsedKnowledgeSource;
    }

    public static BusinessKnowledgeModel parseBusinessKnowledgeModel(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TBusinessKnowledgeModel> businessKnowledgeModel = objectFactory.createBusinessKnowledgeModel((TBusinessKnowledgeModel) value);
        log.debug("Business Knowledge Model: {}", businessKnowledgeModel.getValue().getId());

        BusinessKnowledgeModel parsedBusinessKnowledgeModel = new BusinessKnowledgeModel();
        parsedBusinessKnowledgeModel.setId(businessKnowledgeModel.getValue().getId());
        parsedBusinessKnowledgeModel.setName(businessKnowledgeModel.getValue().getName());
        parsedBusinessKnowledgeModel.setDocumentation(businessKnowledgeModel.getValue().getDescription());

        return parsedBusinessKnowledgeModel;
    }

}
