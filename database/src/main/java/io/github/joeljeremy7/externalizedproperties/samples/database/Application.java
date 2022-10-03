package io.github.joeljeremy7.externalizedproperties.samples.database;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.resolvers.database.DatabaseResolver;
import io.github.joeljeremy7.externalizedproperties.resolvers.database.JdbcConnectionProvider;
import io.github.joeljeremy7.externalizedproperties.resolvers.database.queryexecutors.SimpleNameValueQueryExecutor;
import java.sql.SQLException;
import java.util.List;

public class Application {
  // RUNSCRIPT in connection string is to initialize tables.
  private static final String CONNECTION_STRING =
      "jdbc:h2:mem:./testdb;INIT=RUNSCRIPT FROM 'classpath:initialize_tables.sql'";
  private static final JdbcConnectionProvider CONNECTION_PROVIDER =
      new JdbcConnectionProvider(CONNECTION_STRING);

  public static void main(String[] args) throws SQLException {
    ExternalizedProperties externalizedProperties = buildExternalizedProperties();

    ApplicationProperties applicationProperties =
        externalizedProperties.initialize(ApplicationProperties.class);

    /** Properties from externalized_properties table */
    List<Integer> listProperty = applicationProperties.listProperty();
    System.out.println("list.property from default table: " + listProperty);

    int intProperty = applicationProperties.intProperty();
    System.out.println("int.property from default table: " + intProperty);

    String property = applicationProperties.property();
    System.out.println("property: " + property);

    /** Properties from custom_configurations table */
    List<Integer> listPropertyFromCustomTable = applicationProperties.listPropertyFromCustomTable();
    System.out.println("list.property from custom table: " + listPropertyFromCustomTable);

    int intPropertyFromCustomTable = applicationProperties.intPropertyFromCustomTable();
    System.out.println("int.property from custom table: " + intPropertyFromCustomTable);

    String propertyFromCustomTable = applicationProperties.propertyFromCustomTable();
    System.out.println("property from custom table: " + propertyFromCustomTable);
  }

  private static ExternalizedProperties buildExternalizedProperties() {
    return ExternalizedProperties.builder()
        .defaults()
        .resolvers(new DatabaseResolver(CONNECTION_PROVIDER))
        .resolvers(
            new DatabaseResolver(
                CONNECTION_PROVIDER,
                new SimpleNameValueQueryExecutor(
                    "customschema", "custom_configurations", "config_name", "config_value")))
        .build();
  }
}
