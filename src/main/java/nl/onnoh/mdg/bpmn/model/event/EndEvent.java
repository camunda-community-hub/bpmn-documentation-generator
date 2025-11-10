package nl.onnoh.mdg.bpmn.model.event;

import lombok.Getter;
import lombok.Setter;
import nl.onnoh.mdg.bpmn.TFlowElement;

@Getter
@Setter
public class EndEvent extends Event {

    public EndEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
