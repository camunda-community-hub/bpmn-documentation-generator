package nl.onnoh.mdg.bpmn.model.task;

import nl.onnoh.mdg.bpmn.TFlowElement;


public class BusinessRuleTask extends Task {
    public BusinessRuleTask(String processId, String flowType, TFlowElement flowElement) {
        super(processId, flowType, flowElement);
    }
}
