package nl.onnoh.mdg.dmn.model.decision;

import lombok.Data;

import java.util.List;

@Data
public class DecisionTable {
    String id;
    String hitPolicy;
    List<Input> inputs;
    List<Output> outputs;
    List<Rule> rules;
}
