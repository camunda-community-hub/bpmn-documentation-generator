package nl.onnoh.bdg.dmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.dmn.TDMNElement.ExtensionElements;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.onnoh.bdg.dmn.DmnModelZeebeConstants.ZEEBE_VERSION_TAG;

@Slf4j
public class CommonParser {

    public static Map<String, Object> parseExtensionElements(ExtensionElements extensionElements) {

        Map<String, Object> parsedExtensionElements = new HashMap<>();

        if (extensionElements != null) {
            List<Element> elements = extensionElements.getAnies();
            log.debug("Parsing {} anies", elements.size());
            for (Element element : elements) {
                log.debug("Parsing ani {} {}:{}", element.getLocalName(), element.getNamespaceURI(), element.getNodeName());
                switch (element.getLocalName()) {
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

}
