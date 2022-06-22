package io.github.joeljeremy7.externalizedproperties.samples.fileformats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.ResourceResolver.ResourceReader;

import java.io.IOException;
import java.util.Map;

public class XmlReader implements ResourceReader {
    private final ObjectMapper jsonMapper = new ObjectMapper(new XmlFactory());

    @Override
    public Map<String, Object> read(String resourceContents) throws IOException {
        return jsonMapper.readValue(
            resourceContents, 
            new TypeReference<Map<String, Object>>(){} 
        );
    }
}
