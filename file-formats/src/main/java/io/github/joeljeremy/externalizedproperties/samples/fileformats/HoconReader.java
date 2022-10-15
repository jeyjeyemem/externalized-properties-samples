package io.github.joeljeremy.externalizedproperties.samples.fileformats;

import com.typesafe.config.ConfigFactory;
import io.github.joeljeremy.externalizedproperties.core.resolvers.ResourceResolver.ResourceReader;
import java.io.IOException;
import java.util.Map;

public class HoconReader implements ResourceReader {
  @Override
  public Map<String, Object> read(String resourceContents) throws IOException {
    return ConfigFactory.parseString(resourceContents).root().unwrapped();
  }
}
