package nl.onnoh.bdg.bpmn.model.event;

import lombok.Getter;
import lombok.Setter;
import nl.onnoh.bdg.bpmn.TFlowElement;

@Getter
@Setter
public class EndEvent extends Event {

    public EndEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
