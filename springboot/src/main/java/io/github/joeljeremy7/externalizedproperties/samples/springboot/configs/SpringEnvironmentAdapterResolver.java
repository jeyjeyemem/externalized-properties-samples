package io.github.joeljeremy7.externalizedproperties.samples.springboot.configs;

import io.github.joeljeremy7.externalizedproperties.core.Resolver;
import io.github.joeljeremy7.externalizedproperties.core.proxy.ProxyMethod;
import org.springframework.core.env.Environment;

import java.util.Optional;

public class SpringEnvironmentAdapterResolver implements Resolver {

    private final Environment environment;

    public SpringEnvironmentAdapterResolver(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Optional<String> resolve(ProxyMethod proxyMethod, String propertyName) {
        return Optional.ofNullable(environment.getProperty(propertyName));
    }
}
