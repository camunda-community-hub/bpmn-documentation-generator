package nl.onnoh.mdg.bpmn.model.subprocess;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;


public class SubProcess extends FlowElement {

    boolean collapsed = false;

    public SubProcess(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
