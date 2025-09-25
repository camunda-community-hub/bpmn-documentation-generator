package nl.onnoh.bdg.dmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.dmn.ObjectFactory;
import nl.onnoh.bdg.dmn.TDRGElement;
import nl.onnoh.bdg.dmn.TDecision;

@Slf4j
public class DecisionParser {
    public static String parseDecisionTable(TDRGElement value) {
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDecision> decision = objectFactory.createDecision((TDecision) value);
        log.debug("Decision Table: {}", decision.getValue().getId());
        return decision.getValue().getId();
    }
}
