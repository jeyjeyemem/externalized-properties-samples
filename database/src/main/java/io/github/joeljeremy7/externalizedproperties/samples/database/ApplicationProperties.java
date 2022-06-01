package io.github.joeljeremy7.externalizedproperties.samples.database;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperty;

import java.util.List;

public interface ApplicationProperties {
    @ExternalizedProperty("list.property")
    public List<Integer> listProperty();

    @ExternalizedProperty("int.property")
    public int intProperty();

    @ExternalizedProperty("property")
    public String property();

    @ExternalizedProperty("list.property.custom.table")
    public List<Integer> listPropertyFromCustomTable();

    @ExternalizedProperty("int.property.custom.table")
    public int intPropertyFromCustomTable();

    @ExternalizedProperty("property.custom.table")
    public String propertyFromCustomTable();
}
