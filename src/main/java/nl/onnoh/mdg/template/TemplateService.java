package nl.onnoh.mdg.template;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class TemplateService {
    public static final String TEMPLATES_FOLDER = "templates";
    public static final String TEMPLATE_SUFFIX = ".ftl";

    public static Configuration initTemplateEngine() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
        try {
            cfg.setDefaultEncoding("UTF-8");
            cfg.setDirectoryForTemplateLoading(new File("/"));
            cfg.setClassForTemplateLoading(TemplateService.class, "/");
            TemplateLoader templateLoader = new ClassTemplateLoader(TemplateService.class, "/");
            cfg.setTemplateLoader(templateLoader);
//            cfg.setObjectWrapper(new FlowElementWrapper(cfg.getIncompatibleImprovements()));
        } catch (IOException e) {
            System.err.println("Unable to initialise template engine. " + e.getMessage());
        }

        return cfg;
    }

    private static Template loadTemplate(Configuration cfg, File file) {
        Template template = null;
        try {
            template = cfg.getTemplate(file.getPath());
        } catch (IOException ex) {
            System.err.println("Unable to load template " + file.getPath() + ". " + ex.getMessage());
        }
        return template;
    }

    public static void processTemplate(Configuration configuration, String templateFile, String outputFile, Map<String, Object> model) {

        File file = new File(templateFile);

        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException ex) {
            System.err.println("Unable to create output file " + outputFile + ". " + ex.getMessage());
        }

        Template template = loadTemplate(configuration, file);
        try {
            template.process(model, writer);
        } catch (TemplateException | IOException ex) {
            System.err.println("Error processing template " + file.getPath() + ". " + ex.getMessage());
        }
    }

}
