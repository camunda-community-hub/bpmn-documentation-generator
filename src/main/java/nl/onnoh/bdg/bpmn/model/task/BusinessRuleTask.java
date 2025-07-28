package nl.onnoh.bdg.bpmn.model.task;

import nl.onnoh.bdg.bpmn.TFlowElement;


public class BusinessRuleTask extends Task {
    public BusinessRuleTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
