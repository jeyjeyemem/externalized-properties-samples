package io.github.joeljeremy.externalizedproperties.samples.profiles;

import io.github.joeljeremy.externalizedproperties.core.ExternalizedProperty;

public interface ApplicationProperties {
  @ExternalizedProperty("property")
  public String property();
}
