package nl.onnoh.bdg.bpmn.model.subprocess;

import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.model.common.FlowElement;


public class SubProcess extends FlowElement {

    boolean collapsed = false;

    public SubProcess(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
