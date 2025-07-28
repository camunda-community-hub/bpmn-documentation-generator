package nl.onnoh.bdg.bpmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.bpmn.Documentation;
import nl.onnoh.bdg.bpmn.ExtensionElements;
import nl.onnoh.bdg.bpmn.ObjectFactory;
import nl.onnoh.bdg.bpmn.UserTaskForm;
import nl.onnoh.bdg.bpmn.model.common.ElementTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.bdg.bpmn.BpmnModelConstants.CAMUNDA_ELEMENT_PROPERTIES;
import static nl.onnoh.bdg.bpmn.BpmnModelConstants.CAMUNDA_ELEMENT_PROPERTY;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.CAMUNDA_EXAMPLE_DATA;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_CALLED_DECISION;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_CALLED_ELEMENT;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_EXECUTION_LISTENER;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_EXECUTION_LISTENERS;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_FORM_DEFINITION;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_INPUT;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_OUTPUT;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_TASK_HEADER;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_TASK_HEADERS;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE_ICON;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE_VERSION;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_NS;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_SCRIPT;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_SUBSCRIPTION;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_TASK_DEFINITION;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_USER_TASK_FORM;
import static nl.onnoh.bdg.bpmn.BpmnModelZeebeConstants.ZEEBE_VERSION_TAG;

@Slf4j
public class CommonParser {

    public static String parseDocumentation(List<Documentation> documentations) {
        StringBuilder parsedDocumentation = new StringBuilder();
        if (documentations != null) {
            documentations.forEach(documentation -> {
                documentation.getContent().forEach(content -> {
                    parsedDocumentation.append(content);
                    parsedDocumentation.append("\n");
                });
            });
        }

        return parsedDocumentation.toString();
    }

    public static Map<String, Object> parseExtensionElements(ExtensionElements extensionElements) {
        Map<String, Object> parsedExtensionElements = new HashMap<>();

        if (extensionElements != null) {
            List<Element> elements = extensionElements.getAnies();
            log.debug(" Parsing {} Anies", elements.size());
            for (Element element : elements) {
                log.debug("Parsing {} {}:{}", element.getLocalName(), element.getNamespaceURI(), element.getNodeName());
                switch (element.getLocalName()) {
                    case CAMUNDA_ELEMENT_PROPERTIES:
                        Map<String, String> properties = parseZeebeChildElements(element, CAMUNDA_ELEMENT_PROPERTY);
                        Map<String, String> exampleData = new HashMap<>();
                        if (properties.containsKey(CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON)) {
                            exampleData.put(CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON, properties.get(CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON));
                            properties.remove(CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON);
                        }
                        parsedExtensionElements.put(CAMUNDA_EXAMPLE_DATA, exampleData);
                        parsedExtensionElements.put(CAMUNDA_ELEMENT_PROPERTIES, properties);
                        break;
                    case ZEEBE_IO_MAPPING:
                        parsedExtensionElements.put(ZEEBE_IO_MAPPING_INPUT, parseZeebeChildElements(element, ZEEBE_IO_MAPPING_INPUT));
                        parsedExtensionElements.put(ZEEBE_IO_MAPPING_OUTPUT, parseZeebeChildElements(element, ZEEBE_IO_MAPPING_OUTPUT));
                        break;
                    case ZEEBE_IO_TASK_HEADERS:
                        parsedExtensionElements.put(ZEEBE_IO_TASK_HEADERS, parseZeebeChildElements(element, ZEEBE_IO_TASK_HEADER));
                        break;
                    case ZEEBE_EXECUTION_LISTENERS:
                        parsedExtensionElements.put(ZEEBE_EXECUTION_LISTENERS, parseZeebeChildElements(element, ZEEBE_EXECUTION_LISTENER));
                        break;
                    case ZEEBE_USER_TASK_FORM:
                        ObjectFactory objectFactory = new ObjectFactory();
                        UserTaskForm userTaskForm = objectFactory.createUserTaskForm();
                        parsedExtensionElements.put(ZEEBE_USER_TASK_FORM, userTaskForm);
                        break;
                    case ZEEBE_FORM_DEFINITION:
                    case ZEEBE_TASK_DEFINITION:
                    case ZEEBE_CALLED_ELEMENT:
                    case ZEEBE_CALLED_DECISION:
                    case ZEEBE_SUBSCRIPTION:
                    case ZEEBE_SCRIPT:
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

    private static Map<String, String> parseOtherAttributes(Map<QName, String> otherAttributes) {
        Map<String, String> parsedOtherAttributes = new HashMap<>();
        if (otherAttributes != null) {
            for (Map.Entry<QName, String> attr : otherAttributes.entrySet()) {
                log.debug("Other Attribute: {} - {}", attr.getKey(),  attr.getValue());
                parsedOtherAttributes.put(attr.getKey().getLocalPart(), attr.getValue());
            }
        }
        return parsedOtherAttributes;
    }

    private static Map<String, String> parseZeebeChildElements(Element element, String elementName) {
        Map<String, String> parsedElements = new HashMap<>();
        NodeList nodeList = element.getElementsByTagNameNS(ZEEBE_NS, elementName);
        for (int node = 0; node < nodeList.getLength(); node++) {
            NamedNodeMap inputAttributes = nodeList.item(node).getAttributes();
            String key = "unknown";
            String value = "unknown";
            for (int attribute = 0; attribute < inputAttributes.getLength(); attribute++) {
                switch (inputAttributes.item(attribute).getLocalName()) {
                    case "name":
                    case "source":
                    case "key":
                        key = inputAttributes.item(attribute).getNodeValue();
                        break;
                    case "value":
                    case "target":
                        value = inputAttributes.item(attribute).getNodeValue();
                        break;
                    case "eventType":
                    case "retries":
                    case "type":
                        key = inputAttributes.item(attribute).getLocalName();
                        value = inputAttributes.item(attribute).getNodeValue();
                        break;
                    default:
                        log.warn("Unknown attribute name: {}", inputAttributes.item(attribute).getLocalName());
                        break;
                }
            }
            parsedElements.put(key, value);
        }
        return parsedElements;
    }

    private static Map<String, String> parseZeebeElementAttributes(Element element) {
        Map<String, String> parsedElementAttributes = new HashMap<>();
        NamedNodeMap attributes = element.getAttributes();
        for (int attribute = 0; attribute < attributes.getLength(); attribute++) {
            if (attributes.item(attribute).getNamespaceURI() == null) {
                String key = attributes.item(attribute).getLocalName();
                String value = attributes.item(attribute).getNodeValue();
                parsedElementAttributes.put(key, value);
            }
        }
        return parsedElementAttributes;
    }

    public static ElementTemplate parseElementTemplate(Map<QName, String> otherAttributes) {
        ElementTemplate elementTemplate = new ElementTemplate();
        otherAttributes.forEach((key, value) -> {
            switch (key.getLocalPart()) {
                case ZEEBE_MODELER_TEMPLATE:
                    elementTemplate.setName(value);
                    break;
                case ZEEBE_MODELER_TEMPLATE_VERSION:
                    elementTemplate.setVersion(value);
                    break;
                case ZEEBE_MODELER_TEMPLATE_ICON:
                    elementTemplate.setIcon(value);
                    break;
                default:
                    log.warn("Unknown element attribute: {}", key);
            }
        });
        return elementTemplate;
    }
}
