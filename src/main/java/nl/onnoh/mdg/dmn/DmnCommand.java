package nl.onnoh.mdg.dmn;

import freemarker.template.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.GenerateDocumentation;
import nl.onnoh.mdg.dmn.model.DmnDocumentation;
import nl.onnoh.mdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.mdg.dmn.model.definitions.Decision;
import nl.onnoh.mdg.dmn.model.definitions.InputData;
import nl.onnoh.mdg.dmn.model.definitions.KnowledgeSource;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static nl.onnoh.mdg.CamundaConstants.MODELER_EXECUTION_PLATFORM;
import static nl.onnoh.mdg.CamundaConstants.MODELER_EXECUTION_PLATFORM_VERSION;
import static nl.onnoh.mdg.dmn.DmnModelConstants.DMN_ELEMENT_BUSINESS_KNOWLEDGE_MODEL;
import static nl.onnoh.mdg.dmn.DmnModelConstants.DMN_ELEMENT_DECISION;
import static nl.onnoh.mdg.dmn.DmnModelConstants.DMN_ELEMENT_INPUT_DATA;
import static nl.onnoh.mdg.dmn.DmnModelConstants.DMN_ELEMENT_KNOWLEDGE_SOURCE;
import static nl.onnoh.mdg.dmn.DmnModelConstants.DMN_TARGET_NS;
import static nl.onnoh.mdg.dmn.parser.DefinitionsParser.parseBusinessKnowledgeModel;
import static nl.onnoh.mdg.dmn.parser.DefinitionsParser.parseDecision;
import static nl.onnoh.mdg.dmn.parser.DefinitionsParser.parseInputData;
import static nl.onnoh.mdg.dmn.parser.DefinitionsParser.parseKnowledgeSource;
import static nl.onnoh.mdg.template.TemplateService.TEMPLATES_FOLDER;
import static nl.onnoh.mdg.template.TemplateService.TEMPLATE_SUFFIX;
import static nl.onnoh.mdg.template.TemplateService.initTemplateEngine;
import static nl.onnoh.mdg.template.TemplateService.processTemplate;

@CommandLine.Command(name = "dmn"
        , description = "Handling DMN files"
)
@Slf4j
public class DmnCommand implements Callable {

    @CommandLine.Parameters(index = "0", description = "The DMN file to document.")
    private String modelFile;

    @CommandLine.ParentCommand // picocli injects the parent instance
    private GenerateDocumentation parentCommand;

    @Override
    public Integer call() {
        if (!GenerateDocumentation.checkFile(modelFile)) return 1;
        DmnDocumentation dmnDocumentation = buildTemplateVariables(modelFile);
        if (dmnDocumentation == null) {
            log.error("Invalid DMN file: {}", modelFile);
            return 1;
        }
        dmnDocumentation.setSuppressEmptySections(parentCommand.suppressEmptySections);
        dmnDocumentation.setImageFormat(parentCommand.imageFormat);
        dmnDocumentation.setOpenSections(parentCommand.openSections);

        generateOutput(modelFile, dmnDocumentation, parentCommand.outputFormat);
        log.info("Generated DMN documentation.");
        log.debug("Data object : {}", dmnDocumentation);
        return 0;
    }

    private static void generateOutput(String dmnFile, DmnDocumentation dmnDocumentation, String outputType) {
        String templateFile = "dmn" + File.separator + TEMPLATES_FOLDER + File.separator + outputType + File.separator + "dmn-documentation" +TEMPLATE_SUFFIX;
        String outputFileExtension = ".html";
        if ("markdown".equalsIgnoreCase(outputType)) outputFileExtension = ".md";
        String outputFile = dmnFile.replace(".dmn", outputFileExtension);
        Map<String, Object> templateVariables = new HashMap<>();
        Configuration configuration = initTemplateEngine();
        templateVariables.put("dmn", dmnDocumentation);
        processTemplate(configuration, templateFile, outputFile, templateVariables);
        log.info("Written output file: {}", outputFile);
    }

    private static DmnDocumentation buildTemplateVariables(String dmnFile) {
        DmnDocumentation dmnDocumentation = new DmnDocumentation();
        List<Decision> decisions = new ArrayList<>();
        List<InputData> inputData = new ArrayList<>();
        List<BusinessKnowledgeModel> businessKnowledgeModels = new ArrayList<>();
        List<KnowledgeSource> knowledgeSources = new ArrayList<>();
        log.info("Parsing DMN file: {}", dmnFile);
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Definitions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Definitions definitions = (Definitions) jaxbUnmarshaller.unmarshal(new File(dmnFile));
            if (definitions == null || !DMN_TARGET_NS.equalsIgnoreCase(definitions.getNamespace())) return null;
            dmnDocumentation.setId(definitions.getId());
            dmnDocumentation.setFilePath(dmnFile);
            dmnDocumentation.setFileName(new File(dmnFile).getName());
            dmnDocumentation.setDescription(definitions.getDescription());
            dmnDocumentation.setExporter(definitions.getExporter());
            dmnDocumentation.setExporterVersion(definitions.getExporterVersion());
            definitions.getOtherAttributes().forEach((key, value) -> {
                switch (key.getLocalPart()) {
                    case MODELER_EXECUTION_PLATFORM:
                        dmnDocumentation.setExecutionPlatform(value);
                        break;
                    case MODELER_EXECUTION_PLATFORM_VERSION:
                        dmnDocumentation.setExecutionPlatformVersion(value);
                        break;
                    default:
                        log.warn("Unknown definition attribute: {}", key.getLocalPart());
                }
            });

            definitions.getDrgElements().forEach(drgElement -> {
                switch (drgElement.getName().getLocalPart()) {
                    case DMN_ELEMENT_DECISION:
                        decisions.add(parseDecision(drgElement.getValue()));
                        break;
                    case DMN_ELEMENT_INPUT_DATA:
                        inputData.add(parseInputData(drgElement.getValue()));
                        break;
                    case DMN_ELEMENT_KNOWLEDGE_SOURCE:
                        knowledgeSources.add(parseKnowledgeSource(drgElement.getValue()));
                        break;
                    case DMN_ELEMENT_BUSINESS_KNOWLEDGE_MODEL:
                        businessKnowledgeModels.add(parseBusinessKnowledgeModel(drgElement.getValue()));
                        break;
                    default:
                        log.warn("Unknown DRG element : {}", drgElement.getName());
                }
            });
            dmnDocumentation.setDecisions(decisions);
            dmnDocumentation.setInputData(inputData);
            dmnDocumentation.setBusinessKnowledgeModels(businessKnowledgeModels);
            dmnDocumentation.setKnowledgeSources(knowledgeSources);

        } catch (JAXBException e) {
            log.error("JAXB exception {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Error parsing DMN file: {}", dmnFile, e);
            return null;
        }
        return dmnDocumentation;
    }

}
