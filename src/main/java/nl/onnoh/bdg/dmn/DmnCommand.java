package nl.onnoh.bdg.dmn;

import freemarker.template.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.GenerateDocumentation;
import nl.onnoh.bdg.dmn.model.DmnDocumentation;
import nl.onnoh.bdg.dmn.model.definitions.BusinessKnowledgeModel;
import nl.onnoh.bdg.dmn.model.definitions.Decision;
import nl.onnoh.bdg.dmn.model.definitions.InputData;
import nl.onnoh.bdg.dmn.model.definitions.KnowledgeSource;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.bdg.CamundaConstants.MODELER_EXECUTION_PLATFORM;
import static nl.onnoh.bdg.CamundaConstants.MODELER_EXECUTION_PLATFORM_VERSION;
import static nl.onnoh.bdg.dmn.DmnModelConstants.DMN_ELEMENT_BUSINESS_KNOWLEDGE_MODEL;
import static nl.onnoh.bdg.dmn.DmnModelConstants.DMN_ELEMENT_DECISION;
import static nl.onnoh.bdg.dmn.DmnModelConstants.DMN_ELEMENT_INPUT_DATA;
import static nl.onnoh.bdg.dmn.DmnModelConstants.DMN_ELEMENT_KNOWLEDGE_SOURCE;
import static nl.onnoh.bdg.dmn.parser.DefinitionsParser.parseBusinessKnowledgeModel;
import static nl.onnoh.bdg.dmn.parser.DefinitionsParser.parseDecision;
import static nl.onnoh.bdg.dmn.parser.DefinitionsParser.parseInputData;
import static nl.onnoh.bdg.dmn.parser.DefinitionsParser.parseKnowledgeSource;
import static nl.onnoh.bdg.template.TemplateService.TEMPLATES_FOLDER;
import static nl.onnoh.bdg.template.TemplateService.TEMPLATE_SUFFIX;
import static nl.onnoh.bdg.template.TemplateService.initTemplateEngine;

@CommandLine.Command(name = "dmn"
        , description = "Handling DMN files"
)
@Slf4j
public class DmnCommand implements Runnable {

    @CommandLine.ParentCommand // picocli injects the parent instance
    private GenerateDocumentation parentCommand;

    public void run() {
        DmnDocumentation dmnDocumentation = buildTemplateVariables(parentCommand.modelFile);
        if (dmnDocumentation == null) System.exit(1);
        dmnDocumentation.setSuppressEmptySections(parentCommand.suppressEmptySections);
        dmnDocumentation.setOpenSections(parentCommand.openSections);

        generateOutput(parentCommand.modelFile, dmnDocumentation, parentCommand.outputFormat);
        log.info("Generated DMN documentation.");
        log.debug("Data object : {}", dmnDocumentation);
    }

    private static void generateOutput(String dmnFile, DmnDocumentation dmnDocumentation, String outputType) {
        String templateFile = TEMPLATES_FOLDER + File.separator + outputType + File.separator + "dmmn-documentation" +TEMPLATE_SUFFIX;
        String outputFile = dmnFile.replace(".dmn", "." + outputType);
        Map<String, Object> templateVariables = new HashMap<>();
        Configuration configuration = initTemplateEngine();
        templateVariables.put("dmn", dmnDocumentation);
//        processTemplate(configuration, templateFile, outputFile, templateVariables);
    }

    private static DmnDocumentation buildTemplateVariables(String dmnFile) {
        DmnDocumentation dmnDocumentation = new DmnDocumentation();
        List<Decision> decisions = new ArrayList<>();
        List<InputData> inputData = new ArrayList<>();
        List<BusinessKnowledgeModel> businessKnowledgeModels = new ArrayList<>();
        List<KnowledgeSource> knowledgeSources = new ArrayList<>();

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Definitions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Definitions definitions = (Definitions) jaxbUnmarshaller.unmarshal(new File(dmnFile));
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

        } catch (JAXBException e) {
            log.error("JAXB exception {}", e.getMessage());
            return null;
        }
        return dmnDocumentation;
    }

}
