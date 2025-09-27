package nl.onnoh.bdg.dmn.model.decision;

import java.util.List;

public class DecisionTable {
    String id;
    String hitPolicy;
    List<Input> inputs;
    List<Output> outputs;
    List<Rule> rules;
}
