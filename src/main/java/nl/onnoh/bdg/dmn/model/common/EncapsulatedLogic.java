package nl.onnoh.bdg.dmn.model.common;

import lombok.Data;

import java.util.List;

@Data
public class EncapsulatedLogic {
    List<FormalParameter> formalParameters;
    LiteralExpression literalExpression;
}
