package nl.onnoh.bdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.bdg.bpmn.ObjectFactory;
import nl.onnoh.bdg.bpmn.TBusinessRuleTask;
import nl.onnoh.bdg.bpmn.TFlowElement;
import nl.onnoh.bdg.bpmn.TManualTask;
import nl.onnoh.bdg.bpmn.TReceiveTask;
import nl.onnoh.bdg.bpmn.TScriptTask;
import nl.onnoh.bdg.bpmn.TSendTask;
import nl.onnoh.bdg.bpmn.TServiceTask;
import nl.onnoh.bdg.bpmn.TTask;
import nl.onnoh.bdg.bpmn.TUserTask;
import nl.onnoh.bdg.bpmn.model.task.AnonymousTask;
import nl.onnoh.bdg.bpmn.model.task.BusinessRuleTask;
import nl.onnoh.bdg.bpmn.model.task.ManualTask;
import nl.onnoh.bdg.bpmn.model.task.ReceiveTask;
import nl.onnoh.bdg.bpmn.model.task.ScriptTask;
import nl.onnoh.bdg.bpmn.model.task.SendTask;
import nl.onnoh.bdg.bpmn.model.task.ServiceTask;
import nl.onnoh.bdg.bpmn.model.task.UserTask;

@Slf4j
public class TaskParser {

    static Object parseAnonymousTask(String processId, String flowType, TFlowElement flowElement) {
        AnonymousTask parsedAnonymousTask = new AnonymousTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TTask> task = objectFactory.createTask((TTask) flowElement);
        log.debug("Parsing Task: {}", task.getValue().getName());
        return parsedAnonymousTask;
    }

    static ServiceTask parseServiceTask(String processId, String flowType, TFlowElement flowElement) {
        ServiceTask parsedServiceTask = new ServiceTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TServiceTask> serviceTask = objectFactory.createServiceTask((TServiceTask) flowElement);
        log.debug("ServiceTask: {}", serviceTask.getValue().getName());
        return parsedServiceTask;
    }

    static SendTask parseSendTask(String processId, String flowType, TFlowElement flowElement) {
        SendTask parsedSendTask = new SendTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TSendTask> sendTask = objectFactory.createSendTask((TSendTask) flowElement);
        log.debug("SendTask: {}", sendTask.getValue().getName());
        return parsedSendTask;
    }

    static ReceiveTask parseReceiveTask(String processId, String flowType, TFlowElement flowElement) {
        ReceiveTask parsedReceiveTask = new ReceiveTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TReceiveTask> receiveTask = objectFactory.createReceiveTask((TReceiveTask) flowElement);
        log.debug("ReceiveTask: {}", receiveTask.getValue().getName());
        return parsedReceiveTask;
    }

    static UserTask parseUserTask(String processId, String flowType, TFlowElement flowElement) {
        UserTask parsedUserTask = new UserTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TUserTask> userTask = objectFactory.createUserTask((TUserTask) flowElement);
        log.debug("UserTask: {}", userTask.getValue().getName());
        return parsedUserTask;
    }

    static ManualTask parseManualTask(String processId, String flowType, TFlowElement flowElement) {
        ManualTask parsedManualTask = new ManualTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TManualTask> manualTask = objectFactory.createManualTask((TManualTask) flowElement);
        log.debug("ManualTask: {}", manualTask.getValue().getName());
        return parsedManualTask;
    }

    static ScriptTask parseScriptTask(String processId, String flowType, TFlowElement flowElement) {
        ScriptTask parsedScriptTask = new ScriptTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TScriptTask> scriptTask = objectFactory.createScriptTask((TScriptTask) flowElement);
        log.debug("Script Task: {}", scriptTask.getValue().getName());
        return parsedScriptTask;
    }

    static BusinessRuleTask parseBusinessRuleTask(String processId, String flowType, TFlowElement flowElement) {
        BusinessRuleTask parsedBusinessRuleTask = new BusinessRuleTask(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TBusinessRuleTask> businessTask = objectFactory.createBusinessRuleTask((TBusinessRuleTask) flowElement);
        log.debug("BusinessRuleTask: {}", businessTask.getValue().getName());
        return parsedBusinessRuleTask;
    }
}
