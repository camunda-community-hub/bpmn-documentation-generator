package nl.onnoh.bdg.bpmn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BpmnCommandTest {

    @Test
    void test_if_bpmn_command_is_not_null() {
        BpmnCommand bpmnCommand = new BpmnCommand();
        assertNotNull(bpmnCommand, "BpmnCommand instance should not be null");
    }
}
