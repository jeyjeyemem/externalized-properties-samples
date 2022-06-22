package io.github.joeljeremy7.externalizedproperties.samples.fileformats;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.ResourceResolver;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ExternalizedProperties externalizedProperties = buildExternalizedProperties();

        ApplicationProperties applicationProperties = 
            externalizedProperties.initialize(ApplicationProperties.class);

        System.out.println("properties.int: " + applicationProperties.propertiesIntProperty());

        System.out.println("json.int: " + applicationProperties.jsonIntProperty());

        System.out.println("yaml.int: " + applicationProperties.yamlIntProperty());

        System.out.println("xml.int: " + applicationProperties.xmlIntProperty());

        System.out.println("hocon.int: " + applicationProperties.hoconIntProperty());
    }

    private static ExternalizedProperties buildExternalizedProperties() 
            throws IOException {
        return ExternalizedProperties.builder()
            .defaults()
            .resolvers(
                propertiesFile(),
                jsonFile(),
                yamlFile(),
                xmlFile(),
                hoconFile()
            )
            .build();
    }

    private static ResourceResolver propertiesFile() throws IOException {
        return ResourceResolver.fromUrl(
            Application.class.getResource("/app.properties")
        );
    }

    private static ResourceResolver jsonFile() throws IOException {
        return ResourceResolver.fromUrl(
            Application.class.getResource("/app.json"),
            new JsonReader()
        );
    }

    private static ResourceResolver yamlFile() throws IOException {
        return ResourceResolver.fromUrl(
            Application.class.getResource("/app.yaml"),
            new YamlReader()
        );
    }

    private static ResourceResolver xmlFile() throws IOException {
        return ResourceResolver.fromUrl(
            Application.class.getResource("/app.xml"),
            new XmlReader()
        );
    }

    private static ResourceResolver hoconFile() throws IOException {
        return ResourceResolver.fromUrl(
            Application.class.getResource("/app.conf"),
            new HoconReader()
        );
    }
}
