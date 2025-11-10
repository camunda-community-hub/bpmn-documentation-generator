package nl.onnoh.mdg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateDocumentationTest {

    @Test
    void test_if_generate_documentation_is_not_null() {
        GenerateDocumentation generateDocumentation = new GenerateDocumentation();
        assertNotNull(generateDocumentation, "GenerateDocumentation instance should not be null");
    }
}