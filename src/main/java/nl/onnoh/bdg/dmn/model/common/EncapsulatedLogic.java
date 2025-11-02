package nl.onnoh.bdg.dmn.model.common;

import lombok.Data;

import java.util.List;

@Data
public class EncapsulatedLogic {
    String id;
    List<FormalParameter> formalParameters;
    LiteralExpression literalExpression;
}
