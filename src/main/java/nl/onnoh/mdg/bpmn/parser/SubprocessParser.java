package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TAdHocSubProcess;
import nl.onnoh.mdg.bpmn.TCallActivity;
import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.TSubProcess;
import nl.onnoh.mdg.bpmn.model.subprocess.AdHocSubProcess;
import nl.onnoh.mdg.bpmn.model.subprocess.CallActivity;
import nl.onnoh.mdg.bpmn.model.subprocess.SubProcess;

@Slf4j
public class SubprocessParser {
    static AdHocSubProcess parseAdHocSubProcess(String processId, String flowType, TFlowElement flowElement) {
        AdHocSubProcess parsedAdHocSubProcess = new AdHocSubProcess(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TAdHocSubProcess> adHocSubProcess = objectFactory.createAdHocSubProcess((TAdHocSubProcess) flowElement);
        log.debug("Ad Hoc Sub Process: {}", adHocSubProcess.getValue().getName());
        return parsedAdHocSubProcess;
    }

    static SubProcess parseSubProcess(String processId, String flowType, TFlowElement flowElement) {
        SubProcess parsedSubProcess = new SubProcess(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TSubProcess> subProcess = objectFactory.createSubProcess((TSubProcess) flowElement);
        log.debug("Sub Process: {}", subProcess.getValue().getName());
        return parsedSubProcess;
    }

    static CallActivity parseCallActivity(String processId, String flowType, TFlowElement flowElement) {
        CallActivity parsedCallActivity = new CallActivity(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TCallActivity> callActivity = objectFactory.createCallActivity((TCallActivity) flowElement);
        log.debug("Call Activity: {}", callActivity.getValue().getName());
        return parsedCallActivity;
    }

}
