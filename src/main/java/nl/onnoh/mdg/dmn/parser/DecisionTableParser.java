package nl.onnoh.mdg.dmn.parser;

import nl.onnoh.mdg.dmn.TDecisionRule;
import nl.onnoh.mdg.dmn.TInputClause;
import nl.onnoh.mdg.dmn.TLiteralExpression;
import nl.onnoh.mdg.dmn.TOutputClause;
import nl.onnoh.mdg.dmn.TUnaryTests;
import nl.onnoh.mdg.dmn.model.decision.Input;
import nl.onnoh.mdg.dmn.model.decision.InputEntry;
import nl.onnoh.mdg.dmn.model.decision.InputExpression;
import nl.onnoh.mdg.dmn.model.decision.Output;
import nl.onnoh.mdg.dmn.model.decision.OutputEntry;
import nl.onnoh.mdg.dmn.model.decision.Rule;

import java.util.ArrayList;
import java.util.List;

public class DecisionTableParser {

    public static List<Input> parseDecisionTableInputs(List<TInputClause> inputClauses) {
        List<Input> parsedInputs = new ArrayList<>();
        inputClauses.forEach(inputClause -> {
            Input parsedInput = new Input();
            parsedInput.setId(inputClause.getId());
            parsedInput.setLabel(inputClause.getLabel());
            InputExpression inputExpression = new InputExpression();
            inputExpression.setId(inputClause.getInputExpression().getId());
            inputExpression.setText(inputClause.getInputExpression().getText());
            inputExpression.setTypeRef(inputClause.getInputExpression().getTypeRef());
            parsedInput.setInputExpression(inputExpression);
            parsedInputs.add(parsedInput);
        });
        return parsedInputs;
    }

    public static List<Output> parseDecisionTableOutputs(List<TOutputClause> outputClauses) {
        List<Output> parsedOutputs = new ArrayList<>();
        outputClauses.forEach(outputClause -> {
            Output parsedOutput = new Output();
            parsedOutput.setId(outputClause.getId());
            parsedOutput.setName(outputClause.getName());
            parsedOutput.setLabel(outputClause.getLabel());
            parsedOutput.setTypeRef(outputClause.getTypeRef());
            parsedOutputs.add(parsedOutput);
        });

        return parsedOutputs;
    }

    public static List<Rule> parseDecistionTableRules(List<TDecisionRule> decisionRules) {
        List<Rule> parsedRules = new ArrayList<>();
        decisionRules.forEach(decisionRule -> {
            Rule parsedRule = new Rule();
            parsedRule.setId(decisionRule.getId());
            parsedRule.setDescription(decisionRule.getDescription());
            parsedRule.setInputEntries(parseInputEntries(decisionRule.getInputEntries()));
            parsedRule.setOutputEntries(parseOutputEntries(decisionRule.getOutputEntries()));
            parsedRules.add(parsedRule);
        });

        return parsedRules;
    }

    private static List<InputEntry> parseInputEntries(List<TUnaryTests> inputEntries) {
        List<InputEntry> parsedInputEntries = new ArrayList<>();
        inputEntries.forEach(inputEntry -> {
            InputEntry parsedInputEntry = new InputEntry();
            parsedInputEntry.setId(inputEntry.getId());
            parsedInputEntry.setText(inputEntry.getText());
            parsedInputEntries.add(parsedInputEntry);
        });

        return parsedInputEntries;
    }

    private static List<OutputEntry> parseOutputEntries(List<TLiteralExpression> outputEntries) {
        List<OutputEntry> parsedOutputEntries = new ArrayList<>();
        outputEntries.forEach(outputEntry -> {
            OutputEntry parsedOutputEntry = new OutputEntry();
            parsedOutputEntry.setId(outputEntry.getId());
            parsedOutputEntry.setText(outputEntry.getText());
            parsedOutputEntries.add(parsedOutputEntry);
        });

        return parsedOutputEntries;
    }

}
