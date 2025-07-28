package nl.onnoh.bdg.bpmn.model.common;

import lombok.Getter;
import lombok.Setter;
import nl.onnoh.bdg.bpmn.TFlowElement;

import java.util.Map;

import static nl.onnoh.bdg.bpmn.parser.CommonParser.parseDocumentation;
import static nl.onnoh.bdg.bpmn.parser.CommonParser.parseElementTemplate;
import static nl.onnoh.bdg.bpmn.parser.CommonParser.parseExtensionElements;


@Getter
@Setter
public abstract class FlowElement {

    private String id;
    private String name;
    private String flowType;
    private String processId;
    private String documentation;
    private ElementTemplate template;
    private Map<String, Object> extensions;

    public FlowElement(String processId, String flowType, TFlowElement flowElement) {
        this.id = flowElement.getId();
        this.name = flowElement.getName();
        this.flowType = flowType;
        this.processId = processId;
        this.documentation = parseDocumentation(flowElement.getDocumentations());
        this.template = parseElementTemplate(flowElement.getOtherAttributes());
        this.extensions = parseExtensionElements(flowElement.getExtensionElements());
    }

}
