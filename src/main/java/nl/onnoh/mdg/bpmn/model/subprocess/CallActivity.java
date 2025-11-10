package nl.onnoh.mdg.bpmn.model.subprocess;

import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.model.common.FlowElement;


public class CallActivity extends FlowElement {
    public CallActivity(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
