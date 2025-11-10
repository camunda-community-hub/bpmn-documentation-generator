package nl.onnoh.mdg.dmn.model.decision;

import lombok.Data;

@Data
public class Input {
    String id;
    String label;
    InputExpression inputExpression;
}
