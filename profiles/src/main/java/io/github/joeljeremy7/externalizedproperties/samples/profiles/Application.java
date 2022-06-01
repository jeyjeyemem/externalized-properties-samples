package io.github.joeljeremy7.externalizedproperties.samples.profiles;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties.BuilderConfiguration;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.ResourceResolver;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ExternalizedProperties externalizedProperties = buildExternalizedProperties();

        ApplicationProperties applicationProperties = 
            externalizedProperties.initialize(ApplicationProperties.class);

        String property = applicationProperties.property();
        System.out.println("property: " + property);
    }

    /**
     * Build Externalized Properties with profile-specific configurations.
     * To change which profile gets loaded, set the active profile via 
     * 'externalizedproperties.profile' system property or
     * EXTERNALIZEDPROPERTIES_PROFILE envronment variable. Active profile may
     * also be set via any of the registered non-profile-specific resolvers. 
     * In this case, the profile can also be set via the app.properties file.
     */
    private static ExternalizedProperties buildExternalizedProperties() 
            throws IOException {
        return ExternalizedProperties.builder()
            .defaults()
            .onProfiles().apply(Application::appPropertiesResolver)
            .resolvers(ResourceResolver.fromUrl(
                Application.class.getResource("/app.properties")
            ))
            .build();
    }

    private static void appPropertiesResolver(
            String profile, 
            BuilderConfiguration builder
    ) {
        // Profile specific app properties.
        String resourceName = "/app-" + profile + ".properties";
        try {
            builder.resolvers(ResourceResolver.fromUrl(
                Application.class.getResource(resourceName)
            ));
        } catch (IOException e) {
            throw new IllegalStateException(
                "Failed to load " + resourceName, 
                e
            );
        }
    }
}
