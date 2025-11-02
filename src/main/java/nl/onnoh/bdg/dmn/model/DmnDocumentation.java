package nl.onnoh.bdg.dmn.model;

import lombok.Data;
import nl.onnoh.bdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.bdg.dmn.model.definitions.Decision;
import nl.onnoh.bdg.dmn.model.definitions.InputData;
import nl.onnoh.bdg.dmn.model.definitions.KnowledgeSource;
import nl.onnoh.bdg.dmn.model.requirements.InformationRequirement;
import nl.onnoh.bdg.dmn.model.requirements.KnowledgeRequirement;

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
    String description;
    List<Decision> decisions;
    List<BusinessKnowledgeModel> businessKnowledgeModels;
    List<InformationRequirement> informationRequirements;
    List<KnowledgeRequirement> knowledgeRequirements;
    List<KnowledgeSource> knowledgeSources;
    List<InputData> inputData;
}
