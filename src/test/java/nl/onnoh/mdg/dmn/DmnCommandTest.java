package nl.onnoh.mdg.dmn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DmnCommandTest {

    @Test
    void test_if_dmn_command_is_not_null() {
        DmnCommand dmnCommand = new DmnCommand();
        assertNotNull(dmnCommand, "DmnCommand instance should not be null");
    }
}
