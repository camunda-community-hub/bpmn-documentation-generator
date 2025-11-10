package nl.onnoh.mdg.bpmn.model.event;

import lombok.Getter;
import lombok.Setter;
import nl.onnoh.mdg.bpmn.TFlowElement;

@Getter
@Setter
public class BoundaryEvent extends Event {

    private String attachedTo;
    private boolean isNonInterrupting;
    private boolean isParallelMultiple;

    public BoundaryEvent(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
