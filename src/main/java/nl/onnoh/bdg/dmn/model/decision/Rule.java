package nl.onnoh.bdg.dmn.model.decision;

import lombok.Data;

import java.util.List;

@Data
public class Rule {
    String id;
    String name;
    String description;
    List<InputEntry> inputEntries;
    List<OutputEntry> outputEntries;
}
