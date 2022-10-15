package io.github.joeljeremy.externalizedproperties.samples.fileformats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joeljeremy.externalizedproperties.core.resolvers.ResourceResolver.ResourceReader;
import java.io.IOException;
import java.util.Map;

public class JsonReader implements ResourceReader {
  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Override
  public Map<String, Object> read(String resourceContents) throws IOException {
    return jsonMapper.readValue(resourceContents, new TypeReference<Map<String, Object>>() {});
  }
}
