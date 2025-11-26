package nl.onnoh.mdg.dmn.model;

import lombok.Data;
import nl.onnoh.mdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.mdg.dmn.model.definitions.Decision;
import nl.onnoh.mdg.dmn.model.definitions.InputData;
import nl.onnoh.mdg.dmn.model.definitions.KnowledgeSource;
import nl.onnoh.mdg.dmn.model.requirements.InformationRequirement;
import nl.onnoh.mdg.dmn.model.requirements.KnowledgeRequirement;

import java.util.List;

@Data
public class DmnDocumentation {
    String id;
    String fileName;
    String filePath;
    Boolean suppressEmptySections;
    Boolean openSections;
    String exporter;
    String exporterVersion;
    String executionPlatform;
    String executionPlatformVersion;
    String imageFormat;
    String description;
    List<Decision> decisions;
    List<BusinessKnowledgeModel> businessKnowledgeModels;
    List<InformationRequirement> informationRequirements;
    List<KnowledgeRequirement> knowledgeRequirements;
    List<KnowledgeSource> knowledgeSources;
    List<InputData> inputData;
}
