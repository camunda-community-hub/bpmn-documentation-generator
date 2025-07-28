package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class SendTask extends Task {
    public SendTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
