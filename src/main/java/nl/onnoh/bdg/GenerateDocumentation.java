package nl.onnoh.bdg;

import freemarker.template.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.bpmn.Definitions;
import nl.onnoh.bdg.bpmn.model.BpmnDocumentation;
import nl.onnoh.bdg.bpmn.model.definitions.Category;
import nl.onnoh.bdg.bpmn.model.definitions.Error;
import nl.onnoh.bdg.bpmn.model.definitions.Escalation;
import nl.onnoh.bdg.bpmn.model.definitions.Message;
import nl.onnoh.bdg.bpmn.model.definitions.Process;
import nl.onnoh.bdg.bpmn.model.definitions.Signal;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_CATEGORY;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_COLLABORATION;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ERROR;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_ESCALATION;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_MESSAGE;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_PROCESS;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.BPMN_ELEMENT_SIGNAL;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.MODELER_EXECUTION_PLATFORM;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.MODELER_EXECUTION_PLATFORM_VERSION;
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

@Slf4j
@CommandLine.Command(name = "generate", description = "Generate documentation for given BPMN file.")
public class GenerateDocumentation implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "The BPMN file to document.")
    private String bpmnFile;

    @CommandLine.Option(names = { "-h", "--help", "-?", "-help"}, usageHelp = true, description = "Display this help and exit")
    private boolean help;

    @CommandLine.Option(names = {"-of", "--output-format"}, defaultValue = "html", description = "The document format (default: ${DEFAULT-VALUE}).")
    private String outputFormat;

    @CommandLine.Option(names = {"-s", "--suppress-empty-sections"}, defaultValue = "false", description = "Suppress empty sections (default: ${DEFAULT-VALUE}).")
    private boolean suppressEmptySections;

    @CommandLine.Option(names = {"-o", "--open-sections"}, defaultValue = "false", description = "Open all sections (default: ${DEFAULT-VALUE}).")
    private boolean openSections;

    public static void main(String... args) {
        int exitCode = new CommandLine(new GenerateDocumentation()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        log.info("Analyzing {}", bpmnFile);

        if (!checkFile(bpmnFile)) {
            return 1;
        }

        BpmnDocumentation bpmnDocumentation = buildTemplateVariables(bpmnFile);
        if (bpmnDocumentation == null) return 1;
        bpmnDocumentation.setSuppressEmptySections(suppressEmptySections);
        bpmnDocumentation.setOpenSections(openSections);

        if (generateOutput(bpmnFile, bpmnDocumentation, outputFormat)) {
            log.info("Generated BPMN documentation.");
            log.debug("Data object : {}", bpmnDocumentation);
            return 0;
        }  else {
            log.error("Failed to generate documentation for {}", bpmnFile);
        }

        return 1;
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
            log.error("JAXB exception {}", e.getMessage());
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

    private static boolean generateOutput(String bpmnFile, BpmnDocumentation bpmnDocumentation, String outputType) {
        String templateFile = TEMPLATES_FOLDER + File.separator + outputType + File.separator + "bpmn-documentation" +TEMPLATE_SUFFIX;
        String outputFile = bpmnFile.replace(".bpmn", "." + outputType);
        Map<String, Object> templateVariables = new HashMap<>();
        Configuration configuration = initTemplateEngine();
        templateVariables.put("bpmn", bpmnDocumentation);
        processTemplate(configuration, templateFile, outputFile, templateVariables);
        return true;
    }

    private static boolean checkFile(String bpmnFile) {
        Path filePath = Paths.get(bpmnFile);
        if (!Files.exists(filePath)) {
            log.error("File not found: {}", bpmnFile);
            return false;
        }
        if (!Files.isRegularFile(filePath)) {
            log.error("Not a regular file: {}", bpmnFile);
            return false;
        }
        if (!Files.isReadable(filePath)) {
            log.error("Error reading file: {}", bpmnFile);
            return false;
        }
        return true;
    }

}
