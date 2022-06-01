CREATE TABLE externalized_properties (
    property_name VARCHAR(255),
    property_value VARCHAR(255),
    description VARCHAR(255)  
);

CREATE SCHEMA customschema;

CREATE TABLE customschema.custom_configurations (
    config_name VARCHAR(255),
    config_value VARCHAR(255),
    description VARCHAR(255)  
);

-- Populate the externalized_properties table

INSERT INTO externalized_properties VALUES(
    'property',
    'string-property',
    'Test String property description'
);
INSERT INTO externalized_properties VALUES(
    'int.property',
    '7',
    'Test int property description'
);
INSERT INTO externalized_properties VALUES(
    'list.property',
    '1,2,3',
    'Test List property description'
);

-- Populate custom_configurations table

INSERT INTO customschema.custom_configurations VALUES(
    'property.custom.table',
    'string-property',
    'Test String property description'
);
INSERT INTO customschema.custom_configurations VALUES(
    'int.property.custom.table',
    '3',
    'Test int property description'
);
INSERT INTO customschema.custom_configurations VALUES(
    'list.property.custom.table',
    '3,2,1',
    'Test List property description'
);