package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class SendTask extends Task {
    public SendTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
