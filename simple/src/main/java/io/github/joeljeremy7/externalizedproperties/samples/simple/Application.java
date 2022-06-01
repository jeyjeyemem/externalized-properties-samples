package io.github.joeljeremy7.externalizedproperties.samples.simple;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.MapResolver;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.ResourceResolver;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        ExternalizedProperties externalizedProperties = buildExternalizedProperties();

        ApplicationProperties applicationProperties = 
            externalizedProperties.initialize(ApplicationProperties.class);

        String javaVersion = applicationProperties.javaVersion();
        System.out.println("Java Version: " + javaVersion);

        String pathEnv = applicationProperties.pathEnv();
        System.out.println("PATH Environment Variable: " + pathEnv);

        List<Integer> listProperty = applicationProperties.listProperty();
        System.out.println("list.property: " + listProperty);

        int intProperty = applicationProperties.intProperty();
        System.out.println("int.property: " + intProperty);

        String property = applicationProperties.property();
        System.out.println("property: " + property);
    }

    private static ExternalizedProperties buildExternalizedProperties() 
            throws IOException {
        // For MapResolver.
        Map<String, String> map = Collections.singletonMap(
            "property", 
            "property.value.from.map"
        );

        return ExternalizedProperties.builder()
            .defaults()
            .resolvers(
                ResourceResolver.fromUrl(
                    Application.class.getResource("/app.properties")
                ),
                new MapResolver(map)
            )
            .build();
    }
}
