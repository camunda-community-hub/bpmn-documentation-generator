package nl.onnoh.bdg.dmn.parser;

import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.dmn.ObjectFactory;
import nl.onnoh.bdg.dmn.TDecisionTable;
import nl.onnoh.bdg.dmn.TLiteralExpression;
import nl.onnoh.bdg.dmn.model.common.LiteralExpression;
import nl.onnoh.bdg.dmn.model.decision.DecisionTable;

import static nl.onnoh.bdg.dmn.parser.DecisionTableParser.parseDecisionTableInputs;
import static nl.onnoh.bdg.dmn.parser.DecisionTableParser.parseDecisionTableOutputs;
import static nl.onnoh.bdg.dmn.parser.DecisionTableParser.parseDecistionTableRules;

@Slf4j
public class DecisionParser {

    public static DecisionTable parseDecisionTable(TDecisionTable value) {
        ObjectFactory objectFactory = new ObjectFactory();
        TDecisionTable decisionTable = objectFactory.createDecisionTable(value).getValue();
        log.debug("Decision Table: {}", decisionTable.getId());
        DecisionTable parsedDecisionTable = new DecisionTable();
        parsedDecisionTable.setId(decisionTable.getId());
        parsedDecisionTable.setHitPolicy(decisionTable.getHitPolicy().value());
        parsedDecisionTable.setInputs(parseDecisionTableInputs(decisionTable.getInputs()));
        parsedDecisionTable.setOutputs(parseDecisionTableOutputs(decisionTable.getOutputs()));
        parsedDecisionTable.setRules(parseDecistionTableRules(decisionTable.getRules()));

        return parsedDecisionTable;
    }

    public static LiteralExpression parseLiteralExpression(TLiteralExpression value) {
        ObjectFactory objectFactory = new ObjectFactory();
        TLiteralExpression literalExpression = objectFactory.createLiteralExpression(value).getValue();
        log.debug("Literal Expression: {}", literalExpression.getId());
        LiteralExpression parsedLiteralExpression = new LiteralExpression();
        parsedLiteralExpression.setId(literalExpression.getId());
        parsedLiteralExpression.setText(literalExpression.getText());
        parsedLiteralExpression.setExpressionLanguage(literalExpression.getExpressionLanguage());
        return parsedLiteralExpression;
    }

}
