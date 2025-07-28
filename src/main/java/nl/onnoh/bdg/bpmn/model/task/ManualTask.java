package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;

public class ManualTask extends Task {
    public ManualTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
