package io.github.jeyjeyemem.externalizedproperties.samples.springboot;

import io.github.jeyjeyemem.externalizedproperties.core.annotations.ExternalizedProperty;

import java.util.Arrays;
import java.util.List;

public interface ApplicationProperties {
    // From system properties.
    @ExternalizedProperty("java.version")
    public String javaVersion();

    // From environment variables.
    @ExternalizedProperty("PATH")
    public String pathEnv();

    @ExternalizedProperty("greeting.names")
    public default List<String> greetingNames(String... defaultNames) {
        // Fallback to provided default names if greeting.names
        // property does not exist.
        return Arrays.asList(defaultNames);
    }
}
