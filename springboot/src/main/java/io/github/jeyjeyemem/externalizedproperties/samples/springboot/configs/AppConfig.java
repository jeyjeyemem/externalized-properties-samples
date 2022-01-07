package io.github.jeyjeyemem.externalizedproperties.samples.springboot.configs;

import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedProperties;
import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedPropertiesBuilder;
import io.github.jeyjeyemem.externalizedproperties.samples.springboot.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ExternalizedProperties externalizedProperties() {
        return ExternalizedPropertiesBuilder.newBuilder()
            .withDefaults()
            .build();
    }

    @Bean
    public ApplicationProperties applicationProperties(
            ExternalizedProperties externalizedProperties
    ) {
        return externalizedProperties.proxy(ApplicationProperties.class);
    }
}
