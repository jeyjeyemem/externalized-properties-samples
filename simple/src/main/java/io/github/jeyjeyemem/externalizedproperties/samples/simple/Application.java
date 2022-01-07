package io.github.jeyjeyemem.externalizedproperties.samples.simple;

import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedProperties;
import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedPropertiesBuilder;
import io.github.jeyjeyemem.externalizedproperties.core.conversion.handlers.ListConversionHandler;
import io.github.jeyjeyemem.externalizedproperties.core.conversion.handlers.PrimitiveConversionHandler;
import io.github.jeyjeyemem.externalizedproperties.core.processing.Base64Decode;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.EnvironmentVariableResolver;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.MapResolver;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.PropertiesResolver;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.SystemPropertyResolver;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Application {
    public static void main(String[] args) throws IOException {
        ExternalizedProperties externalizedProperties = buildExternalizedProperties();

        ApplicationProperties applicationProperties = 
            externalizedProperties.proxy(ApplicationProperties.class);

        String javaVersion = applicationProperties.javaVersion();
        System.out.println("Java Version: " + javaVersion);

        String pathEnv = applicationProperties.pathEnv();
        System.out.println("PATH Environment Variable: " + pathEnv);

        List<Integer> listProperty = applicationProperties.listProperty();
        System.out.println("list.property: " + listProperty);

        int intProperty = applicationProperties.intProperty();
        System.out.println("int.property: " + intProperty);

        String base64Property = applicationProperties.base64EncodedString();
        System.out.println("base64.property decoded value: " + base64Property);
    }

    private static ExternalizedProperties buildExternalizedProperties() throws IOException {
        // For PropertiesResolver.
        Properties props = new Properties();
        props.load(
            Application.class.getClassLoader().getResourceAsStream("app.properties")
        );

        // For MapResolver.
        Map<String, String> map = Collections.singletonMap(
            "base64.property", 
            Base64.getEncoder().encodeToString("Hi! You have decoded me!".getBytes())
        );

        return ExternalizedPropertiesBuilder.newBuilder()
            .resolvers(
                new SystemPropertyResolver(),
                new EnvironmentVariableResolver(),
                new PropertiesResolver(props),
                new MapResolver(map)
            )
            .conversionHandlers(
                new PrimitiveConversionHandler(),
                new ListConversionHandler()
            )
            .processors(new Base64Decode())
            .build();
    }
}
