package io.github.joeljeremy.externalizedproperties.samples.git;

import io.github.joeljeremy.externalizedproperties.core.ExternalizedProperty;

public interface ApplicationProperties {
  @ExternalizedProperty("property")
  public String property();
}
