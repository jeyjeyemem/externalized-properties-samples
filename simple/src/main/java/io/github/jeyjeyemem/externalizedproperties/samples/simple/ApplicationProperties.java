package io.github.jeyjeyemem.externalizedproperties.samples.simple;

import io.github.jeyjeyemem.externalizedproperties.core.annotations.ExternalizedProperty;
import io.github.jeyjeyemem.externalizedproperties.core.annotations.ProcessorClasses;
import io.github.jeyjeyemem.externalizedproperties.core.processing.Base64Decode;

import java.util.List;

public interface ApplicationProperties {
    @ExternalizedProperty("java.version")
    public String javaVersion();

    @ExternalizedProperty("PATH")
    public String pathEnv();

    @ExternalizedProperty("list.property")
    public List<Integer> listProperty();

    @ExternalizedProperty("int.property")
    public int intProperty();

    @ExternalizedProperty("base64.property")
    @ProcessorClasses(Base64Decode.class)
    public String base64EncodedString();
}
