package io.github.jeyjeyemem.externalizedproperties.samples.springboot.configs;

import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedProperties;
import io.github.jeyjeyemem.externalizedproperties.core.ExternalizedPropertiesBuilder;
import io.github.jeyjeyemem.externalizedproperties.core.conversion.handlers.ListConversionHandler;
import io.github.jeyjeyemem.externalizedproperties.core.conversion.handlers.PrimitiveConversionHandler;
import io.github.jeyjeyemem.externalizedproperties.core.processing.Base64Decode;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.EnvironmentVariableResolver;
import io.github.jeyjeyemem.externalizedproperties.core.resolvers.SystemPropertyResolver;
import io.github.jeyjeyemem.externalizedproperties.samples.springboot.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ExternalizedProperties externalizedProperties() {
        return ExternalizedPropertiesBuilder.newBuilder()
            .resolvers(
                new SystemPropertyResolver(),
                new EnvironmentVariableResolver()
            )
            .conversionHandlers(
                new PrimitiveConversionHandler(),
                new ListConversionHandler()
            )
            .processors(
                new Base64Decode()
            )
            .build();
    }

    @Bean
    public ApplicationProperties applicationProperties(
            ExternalizedProperties externalizedProperties
    ) {
        return externalizedProperties.proxy(ApplicationProperties.class);
    }
}
