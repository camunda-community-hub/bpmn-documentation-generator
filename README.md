# BPMN Documentation Generator

Generate human-friendly documentation (HTML or Markdown) from a BPMN 2.0/DMN 1.3 diagram file.

This project parses a BPMN file (including Camunda/Zeebe extensions) and produces a structured document with:
- Overview information (definitions id, exporter, execution platform versions)
- Collaboration details and participants
- Processes, lanes, tasks, gateways, and events
- Global artifacts (messages, signals, escalations, errors, categories)

The output is rendered via FreeMarker templates and written next to your input BPMN file. If an SVG with the same base name exists, the document will embed it for visual context.

XML schema definitions for both BPMN and DMN are maintained by the Objects Management Group Standards Development Organization (OMG-SDO for short) and can be found here : https://www.omg.org/spec/ 

---

## Features
- CLI tool powered by picocli (single command: generate)
- Output formats: HTML (default) and Markdown
- Optional behavior flags:
  - Suppress empty sections to keep docs concise
  - Open all sections by default in HTML (details/summary blocks)
- Camunda/Zeebe extension awareness (e.g., execution platform metadata, IO mappings, headers, properties, versionTag)
- Template-based rendering (easy to customize)

## Requirements
- Java 23 (JDK) installed and available on your PATH
- Maven 3.9+

Note: The POM targets Java 23. If you must use an older JDK, you can lower the java.version and compiler release in `pom.xml` accordingly, but this project is configured and tested for 23.

## Build
```
mvn -q clean package
```
This will also run JAXB code generation for the BPMN/XSD schema bindings during the build.

## Run
You can run the CLI using the Maven Exec plugin without changing the POM by specifying full coordinates:

```
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=nl.onnoh.bdg.GenerateDocumentation \
  -Dexec.args="<path/to/your.bpmn> [options]"
```

Alternatively, if you prefer running the compiled classes directly, ensure dependencies are on the classpath (e.g., via a fat jar or your IDE). The canonical entrypoint is `nl.onnoh.bdg.GenerateDocumentation`.

### Command
```
Usage: generate [-h?] [-o] [-of=<outputFormat>] [-s] <bpmnFile>
Generate documentation for given BPMN file.
      <bpmnFile>              The BPMN file to document.
  -h, -?, --help, -help       Display this help and exit
  -o, --open-sections         Open all sections (default: false)
  -of, --output-format=<fmt>  The document format (default: html)
  -s, --suppress-empty-sections
                              Suppress empty sections (default: false)
```

### Output
- Output file is written in the same directory as the input, with extension based on the chosen output format, e.g.:
  - `diagram.bpmn` -> `diagram.html`
  - `diagram.bpmn` -> `diagram.markdown`
- If an SVG is present with the same base name (e.g., `diagram.svg` next to `diagram.bpmn`), it will be referenced in the generated doc.

## Examples
Assuming you want to document the sample diagram included in this repo:

```
# HTML (default)
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=nl.onnoh.bdg.GenerateDocumentation \
  -Dexec.args="src/main/resources/bpmn/all_elements.bpmn"

# Markdown
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=nl.onnoh.bdg.GenerateDocumentation \
  -Dexec.args="src/main/resources/bpmn/all_elements.bpmn --output-format markdown"

# Suppress empty sections and open sections (HTML only effect)
mvn -q org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=nl.onnoh.bdg.GenerateDocumentation \
  -Dexec.args="src/main/resources/bpmn/all_elements.bpmn -s -o"
```

After running, check next to your BPMN file for the new `.html` or `.markdown` file.

## How it works
- JAXB unmarshals the BPMN XML into generated classes (based on the XSDs in `src/main/resources/xsd`).
- The tool walks Definitions root elements to collect:
  - Processes, Collaboration, Messages, Signals, Escalations, Categories, Errors
- A BpmnDocumentation data object holds everything to be rendered.
- FreeMarker templates under `src/main/resources/templates/{html|markdown}` produce the final document.
  - Main templates: `bpmn-documentation.ftl`
  - Utility macros: `bpmn-template-util.ftl`
  - Partial templates: processes, tasks, events, gateways, lanes, globals, collaboration, etc.

## Customizing templates
You can tailor the output by editing the FreeMarker templates:
- HTML templates: `src/main/resources/templates/html/`
- Markdown templates: `src/main/resources/templates/markdown/`
- Shared macros: `src/main/resources/templates/bpmn-template-util.ftl`
- Stylesheet used by HTML template: `src/main/resources/templates/style/markdown.css`

When you rebuild and re-run, your changes will reflect in the generated docs.

## Tips
- Export an SVG image from your BPMN modeler and save it beside the BPMN file (same base name). The generated docs will embed/reference it.
- Alternatively, generate an SVG/PNG from BPMN programmatically using bpmn-io's bpmn-to-image: https://github.com/bpmn-io/bpmn-to-image/blob/main/README.md
- Use `--suppress-empty-sections` to keep the output lean for sparse diagrams.
- Use `--open-sections` to expand all collapsible sections in the HTML output.
- Author rich, marked-up documentation directly in your BPMN using the Camunda Modeler plugin "Rich Documentation Editor": https://github.com/OnnoH/camunda-modeler-plugin-rich-documentation-editor — the formatted documentation will be rendered by this generator.

## Troubleshooting
- File not found / not readable: ensure the path to the BPMN file is correct and accessible.
- Unknown elements warnings: the log may show warnings for elements/extensions not handled by the current parser; output will still be generated.
- Template not found: verify output format matches a folder under `src/main/resources/templates` (html or markdown).
- Java version errors: ensure you are using JDK 23, or adjust `pom.xml` to match your environment if needed.

## Development
- Open the project in your IDE with Maven support.
- Generated JAXB sources are placed under `target/generated-sources/xjc`.
- Main class: `nl.onnoh.bdg.GenerateDocumentation`.
- Logging: SLF4J Simple; set `-Dorg.slf4j.simpleLogger.defaultLogLevel=debug` to see verbose logs when running.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements
- BPMN 2.0 XML schemas
- DMN 1.3 XML schemas
- FreeMarker template engine
- Picocli CLI library
- JAXB and SLF4J

