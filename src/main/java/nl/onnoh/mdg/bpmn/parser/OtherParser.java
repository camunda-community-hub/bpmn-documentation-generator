package nl.onnoh.mdg.bpmn.parser;

import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import nl.onnoh.mdg.bpmn.ObjectFactory;
import nl.onnoh.mdg.bpmn.TDataObject;
import nl.onnoh.mdg.bpmn.TDataObjectReference;
import nl.onnoh.mdg.bpmn.TDataStoreReference;
import nl.onnoh.mdg.bpmn.TFlowElement;
import nl.onnoh.mdg.bpmn.TTransaction;
import nl.onnoh.mdg.bpmn.model.other.DataObject;
import nl.onnoh.mdg.bpmn.model.other.DataObjectReference;
import nl.onnoh.mdg.bpmn.model.other.DataStoreReference;
import nl.onnoh.mdg.bpmn.model.other.Transaction;

@Slf4j
public class OtherParser {
    static Transaction parseTransaction(String processId, String flowType, TFlowElement flowElement) {
        Transaction parsedTransaction = new Transaction(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TTransaction> transaction = objectFactory.createTransaction((TTransaction) flowElement);
        log.debug("Parsing Transaction: {}", transaction.getValue().getName());

        return parsedTransaction;
    }

    static DataObject parseDataObject(String processId, String flowType, TFlowElement flowElement) {
        DataObject parsedDataObject = new DataObject(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDataObject> dataObject = objectFactory.createDataObject((TDataObject) flowElement);
        log.debug("Parsing Data Object: {}", dataObject.getValue().getName());
        return parsedDataObject;
    }

    static DataObjectReference parseDataObjectReference(String processId, String flowType, TFlowElement flowElement) {
        DataObjectReference parsedDataObjectReference = new DataObjectReference(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDataObjectReference> dataObjectReference = objectFactory.createDataObjectReference((TDataObjectReference) flowElement);
        log.debug("Parsing Data Object Reference: {}", dataObjectReference.getValue().getName());
        return parsedDataObjectReference;
    }

    static DataStoreReference parseDataStoreReference(String processId, String flowType, TFlowElement flowElement) {
        DataStoreReference parsedDataStoreReference = new DataStoreReference(processId, flowType, flowElement);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<TDataStoreReference> dataStoreReference = objectFactory.createDataStoreReference((TDataStoreReference) flowElement);
        log.debug("Parsing Data Store Reference: {}", dataStoreReference.getValue().getName());
        return parsedDataStoreReference;
    }
}
