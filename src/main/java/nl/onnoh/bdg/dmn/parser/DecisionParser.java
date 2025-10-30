package nl.onnoh.bdg.dmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.dmn.ObjectFactory;
import nl.onnoh.bdg.dmn.TDecisionTable;
import nl.onnoh.bdg.dmn.TLiteralExpression;
import nl.onnoh.bdg.dmn.model.decision.DecisionTable;
import nl.onnoh.bdg.dmn.model.decision.LiteralExpression;

@Slf4j
public class DecisionParser {

    public static DecisionTable parseDecisionTable(TDecisionTable value) {
        ObjectFactory objectFactory = new ObjectFactory();
        TDecisionTable decisionTable = objectFactory.createDecisionTable(value).getValue();
        log.debug("Decision Table: {}", decisionTable.getId());
        DecisionTable parsedDecisionTable = new DecisionTable();
        parsedDecisionTable.setId(decisionTable.getId());
//        parsedDecisionTable.setInputs();
//        parsedDecisionTable.setOutputs();
//        parsedDecisionTable.setHitPolicy();
//        parsedDecisionTable.setRules();
        return parsedDecisionTable;
    }

    static LiteralExpression parseLiteralExpression(TLiteralExpression value) {
        ObjectFactory objectFactory = new ObjectFactory();
        TLiteralExpression literalExpression = objectFactory.createLiteralExpression(value).getValue();
        log.debug("Literal Expression: {}", literalExpression.getId());
        LiteralExpression parsedLiteralExpression = new LiteralExpression();
        parsedLiteralExpression.setId(literalExpression.getId());
        parsedLiteralExpression.setText(literalExpression.getText());
        return parsedLiteralExpression;
    }
}
