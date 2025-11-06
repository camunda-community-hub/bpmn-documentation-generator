package nl.onnoh.bdg.bpmn;

import freemarker.template.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.GenerateDocumentation;
import nl.onnoh.bdg.bpmn.model.BpmnDocumentation;
import nl.onnoh.bdg.bpmn.model.definitions.Category;
import nl.onnoh.bdg.bpmn.model.definitions.Error;
import nl.onnoh.bdg.bpmn.model.definitions.Escalation;
import nl.onnoh.bdg.bpmn.model.definitions.Message;
import nl.onnoh.bdg.bpmn.model.definitions.Process;
import nl.onnoh.bdg.bpmn.model.definitions.Signal;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static nl.onnoh.bdg.CamundaConstants.MODELER_EXECUTION_PLATFORM;
import static nl.onnoh.bdg.CamundaConstants.MODELER_EXECUTION_PLATFORM_VERSION;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_CATEGORY;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_COLLABORATION;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ERROR;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ESCALATION;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_MESSAGE;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_PROCESS;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SIGNAL;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_TARGET_NS;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseCategory;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseCollaboration;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseError;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseEscalation;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseMessage;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseProcess;
import static nl.onnoh.bdg.bpmn.parser.DefinitionsParser.parseSignal;
import static nl.onnoh.bdg.template.TemplateService.TEMPLATES_FOLDER;
import static nl.onnoh.bdg.template.TemplateService.TEMPLATE_SUFFIX;
import static nl.onnoh.bdg.template.TemplateService.initTemplateEngine;
import static nl.onnoh.bdg.template.TemplateService.processTemplate;

@CommandLine.Command(name = "bpmn"
        , description = "Handling BPMN files"
)
@Slf4j
public class BpmnCommand implements Callable {

    @CommandLine.Parameters(index = "0", description = "The BPMN file to document.")
    private String modelFile;

    @CommandLine.ParentCommand // picocli injects the parent instance
    private GenerateDocumentation parentCommand;

    @Override
    public Integer call() {
        if (!GenerateDocumentation.checkFile(modelFile)) return 1;
        BpmnDocumentation bpmnDocumentation = buildTemplateVariables(modelFile);
        if (bpmnDocumentation == null) {
            log.error("Invalid BPMN file: {}", modelFile);
            return 1;
        }
        bpmnDocumentation.setSuppressEmptySections(parentCommand.suppressEmptySections);
        bpmnDocumentation.setOpenSections(parentCommand.openSections);

        generateOutput(modelFile, bpmnDocumentation, parentCommand.outputFormat);
        log.info("Generated BPMN documentation.");
        log.debug("Data object : {}", bpmnDocumentation);

        return 0;
    }

    private static void generateOutput(String bpmnFile, BpmnDocumentation bpmnDocumentation, String outputType) {
        String templateFile = "bpmn" + File.separator + TEMPLATES_FOLDER + File.separator + outputType + File.separator + "bpmn-documentation" +TEMPLATE_SUFFIX;
        String outputFile = bpmnFile.replace(".bpmn", "." + outputType);
        Map<String, Object> templateVariables = new HashMap<>();
        Configuration configuration = initTemplateEngine();
        templateVariables.put("bpmn", bpmnDocumentation);
        processTemplate(configuration, templateFile, outputFile, templateVariables);
    }

    private static BpmnDocumentation buildTemplateVariables(String bpmnFile) {
        BpmnDocumentation bpmnDocumentation = new BpmnDocumentation();
        List<Process> processes = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        List<Error> errors = new ArrayList<>();
        List<Signal> signals = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<Escalation> escalations = new ArrayList<>();

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Definitions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Definitions definitions = (Definitions) jaxbUnmarshaller.unmarshal(new File(bpmnFile));
            if (definitions == null || BPMN_TARGET_NS.equalsIgnoreCase(definitions.getTargetNamespace())) return null;
            bpmnDocumentation.setId(definitions.getId());
            bpmnDocumentation.setFilePath(bpmnFile);
            bpmnDocumentation.setFileName(new File(bpmnFile).getName());
            bpmnDocumentation.setExporter(definitions.getExporter());
            bpmnDocumentation.setExporterVersion(definitions.getExporterVersion());
            definitions.getOtherAttributes().forEach((key, value) -> {
                switch (key.getLocalPart()) {
                    case MODELER_EXECUTION_PLATFORM:
                        bpmnDocumentation.setExecutionPlatform(value);
                        break;
                    case MODELER_EXECUTION_PLATFORM_VERSION:
                        bpmnDocumentation.setExecutionPlatformVersion(value);
                        break;
                    default:
                        log.warn("Unknown definition attribute: {}", key.getLocalPart());
                }
            });

            definitions.getRootElements().forEach(root -> {
                switch (root.getName().getLocalPart()) {
                    case BPMN_ELEMENT_PROCESS:
                        processes.add(parseProcess(root.getValue()));
                        break;
                    case BPMN_ELEMENT_MESSAGE:
                        messages.add(parseMessage(root.getValue()));
                        break;
                    case BPMN_ELEMENT_SIGNAL:
                        signals.add(parseSignal(root.getValue()));
                        break;
                    case BPMN_ELEMENT_ESCALATION:
                        escalations.add(parseEscalation(root.getValue()));
                        break;
                    case BPMN_ELEMENT_CATEGORY:
                        categories.add(parseCategory(root.getValue()));
                        break;
                    case BPMN_ELEMENT_COLLABORATION:
                        bpmnDocumentation.setCollaboration(parseCollaboration(root.getValue()));
                        break;
                    case BPMN_ELEMENT_ERROR:
                        errors.add(parseError(root.getValue()));
                        break;
                    default:
                        log.warn("Unknown root element : {}", root.getName());
                }
            });
            bpmnDocumentation.setProcesses(processes);
            bpmnDocumentation.setMessages(messages);
            bpmnDocumentation.setSignals(signals);
            bpmnDocumentation.setEscalations(escalations);
            bpmnDocumentation.setCategories(categories);
            bpmnDocumentation.setErrors(errors);
            updateParticipantInformation(bpmnDocumentation);

        } catch (JAXBException e) {
            log.debug("JAXB exception {}", e.getMessage());
            return null;
        }
        return bpmnDocumentation;
    }

    private static void updateParticipantInformation(BpmnDocumentation bpmnDocumentation) {
        if (bpmnDocumentation.getCollaboration() != null && bpmnDocumentation.getCollaboration().getParticipants() != null) {
            bpmnDocumentation.getCollaboration().getParticipants().forEach(participant -> {
                String processId = participant.getProcessRef();
                if (processId == null || processId.isEmpty()) {
                    log.warn("Participant {} has no process reference", participant.getId());
                } else {
                    bpmnDocumentation.getProcesses().stream()
                            .filter(process -> process.getId().equals(processId))
                            .findFirst()
                            .ifPresent(process -> {
                                participant.setProcessName(process.getName());
                            });
                }
            });
        }
    }
}
