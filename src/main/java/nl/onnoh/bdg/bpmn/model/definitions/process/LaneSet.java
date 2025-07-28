package nl.onnoh.bdg.bpmn.model.definitions.process;

import lombok.Data;

import java.util.List;


@Data
public class LaneSet {
    String id;
    String name;
    List<Lane> lanes;
}
