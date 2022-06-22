package io.github.joeljeremy7.externalizedproperties.samples.fileformats;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperty;

public interface ApplicationProperties {
    @ExternalizedProperty("properties.property")
    public int propertiesIntProperty();

    @ExternalizedProperty("json.property")
    public int jsonIntProperty();

    @ExternalizedProperty("yaml.property")
    public int yamlIntProperty();

    @ExternalizedProperty("xml.property")
    public int xmlIntProperty();

    @ExternalizedProperty("hocon.property")
    public int hoconIntProperty();
}
