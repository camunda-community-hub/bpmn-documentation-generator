package nl.onnoh.bdg.bpmn.model.subprocess;

import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;


public class CallActivity extends FlowElement {
    public CallActivity(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
