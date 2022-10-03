package io.github.joeljeremy7.externalizedproperties.samples.springboot.configs;

import io.github.joeljeremy7.externalizedproperties.core.InvocationContext;
import io.github.joeljeremy7.externalizedproperties.core.Resolver;
import java.util.Optional;
import org.springframework.core.env.Environment;

public class SpringEnvironmentResolverAdapter implements Resolver {

  private final Environment environment;

  public SpringEnvironmentResolverAdapter(Environment environment) {
    this.environment = environment;
  }

  @Override
  public Optional<String> resolve(InvocationContext context, String propertyName) {
    return Optional.ofNullable(environment.getProperty(propertyName));
  }
}
