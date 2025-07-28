package nl.onnoh.bdg.bpmn.model.event;

import lombok.Getter;
import lombok.Setter;
import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;

@Getter
@Setter
public abstract class Event extends FlowElement {

    private String eventType;

    public Event(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }

}
