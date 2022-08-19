package io.github.joeljeremy7.externalizedproperties.samples.git;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperty;

public interface ApplicationProperties {
    @ExternalizedProperty("property")
    public String property();
}
