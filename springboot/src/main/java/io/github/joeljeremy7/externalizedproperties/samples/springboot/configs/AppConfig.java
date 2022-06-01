package io.github.joeljeremy7.externalizedproperties.samples.springboot.configs;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.samples.springboot.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {
    @Bean
    ExternalizedProperties externalizedProperties(Environment env) {
        return ExternalizedProperties.builder()
            .defaults()
            .resolvers(new SpringEnvironmentAdapterResolver(env))
            .build();
    }

    @Bean
    ApplicationProperties applicationProperties(
            ExternalizedProperties externalizedProperties
    ) {
        return externalizedProperties.initialize(
            ApplicationProperties.class
        );
    }
}
