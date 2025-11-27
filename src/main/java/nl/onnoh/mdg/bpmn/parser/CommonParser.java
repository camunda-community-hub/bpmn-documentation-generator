package nl.onnoh.mdg.bpmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.Documentation;
import nl.onnoh.mdg.bpmn.ExtensionElements;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.UserTaskForm;
import nl.onnoh.mdg.bpmn.model.common.ElementTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.mdg.CamundaConstants.CAMUNDA_ELEMENT_PROPERTIES;
import static nl.onnoh.mdg.CamundaConstants.CAMUNDA_ELEMENT_PROPERTY;
import static nl.onnoh.mdg.CamundaConstants.CAMUNDA_EXAMPLE_DATA;
import static nl.onnoh.mdg.CamundaConstants.CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_CALLED_DECISION;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_CALLED_ELEMENT;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_EXECUTION_LISTENER;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_EXECUTION_LISTENERS;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_FORM_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_INPUT;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_INPUTS;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_OUTPUT;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_MAPPING_OUTPUTS;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_TASK_HEADER;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_IO_TASK_HEADERS;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE_ICON;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_MODELER_TEMPLATE_VERSION;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_NS;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_SCRIPT;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_SUBSCRIPTION;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_TASK_DEFINITION;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_USER_TASK;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_USER_TASK_FORM;
import static nl.onnoh.mdg.bpmn.BpmnModelZeebeConstants.ZEEBE_VERSION_TAG;

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
            log.debug("Parsing {} anies", elements.size());
            for (Element element : elements) {
                log.debug("Parsing ani {} {}:{}", element.getLocalName(), element.getNamespaceURI(), element.getNodeName());
                switch (element.getLocalName()) {
                    case CAMUNDA_ELEMENT_PROPERTIES:
                        List<Map<String, Object>> propertyList = parseZeebeChildElements(element, CAMUNDA_ELEMENT_PROPERTY);
                        propertyList.forEach(property -> {
                            Object propertiesMap = property.get(CAMUNDA_ELEMENT_PROPERTY);
                            log.debug("Property: {}", propertiesMap);
                            if (propertiesMap instanceof Map) {
                                Map<String, String> propertyMap = (Map<String, String>) propertiesMap;
                                String name = propertyMap.get("name");
                                if (name != null ) {
                                    if (CAMUNDA_MODELER_EXAMPLE_OUTPUT_JSON.equals(name)) {
                                        parsedExtensionElements.put(CAMUNDA_EXAMPLE_DATA, List.of(property));
                                    } else {
                                        parsedExtensionElements.put(CAMUNDA_ELEMENT_PROPERTY, List.of(property));
                                    }
                                } else {
                                    log.warn("Property name is null: {}", propertyMap);
                                }
                            } else {
                                log.warn("Expected a Map for property, but got: {}", propertiesMap.getClass().getName());
                            }
                        });
                        break;
                    case ZEEBE_IO_MAPPING:
                        parsedExtensionElements.put(ZEEBE_IO_MAPPING_INPUTS, parseZeebeChildElements(element, ZEEBE_IO_MAPPING_INPUT));
                        parsedExtensionElements.put(ZEEBE_IO_MAPPING_OUTPUTS, parseZeebeChildElements(element, ZEEBE_IO_MAPPING_OUTPUT));
                        break;
                    case ZEEBE_IO_TASK_HEADERS:
                        parsedExtensionElements.put(ZEEBE_IO_TASK_HEADERS, parseZeebeChildElements(element, ZEEBE_IO_TASK_HEADER));
                        break;
                    case ZEEBE_EXECUTION_LISTENERS:
                        parsedExtensionElements.put(ZEEBE_EXECUTION_LISTENERS, parseZeebeChildElements(element, ZEEBE_EXECUTION_LISTENER));
                        break;
                    case ZEEBE_USER_TASK:
                        parsedExtensionElements.put(ZEEBE_USER_TASK, parseZeebeChildElements(element, ZEEBE_USER_TASK));
                        break;
                    case ZEEBE_USER_TASK_FORM:
                        ObjectFactory objectFactory = new ObjectFactory();
                        UserTaskForm userTaskForm = objectFactory.createUserTaskForm();
                        parsedExtensionElements.put(ZEEBE_USER_TASK_FORM, userTaskForm);
                        break;
                    case ZEEBE_FORM_DEFINITION,
                         ZEEBE_TASK_DEFINITION,
                         ZEEBE_CALLED_ELEMENT,
                         ZEEBE_CALLED_DECISION,
                         ZEEBE_SUBSCRIPTION,
                         ZEEBE_SCRIPT,
                         ZEEBE_VERSION_TAG:
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

    private static List<Map<String, Object>> parseZeebeChildElements(Element element, String elementName) {

        List<Map<String, Object>> parsedElements = new ArrayList<>();
        NodeList nodeList = element.getElementsByTagNameNS(ZEEBE_NS, elementName);
        log.debug("Parsing element {} with {} children", elementName, nodeList.getLength());
        for (int node = 0; node < nodeList.getLength(); node++) {
            log.debug("Parsing child element {}", nodeList.item(node).getNodeName());
            parsedElements.add(Map.of(elementName, parseZeebeElementAttributes((Element) nodeList.item(node))));
        }
        return parsedElements;
    }

    private static Map<String, String> parseZeebeElementAttributes(Element element) {
        Map<String, String> parsedAttributes = new HashMap<>();
        NamedNodeMap attributes = element.getAttributes();
        log.debug("Parsing {} attributes", attributes.getLength());
        for (int attribute = 0; attribute < attributes.getLength(); attribute++) {
            if (attributes.item(attribute).getNamespaceURI() == null) {
                log.debug("Parsing attribute {} - {}", attributes.item(attribute).getLocalName(), attributes.item(attribute).getNodeValue());
                parsedAttributes.put(attributes.item(attribute).getLocalName(), attributes.item(attribute).getNodeValue());
            }
        }
        return parsedAttributes;
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
