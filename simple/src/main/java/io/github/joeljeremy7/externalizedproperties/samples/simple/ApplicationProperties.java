package io.github.joeljeremy7.externalizedproperties.samples.simple;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperty;
import java.util.List;

public interface ApplicationProperties {
  @ExternalizedProperty("java.version")
  public String javaVersion();

  @ExternalizedProperty("path")
  public String pathEnv();

  @ExternalizedProperty("list.property")
  public List<Integer> listProperty();

  @ExternalizedProperty("int.property")
  public int intProperty();

  @ExternalizedProperty("property")
  public String property();
}
