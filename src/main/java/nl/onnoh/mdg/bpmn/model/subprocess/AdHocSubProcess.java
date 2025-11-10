package nl.onnoh.mdg.bpmn.model.subprocess;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;


public class AdHocSubProcess extends FlowElement {

    boolean collapsed = false;

    public AdHocSubProcess(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
